package model;

public class Database {
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
	 * Creates an objected containing statistical information about games stored in
	 * the database these are then used by views to display this data to the user.
	 *
	 * @return contains overall statistics over all the games played.
	 */
	public DatabaseResponse getDatabaseStats() {
		DatabaseResponse responce = new DatabaseResponse();
		
		//TODO make queries to the database and update the response with that info.
		
		return responce;
	}

}
