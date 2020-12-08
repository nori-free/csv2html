package primeministers;

import java.util.List;

public class Tuple {
    private Attributes attributes;
    private List<String> values;

    public Tuple(final List<String> valueCollection) {
		this.values = valueCollection;
        return;
    }

    public Attributes attributes() {
        return this.attributes;
    }

	public void add(int index, String aString) {
        this.values().add(index, aString);
    }

    public List<String> values() {
        return this.values;
    }
}
