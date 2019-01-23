package commandline.view;

import commandline.TopTrumpsView;
import listeners.NextCategoryListener;
import listeners.NextRoundListener;
import listeners.RestartListener;
import listeners.StartGameListener;
import listeners.UserSelectionListener;
import listeners.ViewStatisticsListener;
import model.Card;
import model.DatabaseResponse;


public class CommandLineView implements TopTrumpsView {
	// Listeners in the command view are not linked to individual elements like the web version.
	StartGameListener startGameListner;
	ViewStatisticsListener viewStatisticsListner;
	NextCategoryListener nextCatagoryListener;
	UserSelectionListener userSelectionListner;
	NextRoundListener nextRoundListener;
	RestartListener restartListner;
	
	//should be a endless loop that will continually seek input from system.in 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	//Below methods just add action listeners to the the view allowing separation of the view form the controller. 
	@Override
	public void addStartGameListener(StartGameListener listner) {
		this.startGameListner = listner;

	}

	@Override
	public void addViewStatisticsListener(ViewStatisticsListener listner) {
		this.viewStatisticsListner = listner;
		
	}

	@Override
	public void addNextCategoryListener(NextCategoryListener listner) {
		this.nextCatagoryListener = listner;
		
	}

	@Override
	public void addUserSelectionListener(UserSelectionListener listner) {
		this.userSelectionListner = listner;
		
	}

	@Override
	public void addNextRoundListener(NextRoundListener listner) {
		this.nextRoundListener = listner;
		
	}

	@Override
	public void addRestartListener(RestartListener listner) {
		this.restartListner = listner;
		
	}

	@Override
	public void showStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showStatistics(DatabaseResponse stats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showStartOfPlayerTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showStartOfComputerTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showTurnResolution() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showEndOfGame() {
		// TODO Auto-generated method stub
		
	}
}
