package su.sample.rxjava.app.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eugene.levenetc on 05/08/16.
 */
public class Rx {
    private static final Observable.Transformer mainAndBackgroundTransformer = new Observable.Transformer<Object, Object>() {
        @Override
        public Observable<Object> call(Observable<Object> observable) {
            return observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> Observable.Transformer<T, T> productionScheduler() {
        return mainAndBackgroundTransformer;
    }

    public static <T> Observable.Transformer<T, T> testScheduler() {
        return observable -> observable;
    }
}