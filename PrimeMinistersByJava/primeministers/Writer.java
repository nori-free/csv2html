package primeministers;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import libraries.ImageUtil;

public class Writer {
	private Table table;

	public Writer(Table aTable) {
		this.table = aTable;
		return;
	}

	/**
	* 画像をhtmlタグでラップする
	* @params 画像情報の入ったmap
	* @return tdタグで囲った結果
	*/
	public String getImageWithBlue(Map<String, String> aMap) {
		// ファイル名関連処理
		String currentFilename = ImageUtil.currentFilename(aMap.get("images"));
		Integer imageNumber = Integer.valueOf(ImageUtil.remoteExtension(currentFilename));
		int width = 64;
		int height = 64;
		// 画像のサイズ取得
		BufferedImage aBufferedImage;
		try {
			aBufferedImage = ImageIO.read(new File(this.table().attributes().baseDirectory(), aMap.get("thumbnails")));
			width = aBufferedImage.getWidth();
			height = aBufferedImage.getHeight();
		} catch(IOException e) {
			e.printStackTrace();
		}
		// 出力
		return getTableDataWithBlue(String.format("<a name=\"%s\" href=\"%s\"><img class=\"borderless\" src=\"%s\" width=\"%d\" height=\"%d\" alt=\"%s\"></a>", imageNumber.intValue(), aMap.get("images"), aMap.get("thumbnails"), width, height, currentFilename));
	}

	/**
	* 画像をhtmlタグでラップする
	* @params tdの中身
	* @return tdタグで囲った結果
	*/
	public String getImageWithYellow(Map<String, String> aMap) {
		// ファイル名関連処理
		String currentFilename = ImageUtil.currentFilename(aMap.get("images"));
		Integer imageNumber = Integer.valueOf(ImageUtil.remoteExtension(currentFilename));
		int width = 64;
		int height = 64;
		// 画像のサイズ取得
		BufferedImage aBufferedImage;
		try {
			aBufferedImage = ImageIO.read(new File(this.table().attributes().baseDirectory(), aMap.get("thumbnails")));
			width = aBufferedImage.getWidth();
			height = aBufferedImage.getHeight();
		} catch(IOException e) {
			e.printStackTrace();
		}
		// 出力
		return getTableDataWithYellow(String.format("<a name=\"%s\" href=\"%s\"><img class=\"borderless\" src=\"%s\" width=\"%d\" height=\"%d\" alt=\"%s\"></a>", imageNumber.intValue(), aMap.get("images"), aMap.get("thumbnails"), width, height, currentFilename));
	}

	/**
	* tdタグで囲う
	* @params tdの中身
	* @return tdタグで囲った結果
	*/
	public String getTableDataWithBlue(String aString) {
		return String.format("<td class=\"center-blue\">%s</td>", aString);
	}

	/**
	* tdタグで囲う
	* @params tdの中身
	* @return tdタグで囲った結果
	*/
	public String getTableDataWithYellow(String aString) {
		return String.format("<td class=\"center-yellow\">%s</td>", aString);
	}

	/**
	* 文字列を結合する
	* @params 結合したい文字列群
	* @result 結合結果
	*/
	private String join(String... strings) {
		System.out.println(Arrays.asList(strings));
		StringBuilder aStringBuilder = new StringBuilder();
		Arrays.asList(strings).forEach(aString -> {
			// result.concat(aString);
			aStringBuilder.append(aString);
		});
		return aStringBuilder.toString();
	}

	private Table table() {
		return this.table;
	}

	/**
	* thタグで囲う
	* @params thの中身
	* @return thタグで囲った結果
	*/
	public String tableHeader(String aString) {
		return String.format("<td class=\"center-pink\">%s</td>", aString);
	}

	/**
	* trタグで囲う
	* @params trの中身
	* @return trタグで囲った結果
	*/
	public String getTableRow(List<String> aList) {
		String aString = aList.stream().collect(Collectors.joining(""));
		return String.format("<tr>%s</tr>", aString);
	}

	public void write(Table aTable) {
		List<Tuple> aTuple = aTable.tuples();
		String innerHTML = new String("");
		List<String> formattedList = new ArrayList<String>();
		aTuple.forEach(aRow -> {
			formattedList.add(this.getTableRow(aRow.values()));
		});
		String className = aTable.aClassAttribute().toString();
		// trの複数形
		String trs = formattedList.stream().collect(Collectors.joining(""));

		String f = this.join("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html lang=\"ja\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><meta http-equiv=\"Content-Style-Type\" content=\"text/css\"><meta http-equiv=\"Content-Script-Type\" content=\"text/javascript\"><meta name=\"keywords\" content=\"Smalltalk,Oriented,Programming\"><meta name=\"description\" content=\"Prime Ministers\"><meta name=\"author\" content=\"AOKI Atsushi\"><link rev=\"made\" href=\"http://www.cc.kyoto-su.ac.jp/~atsushi/\"><link rel=\"index\" href=\"index.html\"><style type=\"text/css\"><!--body {	background-color : #ffffff;	margin : 20px;	padding : 10px;	font-family : serif;	font-size : 10pt;}a {	text-decoration : underline;	color : #000000;}a:link {	background-color : #ffddbb;}a:visited {	background-color : #ccffcc;}a:hover {	background-color : #dddddd;}a:active {	background-color : #dddddd;}div.belt {	background-color : #eeeeee;	padding : 0px 4px;}div.right-small {	text-align : right;	font-size : 8pt;}img.borderless {	border-width : 0px;	vertical-align : middle;}table.belt {	border-style : solid;	border-width : 0px;	border-color : #000000;	background-color : #ffffff;	padding : 0px 0px;	width : 100%;}table.content {	border-style : solid;	border-width : 0px;	border-color : #000000;	padding : 2px 2px;}td.center-blue {	padding : 2px 2px;	text-align : center;	background-color : #ddeeff;}td.center-pink {	padding : 2px 2px;	text-align : center;	background-color : #ffddee;}td.center-yellow {	padding : 2px 2px;	text-align : center;	background-color : #ffffcc;}--></style><title>", className, "</title></head><body><div class=\"belt\"><h2>", className, "</h2></div><table class=\"belt\" summary=\"table\"><tbody><tr><td><table class=\"content\" summary=\"table\"><tbody>", trs, "</tbody></table></td></tr></tbody></table><hr><div class=\"right-small\">Created by primeministers.Writer on 2020/09/17 at 12:38:07</div></body></html>");

		try {
			BufferedWriter aBufferedWriter = new BufferedWriter(new FileWriter(new File(aTable.attributes().baseDirectory(), "index.html")));
			aBufferedWriter.write(f);
			aBufferedWriter.newLine();
			aBufferedWriter.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
