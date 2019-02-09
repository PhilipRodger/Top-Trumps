package listeners;

public interface NextCategoryListener {
	/**
	 * Implemented by the controller and added to the view. Called when the user has
	 * seen a computers turn and wishes to resolve the rest of the turn.
	 */
	public void nextCategory();
}
