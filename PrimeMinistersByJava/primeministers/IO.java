package primeministers;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class IO extends Object {

	/**
	* CSVファイルのパス
	*/
	private String aCSVFilePath;

	/**
	* テーブルのインスタンス
	*/
	private Table table;

	/**
	* コンストラクタ
	* @params Table テーブルのインスタンス
	*/
	public IO(Table aTable) {
		this.table = aTable;
	}

	/**
	* 属性リスト
	* @ return Attributes
	*/
	public Attributes attributes() {
		return this.table().attributes();
	}

	/**
	* CSVのファイルパスをセットする
	* @param aFile ファイルオブジェクト
	*/
	public String csvFilePath() {
		return this.aCSVFilePath;
	}

	/**
	* URLから画像ファイルを読み込み行をリストとして返却する
	* @params URL ダウンロードリンク
	* @return BufferedImage
	*/
	public static BufferedImage readImageFromURL( URL anURL) {
		BufferedImage anBufferedImage = null;
		try {
			anBufferedImage = ImageIO.read(anURL);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return anBufferedImage;
	}

	/**
	* URLから画像ファイルを読み込み行をリストとして返却する
	* @params URL ダウンロードリンク
	* @return BufferedImage
	*/
	public static BufferedImage readImageFromURL(final String aString) {
		URL anURL = null;
		try {
			anURL = new URL(aString);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
		return IO.readImageFromURL(anURL);
	}

	/**
	* ファイルパスからファイルを読み込み行をリストとして返却する
	* @params String ファイルパス
	* @return 行を格納したリスト
	*/
	public List<String> readTextFromFile(final String aFilepath) {
		List<String> aList = new ArrayList<String>();
		try {
			BufferedReader instanceOfBufferedReader = new BufferedReader(new FileReader(new File(aFilepath)));
			Stream<String> aStream = instanceOfBufferedReader.lines();
			aStream.forEach(aLine -> {
				aList.add(aLine);
			});
			instanceOfBufferedReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return aList;
	}

	/**
	* URLからファイルを読み込み行をリストとして返却する
	* @params URL ダウンロードリンク
	* @return 行を格納したリスト
	*/
	public List<String> readTextFromURL(final String aString) {
		List<String> aList = new ArrayList<String>();
		try {
			final URL anURL = new URL(aString);
			BufferedReader instanceOfBufferedReader = new BufferedReader(new InputStreamReader(anURL.openStream()));
			instanceOfBufferedReader.lines().forEach((aLine) -> {
				aList.add(aLine);
			});
		} catch(IOException e) {
			e.printStackTrace();
		}
		return aList;
	}

	/**
	* CSVのファイルパスをセットする
	* @param aFile ファイルオブジェクト
	*/
	public void setCSVFilePath(File aFile) {
		this.aCSVFilePath = aFile.getPath();
		return;
	}

	/**
	* テーブルのインスタンスを返す
	* @return テーブルのインスタンス
	*/
	public Table table() {
		return this.table;
	}

	/**
	* テーブルのタプルを返す
	* @return テーブルのタプル
	*/
	public List<Tuple> tuples() {
		return this.table().tuples();
	}

	/**
	* 画像を書き出す
	*/
	public void writeImage(BufferedImage anBufferedImage, String aString) {
		String anExtension = aString.substring(aString.lastIndexOf(".") + 1); // 最後のドットより後、すなわち拡張子を切り分ける
		try {
			ImageIO.write(anBufferedImage, anExtension, new File(aString));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	* 書き込み処理
	* @params List<String> CSVのリスト
	* @params String ベースディレクトリ
	* @return File csvファイルパス
	*/
	public File writeText(final List<String> listOfCSV, String baseDirectory) {
		File aTargetFile = new File(baseDirectory);
		try {
			BufferedWriter instanceOfBufferedWriter = new BufferedWriter(new FileWriter(aTargetFile));
			listOfCSV.forEach((aLine) -> {
				try {
					instanceOfBufferedWriter.write(aLine);
					instanceOfBufferedWriter.newLine();
				} catch(IOException e) {
					e.printStackTrace();
				}
			});
			instanceOfBufferedWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
		return aTargetFile;
	}

}
