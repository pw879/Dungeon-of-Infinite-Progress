/* *********************************************************************************
* Written by Paul Wilson with support from team.
*///********************************************************************************
package Models;

//imported classes
import java.util.Random;

/* _________________________________________________________________________________
* Class Description: BonusItem 
* 	This is a model class that handles the awarding of a bonus item
* 
* Arguments:
* 	There are no arguments.
*///________________________________________________________________________________

public class BonusItem {
	
	//class variables declared
	Random rand = new Random();       // a random value used in calculations
	private int sellValue;            // an integer representing the gold value of item
	private int expValue;             // an integer representing exp value of item
	private String rewardName;        // a string representing the item name
	private String itemDescription;   // a string representing the item description
	private String myCardOutcome;     // a string representing the outcome of the card draw
	
	
	
	/*================================================================================
	* Method Description: chooseBonusItem
	* 	this method randomly chooses between a number of bonus items
	* 
	* Arguments:
	*	- none
	* 
	* Returns:
	* 	- nothing 
	*///===============================================================================
	
	public void chooseBonusItem() {
		
		// a random value is chosen between 1 and 4
		int rewardNumber = rand.nextInt(4) + 1;

		// if statements that award an item based on the random reward number
		if (rewardNumber == 1) {

			sellValue = 400;                          
			expValue = 0;
			rewardName = "Diamond Ring";
			itemDescription = "sell for 400 gold!";
			myCardOutcome = "added to your inventory";
		}
		if (rewardNumber == 2) {

			sellValue = 0;
			expValue = 50;
			rewardName = "Scroll of Wisdom";
			itemDescription = "use for 50 EXP!";
			myCardOutcome = "added to your inventory";
		}
		if (rewardNumber == 3) {

			sellValue = 200;
			expValue = 0;
			rewardName = "Saving Stone";
			itemDescription = "use to avoid a battle!";
			myCardOutcome = "added to your inventory";
		}
		if (rewardNumber == 4) {

			sellValue = 200;
			expValue = 0;
			rewardName = "Ruby Ring";
			itemDescription = "sell for 200 gold!";
			myCardOutcome = "added to your inventory";
		}
		// add reward to inventory
	}
	
	/*================================================================================
	* Method Description: getters
	* Used to retrieve private variables for class
	*///==============================================================================
	
	public int getSellValue() {
		return sellValue;
	}
	public int getExpValue() {
		return expValue;
	}
	public String getRewardName() {
		return rewardName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public String getMyCardOutcome() {
		return myCardOutcome;
	}
	
	/*================================================================================
	* Method Description: setters
	* Used to set private variables for class
	*///===============================================================================
	// NONE YET

}
