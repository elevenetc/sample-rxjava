package su.sample.rxjava.app.database;

import java.util.List;

import rx.Observable;
import su.sample.rxjava.app.models.Item;

/**
 * Created by eugene.levenetc on 11/08/16.
 */
public interface Database {
    void saveItem(Item item);

    Observable<List<Item>> getItems();

    void saveItems(List<Item> items);
}