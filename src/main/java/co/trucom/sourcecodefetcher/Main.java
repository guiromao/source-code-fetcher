package co.trucom.sourcecodefetcher;

public class Main {

	public static void main(String [] args) {
		String sourceCode = SourceFetcher.getSourceOf("https://www.sapo.pt", true);

		System.out.println(sourceCode);
	}

}
