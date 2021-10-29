package co.trucom.sourcecodefetcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {

	private static final Log log = LogFactory.getLog(Main.class.getClass());

	public static void main(String [] args) {
		String sourceCode = SourceFetcher.getSourceOf("https://www.sapo.pt", true);

		log.info(sourceCode);
	}

}
