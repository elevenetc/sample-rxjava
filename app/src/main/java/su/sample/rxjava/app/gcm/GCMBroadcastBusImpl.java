package su.sample.rxjava.app.gcm;

import rx.Observable;
import rx.subjects.PublishSubject;
import su.sample.rxjava.app.models.Item;
import su.sample.rxjava.app.utils.ThreadUtils;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public class GCMBroadcastBusImpl implements GCMBroadcastBus {

    private static GCMBroadcastBus inst;

    public static GCMBroadcastBus inst() {
        if (inst == null) inst = new GCMBroadcastBusImpl();
        return inst;
    }

    private PublishSubject<Item> publishSubject = PublishSubject.create();
    private static int id = 100;

    public GCMBroadcastBusImpl() {
        new Thread(() -> {
            while (true) {
                ThreadUtils.sleep(10000);
                publishSubject.onNext(new Item(id, "GCM"));
                id--;
            }
        }).start();
    }

    @Override
    public Observable<Item> getObservable() {
        return publishSubject;
    }
}
