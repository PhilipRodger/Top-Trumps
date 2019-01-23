package model;

public abstract class Player {
	// player can be a human player or an AI.
	
	private PlayersCards cards; // A player's deck
	private int catagoryChoice; // Holds the player's choice
	private int roundsWon; // Stored for one game
	
	//The way they make decisions should be different.
	public abstract int chooseCatagory(Card c);
	
	
	//Somethings should be done the same for human and AI players:
	public boolean inGame() {
		//TODO: if player has more cards in their pile they should be in game, else they have lost and false should be returned.
		return true;
	}
	public Card playersTurn() {
		Card draw = cards.drawCard();
		catagoryChoice = chooseCatagory(draw);
		return draw;
	}
	
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
