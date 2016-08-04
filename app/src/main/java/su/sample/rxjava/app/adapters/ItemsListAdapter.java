package su.sample.rxjava.app.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import su.sample.rxjava.app.models.Item;

/**
 * Created by Eugene Levenetc on 05/08/2016.
 */
public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {

	private List<Item> items;

	public ItemsListAdapter(List<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	@Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ViewHolder(new TextView(parent.getContext()));
	}

	@Override public void onBindViewHolder(ViewHolder holder, int position) {
		TextView textView = (TextView) holder.itemView;
		Item item = items.get(position);
		textView.setText(position + " - ID: " + item.getId() + " - Name: " + item.getName());
	}

	@Override public int getItemCount() {
		return items.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public ViewHolder(View itemView) {
			super(itemView);
		}
	}
}
