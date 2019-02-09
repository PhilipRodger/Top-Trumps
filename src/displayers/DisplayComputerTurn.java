package displayers;

import model.Round;

public interface DisplayComputerTurn {
	/**
	 * To be implemented and attached to the model via the view. Displays the
	 * computers turn: their decision, community pile size, before the other players
	 * have drawn their cards. Implemented by the view and called by the model.
	 * 
	 * @param currentRound Contains methods and data for the display to update.
	 */
	public void showComputerTurn(Round currentRound);

}
