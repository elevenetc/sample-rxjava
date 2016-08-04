package su.sample.rxjava.app.presenters;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import su.sample.rxjava.app.gcm.GCMBroadcastBus;
import su.sample.rxjava.app.models.Item;
import su.sample.rxjava.app.rest.RestService;
import su.sample.rxjava.app.views.ListView;

/**
 * Created by Eugene Levenetc on 04/08/2016.
 */
public class ListPresenter {

	public RestService restService;
	public GCMBroadcastBus gcmBroadcastBus;

	private ListView view;

	public void onViewCreated(ListView view) {
		this.view = view;
		view.showLoading();
		loadData();
	}

	private void subscribeOn

	private void loadData() {
		restService.getItems()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handlerResponse, this::handleError);
	}

	private void handleError(Throwable throwable) {
		view.showError(throwable.toString());
	}

	private void handlerResponse(List<Item> result) {
		view.showData(result);
	}

	public void onViewDestroyed() {

	}
}
