package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Deck extends CardPile {
	// The deck is a special pile of cards, it should have the ability to create a
	// private representation of a cononical deck of cards from a file,
	// and refresh the it's pile of cards with a shuffled representation.
	private static List<Card> deck = new ArrayList<>();
	private Deque<Card> shuffleDeck = new ArrayDeque<Card>();
	private String fileName = "StarCitizenDeck.txt";

	public Deck(String fileName) {
		FileReader reader = null;

		try {

			reader = new FileReader(fileName);
			Scanner scanner = new Scanner(reader);
			String categoriesIncludingDescription[] = scanner.nextLine().split(" ");
			// removing the first category (description)
			String[] categories = new String[categoriesIncludingDescription.length - 1];
			for (int i = 1; i < categoriesIncludingDescription.length; i++) {
				categories[i-1] = categoriesIncludingDescription[i];
			}
			Card.setCategories(categories);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] array = line.split(" ");
				String description = array[0];
				int[] attributes = new int[array.length - 1];
				for (int i = 1; i < array.length; i++) {
					attributes[i-1] = Integer.parseInt(array[i]);
				}
				Card card = new Card(description, attributes);
				//addCard(card);
				System.out.println(card);
			}

			scanner.close();

		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}

	}

	public Deque<Card> getShuffledDeck() {

		Collections.shuffle(deck);
		shuffleDeck = new ArrayDeque<Card>(deck);

		return shuffleDeck;
	}
		
		

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
