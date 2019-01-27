package listeners;

public interface UserSelectionListener {
	/**
	 * This will be called during the game when the user has selected a
	 * category/attribute on their turn. It triggers the model to be updated in
	 * response.
	 *
	 * @param selectedCatagory is the index of the category the user has chosen 
	 */
	public void userSelection(int selectedCatagory);
}
