package listeners;

public interface UserSelectionListener {
	/**
	 * Implemented by the controller and added to the view. Called during the game
	 * when the user has selected a category/attribute on their turn. It triggers
	 * the resolution of that turn.
	 *
	 * @param selectedCatagory Index of the category the user has chosen.
	 */
	public void userSelection(int selectedCatagory);
}
