package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Deck extends CardPile {
	 // The deck is a special pile of cards, it should have the ability to create a
	// private representation of a cononical deck of cards from a file,
	// and refresh the it's pile of cards with a shuffled representation.
	private static List<Card> deck = new ArrayList<>();
	private String fileName = "StarCitizenDeck.txt";
	

	public Deck(String fileName) throws FileNotFoundException {
		FileReader reader = null;

		try {

			reader = new FileReader(fileName);
			Scanner scanner = new Scanner(reader);

			String line = scanner.nextLine();
			String[] array = line.split(" ");
			for (int i = 0; i < 5; i++) {

				while (scanner.hasNextLine()) {

					String description = array[0];
					int size = Integer.parseInt(array[1]);
					int speed = Integer.parseInt(array[2]);
					int range = Integer.parseInt(array[3]);
					int firepower = Integer.parseInt(array[4]);
					int cargo = Integer.parseInt(array[5]);

					Card card = new Card(description, size, speed, range, firepower, cargo);
					deck.add(card);

				}

			}

			scanner.close();

			System.out.println(deck.toString());

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

	public static ArrayList<Card> getShuffledDeck() {
		Collections.shuffle(deck);
		ArrayList<Card> shuffledDeck = new ArrayList<Card>();
		shuffledDeck = (ArrayList<Card>) deck;
		return shuffledDeck;

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
