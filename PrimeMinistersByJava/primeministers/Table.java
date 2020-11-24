package primeministers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {

	private Object aClassAttribute;

	private Attributes attributes;

	private List<BufferedImage> images;

	private List<BufferedImage> thumbnails;

	private List<Tuple> tuples;

	public Table(Object aClassAttribute, Attributes instanceOfAttributes) {
		this.aClassAttribute = aClassAttribute;
		this.attributes = instanceOfAttributes;
		this.tuples = null;
		this.images = null;
		this.thumbnails = null;
	}

	public void add(final Tuple aTuple) {
		//
	}

	public Object aClassAttribute() {
		return this.aClassAttribute;
	}

	public Attributes attributes() {
		return this.attributes;
	}

	public void attributes(final Attributes instanceOfAttributes) {
		return;
	}

	public List<BufferedImage> images() {
		return new ArrayList<BufferedImage>();
	}

	// private BufferedImage images() {
	// 	return this.images;
	// }

	public List<BufferedImage> thumbnails() {
		return new ArrayList<BufferedImage>();
	}

	public List<Tuple> tuples() {
		return this.tuples;
	}

}
