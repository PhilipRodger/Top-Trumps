package model;

import java.util.Random;

import displayers.DisplayComputerTurn;
import displayers.DisplayUserDrewRound;
import displayers.DisplayUserLostGame;
import displayers.DisplayUserLostRound;
import displayers.DisplayUserOutOfGame;
import displayers.DisplayUserTurn;
import displayers.DisplayUserWonGame;
import displayers.DisplayUserWonRound;

public class Game {
	private static final String DECK_LOCATION = "StarCitizenDeck.txt";
	private int numOfPlayers;

	private int playersTurn;
	private Player[] players;
	private Deck deck;
	private CardPile communityPile;
	private TestFile testFile;
	private GameStatistics stats;
	private Round round;
	private Database db;

	// Potential next actions
	private boolean resolveComputerTurnPossible = false;
	private boolean resolveUserTurnPossible = false;
	private boolean nextTurnPossible = true;
	private boolean autoResolveMode = false;

	
	// Create a new game;
	public Game(int numOfPlayers, Database db) {
		this.numOfPlayers = numOfPlayers;
		this.db = db;
	}

	public void writeTestFile() {
		testFile = new TestFile("LogFile.txt");
	}

	
	// Actions to progress game.
	public void startGame() {
		// A single game of Top-Trumps
		// initialisation for a single game
		deck = new Deck(DECK_LOCATION);

		createPlayers(numOfPlayers);
		dealDeck();

		stats = new GameStatistics(players);
		randomlyChoseStartingPlayer();
		communityPile = new CardPile();
		startRound();
	}

	public void startRound() {
		if (nextTurnPossible) {
			round = new Round(this);
			stats.incrementRoundCounter();

			round.addDisplayUserWonRound(displayUserWonRound);
			round.addDisplayUserDrewRound(displayUserDrewRound);
			round.addDisplayUserLostRound(displayUserLostRound);

			round.startRound(players[playersTurn]);
		}
	}

	public void userSelection(int selectedCategory) {
		if (resolveUserTurnPossible) {
			setCategoryChoice(selectedCategory);
			resolveRound();
		}
	}

	public void nextCategory() {
		// User has confirmed they want the computer to finish it's turn.
		if (resolveComputerTurnPossible) {
			resolveComputerTurnPossible = false;
			resolveRound();	
		}
	}

	
	// Get info about the game.
	public void setCategoryChoice(int categoryChoice) {
		round.setCategoryChoice(categoryChoice);
	}
	
	public int getIndexOfPlayer(Player p) {
		for (int i = 0; i < players.length; i++) {
			if (p == players[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public void setPlayersTurn(int playersTurn) {
		this.playersTurn = playersTurn;
	}

	public int getCommunityPileSize() {
		return communityPile.size();
	}

	public void incrementNumOfDraws() {
		stats.incrementDrawCounter();
	}
	
	public boolean gameOver() {
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

	public void displayGameOverScreen() {
		// Game is over
		stats.setWinner(getRoundWinner());
		db.connectToDB();
		db.writeGameStatistics(stats);
		if (userWonGame()) {
			displayUserWonGame.showUserWonGame(this);

		} else if (userLostGame()) {
			displayUserLostGame.showUserLostGame(this);
		}
	}

	public void displayUserOutOfGameScreen() {
		displayUserOutOfGame.showUserOutOfGame(this);
	}

	public int getChosenCategory() {
		return round.getChosenCategory();
	}

	public Player getRoundWinner() {
		return round.getRoundWinner();
	}

	public void setToAutoResolve() {
		autoResolveMode = true;

	}

	public boolean inAutoResolve() {
		return autoResolveMode;
	}

	public Player[] getPlayers() {
		return players;
	}

	public int getPlayersTurn() {
		return playersTurn;
	}

	public void addCommunityCard(Card firstCard) {
		communityPile.addCard(firstCard);
	}

	public CardPile getCommunityPile() {
		return communityPile;
	}

	// Methods which get the possible next user actions:
	public boolean resolveComputerTurnPossible() {
		return resolveComputerTurnPossible;
	}

	public boolean resolveUserTurnPossible() {
		return resolveUserTurnPossible;
	}

	public boolean nextTurnPossible() {
		return nextTurnPossible;
	}

	// Methods to change the next possible user actions:
	public void setResolveComputerTurnPossible(boolean resolveComputerTurnPossible) {
		this.resolveComputerTurnPossible = resolveComputerTurnPossible;
	}

	public void setResolveUserTurnPossible(boolean resolveUserTurnPossible) {
		this.resolveUserTurnPossible = resolveUserTurnPossible;
	}

	public void setNextTurnPossible(boolean b) {
		nextTurnPossible = b;
	}

	// Helper methods
	private void createPlayers(int numOfPlayers) {
		// initialised players first player is the human the rest are AI

		players = new Player[numOfPlayers];

		// Make first player the human player.
		if (numOfPlayers > 0) {
			players[0] = new HumanPlayer(this);
		}

		// Make the rest Computer controlled players.
		for (int i = 1; i < numOfPlayers; i++) {
			players[i] = new ComputerPlayer(this);
		}

		// Add view specific listeners.
		addTurnDisplayers();
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

	private void randomlyChoseStartingPlayer() {
		Random r = new Random();
		int randomNumber = r.nextInt(players.length);
		playersTurn = randomNumber;
	}

	private void resolveRound() {
		round.resolveRound();
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

	public boolean userOutOfGame() {
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

	// Displayers that update view.
	private DisplayUserWonGame displayUserWonGame;
	private DisplayUserLostGame displayUserLostGame;
	private DisplayUserOutOfGame displayUserOutOfGame;
	private DisplayUserWonRound displayUserWonRound;
	private DisplayUserDrewRound displayUserDrewRound;
	private DisplayUserLostRound displayUserLostRound;
	private DisplayComputerTurn displayComputerTurn;
	private DisplayUserTurn displayUserTurn;

	public void addDisplayUserWonGame(DisplayUserWonGame displayer) {
		this.displayUserWonGame = displayer;
	}

	public void addDisplayUserLostGame(DisplayUserLostGame displayer) {
		this.displayUserLostGame = displayer;
	}

	public void addDisplayUserOutOfGame(DisplayUserOutOfGame displayer) {
		this.displayUserOutOfGame = displayer;
	}

	public void addDisplayUserWonRound(DisplayUserWonRound displayer) {
		this.displayUserWonRound = displayer;
	}

	public void addDisplayUserDrewRound(DisplayUserDrewRound displayer) {
		this.displayUserDrewRound = displayer;
	}

	public void addDisplayUserLostRound(DisplayUserLostRound displayer) {
		this.displayUserLostRound = displayer;
	}

	public void addDisplayUserTurn(DisplayUserTurn displayer) {
		this.displayUserTurn = displayer;
	}

	public void addDisplayComputerTurn(DisplayComputerTurn displayer) {
		this.displayComputerTurn = displayer;
	}

	public void addTurnDisplayers() {
		for (Player player : players) {
			if (player instanceof HumanPlayer) {
				((HumanPlayer) player).addDisplayUserTurn(displayUserTurn);
			} else if (player instanceof ComputerPlayer) {
				((ComputerPlayer) player).addDisplayComputerTurn(displayComputerTurn);
			}
		}
	}


}
