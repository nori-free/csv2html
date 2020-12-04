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
		this.inputTable = new Table(aClassAttribute, new Attributes(aClassAttribute, "input"));
		this.outputTable = new Table(aClassAttribute, new Attributes(aClassAttribute, "output"));
	}

	public String computeNumberOfDays() {
		return null;
	}

	public void execute() {
		Downloader aDownloader = new Downloader(this.inputTable);
		aDownloader.perform();
		System.out.println(this.inputTable.tuples());
		this.translate();
	}

	// public static void perform(Object aClassAttribute) {
	// 	final Translator aTranslator = new Translator(aClassAttribute);
	// 	Translator.execute();
	// }

}
