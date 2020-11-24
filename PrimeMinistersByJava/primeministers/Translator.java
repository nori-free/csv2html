package primeministers;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Calendar;
import java.awt.image.BufferedImage;

public class Translator {

	private Table inputTable;

	private Table outputTable;

	public Translator(Object aClassAttribute) {
		Attributes.flushBaseDirectory();
		// try {
			// final Constructor<? extends Attributes> aConstructor =
			this.inputTable = new Table(aClassAttribute, new Attributes("input"));
			this.outputTable = new Table(aClassAttribute, new Attributes("output"));
		// }
	}

	public String computeNumberOfDays() {
		return null;
	}

	public void execute() {
		// System.out.println(aClassAttribute.toString() + " : " + aClassAttribute.getClass().getName());
		Downloader aDownloader = new Downloader(this.inputTable);
		aDownloader.perform();
	}

	public static void perform(Object aClassAttribute) {
		final Translator aTranslator = new Translator(aClassAttribute);
	}

}
