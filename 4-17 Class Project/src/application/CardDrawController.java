/* *********************************************************************************
* Written by Paul Wilson with support from team.
*///********************************************************************************

package application;

//imports
import java.util.Random;
import Models.BonusItem;
import Models.Monster;
import Models.Player;

import java.lang.String;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/*================================================================================
* Class Description: Card Draw 
* 	This is a model class that handles the daily card draw event
* Every day a user draws a "card". Depending on if the day was a success or failure
* the card can be a "monster", nothing, or a "bonus item".
* 
* Arguments:
* 	There are no arguments.
*///===============================================================================

public class CardDrawController {
	
	// declared variables
	private MainWindowController control; //declaration used to access Main Window controller components
	private Player player;                // player declared - from controller
	private Boolean success = false;      // a boolean that determines if day successful or not - from controller.
	private Boolean okClicked = false;    // default setting
	private Stage stage;                  // a stage object to display the "card" - from controller.
	Random rand = new Random();           // generates random values for the card award/battle computations.
	int monsterStealValue = 0;            // generated value that determines the gold a monster takes
	int sellValue = 0;                    // the sell value of bonus items
	int expValue = 0;                     // the xp value of bonus items
	int goldChange = 0;                   // an int describing the change to the players gold after encounter
	int expChange = 0;                    // an int describing the change in players xp after an encounter 
	String monsterType = "--";            // the name of monster encountered
	String monsterMessage = "--";         // a message displaying the monster type encountered
	String rewardName = "--";                       // a message displaying the bonus item recieved
	String itemDescription = "no treasure found";   // a message displaying whether bonus items are found
	String myCardOutcome;                  //message displaying the outcome of the card/ encounter
	String outcomeIndicator = "--";        //message 
	String cardTitleBar = "--";
	
	

	// declared javaFX components
	@FXML
	private Button cardOK;            // button that closes the stage
	
	@FXML
	private ImageView cardDisplay;    // the image displayed on the card
	
	@FXML
	private Label outcomeDescription; //text describing the outcome of the "battle"
	
	@FXML
	private Label bonusOrBattle;      //title text that changes depending on the card type.
	
	@FXML
	private Label itemOrMonster;      //title text that changes depending on the card type.
	
	@FXML
	private Label cardOutcome;        //text describing the outcome of the "battle"

	@FXML
	private Label cardDescription;    //text describing the changes to the character due to card drawn.

	@FXML
	private Label monsterLevelDisplay;//if a "monster card" is drawn, this text shows the level.

	@FXML
	private Label cardTitle;          //this displays the title of the card depending on card type

	@FXML
	private Label xpAwarded;          //amount of xp awarded by the card/ daily update

	@FXML
	private Label goldAwarded;        //amount of gold awarded by the card


	/*=================================================================================
	* Class Description: setController
	* 	This is a imports a controller object that gives information about the daily card draw event
	* Every day a user draws a "card". Depending on if the day was a success or failure
	* the card can be a "monster", nothing, or a "bonus item".
	* 
	* Arguments:
	* 	control - a main controller object used to bring in MainController methods and variables.
	* 	player - a player model imported from the controller
	* 	success - a boolean value that determines if the player has succeded or failed
	* 	stage - a stage object from the controller
	* 
	* Returns:
	* 	nothing.
	*///===============================================================================
	public void setController(MainWindowController control, Player player, Boolean success, Stage stage) {
		
		// declaring variables
		this.control = control;      //Main Controller  is set
		this.player = player;        //player variable is set
		this.success = false;        //success boolean is set
		this.stage = stage;          //stage is set
		
		// if statement that determines whether to reward player or penalize player.
		if (success == true) {
			standardReward();        // success
		} else standardDeduction();  // failure
	}
	

	/*================================================================================
	* Class Description: isOkClicked
	* 
	* Arguments: 
	* 	none
	* 
	* returns:
	* 	a boolean that is true if OK has been clicked.
	*///==============================================================================
	public boolean isOkClicked() {
		return okClicked;
	}
	

	/*/================================================================================
	* Class Description: StandardReward
	* 	A method that handles the case that a player met their goals for the day.
	* 
	* Arguments:
	* 	none
	* 
	* Returns:
	* 	nothing
	*///===============================================================================
	public void standardReward() { 
		
		// variables set
		outcomeIndicator = "NO BONUS";    // Default State of outcomeIndicator
		expChange = 50;                   // Default amount of exp added
		player.setPlayerGold(player.getPlayerGold() + (50) + (10 * player.getPlayerLevel())); // gold added 
		player.setPlayerExperience(player.getPlayerExperience() + 50); //experience added to player
		itemOrMonster.setText(itemDescription);
		cardTitleBar = "A SUCCESSFUL DAY!"; // String changed for success condition
		cardDescription.setText("--");
		outcomeDescription.setText("--");
		setCardImage("no bonus");
		
		
		//roll to determine if player gets bonus item
		if (rand.nextInt(10) <= 3) {                                  
			BonusItem bonusItem = new BonusItem();         // new bonus item created
			bonusItem.chooseBonusItem();
			outcomeIndicator = "BONUS ITEMS!";             // strings indicating bonus recieved
			cardTitleBar = "YOU HAVE EARNED BONUS ITEMS!"; // strings indication bonus recieved
			itemOrMonster.setText(bonusItem.getRewardName());        //label set to condition
			outcomeDescription.setText(bonusItem.getMyCardOutcome());//label set to condition
			cardDescription.setText(bonusItem.getItemDescription()); //label set to condition
			setCardImage(bonusItem.getRewardName());

		}
		
		//labels set to indicate success
		xpAwarded.setText(String.valueOf(50));      //new exp amount indicated
		goldAwarded.setText(String.valueOf(50 + 10 * player.getPlayerLevel()));   //new gold amount indicated
		bonusOrBattle.setText(outcomeIndicator);           //label set to condition
		cardTitle.setText(cardTitleBar);                   //label set to condition
	}
	
	
	/* =================================================================================
	* Class Description: StandardDeduction
	* 	A method that handles the case that a player met their goals for the day.
	* 
	* Arguments:
	* 	none
	* 
	* Returns:
	* 	nothing
	*/ //===============================================================================

