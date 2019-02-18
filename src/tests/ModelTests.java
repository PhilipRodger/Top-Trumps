package tests;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import model.CardPile;
import model.Deck;
import model.Game;
import view.CommandLineView;

class ModelTests {

	Deck deck;
	Game game;
	CommandLineView command;

	@Test
	void GameWonTest() {
		fail("Not yet implemented");
	}

	@Test
	void RoundWonTest() {
		fail("Not yet implemented");
	}

//	@Test
//	void Checkplayers() {
//		assertEquals(5, command.);
//	}

	@Test
	void PlayerLostTest() {

	}

	@Test
	void DeckSizeTest() {
		// check if deck is empty
		deck.shuffleDeck();
		CardPile shuffled = deck;

		if (shuffled.size() == 0) {
			fail("The deck is empty");
		}
		// test deck "StarCitizenDeck" is 40 cards in size
		if (shuffled.size() == 40) {
			assert true;
		} else {
			assert false;
			if (shuffled.size() < 40) {
				System.out.println("Not all cards loaded");
			} else {
				System.out.println("Trying to load too many cards");
			}
		}
	}
}
