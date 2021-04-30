/* *********************************************************************************
* Written by Paul Wilson with support from team.
*///********************************************************************************
package application;

// imported classes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.TimerTask;

import org.quartz.JobDetail;

import Models.Goal;
import Models.GoalList;
import Models.GoalSkeleton;
import Models.InventoryList;
import Models.MainGoalSkeleton;
import Models.MainList;
import Models.Player;
import Models.PlayerStats;
import Models.QuartzListener;
import Models.QuartzTimerJob;
import Models.Saves;
import Models.junk.Person;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
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
	
	public MainWindowController(QuartzTimerJob myTimer) {
		this.timer = myTimer;
	}
	
	// empty constructor
    public MainWindowController() {
    	
    }
	// declare variables
	public boolean mySuccess = true;  //boolean declaring if the player finished their goals in time
	public Player player = new Player("Name", 1, 0, 0);  //new player declared
	private Boolean okClicked = false;                   //OK set
	private Main main;         // a declaration of a main method object
	public GoalList goalList = new GoalList();
	private InventoryList inventoryList = new InventoryList();
	private Saves save = new Saves();
	public PlayerStats stats = new PlayerStats();
	public QuartzTimerJob timer;
	public JobDetail myJob;
	public MainList myMainList = new MainList();
	public ObservableList<GoalSkeleton> saveSkeleton;
	public ObservableList<Goal>trackedGoals;
	public ObservableList<MainGoalSkeleton> mainSaveSkeleton;
	
	
	

	

	// declare FXML variables
	@FXML
	ProgressBar levelBar = new ProgressBar();;                         // a progress bar denoting the players level
	
	@FXML
	TableView<Goal> tableView;                  // a table view showing current goals

	@FXML
	TableView<Reward> inventoryView;                  // a table view showing current goals
	
	@FXML
	TableColumn<Goal, String> goalTitleColumn;  // a table column displaying info
	
