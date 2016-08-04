package su.sample.rxjava.app;

import android.app.Application;

import rx.functions.Action1;
import rx.plugins.RxJavaHooks;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public class App extends Application {
	@Override public void onCreate() {
		super.onCreate();

		RxJavaHooks.setOnError(new Action1<Throwable>() {
			@Override public void call(Throwable throwable) {
				if (throwable == null) {

				}
			}
		});
	}
}
