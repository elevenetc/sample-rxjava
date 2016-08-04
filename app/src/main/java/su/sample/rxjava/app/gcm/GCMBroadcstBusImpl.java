package su.sample.rxjava.app.gcm;

import rx.Observable;
import rx.subjects.PublishSubject;
import su.sample.rxjava.app.models.Item;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public class GCMBroadcstBusImpl implements GCMBroadcastBus {

	private static GCMBroadcastBus inst;

	public static GCMBroadcastBus inst() {
		if (inst == null) inst = new GCMBroadcstBusImpl();
		return inst;
	}

	private PublishSubject<Item> publishSubject = PublishSubject.create();
	private static int id;

	public GCMBroadcstBusImpl() {
		new Thread(() -> {
			while (true) {
				id++;
				publishSubject.onNext(new Item(id, "gcm"));
			}
		}).start();
	}

	@Override public Observable<Item> getObservable() {
		return publishSubject;
	}
}
