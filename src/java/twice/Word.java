package twice;

public interface Word {
	
	public void letterize(String dictWord);
	
	public boolean compareWord(String dictWord);
	
	public void updateWordList(String dictWord);
	
	public int getTotalNumberOfMatches();
	
	public String printResults();
	
}
