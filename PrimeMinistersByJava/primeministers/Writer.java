package primeministers;

import java.util.Calendar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Writer extends IO {

	public Writer(final Table aTable) {
		super(aTable);
		return;
	}

	private String unformattedHTML() {
		String aHTML = new String("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html lang=\"ja\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><meta http-equiv=\"Content-Style-Type\" content=\"text/css\"><meta http-equiv=\"Content-Script-Type\" content=\"text/javascript\"><meta name=\"keywords\" content=\"Smalltalk,Oriented,Programming\"><meta name=\"description\" content=\"Prime Ministers\"><meta name=\"author\" content=\"AOKI Atsushi\"><link rev=\"made\" href=\"http://www.cc.kyoto-su.ac.jp/~atsushi/\"><link rel=\"index\" href=\"index.html\"><style type=\"text/css\"><!--body {	background-color : #ffffff;	margin : 20px;	padding : 10px;	font-family : serif;	font-size : 10pt;}a {	text-decoration : underline;	color : #000000;}a:link {	background-color : #ffddbb;}a:visited {	background-color : #ccffcc;}a:hover {	background-color : #dddddd;}a:active {	background-color : #dddddd;}div.belt {	background-color : #eeeeee;	padding : 0px 4px;}div.right-small {	text-align : right;	font-size : 8pt;}img.borderless {	border-width : 0px;	vertical-align : middle;}table.belt {	border-style : solid;	border-width : 0px;	border-color : #000000;	background-color : #ffffff;	padding : 0px 0px;	width : 100%;}table.content {	border-style : solid;	border-width : 0px;	border-color : #000000;	padding : 2px 2px;}td.center-blue {	padding : 2px 2px;	text-align : center;	background-color : #ddeeff;}td.center-pink {	padding : 2px 2px;	text-align : center;	background-color : #ffddee;}td.center-yellow {	padding : 2px 2px;	text-align : center;	background-color : #ffffcc;}--></style><title>Prime Ministers</title></head><body><div class=\"belt\"><h2>Prime Ministers</h2></div><table class=\"belt\" summary=\"table\">	<tbody>		<tr>			<td>				<table class=\"content\" summary=\"table\">					<tbody>					</tbody>				</table>			</td>		</tr>	</tbody></table><hr><div class=\"right-small\">Created by primeministers.Writer on 2020/09/17 at 12:38:07</div></body></html>");
		return aHTML;
	}

	public void writeAttributesOn(BufferedWriter aStream) {

	}

	public void writeFooterOn(BufferedWriter aStream) {

	}

	public void writeHeaderOn(BufferedWriter aStream) {

	}

	public void writeTableOn(BufferedWriter aStream) {

	}

	public void writeTuplesOn(BufferedWriter aStream) {

	}

}
