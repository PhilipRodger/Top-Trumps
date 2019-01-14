package model;

import java.util.List;

public class Deck extends CardPile{
	// The deck is a special pile of cards, it should have the ability to create a private representation of a cononical deck of cards from a file,
	// and refresh the it's pile of cards with a shuffled representation.
	private List canonicalDeck;

	public Deck(String fileName) {
		//TODO: Make a cannonical deck out of a file
	}
	
	public void refreshDeck() {
		//TODO: Refreshes the current pile with a shuffled version of the cannonical deck
		// the idea is to store the cards in the cannonical deck so that the deck file does not need to be read each game.
	}
}
