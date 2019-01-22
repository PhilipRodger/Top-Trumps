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
	 * @param deck representation of the deck to write to log file.
	 */
	public void writeDeck(Deck deck) {
		// TODO
	}

	/**
	 * Opens the output file and writes a representation of each players deck with a
	 * title indicating who's deck is which.
	 *
	 * @param players is a list of players playing the game and who's decks are to
	 *                be written to the log file.
	 */
	public void writePlayersDecks(Player[] players) {
		// TODO
	}

	/**
	 * Opens the output file and writes a representation of the pile of cards given
	 * to it, this is needed to show contents of the communal pile, and cards in
	 * play.
	 *
	 * @param cardPile is a pile of cards to be written to the log file
	 */
	public void writeCardPile(CardPile cardPile) {
		// TODO
	}

	/**
	 * Opens the output file and writes the currently selected category for the round
	 *
	 * @param cardPile is the current cards in play.
	 * @param selection is the selected category for the round.
	 */
	public void writeCurrentCatagory(CardPile cardPile, int selection) {
		// TODO
	}
	
	/**
	 * Opens the output file and writes who the winner of the game was.
	 *
	 * @param winner is whoever won that game.
	 */
	public void writeWinner(Player winner) {
		// TODO
	}

}
