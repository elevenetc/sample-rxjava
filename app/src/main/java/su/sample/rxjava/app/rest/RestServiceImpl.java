package su.sample.rxjava.app.rest;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import su.sample.rxjava.app.models.Item;
import su.sample.rxjava.app.utils.ThreadUtils;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public class RestServiceImpl implements RestService {
	@Override public Observable<List<Item>> getItems() {
		return Observable.create(new Observable.OnSubscribe<List<Item>>() {
			@Override public void call(Subscriber<? super List<Item>> subscriber) {
				ThreadUtils.sleep(1000);
				List<Item> result = new ArrayList<>();

				for (int i = 0; i < 40; i++) {
					Item item = new Item(String.valueOf(i), String.valueOf(i));
					result.add(item);
				}

				if (!subscriber.isUnsubscribed()) {
					subscriber.onNext(result);
					subscriber.onCompleted();
				}
			}
		});
	}
}
