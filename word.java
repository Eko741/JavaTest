
class WordSet {
	String wordSet[][]; // x = words, y = attributes
	int NOA;
	int NOW;
	
	WordSet(String[][] wordSet) {
		this.wordSet = wordSet;
		this.NOA = wordSet[0].length;
		this.NOW = wordSet.length;
	}
}
