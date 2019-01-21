package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Card;

class CardTest {

	@Test
	void testMakeCard() {
		String name = "Bob";
		int[] values = {1,2,3};
		Card card = new Card(name, values);
		assertEquals("Bob", card.getName());
		assertEquals(1, card.getValue(0));
		assertEquals(2, card.getValue(1));
		assertEquals(3, card.getValue(2));

	}

}
