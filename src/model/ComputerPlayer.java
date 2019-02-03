package model;

import displayers.DisplayComputerTurn;

public class ComputerPlayer extends Player {
	DisplayComputerTurn showComputerTurnListener;
	private static int nextComputerNumber = 1;

	public ComputerPlayer(Game game) {
		super(game);
		name = "Computer" + nextComputerNumber;
		nextComputerNumber++;
	}
	

	@Override
	protected void showCard(Round currentRound) {
		if (showComputerTurnListener != null || game.inAutoResolve()) {
			game.setResolveComputerTurnPossible(true);
			int categoryChoice = justMakeARandomChoice();
			game.setCategoryChoice(categoryChoice);
			showComputerTurnListener.showComputerTurn(currentRound);
		} else {
			// It can be assumed that we are to auto resolve this turn so we can shortcut showing the user the turn.
			chosenCategory(this.justMakeARandomChoice());
			game.startRound();
		}
	}
	
	public void addDisplayComputerTurn(DisplayComputerTurn listener) {
		this.showComputerTurnListener = listener;
	}
}
