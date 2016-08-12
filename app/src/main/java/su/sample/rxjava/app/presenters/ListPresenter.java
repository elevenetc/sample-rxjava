package su.sample.rxjava.app.presenters;

import rx.Observable;
import rx.Subscription;
import su.sample.rxjava.app.annotations.Inject;
import su.sample.rxjava.app.database.Database;
import su.sample.rxjava.app.gcm.GCMBroadcastBus;
import su.sample.rxjava.app.rest.RestService;
import su.sample.rxjava.app.utils.RxSchedulers;
import su.sample.rxjava.app.views.ListView;

/**
 * Created by Eugene Levenetc on 04/08/2016.
 */
public class ListPresenter {

    @Inject
    public Database database;
    @Inject
    public RestService restService;
    @Inject
    public GCMBroadcastBus gcmBroadcastBus;

    private ListView view;

    private Subscription gcmSub;
    private Subscription dataSub;

    public void onViewCreated(ListView view) {
        this.view = view;
        view.showLoading();
        loadData();
        subscribeOnGCM();
    }

    public void onViewDestroyed() {
        gcmSub.unsubscribe();
        dataSub.unsubscribe();
    }

    private void subscribeOnGCM() {
        gcmSub = gcmBroadcastBus.getObservable()
                .doOnNext(database::saveItem)
                .compose(RxSchedulers.composer())
                .subscribe(view::addItem, view::showGCMError);
    }

    private void loadData() {
        dataSub = Observable
                .concat(database.getItems(), restService.getItems().doOnNext(database::saveItems))
                .takeFirst(items -> items.size() > 0)
                .compose(RxSchedulers.composer())
                .subscribe(view::showItems, view::showError);
    }
}
