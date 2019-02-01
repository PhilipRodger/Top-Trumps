package model;

import java.util.ArrayList;
import java.util.Random;

import listeners.ShowComputerTurnListener;
import listeners.UserDrewRoundListener;
import listeners.UserLostGameListener;
import listeners.UserLostRoundListener;
import listeners.UserOutOfGameListener;
import listeners.UserTurnListener;
import listeners.UserWonGameListener;
import listeners.UserWonRoundListener;

public class TopTrumpsModel {
	private String DECK_LOCATION = "StarCitizenDeck.txt";
	private int numberOfPlayers;
	private Player[] players;
	private Deck deck;
	private Database db = new Database();

	// Listeners that update view.
	// Game end
	UserWonGameListener userWonGameListener;
	UserLostGameListener userLostGameListener;
	// User out of the game
	UserOutOfGameListener userOutOfGameListener;
	// Round status
	UserWonRoundListener userWonRoundListener;
	UserDrewRoundListener userDrewRoundListener;
	UserLostRoundListener userLostRoundListener;

	// Holds the listeners to apply to the players when created.
	// When it is the user's turn
	private UserTurnListener userTurnListener;
	// When it is the computer's turn
	private ShowComputerTurnListener computerTurnListener;

	// Game Specific Variables
	private CardPile communityPile;
	private TestFile testFile;
	private GameStatistics stats;

	// Round Specific Variables
	private int playersTurn;
	private int nextPlayer;
	private int numberOfSurvingPlayers;
	private Card firstCard;
	private CardPile roundPile;
	private int chosenCategory;
	private Player roundWinner;

	// Potential next actions
	private boolean newGamePossible = true;
	private boolean viewStatsPossible = true;
	private boolean resolveComputerTurnPossible = false;
	private boolean resolveUserTurnPossible = false;
	private boolean nextTurnPossible = false;
	private boolean autoResolveMode = false;

	public void setTestFile(TestFile testFile) {
		this.testFile = testFile;
	}

	public TopTrumpsModel(int numberOfPlayers) {
		// set up the objects needed to play any number of games
		this.numberOfPlayers = numberOfPlayers;
		deck = new Deck(DECK_LOCATION);
	}
	
	
	
	// Actions callable by the controller to progress game.
	public void playGame() {
		// A single game of Top-Trumps
		// initialisation for a single game
		createPlayers(numberOfPlayers);
		dealDeck();

		stats = new GameStatistics(players);
		randomlyChoseStartingPlayer();
		communityPile = new CardPile(); 
		startRound();
	}

	// Private Helper Methods
	private void createPlayers(int numberOfPlayers) {
		// initialised players first player is the human the rest are AI

		players = new Player[numberOfPlayers];

		// Make first player the human player.
		if (numberOfPlayers > 0) {
			players[0] = new HumanPlayer(this);
		}

		// Make the rest Computer controlled players.
		for (int i = 1; i < numberOfPlayers; i++) {
			players[i] = new ComputerPlayer(this);
		}

		// Add view specific listners.
		addTurnListners();
	}

	private void dealDeck() {
		CardPile shuffled = deck.getShuffledDeck();					
		

		while (shuffled.hasNextCard()) {
			for (Player player : players) {
				if (shuffled.hasNextCard()) {
					player.addCardToBottomOfPile(shuffled.drawCard());
				}
			}
		}
	}

	public void startRound() {
		// A single round in a game!
		nextTurnPossible = false;
		
		if (roundWinner != null) {
			// If not a draw then add the communal pile to the winners deck
			for (Player player : players) {
				if (roundWinner == player) {
					player.addCardPileToBottom(communityPile);
					playersTurn = getIndexOfPlayer(roundWinner); // the winner of a round should pick the next round.
				}
			}
		}

		// The player's who's turn this is should pick up their card.
		firstCard = players[playersTurn].playersDrawPhase();

		// Community pile should persist between rounds in case of a draw
		communityPile.addCard(firstCard);

		// Refresh round pile, this pile is to make it easy to identify who/if anyone
		// won a round.
		roundPile = new CardPile();
		roundPile.addCard(firstCard);
	}

