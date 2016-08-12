package su.sample.rxjava.app;

import android.app.Application;

import rx.functions.Action1;
import rx.plugins.RxJavaHooks;
import su.sample.rxjava.app.utils.Rx;
import su.sample.rxjava.app.utils.RxSchedulers;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RxSchedulers.transformer = Rx.productionScheduler();

        RxJavaHooks.setOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable == null) {

                }
            }
        });
    }
}
