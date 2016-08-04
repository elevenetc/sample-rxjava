package su.sample.rxjava.app.rest;

import java.util.List;

import rx.Observable;
import su.sample.rxjava.app.models.Item;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public interface RestService {
	Observable<List<Item>> getItems();
}
