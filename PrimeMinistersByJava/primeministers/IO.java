package primeministers;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class IO {

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
	* URLからファイルを読み込み行をリストとして返却する
	* @params URL ダウンロードリンク
	* @return 行を格納したリスト
	*/
	public List<String> readTextFromURL(final URL aURL) {
		List<String> aList = new ArrayList<String>();
		try {
			InputStream anInputStream = aURL.openStream();
			InputStreamReader anInputStreamReader = new InputStreamReader(anInputStream);
			BufferedReader instanceOfBufferedReader = new BufferedReader(anInputStreamReader);
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
	* 書き込み処理
	* @params List<String> CSVのリスト
	* @params String ベースディレクトリ
	*/
	public void writeText(final List<String> listOfCSV, String baseDirectory) {
		String baseName = this.table.aClassAttribute().toString();
		File aDestinationDirectory = new File(baseDirectory, baseName.concat("_Java")); // デスクトップにディレクトリ作成
		aDestinationDirectory.mkdir();
		try {
			File aTargetFile = new File(aDestinationDirectory, baseName.concat(".csv"));
			System.out.println(aTargetFile.getPath());
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
		}
	}

}
