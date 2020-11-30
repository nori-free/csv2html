package primeministers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

import libraries.Condition;
import libraries.ImageUtil;

public class Reader extends IO {

	private String aClassName;

	private String filename;

	/**
	* リーダのコンストラクタ
	*/
	public Reader(final Table aTable, final File aCSVFilePath, Object aClassName) {
		super(aTable);
		super.setCSVFilePath(aCSVFilePath);
		this.aClassName = aClassName.toString();
		return;
	}

	public List<String> organize(List<String> beforeList) {
		return null;
	}

	/**
	* オブジェクトを指定文字で分割する。ついでにファイルをフルパスにする
	* @return List<String> 分割後の文字列
	*/
	private List<String> split(String aString, String aSeparator) {
		List<String> result = new ArrayList<>();
		Arrays.asList(aString.split(aSeparator)).forEach(aSplittedWord -> {
			new Condition(() -> ImageUtil.isImageFile(aSplittedWord))
			.ifThenElse(() -> {
				result.add(aSplittedWord);
			},
			() -> {
				result.add(aSplittedWord);
			});
		});
		return result;
	}

	/**
	* CSVファイルの読み込み処理
	*/
	public List<List<String>> perform() {
		// 保存したCSVファイルを読み込む
		final List<String> aListOfCSV = super.readTextFromFile(super.csvFilePath());
		// csvをカンマごとに区切り、List<List<String>>とする
		List<List<String>> splittedList = new ArrayList<>();
		aListOfCSV.forEach(aLine -> {
			splittedList.add(this.split(aLine, ","));
		});
		return splittedList;
	}

}
