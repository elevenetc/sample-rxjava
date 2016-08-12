package su.sample.rxjava.app.utils;

import rx.Observable;

/**
 * Created by eugene.levenetc on 05/08/16.
 */
public class RxSchedulers {

    public static Observable.Transformer transformer;

    public static <T> Observable.Transformer<T, T> composer() {
        return transformer;
    }
}
