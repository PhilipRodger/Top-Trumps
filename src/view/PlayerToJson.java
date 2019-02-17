package view;
 /**
* The reason for this class is that during development the round objects and
* player objects were too complex to convert to JSON directly, instead this
* simple class if filled with state of the game to be transmitted via the
* RESTAPI
*/
public class PlayerToJson {
	public boolean inGame;
	public boolean humanPlayer;
	public int numberOfCards;
	
	// Round Card
	public String cardName;
	public int size;
	public int speed;
	public int range;
	public int firepower;
	public int cargo;
}
