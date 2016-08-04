package su.sample.rxjava.app.gcm;

import rx.Observable;
import su.sample.rxjava.app.models.Item;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public interface GCMBroadcastBus {
	Observable<Item> getObservable();
}
