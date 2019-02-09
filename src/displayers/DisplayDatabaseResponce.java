package displayers;

import model.DatabaseResponse;

public interface DisplayDatabaseResponce {

	/**
	 * To be implemented and attached to the model via the view. Called by the model
	 * to update the view with statistics from the database
	 * 
	 * @param response Contains info packaged by the database to be displayed.
	 */
	public void showDatabaseResonce(DatabaseResponse response);
}
