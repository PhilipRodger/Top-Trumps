package model;

import java.sql.*;

public class Database {
	private Connection c = null; // connection attribute for connecting to database

	public Database() { // default constructor

	}

	/*
	 * Method that makes connection to database. Makes print statement to console to
	 * report connection status.
	 * 
	 */
	public Connection connectToDB() {
		final String username = "postgres";
		final String password = "6114680d";
		final String dbName = "jdbc:postgresql:topTrumps";

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbName, username, password);
			
		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
			//e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			//ex.printStackTrace();
		}
		if (c != null) {
			System.out.println("Successfully connected to database.");
		} else {
			System.out.println("Failed to connect to database.");
		}
		return c;
	}

	/**
	 * Severs connection to database
	 */
	public void disconnectDB() {
		try {
			c.close();

		} catch (Exception e) {
			System.out.println("You could not disconnect from the database.");
			//e.printStackTrace();
		}
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

		int gameID = getTotalGamesPlayed() + 1; // game stats to be added to database each game, first game in database has
						// gameID of 1
		int nDraws = gstats.getNumOfDraws(); // number of draws in the game
		int nRounds = gstats.getNumOfRounds(); // number of rounds in the game
		int gWinner = 0; // integer denoting which player won the game. if 1, human player wins, beyond 1
							// denotes a computer victory
		int p1RW = players[0].getRoundsWon(); // rounds won per player
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
		String gameStats = "INSERT INTO Game VALUES (" + gameID + ", " + nDraws + ", " + nRounds + ", " + gWinner + ", "
				+ p1RW + ", " + p2RW + ", " + p3RW + ", " + p4RW + ", " + p5RW + ");";

		insertStats(gameStats);
	}

	/**
	 * takes a string of game stats from the method writeGameStatistics() and uses
	 * the string to insert game stats into the database
	 * 
	 * @param stats
	 */

	public void insertStats(String stats) {
		Statement stmt = null;
		if (c != null) {
			try {
				stmt = c.createStatement();
				stmt.executeUpdate(stats);
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Queries database to get total number of games played.
	 * 
	 * @return total number of games played
	 */
	public int getTotalGamesPlayed() {
		Statement stmt = null;
		String sql = "Select Count(gameID) as totalGames From Game";
		int totalGamesPlayed = 0;

		try {
			stmt = c.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				totalGamesPlayed = results.getInt("totalGames");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalGamesPlayed;

	}

	/**
	 * Queries database to get total computer wins.
	 * 
	 * @return total number of games played
	 */
	public int getTotalComputerWins() {
		Statement stmt = null;
		String sql = "Select Count(game_winner) as computerWins From Game Where game_winner > 1 ";
		int totalComputerWins = 0;

		try {
			stmt = c.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				totalComputerWins = results.getInt("computerWins");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalComputerWins;

	}

	/**
	 * Queries database to get total human wins.
	 * 
	 * @return total number of games played
	 */
	public int getTotalHumanWins() {
		Statement stmt = null;
		String sql = "SELECT COUNT(game_winner) as humanWins FROM Game WHERE game_winner = 1 ";
		int totalHumanWins = 0;

		try {
			stmt = c.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				totalHumanWins = results.getInt("humanWins");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalHumanWins;

	}

	/**
	 * Queries database to get average draws per game.
	 * 
	 * @return total number of games played
	 */
	public int getAverageDrawsPerGame() {
		Statement stmt = null;
		String sql = "SELECT AVG(total_draws) as avgDraws FROM Game ";
		int averageDraws = 0;

		try {
			stmt = c.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				averageDraws = results.getInt("avgDraws");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return averageDraws;

	}

	/**
	 * Queries database to get largest number of rounds played in a game.
	 * 
	 * @return total number of games played
	 */
	public int getLargestNumberOfRounds() {
		Statement stmt = null;
		String sql = "SELECT MAX(total_rounds) as maxTotalRounds FROM Game ";
		int largestNumberofRounds = 0;

		try {
			stmt = c.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				largestNumberofRounds = results.getInt("maxTotalRounds");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return largestNumberofRounds;

	}

	/**
	 * Creates an object containing statistical information about games stored in
	 * the database these are then used by views to display this data to the user.
	 *
	 * @return contains overall statistics over all the games played.
	 */
	public DatabaseResponse getDatabaseStats() {
		DatabaseResponse response = new DatabaseResponse();
		int totalGamesPlayed = getTotalGamesPlayed();
		int totalComputerWins = getTotalComputerWins();
		int totalHumanWins = getTotalHumanWins();
		int averageDrawsPerGame = getAverageDrawsPerGame();
		int largestNumberOfRounds = getLargestNumberOfRounds();

		response.setTotalGamesPlayed(totalGamesPlayed);
		response.setTotalComputerWins(totalComputerWins);
		response.setTotalHumanWins(totalHumanWins);
		response.setAverageDrawsPerGame(averageDrawsPerGame);
		response.setLargestNumberOfRounds(largestNumberOfRounds);

		return response;

	}

}
