package primeministers;

import libraries.Condition;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

public class Attributes {

	/**
	* ベースとなるディレクトリを記憶する（クラス変数）フィールド。
	*/
	private static String baseDirectory = null;

	/**
	* クラス名
	*/
	private static String aClassName = null;

	/**
	* I/Oモード
	*/
	private String mode = null;

	/**
	* 属性リストのキー群を記憶する（インスタンス変数）フィールド。
	*/
	private List<String> keys;

	/**
	* 属性リストの名前群を記憶する（インスタンス変数）フィールド。
	*/
	private List<String> names;

	/**
	* 総理大臣入力テーブルのカラム情報の列挙型
	*/
	private static enum PrimeMinistersInputEnum {
		No,
		Order,
		Name,
		Kana,
		Period,
		School,
		Party,
		Place,
		Image,
		Thumbnail
	}

	/**
	* 総理大臣出力テーブルのカラム情報の列挙型
	*/
	private static enum PrimeMinistersOutputEnum {
		人目,
		代,
		氏名,
		ふりがな,
		在位期間,
		在位日数,
		出身校,
		政党,
		出身地,
		画像
	}

	/**
	* 徳川幕府入力テーブルのカラム情報の列挙型
	*/
	private static enum TokugawaShogunateInputEnum {
		No,
		Name,
		Kana,
		Period,
		Family,
		Rank,
		Image,
		Thumbnail,
		Former,
		Cemetery
	}

	/**
	* 徳川幕府出力テーブルのカラム情報の列挙型
	*/
	private static enum TokugawaShogunateOutputEnum {
		代,
		氏名,
		ふりがな,
		在位期間,
		在位日数,
		出身家,
		官位,
		画像,
		院号,
		墓所
	}

	/**
	* 属性リストを作成するコンストラクタ。
	* @params Object クラス名(PrimeMinisters or TokugawaShogunateが格納されたObject)
	* @params String [input or output]
	*/
	public Attributes(Object aClassAttribute, String aString) {
		this.keys = new ArrayList<String>();
		this.names = new ArrayList<String>();
		this.mode = aString;
		// enumのクラス名を記憶させる
		Attributes.aClassName = aClassAttribute.toString();

		// enumのクラスごとにコンストラクタを捌く
		Condition isPrimeMinister = this.isPrimeMinisters();
		Condition isTokugawa = this.isTokugawaShogunate();

		isPrimeMinister.ifTrue(() -> this.primeMinistersConstructor());
		isTokugawa.ifTrue(() -> this.tokugawaShogunateConstructor());
	}

	/**
	* keysとnamesに情報を追加
	* @params aKey keysに追加する値
	*/
	protected void addToTheList(String aKey) {
		this.keys.add(aKey);
		this.names.add(new String());
	}

	/**
	* 指定されたインデックスに対応する名前を応答する。
	*/
	protected String at(Integer index) {
		return null;
	}

	/**
	* 保存先を文字列で応答する。
	*/
	public String baseDirectory() {
		return Attributes.baseDirectory;
	}

	public List<String> getEnum() {
		List<String> aList = new ArrayList<>();
		System.out.println(this.mode);
		this.isPrimeMinisters()
		.ifTrue(() -> {
			this.isInputMode()
			.ifTrue(() -> {
				// 総理大臣 | 入力
				Arrays.asList(Stream.of(PrimeMinistersInputEnum.values()).map(PrimeMinistersInputEnum::name).toArray(String[]::new)).forEach(aString -> {
					aList.add(aString);
				});
			});
			this.isOutputMode()
			.ifTrue(() -> {
				// 総理大臣 | 出力
				Arrays.asList(Stream.of(PrimeMinistersOutputEnum.values()).map(PrimeMinistersOutputEnum::name).toArray(String[]::new)).forEach(aString -> {
					aList.add(aString);
				});
			});
		});
		this.isTokugawaShogunate()
		.ifTrue(() -> {
			this.isInputMode()
			.ifTrue(() -> {
				// 徳川幕府 | 入力
				Arrays.asList(Stream.of(TokugawaShogunateInputEnum.values()).map(TokugawaShogunateInputEnum::name).toArray(String[]::new)).forEach(aString -> {
					aList.add(aString);
				});
			});
			this.isOutputMode()
			.ifTrue(() -> {
				// 徳川幕府 | 出力
				Arrays.asList(Stream.of(TokugawaShogunateOutputEnum.values()).map(TokugawaShogunateOutputEnum::name).toArray(String[]::new)).forEach(aString -> {
					aList.add(aString);
				});
			});
		});
		return aList;
	}

	/**
	* 情報の在処(URL)を文字列で応答する。
	*/
	public String baseUrl() {
		return new String("http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/VisualWorks/CSV2HTML/");
	}

