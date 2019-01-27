package model;

import java.util.Random;

public abstract class Player {
	// player can be a human player or an AI.
	
	protected TopTrumpsModel model; // The model this player belongs to.
	private PlayersCards cards; // A player's deck
	private int catagoryChoice; // Holds the player's choice
	private int roundsWon; // Stored for one game
	private Card currentCardDrawn;
	
	
	
	public Player(TopTrumpsModel model) {
		this.model = model;
	}

	//The way they make decisions should be different.
	public void chosenCategory(int catagoryChoice) {
		//User or Computer has made a decision and resolve the round.
		this.catagoryChoice = catagoryChoice;
		model.resolveRound();
		
	}
	
	//Somethings should be done the same for human and AI players:
	public boolean inGame() {
		//TODO: if player has more cards in their pile they should be in game, else they have lost and false should be returned.
		return true;
	}
	public Card playersDrawPhase() {
		currentCardDrawn = cards.drawCard();
		showCard();
		return currentCardDrawn;
	}
	
	protected int justMakeARandomChoice() {
		Random r = new Random();
		return r.nextInt(Card.getCategories().length);
	}
	
	protected abstract void showCard();


	public Card drawCard() {
		return cards.drawCard();
	}
	
	public int getCatagoryChoice() {
		return catagoryChoice;
	}


	public void addCardPileToBottom(CardPile pileToAdd) {
		//TODO: code that adds a pile of cards to the players cards, used when a player wins a round
	}

	public void incrementRoundsWon() {
		roundsWon++;
	}

	public void resetNumRoundsWon() {
		roundsWon = 0;
	}

}
