package su.sample.rxjava.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import su.sample.rxjava.app.R;
import su.sample.rxjava.app.adapters.ItemsListAdapter;
import su.sample.rxjava.app.gcm.GCMBroadcstBusImpl;
import su.sample.rxjava.app.models.Item;
import su.sample.rxjava.app.presenters.ListPresenter;
import su.sample.rxjava.app.rest.RestServiceImpl;
import su.sample.rxjava.app.views.ListView;

/**
 * Created by Eugene Levenetc on 04/08/2016.
 */
public class ListFragment extends Fragment implements ListView {

	private ItemsListAdapter adapter;

	public static ListFragment create() {
		return new ListFragment();
	}

	@BindView(R.id.recycler_view) RecyclerView recyclerView;
	@BindView(R.id.text_status) TextView textStatus;
	private ListPresenter presenter;

	@Override public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		presenter = new ListPresenter();
		presenter.restService = new RestServiceImpl();
		presenter.gcmBroadcastBus = GCMBroadcstBusImpl.inst();
	}

	@Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.view_list, container, false);
		ButterKnife.bind(this, result);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		return result;
	}

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		presenter.onViewCreated(this);
	}

	@Override public void onDestroyView() {
		presenter.onViewDestroyed();
		super.onDestroyView();
	}

	@Override public void addItem(Item item) {
		if (adapter == null) {
			ArrayList<Item> items = new ArrayList<>();
			items.add(item);
			initRecyclerView(items);
		} else {
			adapter.addItem(item);
			adapter.notifyDataSetChanged();
			recyclerView.scrollToPosition(adapter.getItemCount());
		}
	}

	@Override public void showLoading() {
		showStatus("Loading...");
	}

	@Override public void showError(String text) {
		showStatus("Error: " + text);
	}

	@Override public void showData(List<Item> items) {
		if (items.isEmpty()) showStatus("No data");
		else initRecyclerView(items);
	}

	private void initRecyclerView(List<Item> items) {

		textStatus.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);

		adapter = new ItemsListAdapter(items);
		recyclerView.setAdapter(adapter);
	}

	private void showStatus(String text) {
		textStatus.setText(text);
		textStatus.setVisibility(View.VISIBLE);
		recyclerView.setVisibility(View.GONE);
	}

}
