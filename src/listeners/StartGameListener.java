package listeners;

public interface StartGameListener {

	/**
	 * Implemented by the controller and added to the view. Called when the user
	 * wishes to begin a new game.
	 * 
	 * @param numOfPlayers Total number of players including the user to start the
	 *                     game with.
	 */
	public void startNewGame(int numOfPlayers);
}
