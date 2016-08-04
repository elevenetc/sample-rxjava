package su.sample.rxjava.app.views;

import java.util.List;

import su.sample.rxjava.app.models.Item;

/**
 * Created by Eugene Levenetc on 04/08/2016.
 */
public interface ListView {
	void addItem(Item item);

	void showLoading();

	void showError(String text);

	void showData(List<Item> items);
}
