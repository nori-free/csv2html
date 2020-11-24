package primeministers;

import libraries.Condition;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Attributes {

	private static String baseDirectory = null;

	private List<String> keys;

	private List<String> names;

	public Attributes(String aString) {
		this.keys = new ArrayList<String>();
		this.names = new ArrayList<String>();
		Condition isInput = new Condition(() -> Boolean.valueOf(aString.compareTo("input") == 0));
		Condition isOutput = new Condition(() -> Boolean.valueOf(aString.compareTo("output") == 0 ));

		isInput.ifTrue(() -> {
			System.out.println("input!!");
		});
		isOutput.ifTrue(() -> {
			System.out.println("output!!");
		});
	}

	protected String at(Integer index) {
		return null;
	}

	public String baseUrl() {
		return new String("http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/VisualWorks/CSV2HTML/");
	}

	/**
	* ベースとなるディレクトリの記憶を水に流す。
	*/
	public static void flushBaseDirectory() {
		Attributes.baseDirectory = null;
		return;
	}

}
