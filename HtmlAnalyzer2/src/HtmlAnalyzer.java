
public class HtmlAnalyzer {

	private int serialNumber;
	private String word;
	private int count;
	
	public HtmlAnalyzer() {
		
	}

	public HtmlAnalyzer(int serialNumber, String word, int count) {
		super();
		this.serialNumber = serialNumber;
		this.word = word;
		this.count = count;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
