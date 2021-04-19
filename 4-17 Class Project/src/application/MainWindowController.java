/* *********************************************************************************
* Written by Paul Wilson with support from team.
*///********************************************************************************
package application;

// imported classes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.TimerTask;

import Models.Goal;
import Models.GoalList;
import Models.Player;
import Models.junk.Person;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/*================================================================================
* Class Description: MainWindowController
* This is a controller class that handles the control of the main panel
* 
* Arguments:
* 	There are no arguments.
*///===============================================================================
public class MainWindowController implements Initializable {
	
	// declare variables
	boolean mySuccess = false;  //boolean declaring if the player finished their goals in time
	public Player player = new Player("Name", 1, 0, 0);  //new player declared
	private Boolean okClicked = false;                   //OK set
	private Main main;         // a declaration of a main method object
	private GoalList goalList = new GoalList();

	// declare FXML variables
	@FXML
	ProgressBar levelBar;                         // a progress bar denoting the players level
	
	@FXML
	TableView<Goal> tableView;                  // a table view showing current goals
	
	@FXML
	TableColumn<Goal, String> goalTitleColumn;  // a table column displaying info
	
	@FXML
	TableColumn<Goal, String> goalDescriptionColumn;   // a table column displaying info
	
	@FXML
	TableColumn<Goal, ProgressBar> progressBar; // a progress bar within a goal that displays progress
	
	@FXML
	TableColumn<Goal, CheckBox> completeCheck;  // a checkbox within a goal that denotes completion
	
	@FXML
	TableColumn<Goal, CheckBox> progressCheck;  // a checkbox within a goal that denotes completion
	
	
	@FXML
	Label goalTitleLabel,             // Label that shows the "title" of goal
	      goalDescriptionLabel,       // Label that shows the "description" of goal
		  goalMotivationLabel,        // Label that shows the "motivation" for the goal
		  projectDueLabel,            // Label that shows the "Days till due" for goal
		  habitDurationLabel,         // Label that shows the duration the habit will be carried out
		  checkCount,                 // a temporary label
		  timerLabel,                 // a temporary label
		  levelLabel,                 // a label that displays the characters current level
		  goldLabel,                  // a label that displays the characters current gold
		  experienceLabel;            // a label that displays the characters current experience
	
	
	@FXML 
	Button timerStart,         // a temporary button that starts a timer
	       timerStop;          // a temporary button that stops a timer
	
	
	@FXML ImageView levelImage;// an image view that displays an image of the characters avatar;
	
	
	/*================================================================================
	* Method Description: setMain
	* 	This method sets an imported main as the "main" file to which this controller refers
	* 	It also brings in some elements from that main to be used here
	* 
	* Arguments:
	* 	- main This is an instance of the main class
	* 	- player This is an instance of the player class brought in from the main class
	* 
	* Returns:
	* 	-nothing
	*///===============================================================================
	
	public void setMain(Main main) {
		this.main = main;     // a main class created here is given the imported mains properties
		
		// a table view is set with information from a method in main
		goalList.mainGoalList();
		tableView.setItems(goalList.getGoalData()); 
	}
	
	/*================================================================================
	 * THIS METHOD WILL BE REPLACED!!!!!!!!
	* Class Description: startTimer
	* implements a timer object
	* 
	* Arguments:
	* 	There are no arguments.
	* 
	* Returns:
	* 	nothing.
	*///===============================================================================
	
	public void startTimer() {
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		int x = 1;
		@Override
		public void run() {
			if (x < 10) {
			String out = String.valueOf(x);
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	//levelLabel.setText(out);
			    }
			});
			
