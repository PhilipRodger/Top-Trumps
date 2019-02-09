package displayers;

import model.Round;

public interface DisplayUserLostRound {
	/**
	 * To be implemented and attached to the model via the view. Called by the model
	 * when the user has lost a round.
	 * 
	 * @param currentRound Contains methods and data for the display to update.
	 */
	public void showUserLostRound(Round currentRound);

}
