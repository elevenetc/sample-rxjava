package su.sample.rxjava.app.models;

/**
 * Created by Eugene Levenetc on 04/08/2016.
 */
public class Item {
	private final String name;
	private final String id;

	public Item(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
