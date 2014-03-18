package twice.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import twice.Word;

public class WordJumble implements Word {
	private String word;
	private Map<Character, Integer> wordChars;
	private Map<Character, Integer> wordCharsDefault;
	private Set<String> wordMatch;
	
	public WordJumble(String word) {
		this.word = word;
		this.wordChars = new HashMap<Character, Integer>();
		this.wordCharsDefault = new HashMap<Character, Integer>();
		this.wordMatch = new TreeSet<String>();
		
		letterize(word);
	}

	@Override
	public void letterize(String dictWord) {
		
		if (word != null) {
			
			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
				
				try {
					wordCharsDefault.put(ch, wordCharsDefault.get(ch).intValue() + 1);
				} catch (NullPointerException npe) {
					wordCharsDefault.put(ch, 1);
				}
			}
			reset();
		}
	}

	@Override
	public boolean compareWord(String dictWord) {
		boolean result = true;
		
		for (int i = 0; i < dictWord.length(); i++) {
			char ch = dictWord.charAt(i);
			Integer count = wordChars.get(ch);
			
			if (count == null || (--count < 0)) {
				result = false;
				break;
			}
			wordChars.put(ch, count);
		}
		reset();
		
		return result;
	}
	
	private void reset() {
		wordChars.putAll(wordCharsDefault);
	}
	
	@Override
	public void updateWordList(String dictWord) {
		wordMatch.add(dictWord);
	}

	@Override
	public int getTotalNumberOfMatches() {
		return wordMatch.size();
	}
	
	@Override
	public String printResults() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		char lastChar = ' ';
		boolean firstWord = true;
		boolean lineIsFull = false;
		boolean differentLastChar = false;
		
		for (Iterator<String> resultIter = wordMatch.iterator(); resultIter.hasNext();) {
			String word = resultIter.next();
			
			lineIsFull = (count % 8) == 0;
			differentLastChar = word.charAt(0) != lastChar;
			
			if (!firstWord) {
				
				if (differentLastChar) {
					sb.append("\n--------------\n");
					count = 0;
				} else if (lineIsFull) {
					sb.append("\n");
					count = 0;
				} else {
					sb.append(", ");
				}
			}
			sb.append(word.trim());
			
			count++;
			firstWord = false;
			lastChar = word.charAt(0);
		}
		return sb.toString();
	}
}
