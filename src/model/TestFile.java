package model;

public class TestFile {
	private String testFileName;
	private String seperator = "----------";

	/**
	 * Creates a file for logging information about the internal state of the top
	 * trumps game, if the file already exists the file will be erased.
	 *
	 * @param testFileName a relative path for the log file to be created.
	 */
	public TestFile(String testFileName) {
		this.testFileName = testFileName;
		// TODO Make a new empty text file
	}

	/**
	 * Opens the file made when this class is instantiated and writes a
	 * representation of both the before shuffled deck (order they appear in the
	 * deck file) and the shuffled deck.
	 *
	 * @param deck representation of the deck to write to  log file.
	 */
	public void writeDeck(Deck deck) {
		// TODO
	}
}
