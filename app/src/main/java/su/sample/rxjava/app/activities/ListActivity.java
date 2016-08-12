package su.sample.rxjava.app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import su.sample.rxjava.app.R;
import su.sample.rxjava.app.fragments.ListFragment;

public class ListActivity extends AppCompatActivity {

    public static AppCompatActivity ref;

    @Override
    protected void onResume() {
        super.onResume();
        ref = this;
    }

    @Override
    protected void onStop() {
        ref = null;
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root_container, ListFragment.create())
                    .commit();
        }
    }
}