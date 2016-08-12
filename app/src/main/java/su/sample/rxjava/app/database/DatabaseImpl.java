package su.sample.rxjava.app.database;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import su.sample.rxjava.app.log.Logger;
import su.sample.rxjava.app.models.Item;

/**
 * Created by eugene.levenetc on 11/08/16.
 */
public class DatabaseImpl implements Database {

    private static Database inst;

    public static Database inst() {
        if (inst == null) inst = new DatabaseImpl();
        return inst;
    }

    private List<Item> items = new ArrayList<>();

    @Override
    public void saveItem(Item item) {
        Logger.log("Database: saveItem");
        items.add(item);
    }

    @Override
    public Observable<List<Item>> getItems() {
        return Observable.create(new Observable.OnSubscribe<List<Item>>() {
            @Override
            public void call(Subscriber<? super List<Item>> subscriber) {
                if (!subscriber.isUnsubscribed()) {

                    if (!items.isEmpty()) Logger.log("Database items retrieved");

                    for (Item item : items) item.changeSource("Database");

                    subscriber.onNext(new ArrayList<>(items));
                    subscriber.onCompleted();

                }
            }
        });
    }

    @Override
    public void saveItems(List<Item> items) {
        Logger.log("Database: saveItems");
        for (Item item : items)
            this.items.add(item);
    }
}