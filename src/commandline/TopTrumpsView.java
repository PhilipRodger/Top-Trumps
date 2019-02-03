package commandline;

import listeners.*;
import model.*;

public interface TopTrumpsView {
	// Interface that the CLI and Website should implement.
	
	
	// Things the view will need to display to the user
	public void showMainMenu();
//	
//	public void showStatistics(DatabaseResponse stats);
//	
//	public void showStartOfPlayerTurn();
//	
//	public void showStartOfComputerTurn();
//	
//	public void showTurnResolution();
//	
//	public void showEndOfGame();
	
	
	// Below are ways that the view will notify the controller that the user has
	// made a decision.	
	public void addStartGameListener(StartGameListener listener);

	public void addViewStatisticsListener(ViewStatisticsListener listener);

	public void addNextCategoryListener(NextCategoryListener listener);

	public void addUserSelectionListener(UserSelectionListener listener);

	public void addNextRoundListener(NextRoundListener listener);

	public void addAutoResolveModeListener(AutoResolveModeListener userWantsToAutoResolveGameListner);
	
	
	// Start the action->controller->model->view loop, this is called once the MVC objects have been initialised.
	public void run();


}
