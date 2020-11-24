package primeministers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Downloader extends IO {

	/**
	* CSVの参照先であるベースのURL
	*/
	private URL url;

	/**
	* ダウンロードを行うためのコンストラクタ
	* @params aClassName Enumから与えられたクラス名
	*/
	public Downloader(Table aTable) {
		super(aTable);
		String aClassName = aTable.aClassAttribute().toString();
		System.out.println(super.table());
		try {
			this.url = new URL(
			String.format("%s%s/%s.csv",super.table().attributes().baseUrl(), aClassName, aClassName)
			); // URLを完成させる
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	* CSVをダウンロードする
	*/
	public void downloadCsv() {
		List<String> listOfCSV = super.readTextFromURL(this.url);
		Attributes.setBaseDirectory();
		super.writeText(listOfCSV, Attributes.getBaseDirectory());
		return;
	}

	/**
	* 画像群をダウンロードする
	*/
	public void downloadImages() {
		//
	}

	/**
	* 画像群またはサムネイル画像群をダウンロードする
	*/
	public void downloadPictures(final int indexOfPictures) {
		super.tuples().stream()
		.map(aTuple->aTuple.values().get(indexOfPictures))
		.forEach(item -> {
			System.out.println(item);
			//ダウンロード処理
		});
	}

	/**
	* サムネイル画像群をダウンロードする
	*/
	public void downloadThumbnails() {
		//
	}

	public void perform() {
		this.downloadCsv();
		this.downloadImages();
	}

}
