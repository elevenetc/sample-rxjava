package su.sample.rxjava.app.models;

/**
 * Created by Eugene Levenetc on 04/08/2016.
 */
public class Item {
    private String source;
    private final int id;

    public Item(int id, String source) {
        this.id = id;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public void changeSource(String newSource) {
        source = newSource;
    }
}
