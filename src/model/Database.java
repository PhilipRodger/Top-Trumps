package model;
import java.sql.*;

public class Database {
	private Connection connection = null; //Variable for connecting to database
	
	
	
	public Database() { //default constructor
		
	}
	
	public boolean connectedToDB() {
		//database information
		String username = "postgres";
		String password = "6114680d";
		String dbName = "jdbc:postgresql:topTrumps";
		
		try {
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * Writes to the database the number of draws, who won the game, number of
	 * rounds in the game and how many rounds each player won.
	 *
	 * @param stats This object holds the statistics of a game of Top Trumps which
	 *              has completed.
	 */
	public void writeGameStatistics(GameStatistics stats) {
		//TODO write stats to database.
	}

	/**
	 * Creates an object containing statistical information about games stored in
	 * the database these are then used by views to display this data to the user.
	 *
	 * @return contains overall statistics over all the games played.
	 */
	public DatabaseResponse getDatabaseStats() {
		DatabaseResponse response = new DatabaseResponse();
		
		//TODO make queries to the database and update the response with that info.
		
		return response;
	}

}
