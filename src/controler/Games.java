package controler;

import model.Deck;
import model.Player;
import model.RequestDecision;

public class Games {
	private RequestDecision view;
	
	private String DECK_LOCATION = "StarCitizenDeck.txt";
	private Player[] players;
	private Deck deck;

	public Games(int numberOfPlayers) {
		//set up the objects needed to play any number of games
		createPlayers(numberOfPlayers);
		deck = new Deck(DECK_LOCATION);
		
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			playGame();
			userWantsToQuit=true; // use this when the user wants to exit the game			
		}
	}

	private void playGame() {
		// A single game of Top-Trumps
		// initialisation for a single game
		
		// game play loop
		while (!gameOver()) {
			playRound();
		}
		// what to do if someone one wins
		
	}

	private void playRound() {
		// TODO A single round in a game!
		
	}

	private void createPlayers(int numberOfPlayers) {
		// TODO initialised players first player is the human the rest are AI
	}
	
	private boolean gameOver() {
		// TODO if game is over (less than 2 players have cards in their decks) return true, else false
		// hint player has a method for this
		return false;
	}
	
}
