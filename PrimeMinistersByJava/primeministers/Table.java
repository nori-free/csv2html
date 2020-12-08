package primeministers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import libraries.Condition;

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
		this.tuples().add(aTuple);
	}

	public Object aClassAttribute() {
		return this.aClassAttribute;
	}

	public Attributes attributes() {
		return this.attributes;
	}

	public List<BufferedImage> images() {
		return new ArrayList<BufferedImage>();
	}

	public List<BufferedImage> thumbnails() {
		return new ArrayList<BufferedImage>();
	}

	public List<Tuple> tuples() {
		new Condition(() -> this.tuples == null)
		.ifTrue(() -> {
			this.tuples = new ArrayList<Tuple>();
		});
		return this.tuples;
	}

}
