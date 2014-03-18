package twice;

import twice.impl.DictionaryLemma;
import twice.impl.WordJumble;

public class Main {
	private Dictionary dictionary;
	private WordJumble wordJumble;
	
	public Main(String... args) {
		wordJumble = new WordJumble(args[0]);
		dictionary = new DictionaryLemma((args.length > 1) ? args[1] : null);
	}
	
	public void checkWordJumble() {
		dictionary.lookupWord(wordJumble);
	}
	
	public void printResults() {
		System.out.println("Total number of matches: " + wordJumble.getTotalNumberOfMatches());
		System.out.println("-----------------------------------------");
		System.out.println(wordJumble.printResults());
		System.out.println("=======================================================================");
	}
	
	
	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("USAGE: Main <word to check> [<dictionary_file_inside_files_folder>]");
			System.out.println("Default file is lemma.txt");
			return;
		}
		Main main = new Main(args);
		
		main.checkWordJumble();
		main.printResults();
	}

}
