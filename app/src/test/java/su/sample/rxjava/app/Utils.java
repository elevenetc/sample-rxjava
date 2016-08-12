package su.sample.rxjava.app;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import su.sample.rxjava.app.models.Item;

/**
 * Created by eugene.levenetc on 12/08/16.
 */
public class Utils {

    public static Observable<Item> item() {
        return Observable.just(new Item(0, "Test"));
    }

    public static List<Item> items() {
        List<Item> result = new ArrayList<>();
        result.add(new Item(0, "Test"));
        return result;
    }

    public static Observable<List<Item>> error() {
        return Observable.error(new RuntimeException());
    }
}