	public void standardDeduction() {
		
		//variables set
		player.setPlayerGold(player.getPlayerGold() - 50);            // decrement 50 gold
			if(player.getPlayerGold() < 0) {player.setPlayerGold(0);} // if a player has negative gold, set to 0
			
		player.setPlayerExperience(player.getPlayerExperience() - 50);             // decrement 50 xp
			if(player.getPlayerExperience() < 0) {player.setPlayerExperience(0);}  // exp < 0, set to 0
			
		cardTitleBar = "YOU HAVE NEGLECTED YOUR GOALS!"; // card title upon failure condition
		outcomeIndicator = "NO MONSTER";                 // default outcome string
		expChange = -50;                                 //players changes to exp
		goldChange = -50;
		setCardImage("no monster");
		
		
		//roll to determine if monster encountered
		if (rand.nextInt(10) <= 5) {      
			Monster monster = new Monster();         // new monster created
			monster.chooseMonster();                 // a monster is chosen in model
			monster.monsterBattle(player);           // monster is battled in model
			outcomeIndicator = "MONSTER BATTLE!";    // card title set upon monster creation 
			cardTitleBar = "YOUR LAZINESS HAS ATTRACTED MONSTERS!";  // card label set upon monster creation
			monsterLevelDisplay.setText("ENEMY LVL: " + String.valueOf(monster.getMonsterLevel()));  // level of monster 
			monsterType = monster.getMonsterType();                     // string for monster name
			monsterMessage = monster.getmyCardOutCome();                // outcome of monsterbattle from model
			goldChange = (goldChange - monster.getMonsterStealValue()); // amount of gold changed
			setCardImage(monster.getMonsterType());                     // an image of the monster is placed on card
		}
		
		// labels set to indicate failure and/or monster appearance
		xpAwarded.setText(String.valueOf(expChange));    // new exp amount indicated
		goldAwarded.setText(String.valueOf(goldChange)); // new gold amount indicated
		bonusOrBattle.setText(outcomeIndicator);         // label set to condition
		cardDescription.setText("--");                   // type of monster encountered //change later
		cardTitle.setText(cardTitleBar);                 //main title of the card
		itemOrMonster.setText(monsterType);              //label is assigned monster name
		outcomeDescription.setText(monsterMessage);      //label is assigned a string describing battle outcome
	}
	
	/* =================================================================================
	* Class Description: StandardDeduction
	* 	A method that handles the case that a player met their goals for the day.
	* 
	* Arguments:
	* 	none
	* 
	* Returns:
	* 	nothing
	*/ //===============================================================================
	
	public void setCardImage(String name) {
		
		// declaring bonus item images
		Image bonus1 = new Image("\\images\\item7.jpg"); // diamond ring 
		Image bonus2 = new Image("\\images\\item6.jpg"); // ruby ring 
		Image bonus3 = new Image("\\images\\item5.jpg"); // scroll 
		Image bonus4 = new Image("\\images\\item3.jpg"); // healing stone 
		Image noBonus = new Image("\\images\\empty chest.jpg"); // no bonus 
		
		// declaring monster images
		Image monster1 = new Image("\\images\\monster1.jpg"); // goal troll 
		Image monster2 = new Image("\\images\\monster2.jpg"); // black dragon 
		Image monster3 = new Image("\\images\\monster3.jpg"); // wizard
		Image monster4 = new Image("\\images\\monster4.jpg"); // dark elf 
		Image noMonster = new Image("\\images\\no monster.jpg"); // no monster
		
		// if statements that check the passed name and display an image accordingly
		if(name.equals("Diamond Ring")) cardDisplay.setImage(bonus1);
		if(name.equals("Scroll of Wisdom")) cardDisplay.setImage(bonus3);
		if(name.equals("Saving Stone")) cardDisplay.setImage(bonus4);
		if(name.equals("Ruby Ring")) cardDisplay.setImage(bonus2);
		if(name.equals("no bonus")) cardDisplay.setImage(noBonus);
		if(name.equals("Goal Troll")) cardDisplay.setImage(monster1);
		if(name.equals("Dark Elven Procrastinator")) cardDisplay.setImage(monster4);
		if(name.equals("Corrupted Wizard of Malaise")) cardDisplay.setImage(monster3);
		if(name.equals("Black Dragon of Stagnation")) cardDisplay.setImage(monster2);
		if(name.equals("no monster")) cardDisplay.setImage(noMonster);
	}
	
	/* =================================================================================
	* Class Description: handleOK
	* 	A method that activates if OK button is clicked.
	* 
	* Arguments:
	* 	none
	* 
	* Returns:
	* 	nothing
	*/ //===============================================================================
	public void handleOK() {
		stage.close();         // closes the stage
	}
}
