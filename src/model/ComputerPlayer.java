package model;

import listeners.ComputerTurnListener;

public class ComputerPlayer extends Player {
	ComputerTurnListener computerTurnListener;

	public ComputerPlayer(TopTrumpsModel model) {
		super(model);
	}
	

	@Override
	protected void showCard() {
		if (computerTurnListener != null) {
			model.setResolveComputerTurnPossible(true);
			computerTurnListener.showComputerTurn();
		} else {
			// It can be assumed that we are to auto resolve this turn so we can shortcut showing the user the turn.
			chosenCategory(this.justMakeARandomChoice());
		}
	}
	
	public void addComputerTurnListener(ComputerTurnListener listener) {
		this.computerTurnListener = listener;
	}
}
