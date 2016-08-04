package su.sample.rxjava.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import su.sample.rxjava.app.views.ListView;

/**
 * Created by Eugene Levenetc on 04/08/2016.
 */
public class ListFragment extends Fragment implements ListView {

	public static ListFragment create() {
		return new ListFragment();
	}

	@Override public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		RecyclerView result = new RecyclerView(getContext());
		return result;
	}
}
