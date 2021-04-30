/* *********************************************************************************
* Written by Paul Wilson with support from team.
*///********************************************************************************
package Models;

// imported classes
import java.util.Random;

/* _________________________________________________________________________________
* Class Description: Monster 
* 	This is a model class that handles the arrival of a monster
* 
* Arguments:
* 	There are no arguments.
*///________________________________________________________________________________
public class Monster {
	
	//class variables declared
	Random rand = new Random(); // a random value used in calculations
	private int monsterLevel;           // an integer representing the level of the monster
	private int monsterStealValue;      // an integer representing the amount of gold used
	private String monsterType;         // a string indicating the type of monster encountered
	private String myCardOutcome;       // a string indicating the outcome of a monster battle
	private InventoryList invList = new InventoryList();
	
	
	/*================================================================================
	* Method Description: stealVal
	* 	This is a method that determines the amount of gold a monster steals
	* 
	* Arguments:
	* 	- mLevel an integer value that determines the indicates the monsters level
	* 
	* Returns: 
	*  - steal , an integer value indicating how much gold the monster has stolen from
	*  the player.
	*///===============================================================================

	public int stealVal(int mLevel) {
		
		// method variables declared
		int steal = 0;    // an integer to be returned that determines the steal value
		
		// a series of if statements that determine how much a monster steals based on its level
		if (mLevel <= 3) {                 
			steal = rand.nextInt(3 * 100);       // steal amount determined using rand and mLevel.
		}
		if (mLevel > 3 && mLevel <= 5) {
			steal = rand.nextInt(2 * 100) + 200; // steal amount determined using rand and mLevel.
		}
		if (mLevel > 5 && mLevel <= 8) {
			steal = rand.nextInt(3 * 100) + 500; // steal amount determined using rand and mLevel.
		}
		if (mLevel > 8 && mLevel <= 10) {
			steal = rand.nextInt(3 * 100) + 700; // steal amount determined using rand and mLevel.
		}
		return steal;                            // the steal amount is returned
	}
	
	
	/*================================================================================
	* * ENEMIES ARE ADDED HERE
	* Method Description: chooseMonster 
	* 	This is a method that determines the type of monster encountered based on a
	* 	randomly chosen number
	* 
	* Arguments:
	*	-none 
	* Returns:
	*  - nothing 
	*///===============================================================================
	
	public void chooseMonster() {
		int monsterNumber = rand.nextInt(4) + 1;

		if (monsterNumber == 1) {

			monsterType = "GOAL TROLL";
			monsterLevel = rand.nextInt(3) + 1;
			monsterStealValue = stealVal(monsterLevel);
		}
		if (monsterNumber == 2) {
			monsterType = "TIME THIEF";
			monsterLevel = rand.nextInt(2) + 5;
			monsterStealValue = stealVal(monsterLevel);
		}
		if (monsterNumber == 3) {
			monsterType = "DARK PROCRASTINATOR";
			monsterLevel = rand.nextInt(2) + 7;
			monsterStealValue = stealVal(monsterLevel);
		}
		if (monsterNumber == 4) {

			monsterType = "DRAGON OF SLOTH";
			monsterLevel = rand.nextInt(2) + 9;
			monsterStealValue = stealVal(monsterLevel);
		}

	}
	
	
	/*================================================================================
	* Method Description: monsterBattle
	* 	method called when a player is confronted with a monster after the monster is
	* 	chosen. An outcome is determined based on the characters level and the monsters level
	* 
	* Arguments:
	*	-player
	*
	* Returns:
	*  - nothing 
	*///===============================================================================
	public void monsterBattle(Player player) {
		myCardOutcome = "YOU SUCCESSFULLY DEFEND YOUR GOLD!";  // default message
		
		
		// determines if player is lower level than monster
		if (player.getPlayerLevel() <= monsterLevel) {
			if(invList.hasStone() == false){
				// compare players level to monsters
				player.setPlayerGold(player.getPlayerGold() - monsterStealValue); // monster wins/steals
				myCardOutcome = ("YOU WERE DEFEATED, AN EXTRA " + monsterStealValue + " GOLD LOST!");
			}
			//
			if(invList.hasStone() == true) {
				myCardOutcome = "YOU USED A SAVING STONE AND ESCAPED WITH YOUR TREASURE!";
			}

			// if player is out of gold
			if (player.getPlayerGold() < 0) { 
				player.setPlayerGold(0);
				myCardOutcome = "THE LAST OF YOUR GOLD WAS TAKEN!";
			}
			
			// determines if you have a saving stone in inventory
		} else {monsterStealValue = 0;}

	}
	
	/*================================================================================
	* Method Description: getters
	* Used to retrieve private variables for class
	*///===============================================================================
	
	public int getMonsterLevel() {         //retrieves monster level
		return monsterLevel;
	}
	public int getMonsterStealValue() {    //retrieves amount of gold stolen by monster
		return  monsterStealValue;
	}
	public String getMonsterType() {       //retrieves monster name
		return monsterType;
	}
	public String getmyCardOutCome() {     //retrieves string indicating the battle outcome
		return myCardOutcome;
	}
	
	/*================================================================================
	* Method Description: setters
	* Used to set private variables for class
	*///===============================================================================


	
	
	
	
	
	
	
	
	
	// NONE YET
}