	/**
	* プロジェクトのルートディレクトリへの絶対パスを文字列で応答する。
	*/
	private String desktopPath() {
		File desktopPath = new File(System.getProperty("user.home"), "Desktop");
		return desktopPath.getPath();
	}

	/**
	* aClassNameを取得
	*/
	public static String className() {
		return Attributes.aClassName;
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
	* 与えられたキーのインデックスを返す
	* @params aString 場所を探すキー
	* @return int インデックス
	*/
	protected int indexOf(final String aString) {
		return this.keys.indexOf(aString);
	}

	/**
	* 在位日数のインデックスを返す
	* @return int インデックス
	*/
	public int indexOfDays() {
		return this.indexOf("Days");
	}

	/**
	* 画像のインデックスを返す
	* @return int インデックス
	*/
	public int indexOfImage() {
		return this.indexOf("Image");
	}

	/**
	* 仮名のインデックスを返す
	* @return int インデックス
	*/
	public int indexOfKana() {
		return this.indexOf("Kana");
	}

	/**
	* 名前のインデックスを返す
	* @return int インデックス
	*/
	public int indexOfName() {
		return this.indexOf("Name");
	}

	/**
	* 番号のインデックスを返す
	* @return int インデックス
	*/
	public int indexOfNo() {
		return this.indexOf("No");
	}

	/**
	* 在位期間のインデックスを返す
	* @return int インデックス
	*/
	public int indexOfPeriod() {
		return this.indexOf("Period");
	}

	/**
	* サムネイルのインデックスを返す
	* @return int インデックス
	*/
	public int indexOfThumbnail() {
		return this.indexOf("Thumbnail");
	}

	/**
	* 入力モードか判定する
	* @return Condition 条件分岐オブジェクト
	*/
	private Condition isInputMode() {
		return new Condition(() -> Boolean.valueOf(this.mode.compareTo("input") == 0));
	}

	/**
	* 出力モードか判定する
	* @return Condition 条件分岐オブジェクト
	*/
	private Condition isOutputMode() {
		return new Condition(() -> Boolean.valueOf(this.mode.compareTo("output") == 0));
	}

	/**
	* 総理大臣モードか判定する
	* @return Condition 条件分岐オブジェクト
	*/
	private Condition isPrimeMinisters() {
		return new Condition(() -> Attributes.aClassName.compareTo("PrimeMinisters") == 0);
	}

	/**
	* 徳川幕府モードか判定する
	* @return Condition 条件分岐オブジェクト
	*/
	private Condition isTokugawaShogunate() {
		return new Condition(() -> Attributes.aClassName.compareTo("TokugawaShogunate") == 0);
	}

	public List<String> keys() {
		return this.keys;
	}

	/**
	* 総理大臣の場合のコンストラクタの続き：インデックスの作成など
	*/
	private void primeMinistersConstructor() {
		this.isInputMode()
		.ifTrue(() -> {
			System.out.println("総理大臣 input");
			Arrays.stream(PrimeMinistersInputEnum.values()).forEach(aValue -> this.addToTheList(aValue.toString()));
		});
		this.isOutputMode()
		.ifTrue(() -> {
			System.out.println("総理大臣 output");
			Arrays.stream(PrimeMinistersOutputEnum.values()).forEach(aValue -> this.addToTheList(aValue.toString()));
		});
	}

	/**
	* ベースディレクトリに値を設定
	*/
	public void setBaseDirectory() {
		Condition isPrimeMinister = this.isPrimeMinisters();
		Condition isTokugawa = this.isTokugawaShogunate();

		isPrimeMinister.ifTrue(() -> {
			final File projectRootPath = new File(this.desktopPath(), "PrimeMinisters_Java");
			Attributes.baseDirectory = projectRootPath.getPath();
		});
		isTokugawa.ifTrue(() -> {
			final File projectRootPath = new File(this.desktopPath(), "TokugawaShogunate_Java");
			Attributes.baseDirectory = projectRootPath.getPath();
		});
	}

	/**
	* 徳川の場合のコンストラクタの続き：インデックスの作成など
	*/
	private void tokugawaShogunateConstructor() {
		this.isInputMode()
		.ifTrue(() -> {
			System.out.println("徳川将軍 input");
			Arrays.stream(TokugawaShogunateInputEnum.values()).forEach(aValue -> this.addToTheList(aValue.toString()));
		});
		this.isOutputMode()
		.ifTrue(() -> {
			System.out.println("徳川将軍 output");
			Arrays.stream(TokugawaShogunateOutputEnum.values()).forEach(aValue -> this.addToTheList(aValue.toString()));
		});
		return;
	}

}
