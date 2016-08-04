package su.sample.rxjava.app.utils;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public class ThreadUtils {
	public static void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
