package model;

import java.util.List;
import java.util.Random;

public abstract class Player {
	// player can be a human player or an AI.
	
	private PlayersCards cards; // A player's deck
	private int roundsWon; // Stored for one game
	protected Card currentCardDrawn;
	protected String name = "";
	protected Game game;
	
	
	
	public Player(Game game) {
		cards = new PlayersCards(this);
		this.game = game;
	}

	
	//Somethings should be done the same for human and AI players:
	public boolean inGame() {
		// If player has more cards in their pile they should be in game, else they have lost and false should be returned.
		if (cards.hasNextCard()) {
			return true;
		}
		return false;
	}
	
	public Card playersDrawPhase() {
		currentCardDrawn = cards.drawCard();
		return currentCardDrawn;
	}
	
	protected int justMakeARandomChoice() {
		Random r = new Random();
		return r.nextInt(Card.getCategories().length);
	}
	

	protected abstract void showCard(Round currentRound);


	public Card drawCard() {
		return cards.drawCard();
	}
	
	public void addCardToBottomOfPile(Card c) {
		cards.addCard(c);
	}
	
	
	public int getRoundsWon() {
		return roundsWon;
	}


	public void addCardPileToBottom(CardPile pileToAdd) {
		//TODO: code that adds a pile of cards to the players cards, used when a player wins a round
		while(pileToAdd.hasNextCard()) {
			addCardToBottomOfPile(pileToAdd.drawCard());
		}
	}

	public void incrementRoundsWon() {
		roundsWon++;
	}

	public String getName() {
		return name;
	}
	
	public int getNumberOfCards() {
		return cards.size();
	}
	
	public List<Card> getCardList(){
		return cards.getListRepresentation();
	}

}
