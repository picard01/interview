package twice.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import twice.Dictionary;
import twice.Word;

public class DictionaryLemma implements Dictionary {
	private static final String FILE_PATH = "/files/";
	private static final String filePathDefault = "/files/lemma.txt";
	private String file;
	
	public DictionaryLemma(String... files) {
		if (files.length > 0) {
			this.file = files[0];
		}
	}
	
	@Override
	public void lookupWord(Word word) {
		String line = null;
		
		try {
			BufferedReader reader = getReader();
			
			while ((line = reader.readLine()) != null && !line.equals("")) {
				String[] sanitizedLine = line.replaceAll("[\\p{Punct}]+", "").trim().split("\\s+");
				
				for (String lineWord : sanitizedLine) {
					
					if (word.compareWord(lineWord)) {
						word.updateWordList(lineWord);
					}
				}
			}
			reader.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BufferedReader getReader() throws Exception {
		InputStream inStream = null;
		BufferedReader reader = null;
		
		try {
			String filePath = FILE_PATH + file;
			
			inStream = DictionaryLemma.class.getResourceAsStream(filePath);
			reader = new BufferedReader(new InputStreamReader(inStream));
		} catch (Exception e) {
			inStream = DictionaryLemma.class.getResourceAsStream(filePathDefault);
			reader = new BufferedReader(new InputStreamReader(inStream));
		}
		return reader;
	}
	
}
