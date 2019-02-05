package model;
/*
 * 	// makes frame view

	// public final MainFrameView view;

	public TrumpLog(final MainFrameView thatView) {
		view = thatView;
	}

	// log for when the game is over

	public void gameOverLog() {
		if (view != null) {
			view.log("Game Over!");
		}
	}

	public void logDraw() {
		if (view != null) {
			view.log("Draw!");
		}
	}

	public void playerLoss(final Player player) {
		if (view != null) {
			view.log(player.getName() + " Loses this game.");
		}
	}

	public void playerVictory(final Player player) {
		if (view != null) {
			view.log(player.getName() + "wins!");
		}
	}

	// logs when players wins

	// player who won the turn, index of player in array, player array of all
	// players //values of the turn

	public void logPlayerWonTurn(final Player player, final int playerIndex, final Player[] allPlayers,
			final int[] values) {
		if (view != null) {
			StringBuilder message = new StringBuilder();
			message.append(player.getName());
			message.append("wins, the winning card for this round was: ");
			message.append(player.lookupTopCard().getName());
			message.append(player.lookupTopCard().getValues());
			message.append("(").append(values[playerIndex]);
			message.append(") wins ");

			for (int i = 0; i < values.length; i++) {
				if (i != playerIndex) {
					message.append(allPlayers[i].getName()).append("s");
					message.append(allPlayers[i]).lookupTopCard().getName();
					message.append("(").append(values[i]).append("), ");

				}

			}
			view.log(message.substring(0, message, length() - 2));

		}

	}

	// logs unknown error

	public void logUnkownError(final Exception e) {

		if (view != null) {
			view.logError("An unkown error occured.");
			if (e.getMessage() != null) {
				view.logError(e.getMessage());
			}
		}
	}
 */

public class LogFile {
	private String testFileName;
	private String seperator = "----------";

	/**
	 * Creates a file for logging information about the internal state of the top
	 * trumps game, if the file already exists the file will be erased.
	 *
	 * @param testFileName a relative path for the log file to be created.
	 */
	public LogFile(String testFileName) {
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
