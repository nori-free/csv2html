package libraries;

import java.io.File;

public class ImageUtil {

	/**
	* ファイル名
	*/
	private static String filename;

	/**
	* 拡張子を削除
	* @ param ファイル名
	* @return 拡張子を除いたファイル名
	*/
	public static String currentFilename(String aString) {
		String[] splittedString = aString.split("/");
		return splittedString[splittedString.length - 1];
	}

	/**
	* ファイル名を取得する
	* @return String ファイル名
	*/
	private static String filename() {
		return ImageUtil.filename;
	}

	/**
	* bmpファイルか調べる
	* @return boolean 判定結果(bmp -> true, else -> false)
	*/
	private static boolean isBMP() {
		return ImageUtil.filename().endsWith("bmp");
	}

	/**
	* gifファイルか調べる
	* @return boolean 判定結果(gif -> true, else -> false)
	*/
	private static boolean isGIF() {
		return ImageUtil.filename().endsWith("gif");
	}

	/**
	* heicファイルか調べる
	* @return boolean 判定結果(heic -> true, else -> false)
	*/
	private static boolean isHEIC() {
		return ImageUtil.filename().endsWith("heic");
	}

	/**
	* 画像ファイルか判定する
	*/
 	public static boolean isImageFile(String aFilename) {
		try {
			String aFilenameWithLowerCase = aFilename.toLowerCase();
			ImageUtil.setFilename(aFilenameWithLowerCase);
			// 画像ファイルか判定する
			return (
				ImageUtil.isBMP() ||
				ImageUtil.isGIF() ||
				ImageUtil.isHEIC() ||
				ImageUtil.isJPEG() ||
				ImageUtil.isJPG() ||
				ImageUtil.isPNG() ||
				ImageUtil.isTIFF()
			);
		}catch(NullPointerException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	* jpegファイルか調べる
	* @return boolean 判定結果(jpeg -> true, else -> false)
	*/
	private static boolean isJPEG() {
		return ImageUtil.filename().endsWith("jpeg");
	}

	/**
	* jpgファイルか調べる
	* @return boolean 判定結果(jpg -> true, else -> false)
	*/
	private static boolean isJPG() {
		return ImageUtil.filename().endsWith("jpg");
	}

	/**
	* pngファイルか調べる
	* @return boolean 判定結果(png -> true, else -> false)
	*/
	private static boolean isPNG() {
		return ImageUtil.filename().endsWith("png");
	}

	/**
	* tiffファイルか調べる
	* @return boolean 判定結果(tiff -> true, else -> false)
	*/
	private static boolean isTIFF() {
		return ImageUtil.filename().endsWith("tiff");
	}

	/**
	* 拡張子を削除
	* @ param ファイル名
	* @return 拡張子を除いたファイル名
	*/
	public static String remoteExtension(String aString) {
		// isImageFile()で画像ファイルと信頼されているため、lastIndexOfから-1が帰ってこないものとしている
		return aString.substring(0, aString.lastIndexOf("."));
	}

	/**
	* ファイル名を設定する
	* @params String ファイル名
	*/
	private static void setFilename(String aFilename) {
		ImageUtil.filename = aFilename;
	}
}
