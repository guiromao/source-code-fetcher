package co.trucom.sourcecodefetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SourceFetcher {

	private static final Log log = LogFactory.getLog(SourceFetcher.class.getClass());

	private SourceFetcher() {
	}

	public static String getSourceOf(String url, boolean indent) {
		try {
			URL urlObject = new URL(url);
			URLConnection connection = urlObject.openConnection();

			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			return indent ? indentHtml(stringFromInput(connection.getInputStream())) : 
				stringFromInput(connection.getInputStream());
		}
		catch(IOException e) {
			throw new IllegalStateException("Errors: " + e);
		}
	}

	private static String stringFromInput(InputStream inputStream) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
			String sourceLine;
			StringBuilder result = new StringBuilder();
			
			while((sourceLine = reader.readLine()) != null) {
				result.append(sourceLine);
			}

			return result.toString();
		}
	}

	private static String indentHtml(String html) {
		Document doc = Jsoup.parse(html);
		String caller = Thread.currentThread().getStackTrace()[Thread.currentThread().getStackTrace().length - 1]
				.getModuleName();
		log.info("Being called from: " + caller);

		return doc.toString();
	}

}