			x++;
			} else {
				cancel();
			}
		}
	};
	timer.scheduleAtFixedRate(task, 0, 1000);
	}

	
	/*================================================================================
	 * 
	* Method description: initialize
	* 	A table views columns are set up with properties from the person model
	* 
	* Arguments:
	* 	There are no arguments.
	* 
	* Returns:
	* 	nothing.
	*///===============================================================================
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		goalTitleColumn.setCellValueFactory(new PropertyValueFactory<Goal, String>("goalTitle"));
		goalDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Goal, String>("goalDescription"));
		progressBar.setCellValueFactory(new PropertyValueFactory<Goal, ProgressBar>("progress"));
		completeCheck.setCellValueFactory(new PropertyValueFactory<Goal, CheckBox>("complete"));
		progressCheck.setCellValueFactory(new PropertyValueFactory<Goal, CheckBox>("inProgress"));
		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDetails(newValue));
		player.setMainWindowController(this);

	}
	

	/*================================================================================
	 * 
	* Method description: refereshTableView
	* 	This method looks back to the original Observable list in GoalList and reloads using
	* 	that information
	* 	THIS WILL BE SET UP USING INFO FROM OUR GOALS MODEL USE PERSON AS A REFERENCE
	*
	* Arguments:
	* 	There are no arguments.
	* 
	* Returns:
	* 	nothing.
	*///===============================================================================
	
	public void refreshTableView() {
		tableView.setItems(null);
		tableView.layout();
		tableView.setItems(goalList.getGoalData());
	}
	
	
	/*================================================================================
	 * 
	* Method description: showDetails
	* 	This method takes in a Goal object and displays its properties  
	* 	using a list of labels on the main view
	*
	* Arguments:
	* 	-a Goal object
	* 
	* Returns:
	* 	-nothing.
	*///===============================================================================
	
	public void showDetails(Goal newValue) {             // a goal is passed as argument
		goalTitleLabel.setText(newValue.getGoalTitle()); // labels set using goal values
		goalDescriptionLabel.setText(newValue.getGoalDescription()); // label set w/ goal values
		goalMotivationLabel.setText(newValue.getGoalMotivation());   // label set w/ goal values
		projectDueLabel.setText(newValue.getProjectDue());           // label set w/ goal values
		habitDurationLabel.setText(newValue.getHabitDuration());     // label set w/ goal values
	}
	
	
	/*================================================================================
	* Method description: showPlayerLevel - passes player level to a shown label
	*
	* Arguments: player
	* 
	* Returns: nothing
	*///===============================================================================
	
	public void showPlayerLevel(Player player) {
		levelLabel.setText("LVL: " + (String.valueOf(player.getPlayerLevel())));
	}
	
	
	/*================================================================================
	* Method description: showPlayerGold - passes player gold to a shown label
	*
	* Arguments: player
	* 
	* Returns: nothing
	*///===============================================================================
	
	public void showPlayerGold(Player player) {   
		goldLabel.setText(String.valueOf(player.getPlayerGold()) + " : GOLD");
	}
	
	/*================================================================================
	* Method description: showPlayerExperience - passes player experience to a shown label
	*
	* Arguments: player
	* 
	* Returns: nothing
	*///===============================================================================
	
	public void showPlayerExperience(Player player) {   //TO PLAYER OBJECT
		experienceLabel.setText(String.valueOf(player.getPlayerExperience()) + " : EXP");
	}
	
	
	/*================================================================================
	* Method description: newGoalWindow 
	* 	Method that Opens a new FXML window "NewGoalView". 
	*
	* Arguments: 
	*   - a goal can be taken in for processing if an existing goal is selected
	* 
	* Returns:
	* 	- a boolean
	*///===============================================================================
	
	public boolean newGoalWindow(Goal goal) { //Goal can be passed
		try {
			
			// create a new FXML scene
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("NewPersonView.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// make a new stage
			Stage stage = new Stage();

			// set controller
			NewGoalController controller = loader.getController();
			controller.setMainWindowController(this, stage, goal, goalList);

			// put scene onto stage
			stage.setScene(scene);
			stage.setResizable(false);   // size of stage is fixed
			stage.showAndWait();         // stage wait for user reaction
			return isOkClicked();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/*================================================================================
	* Method description: isOKClicked - checks if OK has been clicked
	*
	* Arguments: nothing
	* 
	* Returns: boolean
	*///===============================================================================
	public boolean isOkClicked() {
		
		return okClicked;
	}
	
   
	/*================================================================================
	* Method description: handleNew - method for a button that runs a method "New Goal Window"
	*
	* Arguments: nothing
	* 
	* Returns: nothing
	*///===============================================================================
	
	@FXML // Button
	public void handleNew() {
		
		newGoalWindow(null);
	}
	
		
	/*================================================================================
	* Method description: handleEdit - method for a button that runs a method "New Goal Window"
	*
	* Arguments: nothing
	* 
	* Returns: nothing
	*///===============================================================================
	
	@FXML // Button
	public void handleEdit() {
		
		Goal goal = tableView.getSelectionModel().getSelectedItem(); // goal selected saved 
		Boolean okClicked = newGoalWindow(goal);// new window with selected goal passed as argument
		
		if (okClicked) {            // if OK is clicked update the main table view with new info
			refreshTableView();
		}
	}

	
	/*================================================================================
	* Method description: handleDelete - method for a button that runs a method "New Goal Window"
	*
	* Arguments: nothing
	* 
	* Returns: nothing
	*///===============================================================================
	
	@FXML // Button
	public void handleDelete() {
		
		int index = tableView.getSelectionModel().getSelectedIndex(); // an item is selected
		goalList.getGoalData().remove(index); // selected item is removed

	}
	
	/*================================================================================
	* Method description: countChecks - counts the number of items checked in the tableView
	*
	* Arguments: nothing
	* 
	* Returns: an int for the number of checked items
	*///===============================================================================
	
	@FXML
	public int countChecks() {
		
		int count = 0;    // a varialble to hold count
		for(Goal mygoal : goalList.getGoalData()) // loop through goalList
		{
			if ((mygoal.getComplete().isSelected()) | (mygoal.getInProgress().isSelected())) {
				count++;  // if a check is present in either box the counter goes up
			}
		}
			checkCount.setText(String.valueOf(count)); // a label is set to the count
		return count; // the count is returned
		
	}
	
	/*================================================================================
	* Method description: fillProgressBars 
	* 	- method that fills each goals progress bar
	*
	* Arguments: nothing
	* 
	* Returns: nothing
	*///===============================================================================

	public void fillProgressBars() {
		
		for(Goal goal : goalList.getGoalData()) {         //cycle through all goals in goals list
			
			// progress for the day is calculated using a ratio of current to total days
			Double progress = (Double.valueOf(goal.getCurrentDay()) / Double.valueOf(goal.getTotalDays()));
			
			//progress is added to the progress bar
			ProgressBar myProgress = new ProgressBar(progress);
			
			// progress bar is updated
			goal.setProgress(myProgress);
			
			// if time is up for the goal, or the goal is complete,  the progress bar will turn red
			if (goal.getCurrentDay() >= goal.getTotalDays()) {
				goal.setCurrentDay(goal.getTotalDays());
				ProgressBar full = new ProgressBar(1.0);
				full.setStyle("-fx-accent: red;");
				goal.setProgress(full);
			}
		}
	}
	
	
	/*================================================================================
	* Method description: incrementDay 
	* 	- method that is run when a day is passed
	*
	* Arguments: nothing
	* 
	* Returns: nothing
	*///===============================================================================
	
	public void incrementDay() {          // occurs when timer cycles through one day
		showPlayerGold(player);           // updated gold is displayed
		showPlayerExperience(player);     // updated experience is displayed
		player.calculateLevel();          // player Level is calculated
		showPlayerLevel(player);          // Level is displayed
		player.changeAvatar();            // player avatar is changed according to level
		
		// the current day is incremented in the goals list 
		for(Goal goal : goalList.getGoalData()) {
			int i = goal.getCurrentDay();
			goal.setCurrentDay(i+1);			
		}
		
		
		fillProgressBars();     // progress bars are filled amount appropriate for one day
		player.setPlayerGold(player.getPlayerGold() + 50);             // player gold is changes
		player.setPlayerExperience(player.getPlayerExperience() + 20); // player experience is updated
		player.calculateLevel();        // player level is calculated
		showPlayerLevel(player);        // player level is shown
		player.changeAvatar();          // player avatar is changed
		refreshTableView();             // table view is reloaded
	
		// FOR FUTURE USE
	for(Goal goal : goalList.getGoalData()) {  
	System.out.println(goal.getCurrentDay()); 
	System.out.println(goal.getTotalDays());
	
	}
	}

	


	/*================================================================================
	* Method description; pullDayCard 
	* 	- a card that is drawn each day by the timer
	*
	* Arguments: nothing
	* 
	* Returns: nothing
	*///===============================================================================
	
	// Buttons
	public void pullDayCard() {
		DailyUpdate();                 // character is updated
		showPlayerGold(player);        // player gold is shown
		showPlayerExperience(player);  // experience is shown
		showPlayerLevel(player);       // player level is shown
		player.calculateLevel();       // player Level is calculated
		player.changeAvatar();         // avatar is changed
		
	}
	

	/*================================================================================
	* Method description; DailyUpdate 
	* 	- opens a screen that controls a daily card pull to punish or reward the user
	*
	* Arguments: nothing
	* 
	* Returns: boolean
	*///===============================================================================
	public boolean DailyUpdate () {
		
		try {
			// opens a new window for the "card" dialog
			FXMLLoader loader2 = new FXMLLoader(Main.class.getResource("UpdateWindow.fxml"));
			AnchorPane pane = loader2.load();
			Scene scene = new Scene(pane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// make a new stage
			Stage stage = new Stage();

			// set controller
			CardDrawController controller2 = loader2.getController();
			controller2.setController(this, player, mySuccess, stage);
			

			// put scene onto stage
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait(); // stage shown and waits for user reaction
			return true; //controller2.isOkClicked();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*================================================================================
	* Method description - setLevelImage - sets the imageview used for the avatar 
	*
	* Arguments: image
	* 
	* Returns: nothing
	*///===============================================================================
	
	public void setLevelImage(Image image) {
		levelImage.setImage(image); // replace current avatar with new image 
		
	}
}




//END------------------------------------------------------------------------------------


//BONEYARD - SAVED CODE FOR LATER_________________________________________________
	
//// method that determines whether all the goals were completed for the day
//public boolean daySucessful() {
//	boolean isSuccess = false;
//	if (countChecks() == goalCounter() ) {
//		isSuccess = true;
//	}
//	return isSuccess;
//}
//
//// method that calculates the experience point gains and losses.
//public int calculateExp(int exp,int expDiff) {
//	int expChange = exp;
//	int expAdd = expDiff;
//	int newExp = expChange + expDiff;
//	return newExp;
//	




	
	
	
	
	



