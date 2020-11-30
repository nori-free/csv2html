// package libraries;
//
// import java.io.File;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import org.apache.http.HttpEntity;
// import org.apache.http.client.ClientProtocolException;
// import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.methods.HttpGet;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
// import org.apache.http.util.EntityUtils;
// import libraries.Condition;
//
// /**
// * DownloadUtil.saveThe("http://example.com").asA("example.txt").in("~/Desktop");
// * のように、英文的に保存処理を行うためのAPI
// */
// public class DownloadUtil {
//
// 	private static String url = null;
//
// 	private static String saveFilename = null;
//
// 	private static String saveFilepath = null;
//
// 	/**
// 	* プライベートコンストラクタ
// 	* @params aGotCondition 評価したい条件
// 	*/
// 	private DownloadUtil(String url, String saveFilename, String saveFilepath) {
// 		DownloadUtil.url = url;
// 		DownloadUtil.saveFilename = saveFilename;
// 		DownloadUtil.saveFilepath = saveFilepath;
// 	}
//
// 	/**
// 	* このURLを保存対象とする
// 	* @params String 対象のURL
// 	*/
// 	public static DownloadUtil saveThe(String aURL) {
// 		DownloadUtil.url = aURL;
// 		return new DownloadUtil(this.url, this.saveFilename, this.saveFilepath);
// 	}
//
// 	/**
// 	* 保存ファイル名を設定
// 	* @params String 保存先のパス
// 	*/
// 	public static DownloadUtil asA(String aString) {
// 		Condition isURLExists = new Condition(() -> DownloadUtil.url != null);
// 		// URLが既に設定されていれば保存ファイル名を設定
// 		isURLExists.ifTrue(() -> {
// 			DownloadUtil.saveFilename = aString;
// 		});
// 		return new DownloadUtil(this.url, this.saveFilename, this.saveFilepath);
// 	}
//
// 	/**
// 	* このパスに保存する
// 	* @params String 保存先へのパス(ファイル名を含まない)
// 	*/
// 	public static void in(String aString) {
// 		Condition isURLExists = new Condition(() -> DownloadUtil.url != null && DownloadUtil.saveFilename != null);
// 		// URLと保存ファイル名が既に設定されていればダウンロード開始
// 		isURLExists.ifTrue(() -> {
// 			// ダウンロード処理
// 			File aFilepath = new File(aString);
// 			new Condition(() -> aFilepath.exists())
// 			.ifFalse(() -> {
// 				// パスが存在しなければディレクトリを作成
// 				aFilepath.mkdirs();
// 			});
// 			DownloadUtil.execute();
// 		});
// 		// 軌道を乱さないために初期化
// 		DownloadUtil.url = null;
// 		DownloadUtil.saveFilename = null;
// 		DownloadUtil.saveFilepath = null;
// 	}
//
// 	/**
// 	* 指定したディレクトリ・ファイル名でダウンロード
// 	* @params String 保存先へのパス(ファイル名を含まない)
// 	*/
// 	private static void execute() {
// 		// 保存先を設定
// 		File aSaveFile = new File(DownloadUtil.saveFilepath, DownloadUtil.saveFilename);
// 		try (
// 			// HttpClientを設定
// 			final CloseableHttpClient aClient = HttpClients.createDefault();
// 			final CloseableHttpResponse response = aClient.execute(new HttpGet(DownloadUtil.url))
// 		) {
// 			// statusを確認して通信成功したらファイルを保存
// 			final int status = response.getStatusLine().getStatusCode();
// 			Condition httpStatusOK = new Condition(() -> status >= 200 && status < 300);
// 			httpStatusOK.ifThenElse(() -> {
// 				// ファイル保存
// 				final HttpEntity anEntity = response.getEntity();
// 				Files.write(Paths.get(aSaveFile.toString()), entity == null? new byte[0] : EntityUtils.toByteArray(entity));
// 			},
// 			() -> {
// 				// 例外を返す
// 				throw new ClientProtocolException(String.format("Unexpecte response status: %d", status));
// 			});
// 		}
// 	}
// }
