package model;

import java.sql.*;

public class Database {
 
	public Database() { //default constructor
		
	}
	/*
	 * Method that makes connection to database. Makes print statement to console to report
	 * connection status.
	 * 
	 */
	public Connection connectToDB() {
		final String username = "postgres";
		final String password = "6114680d";
		final String dbName = "jdbc:postgresql:topTrumps";
		Connection c = null;

		try {
			c = DriverManager.getConnection(dbName, username, password);

		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
			e.printStackTrace();
		}
		if (c != null) {
			System.out.println("Successfully connected to database.");
		} else {
			System.out.println("Failed to connect to database.");
		}
		return c;
	}

	/**
	 * Writes to the database the number of draws, who won the game, number of
	 * rounds in the game and how many rounds each player won.
	 *
	 * @param stats This object holds the statistics of a game of Top Trumps which
	 *              has completed.
	 */
	public void writeGameStatistics(GameStatistics gstats) {
		Player[] players = gstats.getPlayerArray();
		Player winner = gstats.getWinner();
		
		int gameID = 1; // game stats to be added to database each game
		int nDraws = gstats.getNumOfDraws(); //number of draws in the game
		int nRounds = gstats.getNumOfRounds(); //number of rounds in the game
		int gWinner = 0; 						//integer denoting which player won the game
		int p1RW = players[0].getRoundsWon(); //rounds won per player
		int p2RW = players[1].getRoundsWon();
		int p3RW = players[2].getRoundsWon();
		int p4RW = players[3].getRoundsWon();
		int p5RW = players[4].getRoundsWon();
		if (winner instanceof HumanPlayer) {
			gWinner = 1;
		} else if (winner instanceof ComputerPlayer) {
			if (winner == players[1]) {
				gWinner = 2;
			} else if (winner == players[2]) {
				gWinner = 3;
			} else if (winner == players[3]) {
				gWinner = 4;
			} else if (winner == players[4]) {
				gWinner = 5;
			}
		}
		gameID++;
		String gameStats = "INSERT INTO TopTrumpStats VALUES (" + gameID + ", " + nDraws + ", " + nRounds + ", "
				+ gWinner + ", " + p1RW + ", " + p2RW + ", " + p3RW + ", " + p4RW + ", " + p5RW + ");";

		insertStats(gameStats);
	}
	/**
	 * takes a string of game stats from the method writeGameStatistics()
	 * and uses the string to insert game stats into the database
	 * @param stats
	 */
	
	public void insertStats(String stats) {
		Statement stmt = null;
		Connection c = connectToDB();
		if (c != null) {
			try {
				stmt = c.createStatement();
				stmt.executeQuery(stats);
				stmt.close();
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * Creates an object containing statistical information about games stored in
	 * the database these are then used by views to display this data to the user.
	 *
	 * @return contains overall statistics over all the games played.
	 */
	public DatabaseResponse getDatabaseStats() {
		DatabaseResponse response = new DatabaseResponse();

		// TODO make queries to the database and update the response with that info.

		return response;
	}

}
