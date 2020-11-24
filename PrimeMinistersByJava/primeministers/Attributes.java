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

	public Attributes(Object aClassAttribute, String aString) {
		this.keys = new ArrayList<String>();
		this.names = new ArrayList<String>();
		Condition isPrimeMinister = new Condition(() -> Boolean.valueOf(aClassAttribute.toString().compareTo("PrimeMinisters") == 0));
		Condition isTokugawa = new Condition(() -> Boolean.valueOf(aClassAttribute.toString().compareTo("TokugawaShogunate") == 0 ));

		isPrimeMinister.ifTrue(() -> this.primeMinistersConstructor(aString));
		isTokugawa.ifTrue(() -> this.tokugawaShogunateConstructor(aString));
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

	/**
	* ベースとなるディレクトリを返す。
	* @return ベースとなるディレクトリ
	*/
	public static String getBaseDirectory() {
		return Attributes.baseDirectory;
	}

	/**
	* 総理大臣の場合のコンストラクタの続き：インデックスの作成など
	*/
	private void primeMinistersConstructor(String aString) {
		new Condition(() -> Boolean.valueOf(aString.compareTo("input") == 0))
		.ifTrue(() -> {
			System.out.println("総理大臣 input");
		});
		new Condition(() -> Boolean.valueOf(aString.compareTo("output") == 0))
		.ifTrue(() -> {
			System.out.println("総理大臣 output");
		});
	}

	public static void setBaseDirectory() {
		File aFileInstance = new File(System.getProperty("user.home"), "Desktop");
		Attributes.baseDirectory = aFileInstance.getPath();
		System.out.println(Attributes.baseDirectory);
	}

	/**
	* 徳川の場合のコンストラクタの続き：インデックスの作成など
	*/
	private void tokugawaShogunateConstructor(String aString) {
		new Condition(() -> Boolean.valueOf(aString.compareTo("input") == 0))
		.ifTrue(() -> {
			System.out.println("徳川将軍 input");
		});
		new Condition(() -> Boolean.valueOf(aString.compareTo("output") == 0))
		.ifTrue(() -> {
			System.out.println("徳川将軍 output");
		});
	}

}
