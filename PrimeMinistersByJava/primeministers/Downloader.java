package primeministers;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Stream;

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
		try {
			InputStream anInputStream = this.url.openStream();
			InputStreamReader anInputStreamReader = new InputStreamReader(anInputStream);
			BufferedReader aBufferedReader = new BufferedReader(anInputStreamReader);
			Stream<String> aStream = aBufferedReader.lines();
			aStream.forEach(System.out::println);
			aBufferedReader.close();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		return;
	}

	public void downloadImages() {
		//
	}

	public void downloadThumbnails() {
		//
	}

	public void perform() {
		this.downloadCsv();
	}

}
