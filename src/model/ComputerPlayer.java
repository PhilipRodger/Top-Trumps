package model;

import listeners.ShowComputerTurnListener;

public class ComputerPlayer extends Player {
	ShowComputerTurnListener computerTurnListener;
	private static int nextComputerNumber = 1;

	public ComputerPlayer(TopTrumpsModel model) {
		super(model);
		name = "Computer" + nextComputerNumber;
		nextComputerNumber++;
	}
	

	@Override
	protected void showCard() {
		if (computerTurnListener != null) {
			model.setResolveComputerTurnPossible(true);
			categoryChoice = justMakeARandomChoice();
			model.setCategoryChoice(categoryChoice);
			computerTurnListener.showComputerTurn(currentCardDrawn);
		} else {
			// It can be assumed that we are to auto resolve this turn so we can shortcut showing the user the turn.
			chosenCategory(this.justMakeARandomChoice());
		}
	}
	
	public void addComputerTurnListener(ShowComputerTurnListener listener) {
		this.computerTurnListener = listener;
	}
}
