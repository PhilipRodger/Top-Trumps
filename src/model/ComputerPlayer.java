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
		game.setResolveComputerTurnPossible(true);
		int categoryChoice = justMakeARandomChoice();
		game.setCategoryChoice(categoryChoice);
		showComputerTurnListener.showComputerTurn(currentRound);

	}

	public void addDisplayComputerTurn(DisplayComputerTurn listener) {
		this.showComputerTurnListener = listener;
	}

	public static void resetPlayerCounter() {
		nextComputerNumber = 1;
	}
}
