package su.sample.rxjava.app;

import org.junit.Before;
import org.junit.Test;

import su.sample.rxjava.app.database.Database;
import su.sample.rxjava.app.gcm.GCMBroadcastBus;
import su.sample.rxjava.app.presenters.ListPresenter;
import su.sample.rxjava.app.rest.RestService;
import su.sample.rxjava.app.utils.Rx;
import su.sample.rxjava.app.utils.RxSchedulers;
import su.sample.rxjava.app.views.ListView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rx.Observable.empty;
import static rx.Observable.just;
import static su.sample.rxjava.app.Utils.error;
import static su.sample.rxjava.app.Utils.item;
import static su.sample.rxjava.app.Utils.items;

/**
 * Created by eugene.levenetc on 05/08/16.
 */
public class ListPresenterTest {

    ListPresenter listPresenter;

    ListView view;

    Database database;
    RestService restService;
    GCMBroadcastBus gcmBroadcastBus;

    @Before
    public void before() {
        listPresenter = new ListPresenter();
        view = mock(ListView.class);
        database = mock(Database.class);
        restService = mock(RestService.class);
        gcmBroadcastBus = mock(GCMBroadcastBus.class);

        listPresenter.database = database;
        listPresenter.gcmBroadcastBus = gcmBroadcastBus;
        listPresenter.restService = restService;

        RxSchedulers.transformer = Rx.testScheduler();
    }

    @Test
    public void successRestLoading() {

        when(database.getItems()).thenReturn(empty());
        when(restService.getItems()).thenReturn(just(items()));
        when(gcmBroadcastBus.getObservable()).thenReturn(empty());

        listPresenter.onViewCreated(view);

        verify(view, times(1)).showItems(any());
    }

    @Test
    public void successDatabaseLoading() {

        when(database.getItems()).thenReturn(just(items()));
        when(restService.getItems()).thenReturn(empty());
        when(gcmBroadcastBus.getObservable()).thenReturn(empty());

        listPresenter.onViewCreated(view);

        verify(view, times(1)).showItems(any());
    }

    @Test
    public void failRestLoading() {

        when(database.getItems()).thenReturn(empty());
        when(restService.getItems()).thenReturn(error());
        when(gcmBroadcastBus.getObservable()).thenReturn(empty());

        listPresenter.onViewCreated(view);

        verify(view, times(1)).showError(any());
    }

    @Test
    public void failDatabaseLoading() {

        when(database.getItems()).thenReturn(error());
        when(restService.getItems()).thenReturn(empty());
        when(gcmBroadcastBus.getObservable()).thenReturn(empty());

        listPresenter.onViewCreated(view);

        verify(view, times(1)).showError(any());
    }

    @Test
    public void successGCM() {

        when(database.getItems()).thenReturn(empty());
        when(restService.getItems()).thenReturn(empty());
        when(gcmBroadcastBus.getObservable()).thenReturn(item());

        listPresenter.onViewCreated(view);

        verify(view, times(1)).addItem(any());
    }

}