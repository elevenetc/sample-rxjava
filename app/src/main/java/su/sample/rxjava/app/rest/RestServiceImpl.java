package su.sample.rxjava.app.rest;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import su.sample.rxjava.app.log.Logger;
import su.sample.rxjava.app.models.Item;
import su.sample.rxjava.app.utils.ThreadUtils;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public class RestServiceImpl implements RestService {

    private static RestServiceImpl inst;

    public static RestService inst() {
        if (inst == null) inst = new RestServiceImpl();
        return inst;
    }

    @Override
    public Observable<List<Item>> getItems() {
        return Observable.create(new Observable.OnSubscribe<List<Item>>() {
            @Override
            public void call(Subscriber<? super List<Item>> subscriber) {
                ThreadUtils.sleep(1000);
                List<Item> result = new ArrayList<>();

                for (int i = 0; i < 5; i++) {
                    Item item = new Item(i, "REST");
                    result.add(item);
                }

                if (!subscriber.isUnsubscribed()) {
                    Logger.log("Rest service call finished");
                    subscriber.onNext(result);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
