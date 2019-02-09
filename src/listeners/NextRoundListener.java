package listeners;

public interface NextRoundListener {
	/**
	 * Implemented by the controller and added to the view. Called when the user has
	 * seen the outcome of a turn and wants the next turn to start.
	 */
	public void nextRound();
}
