package primeministers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;

import javax.imageio.ImageIO;

import libraries.Condition;


public class Downloader extends IO {

	/**
	* CSVの参照先であるベースのURL
	*/
	// private URL url;
	private String url;

	/**
	* ダウンロードを行うためのコンストラクタ
	* @params aClassName Enumから与えられたクラス名
	*/
	public Downloader(Table aTable) {
		super(aTable);
		String aClassName = aTable.aClassAttribute().toString();
		this.url = String.format("%s%s/%s.csv",super.table().attributes().baseUrl(), aClassName, aClassName);
	}

	/**
	* CSVをダウンロードする
	* @return File CSVの保存先
	*/
	public File downloadCsv() {
		super.table().attributes().setBaseDirectory();
		File aTargetFilepath = new File(Attributes.getBaseDirectory(), Attributes.className().concat(".csv"));
		this.downloadFile(this.url.toString(), aTargetFilepath.getPath());
		return aTargetFilepath;
	}

	/**
	* ダウンロードを統括するメソッド
	* @params String aTargetURL 保存するURL
	* @params String aTargetFilepath 保存先のファイルパス
	*/
	private void downloadFile(String aTargetURL, String aTargetFilepath) {
		List<String> listOfTargetURL = super.readTextFromURL(aTargetURL);
		File aCSVFilePath = super.writeText(listOfTargetURL, aTargetFilepath);
		return;
	}

	/**
	* 画像群をダウンロードする
	*/
	public void downloadImages() {
		this.downloadPictures(super.attributes().indexOfImage());
		return;
	}

	/**
	* 画像群またはサムネイル画像群をダウンロードする
	*/
	public void downloadPictures(final int indexOfPictures) {
		new Condition(() -> super.tuples() == null)
		.ifThenElse(() -> {
			System.out.println("Tuple is null.");
		},
		() -> {
			System.out.println("Yes, tuple.");
			super.tuples().stream()
			.map(aTuple->aTuple.values().get(indexOfPictures))
			.forEach(item -> {
				File aFile = new File(super.table().attributes().baseDirectory(), item);
				String directoryOfItem = aFile.getPath();

				String urlOfItem = super.table().attributes().baseUrl().concat(
					new File(
					Attributes.className(), item).getPath()
				);
				System.out.printf("[Downloading...] %s -> %s%n", urlOfItem, directoryOfItem);
				BufferedImage anBufferedImage = IO.readImageFromURL(urlOfItem);
				super.writeImage(anBufferedImage, directoryOfItem);
				System.out.printf("complete!!%n%n");
			});
		});
	}

	/**
	* サムネイル画像群をダウンロードする
	*/
	public void downloadThumbnails() {
		this.downloadPictures(super.attributes().indexOfThumbnail());
		return;
	}

	public void perform() {
		// プロジェクトルートディレクトリの作成
		File desktopPath = new File(System.getProperty("user.home"), "Desktop");
		File aDestinationDirectory = new File(desktopPath, super.table().aClassAttribute().toString().concat("_Java")); // デスクトップにディレクトリ作成
		aDestinationDirectory.mkdir();
		new File(aDestinationDirectory, "images").mkdir();
		new File(aDestinationDirectory, "thumbnails").mkdir();
		// ダウンロード作業
		AtomicInteger anAtomicInteger = new AtomicInteger();
		File aCSVFilePath = this.downloadCsv();
		final Reader aReader = new Reader(super.table(), aCSVFilePath, super.table().aClassAttribute());
		List<List<String>> splittedList = aReader.perform();
		splittedList.forEach(aLine -> {
			new Condition(() -> anAtomicInteger.getAndIncrement() != 0).
			ifTrue(() -> {
				Tuple aTuple = new Tuple(aLine);
				super.table().add(new Tuple(aLine));
			});
		});
		this.downloadImages();
		this.downloadThumbnails();
	}

}
