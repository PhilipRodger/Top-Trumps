package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LogFile {
	private String testFileName;
	private String seperator = "\n\n----------\n";

	/**
	 * Creates a file for logging information about the internal state of the top
	 * trumps game, if the file already exists the file will be erased.
	 *
	 * @param testFileName a relative path for the log file to be created.
	 */
	public LogFile(String testFileName) {
		this.testFileName = testFileName + ".txt";

		// Create blank new file / overwrite existing file.
		try (BufferedWriter out = new BufferedWriter(new FileWriter(this.testFileName))) {
			out.write("----------------------------\n");
			out.write("--- Top Trumps Log File  ---\n");
			out.write("----------------------------\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void appendLogFile(String textToAppend) {

		// Writes passed String to the file.
		try (BufferedWriter out = new BufferedWriter(new FileWriter(testFileName, true))) {
			out.write(textToAppend);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the file made when this class is instantiated and writes a
	 * representation of both the before shuffled deck (order they appear in the
	 * deck file) and the shuffled deck.
	 *
	 * @param deck representation of the deck to write to log file.
	 */
	public void writeDeck(Deck deck) {
		StringBuilder sb = new StringBuilder();
		sb.append(seperator);
		sb.append("Deck Unshuffled: \n");
		List<Card> unshuffled = deck.getUnshuffledDeck();
		for (Card card : unshuffled) {
			sb.append(card.toString() + "\n");
		}
		sb.append(seperator);
		sb.append("Deck Shuffled: \n");
		appendLogFile(sb.toString());
		writeCardList(deck.getListRepresentation());
	}

	/**
	 * Opens the output file and writes a representation of each players deck with a
	 * title indicating who's deck is which.
	 *
	 * @param players is a list of players playing the game and who's decks are to
	 *                be written to the log file.
	 */
	public void writePlayersDecks(Player[] players) {
		appendLogFile(seperator);
		appendLogFile("Printing Players Decks: \n");
		for (Player player : players) {
			appendLogFile(String.format("\n%s's deck (%d cards):\n", player.getName(), player.getNumberOfCards()));
			writeCardList(player.getCardList());
		}
	}

	/**
	 * Opens the output file and writes a representation of the pile of cards given
	 * to it, this is needed to show contents of the communal pile, and cards in
	 * play.
	 *
	 * @param cardPile is a pile of cards to be written to the log file
	 */
	public void writeCardList(List<Card> cardList) {
		StringBuilder sb = new StringBuilder();
		for (Card card : cardList) {
			sb.append(card.toString() + "\n");
		}
		appendLogFile(sb.toString());
	}

	/**
	 * Opens the output file and writes who the winner of the game was.
	 *
	 * @param winner is whoever won that game.
	 */
	public void writeWinner(Player winner) {
		appendLogFile(seperator);
		appendLogFile(String.format("\n---------------- Winner Of Game %s ---------------", winner.getName()));
		appendLogFile(seperator);
	}

	public void writeRound(Round round) {
		appendLogFile(seperator);
		appendLogFile(String.format("\n---------------- Round %d ---------------", Round.getRoundNumber()));
		appendLogFile(seperator);

		Player roundWinner = round.getRoundWinner();
		String winnerName = "";
		if (roundWinner != null) {
			winnerName = roundWinner.getName();
		} else {
			winnerName = "No one!";
		}
		appendLogFile(String.format("\nPrinting Cards in Round (size %d):\n", round.getListOfCardsInRound().size()));
		writeCardList(round.getListOfCardsInRound());

		appendLogFile(String.format("\nIt was %s's turn, they selected %s and the winner was %s.\n",
				round.getPlayersTurn().getName(), Card.getCategories()[round.getChosenCategory()], winnerName));		
	}
	public void writeDecksAtEndOfRound(Round round) {
		appendLogFile(String.format("\nPrinting Community Pile (size %d):\n", round.getCommunityPile().size()));
		writeCardList(round.getCommunityPile().getListRepresentation());
		writePlayersDecks(round.getPlayers());
	}

}