//	@FXML
//	TableColumn<Goal, String> goalDescriptionColumn;   // a table column displaying info
	
	@FXML
	TableColumn<Goal, ProgressBar> progressBar; // a progress bar within a goal that displays progress
	
	@FXML
	TableColumn<Goal, CheckBox> completeCheck;  // a checkbox within a goal that denotes completion
	
	@FXML
	TableColumn<Goal, CheckBox> progressCheck;  // a checkbox within a goal that denotes completion
	
    @FXML
    TableColumn<Reward, String> itemName;
    
    @FXML
    TableColumn<Reward, String> itemImage;
	
	
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
		  experienceLabel;            // a label that displays the characters current experience;					
	@FXML
	public Label myTimer;
	public String myTime = "";
		
	
	
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
		//goalList.mainGoalList();
		myMainList.mainGoalList();
		//tableView.setItems(goalList.getGoalData());
		
		tableView.setItems(myMainList.getGoalData());
		if(goalList == null)System.out.println("NO GOALS"); 
		inventoryList.mainInventoryList();
		inventoryView.setItems(inventoryList.getInventoryData());


		
		
		
		
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
		progressBar.setCellValueFactory(new PropertyValueFactory<Goal, ProgressBar>("progress"));
		completeCheck.setCellValueFactory(new PropertyValueFactory<Goal, CheckBox>("complete"));
		progressCheck.setCellValueFactory(new PropertyValueFactory<Goal, CheckBox>("inProgress"));
		
		// fo rthe inventory display
		itemImage.setCellValueFactory(new PropertyValueFactory<Reward, String>("name"));
		itemName.setCellValueFactory(new PropertyValueFactory<Reward, String>("name"));
		
		
		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDetails(newValue));
		
		player.setMainWindowController(this);
		loadStats(player);
		
		System.out.println("PLAYER LOADED"); 
		
		// player info set
		showPlayerGold(player);        // player gold is shown
		showPlayerExperience(player);  // experience is shown
		showPlayerLevel(player);       // player level is shown
		player.calculateLevel();       // player Level is calculated
		player.changeAvatar();         // avatar is changed
		
		//fill inventory
		inventoryList.mainInventoryList();
		inventoryView.setItems(inventoryList.getInventoryData()); 
		
		// fill bars
		fillProgressBars();
	
		
		
		
		//setting up inventory tableview
		
		//loading player 
		


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
		tableView.setItems(myMainList.getGoalData());
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
		//goalDescriptionLabel.setText(newValue.getGoalDescription()); // label set w/ goal values
		goalMotivationLabel.setText(newValue.getGoalMotivation());   // label set w/ goal values
		projectDueLabel.setText(newValue.getProjectDue());           // label set w/ goal values
		habitDurationLabel.setText(newValue.getProjectDue());     // label set w/ goal values
	}
	
	
	/*================================================================================
	* Method description: showPlayerLevel - passes player level to a shown label
	*
	* Arguments: player
	* 
	* Returns: nothing
	*///===============================================================================
	
	public void showPlayerLevel(Player player) {
		levelLabel.setText( (String.valueOf(player.getPlayerLevel())));
		handleLevelBar();
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
			controller.setMainWindowController(this, stage, goal, goalList, saveSkeleton, myMainList);

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
		myMainList.getGoalData().remove(index); // selected item is removed

	}
	
	
	/*================================================================================
	* Method description: handleUse - a method that uses a selected item in the inventory
	*
	* Arguments: nothing
	* 
	* Returns: nothing
	*///===============================================================================
	
	@FXML // Button
	public void handleUse() {
		
		int index = inventoryView.getSelectionModel().getSelectedIndex(); // an item is selected
		inventoryList.removeInventory(index);
		inventoryView.setItems(inventoryList.getInventoryData()); 
		//inventoryList.getInventoryData().remove(index); // selected item is removed
		
		

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
		for(Goal mygoal : myMainList.getGoalData()) // loop through goalList
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
		
		for(Goal goal : myMainList.getGoalData()) {         //cycle through all goals in goals list
			
			// progress for the day is calculated using a ratio of current to total days
			Double progress = (Double.valueOf(goal.getCurrentDay()) / Double.valueOf(goal.getProjectDue()));
			
			//progress is added to the progress bar
			ProgressBar myProgress = new ProgressBar(progress);
			myProgress.setPrefWidth(200);
			
			// progress bar is updated
			goal.setProgress(myProgress);
			
			// if time is up for the goal, or the goal is complete,  the progress bar will turn red
			if (Integer.valueOf(goal.getCurrentDay()) >= Integer.valueOf(goal.getTotalDays())) {
				goal.setCurrentDay(goal.getTotalDays());
				ProgressBar full = new ProgressBar(1.0);
				full.setPrefWidth(200);
				full.setStyle("-fx-accent: green;");
				goal.setProgress(full);
				goal.setGoalMotivation("COMPLETED!!! YOUR GOLD HAS BEEN AWARDED.");
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
		for(Goal goal : myMainList.getGoalData()) {
			int i = Integer.parseInt(goal.getCurrentDay());
			goal.setCurrentDay(String.valueOf(i + 1));			
		}
		
		
		fillProgressBars();     // progress bars are filled amount appropriate for one day
		player.setPlayerGold(player.getPlayerGold() + 50);             // player gold is changes
		player.setPlayerExperience(player.getPlayerExperience() + 20); // player experience is updated
		player.calculateLevel();        // player level is calculated
		showPlayerLevel(player);        // player level is shown
		player.changeAvatar();          // player avatar is changed
		refreshTableView();             // table view is reloaded
		
		//save player
		//save.savePlayer(player);
	
		// FOR FUTURE USE
	for(Goal goal : myMainList.getGoalData()) {  
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
		inventoryList.mainInventoryList();
		inventoryView.setItems(inventoryList.getInventoryData()); 
		

		
		
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
	
	/*================================================================================
	* Method description: newShopWindow 
	* 	Method that Opens a new FXML window "NewGoalView". 
	*
	* Arguments: 
	*   - a goal can be taken in for processing if an existing goal is selected
	* 
	* Returns:
	* 	- a boolean
	*///===============================================================================
	
	public void newShopWindow() { 
	
		Stage primaryStage = new Stage();
		
			try {
				FXMLLoader loader3 = new FXMLLoader(Main.class.getResource("/application/rewardmenu.fxml"));
				Pane root = loader3.load(); //loads fxml file
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				RewardController controller2 = loader3.getController();
				controller2.setMyController(this, player, primaryStage);
				
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	/*================================================================================
	* Method description: handleSaveClose 
	* 	Method that saves character when the close button is pressed. It also closes the 
	* 	stage.
	*
	* Arguments: 
	*   - a goal can be taken in for processing if an existing goal is selected
	* 
	* Returns:
	* 	- a boolean
	*///===============================================================================
	
	public void handleSaveClose() {
		stats.PlayerStats(player);
		ObservableList<MainGoalSkeleton> mainSaveSkeleton =  FXCollections.observableArrayList();
		this.mainSaveSkeleton = mainSaveSkeleton; // could be problems
		
		try {
			File file = new File("playerStats.ser");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(stats);
			oos.close();
			fos.close();
			System.out.println("STATS SUCCESSFUL"); 
		} catch (Exception e) {
			System.out.println("DID NOT SAVE PROPERLY"); 
			e.printStackTrace();
		}
		
		for (Goal myGoal: myMainList.getGoalData()) {
			MainGoalSkeleton save = new MainGoalSkeleton(
					myGoal.getGoalTitle(),
					myGoal.getGoalMotivation(),
					myGoal.getGoalReminderQueue(),
					myGoal.getGoalSubstituteField(),
					myGoal.getDailyRewardField(),
					myGoal.getProjectDue(),
					myGoal.getCurrentDay(),
					myGoal.getTotalDays(),
					myGoal.getGoalStatus()
					);
			mainSaveSkeleton.add(save);
		}
		
		try {
			File file = new File("mainGoalSkeletons.ser");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(new ArrayList<MainGoalSkeleton>(mainSaveSkeleton));
			oos.close();
			fos.close();
			System.out.println("SKELETON SAVED"); 
		} catch (Exception e) {
			System.out.println("DID NOT SAVE PROPERLY"); 
			e.printStackTrace();
		}
	
		

		main.getStage().close();
			
			
		}
		
		
		
		
	

	
	

/*================================================================================
* Method description: loadStats() 
* 	A method that loads a player stats object that is then used to build a saved 
* 	player. 
*
* Arguments: 
*   - a goal can be taken in for processing if an existing goal is selected
* 
* Returns:
* 	- a boolean
*///===============================================================================

	public void loadStats(Player player) {
		
			PlayerStats mystats = new PlayerStats();

			try {
				FileInputStream fis = new FileInputStream("playerStats.ser");
				ObjectInputStream in = new ObjectInputStream(fis);
				mystats = (PlayerStats) in.readObject();
				in.close();
				fis.close();
			} catch (Exception e) {
				System.out.println("DID NOT LOAD PROPERLY"); 
				e.printStackTrace();
			}
			
			player.setPlayerGold(mystats.getStatGold());
			player.setPlayerLevel(mystats.getStatlevel());
			player.setPlayerExperience(mystats.getStatexp());
			player.setPlayerName(mystats.getStatName());
		}
	
	/*================================================================================
	* Method description: handleLevelBar() 
	* 	A method that increments the main level bar at the top of the page
	*
	* Arguments: 
	*   - a goal can be taken in for processing if an existing goal is selected
	* 
	* Returns:
	* 	- a boolean
	*///===============================================================================
	
	public void handleLevelBar() {
		
			
			// progress for the day is calculated using a ratio of current to total days
			Double progress = ((Double.valueOf(player.getPlayerExperience()) % 200) / 200);
		
			//progress is added to the progress bar
			
			levelBar.setPrefWidth(350);
			levelBar.setProgress(progress);
			//levelBar = newBar;
	}


	
	public void daySucessful() {
		
		mySuccess = false;
		
		int myGoals = myMainList.getGoalData().size();
		if (countChecks() == myGoals ) {
			mySuccess = true;
		}
		
	}
}

	
	
	




//END------------------------------------------------------------------------------------


//BONEYARD - SAVED CODE FOR LATER_________________________________________________
	
// method that determines whether all the goals were completed for the day

//
//// method that calculates the experience point gains and losses.
//public int calculateExp(int exp,int expDiff) {
//	int expChange = exp;
//	int expAdd = expDiff;
//	int newExp = expChange + expDiff;
//	return newExp;
//	




	
	
	
	
	



