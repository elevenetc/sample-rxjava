package su.sample.rxjava.app.log;

import android.widget.Toast;

import su.sample.rxjava.app.activities.ListActivity;

/**
 * Created by eugene.levenetc on 11/08/16.
 */
public class Logger {
    public static void log(String message) {
        if (ListActivity.ref != null) {
            ListActivity.ref.runOnUiThread(() -> Toast.makeText(ListActivity.ref, message, Toast.LENGTH_SHORT).show());
        }

    }
}