	public Card getFirstCard() {
		return firstCard;
	}
	
	private void winnerOfRound() {
		// sets the round winner to the one who has won the round or null if it was a
		// draw.
		int maxSeen = -1;
		boolean draw = false;
		Player winner = null;
		ArrayList<Card> roundPileCopy = roundPile.getListRepresentation();
		

		for (int i = 0; i < roundPileCopy.size(); i++) {
			Card toCompare = roundPileCopy.get(i);
			if (toCompare.getValue(chosenCategory) > maxSeen) {
				maxSeen = toCompare.getValue(chosenCategory);
				draw = false;
				winner = toCompare.getOwner();
			} else if (toCompare.getValue(chosenCategory) == maxSeen) {
				draw = true;
				winner = null;
			}
		}
		roundWinner = winner;
	}

	private void incrementPlayer() {
		nextPlayer = (nextPlayer + 1) % numberOfPlayers;
	}

	private void randomlyChoseStartingPlayer() {
		Random r = new Random();
		int randomNumber = r.nextInt(numberOfPlayers);
		playersTurn = randomNumber;
	}

	private int getIndexOfPlayer(Player p) {
		for (int i = 0; i < players.length; i++) {
			if (p == players[i]) {
				return i;
			}
		}
		return -1;
	}

	
	private void resetGameState() {
		// Reset potential next actions so that only new game, or view stats are
		// possible
		newGamePossible = true;
		viewStatsPossible = true;
		resolveComputerTurnPossible = false;
		resolveUserTurnPossible = false;
		nextTurnPossible = false;
	}

	private void displayTurnResolution() {
		// Communicates to the view what the result of the round was.

		if (gameOver()) {
			// Game is over
			resetGameState();
			db.writeGameStatistics(stats);
			if (userWonGame()) {
				userWonGameListener.UserWonGame();

			} else if (userLostGame()) {
				userLostGameListener.UserLostGame();
			}

		} else if (userOutOfGame()) {
			userOutOfGameListener.UserOutOfGame();
		} else {
			// What to do if game is still ongoing:
			// Work out if it was a win, loss, or draw for the round
			nextTurnPossible = true;
			if (userWonRound()) {
				userWonRoundListener.UserWonRound();

			} else if (userLostRound()) {
				userLostRoundListener.UserLostRound();
			} else {
				// draw round
				userDrewRoundListener.UserDrewRound();
			}

		}
	}

	private boolean userWonRound() {
		if (roundWinner instanceof HumanPlayer) {
			return true;
		} else {
			return false;
		}
	}

	private boolean userLostRound() {
		if (roundWinner instanceof ComputerPlayer) {
			return true;
		} else {
			return false;
		}
	}

	private boolean gameOver() {
		// if game is over (less than 2 players have cards in their decks) return
		// true, else false
		int numberOfActivePlayers = 0;
		for (Player player : players) {
			if (player.inGame()) {
				numberOfActivePlayers++;
			}
		}
		if (numberOfActivePlayers < 2) {
			return true;
		}
		return false;
	}

	private boolean userWonGame() {
		for (Player player : players) {
			if (player instanceof ComputerPlayer) {
				if (player.inGame()) {
					// Able to find a computer player still in the game.
					return false;
				}
			}
		}
		// Unable to find a computer player still in the game so user has won.
		return true;
	}

	private boolean userLostGame() {
		int numberOfActivePlayers = 0;
		for (Player player : players) {
			if (player.inGame()) {
				numberOfActivePlayers++;
			}
		}
		if (numberOfActivePlayers < 2 && !userWonGame()) {
			return true;
		}
		return false;
	}

	private boolean userOutOfGame() {
		for (Player player : players) {
			if (player instanceof HumanPlayer) {
				if (player.inGame()) {
					// Able to find a human player still in the game so user has not lost.
					return false;
				}
			}
		}
		// Unable to find a human player still in the game so user must have lost.
		return true;
	}

	public void nextCatagory() {
		// User has confirmed they want the computer to finish it's turn, this will
		// cause the computer player to chose a random category.
		resolveComputerTurnPossible = false;
		players[playersTurn].chosenCategory(players[playersTurn].getCatagoryChoice());

	}

	public void userSelection(int selectedCategory) {
		// User has confirmed their selected category.
		resolveUserTurnPossible = false;
		players[playersTurn].chosenCategory(selectedCategory);
	}

	public void resolveRound() {
		// Actions that happen after a player has made their choice of category for the
		// round and contributed to the communal pile.

		// A loop over all other players in the game.
		nextPlayer = playersTurn;
		incrementPlayer();
		while (nextPlayer != playersTurn) {
			if (players[nextPlayer].inGame()) {
				// The player is in the game so they should draw a card and add to the communal
				// and round pile.

				Card drawnCard = players[nextPlayer].drawCard();
				communityPile.addCard(drawnCard);
				roundPile.addCard(drawnCard);
			}

			incrementPlayer();
		}
		
		// All player's cards on pile, need to work out who won.
		winnerOfRound();

		// If a draw do nothing with community pile, and next player will stay the same.
		displayTurnResolution();
	}
	

	public void setCategoryChoice(int choice) {
		chosenCategory = choice;
	}
	
	public String getCategoryChoice() {
		return Card.getCategories()[chosenCategory];
	}
	
	public CardPile getRound() {
		return roundPile;
	}
	
	public int getCommunityPileSize() {
		return communityPile.size();
	}

	
	
	
	// To communicate valid ways the user can communicate with the game.
	public boolean newGamePossible() {
		return newGamePossible;
	}

	public void setNewGamePossible(boolean newGamePossible) {
		this.newGamePossible = newGamePossible;
	}

	public boolean viewStatsPossible() {
		return viewStatsPossible;
	}

	public void setViewStatsPossible(boolean viewStatsPossible) {
		this.viewStatsPossible = viewStatsPossible;
	}

	public boolean resolveComputerTurnPossible() {
		return resolveComputerTurnPossible;
	}

	public void setResolveComputerTurnPossible(boolean resolveComputerTurnPossible) {
		this.resolveComputerTurnPossible = resolveComputerTurnPossible;
	}

	public boolean resolveUserTurnPossible() {
		return resolveUserTurnPossible;
	}

	public void setResolveUserTurnPossible(boolean resolveUserTurnPossible) {
		this.resolveUserTurnPossible = resolveUserTurnPossible;
	}

	public boolean nextTurnPossible() {
		return nextTurnPossible;
	}
	
	// A lot of interfaces to update the view.
	public void addUserWonGameListener(UserWonGameListener listener) {
		this.userWonGameListener = listener;
	}

	public void addUserLostGameListener(UserLostGameListener listener) {
		this.userLostGameListener = listener;
	}

	public void addUserOutOfGameListener(UserOutOfGameListener listener) {
		this.userOutOfGameListener = listener;
	}

	public void addUserWonRoundListener(UserWonRoundListener listener) {
		this.userWonRoundListener = listener;
	}

	public void addUserDrewRoundListener(UserDrewRoundListener listener) {
		this.userDrewRoundListener = listener;
	}

	public void addUserLostRoundListener(UserLostRoundListener listener) {
		this.userLostRoundListener = listener;
	}

	public void addTurnListners() {
		for (Player player : players) {
			if (player instanceof HumanPlayer) {
				((HumanPlayer) player).addUserTurnListener(userTurnListener);
			} else if (player instanceof ComputerPlayer) {
				((ComputerPlayer) player).addComputerTurnListener(computerTurnListener);
			}
		}
	}

	public void addUserTurnListener(UserTurnListener listener) {
		this.userTurnListener = listener;
	}

	public void addShowComputerTurnListener(ShowComputerTurnListener listener) {
		this.computerTurnListener = listener;
	}

	public int getChosenCategory() {
		return chosenCategory;
	}
	
	public Player getWinner() {
		return roundWinner;
	}

	public void setToAutoResolve() {
		autoResolveMode = true;
		
	}

	public boolean isAutoResolve() {
		return autoResolveMode;
	}
}
