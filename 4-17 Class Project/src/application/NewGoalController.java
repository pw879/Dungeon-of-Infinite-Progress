package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// imported classes
//import Models.Goal;
//import Models.GoalList;
//import Models.GoalSkeleton;
//import Models.Saves;
//import Models.junk.Person;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TableColumn;
//import javafx.fxml.Initializable;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Stage;

import Models.Goal;
import Models.GoalList;
import Models.GoalSkeleton;
import Models.MainList;
import Models.Saves;
import Models.junk.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*================================================================================
* Class Description: New Goal Controller
* This is a controller class that handles the control of the main panel
*
* 	There are no arguments.
*///===============================================================================
public class NewGoalController implements Initializable{
	
//	// FXMLdeclarations
//    @FXML
//    private TextField goalMotivationField;        // a field for user input of a new goal
//    
//    @FXML
//    private TextField goalDescriptionField;       // a field for user input of a new goal
//
//    @FXML
//    private TextField goalTitleField;             // a field for user input of a new goal
//    
//    @FXML
//    private TextField projectDurationField;       // a field for user input of a new goal
//
//    @FXML
//    private TextField habitDurationField;          // a field for user input of a new goal
	
	@FXML
    private Label test;
	
	@FXML
    private TextField goalSubstituteField;


    @FXML
    private Button deleteButton;

    @FXML
    private TextField goalTitleField;

    @FXML
    private TableColumn rewardColumn;

    @FXML
    private Button newHabitButton;

    @FXML
    private Button addVaultButton;

    @FXML
    private Label goalSubstituteLabel;

    @FXML
    private Button addDungeonButton;

    @FXML
    private Button okButton;

    @FXML
    private TextField projectDurationField;

    @FXML
    private TextField goalReminderQueue;

    @FXML
    private Label goalTitleLabel;

    @FXML
    private Button newProjectButton;

    @FXML
    private Label projectDurationLabel;

    @FXML
    private Label goalMotivationLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField dailyRewardField;

    @FXML
    private TableView<Goal> goalVaultTable;

    @FXML
    private TableColumn goalTitleColumn;

    @FXML
    private TableColumn statusColumn;

    @FXML
    private Label goalReminderQueueLabel;

    @FXML
    private TableColumn goalDurationColumn;

    @FXML
    private Label dailyRewardLabel;

    @FXML
    private TextField goalMotivationField;

	
	// variable declarations
	private MainWindowController controller;  // Main controller window is declared so it can be accessed
	private Stage stage;                      // stage is declared - passed by controller
	//private Goal goal;                        // goal is declared - passed by controller
	public GoalList goalList;                // goal list declared - passed by controller
	private Boolean okClicked = false;        //default state   
	private Saves save = new Saves();
	private ObservableList goalSkeletonList;
	public MainList myMainList;
	

	
	
	/*================================================================================
	* Method Description: setMainWindowController - sets a controller so its components can be used
	* 
	* Arguments:
	* 	- controller - the controller whose components can be used
	*	- stage - stage used to hold FXML
	*	- goal - a goal to be manipulated
	*	- goalList - the current list of goals
	* 
	* Returns:
	* 	-nothing
	*///===============================================================================
	
	public void setMainWindowController(MainWindowController controller, 
			 		Stage stage, Goal goal, GoalList goalList,  ObservableList goalSkeletonList, MainList myMainList) {
		
		
		this.controller = controller;
		this.stage = stage;
		//this.goal = goal;
		this.goalList = goalList;
		this.goalSkeletonList = goalSkeletonList;
		goalList.mainGoalList();
		goalVaultTable.setItems(goalList.getGoalData());
		this.myMainList = myMainList;
		
	
		
		// condition if person is not null
		if(goal != null) {
			
			//FIX ME
			
		}
		
	}
	
	
	/*================================================================================
	* Method Description: setMainWindowController - sets a controller so its components can be used
	* 
	* Arguments:
	* 	- controller - the controller whose components can be used
	*	- stage - stage used to hold FXML
	*	- goal - a goal to be manipulated
	*	- goalList - the current list of goals
	* 
	* Returns:
	* 	-nothing
	*///===============================================================================
//	public void fillGoalDetails() {
//		goalTitleField.setText ( goal.getGoalTitle());
//		goalDescriptionField.setText ( goal.getGoalDescription() );
//		goalMotivationField.setText ( goal.getGoalMotivation() );
//		projectDurationField.setText ( goal.getProjectDue() );
//		habitDurationField.setText ( goal.getHabitDuration() );
//	}
	
//	public void fillGoalDetails() {
//		goalTitleField.setText(goal.getGoalTitle());
//		goalMotivationField.setText(goal.getGoalMotivation());
//		goalReminderQueue.setText(goal.getGoalReminderQueue());
//		goalSubstituteField.setText(goal.getGoalSubstituteField());
//		dailyRewardField.setText(goal.getDailyRewardField());
//		projectDurationField.setText(String.valueOf(goal.getProjectDue()));
//		
//	}
	
	
	/*================================================================================
	* Method Description: handleOK - handles OK clicked event
	* 
	* Arguments: none
	* 
	* Returns: nothing
	*///===============================================================================
	
	// buttons
//	@FXML
//	public void handleOk() {
//		
//		// if the goal has existing info
//		if(goal != null) {
//			//from setters in the person model
//			goal.setGoalTitle(goalTitleField.getText());
//			goal.setGoalDescription(goalDescriptionField.getText());
//			goal.setGoalMotivation(goalMotivationField.getText());
//			goal.setProjectDue(projectDurationField.getText());
//			goal.setHabitDuration(habitDurationField.getText());
//			goal.setTotalDays(Integer.parseInt(projectDurationField.getText())); // fix when Rowan converts to single
//			goal.setCurrentDay(1);
//			okClicked = true;
//		} else { // if the goal is new
//			Goal goal = new Goal( 
//			
//					goalTitleField.getText(),
//					goalDescriptionField.getText(),
//					goalMotivationField.getText(),
//					projectDurationField.getText(),
//					habitDurationField.getText(),
//					Integer.parseInt(projectDurationField.getText()), // fix when Rowan converts to single
//					1
//					);
//			goalList.getGoalData().add(goal);
//			System.out.println("FILE WRITTEN"); 
//			
//			
//		}
//		
//		
//		stage.close(); // close the stage
//		
//	}
	
//	@FXML
//	public void handleOk() {
//		
//		// if the goal has existing info
//		if(goal != null) {
//			//from setters in the person model
//			goal.setGoalTitle(goalTitleField.getText());
//			goal.setGoalMotivation(goalMotivationField.getText());
//			goal.setGoalReminderQueue(goalReminderQueue.getText());
//			goal.setGoalSubstituteField(goalSubstituteField.getText());
//			goal.setDailyRewardField(dailyRewardField.getText());
//			goal.setProjectDue(projectDurationField.getText());
//			goal.setTotalDays("2");
//			goal.setCurrentDay("2");
//			goal.setGoalStatus("status");
//			okClicked = true;
//		} else { // if the goal is new
//			Goal newGoal = new Goal(
//					goalTitleField.getText(),
//					goalMotivationField.getText(),
//					goalReminderQueue.getText(),
//					goalSubstituteField.getText(),
//					dailyRewardField.getText(),
//					projectDurationField.getText(),
//					"3",
//					"3",
//					"status"
//					);
//			goalList.getGoalData().add(newGoal);
//		}
//
//		//stage.close(); // close the stage
//		
//	}
	

	
	
	/*================================================================================
	* Method Description: isOkClicked 
	* 
	* Arguments: none
	* 
	* Returns: boolean
	*///===============================================================================
	public boolean isOkClicked() {
		return okClicked;
	}
	
	
	/*================================================================================
	* Method Description: handleCancel - closes window and discard changes
	* 
	* Arguments: none
	* 
	* Returns: none
	*///===============================================================================
	@FXML
	public void handleCancel() {
		stage.close();
	}
	
	@FXML public void handleMouseClick(MouseEvent arg0) {
		Goal goal = goalVaultTable.getSelectionModel().getSelectedItem();
		
		goalTitleField.setText(goal.getGoalTitle());
		goalMotivationField.setText(goal.getGoalMotivation());
		goalReminderQueue.setText(goal.getGoalReminderQueue());
		goalSubstituteField.setText(goal.getGoalSubstituteField());
		dailyRewardField.setText(goal.getDailyRewardField());
		projectDurationField.setText(goal.getProjectDue());
		
	}
	
	@FXML
	public void handleHabit() {
		 
		goalTitleLabel.setText("I WANT TO DO THIS DAILY");
		goalMotivationLabel.setText("IT IS IMPORTANT BECAUSE...");
		goalReminderQueueLabel.setText("MY REMINDER IS");
		goalSubstituteLabel.setText("IF I CAN'T I WILL DO THIS");
		dailyRewardLabel.setText("DAILY REWARD");
		projectDurationLabel.setText("DAYS TO TRACK");
	}
	

	
	@FXML
	public void handleProject() {
		goalTitleLabel.setText("I WANT TO DO THIS...");
		goalMotivationLabel.setText("IT IS IMPORTANT BECAUSE...");
		goalReminderQueueLabel.setText("OPTIONAL NOTES");
		goalSubstituteLabel.setText("OPTIONAL NOTES");
		dailyRewardLabel.setText("REWARD FOR COMPLETION");
		projectDurationLabel.setText("I WILL BE DONE IN __ DAYS");
	}
	
	//Creating Observable Array
	@Override
    public void initialize (URL arg0, ResourceBundle arg1) {
	//public void addVault() {
        final ObservableList<Goal> goalVaultList = FXCollections.observableArrayList();
		
		//goalList.getGoalData().add(goal);
        
	//associate Data with columns
	goalTitleColumn.setCellValueFactory(new PropertyValueFactory<Goal, String>("goalTitle"));
	goalDurationColumn.setCellValueFactory(new PropertyValueFactory<Goal, String>("projectDue"));
	rewardColumn.setCellValueFactory(new PropertyValueFactory<Goal, String>("dailyReward"));
	statusColumn.setCellValueFactory(new PropertyValueFactory<Goal, String>("goalStatus"));
	
	
	
	}
	
	@FXML
	public void handleSaveEdits() {
		
		Goal inv = goalVaultTable.getSelectionModel().getSelectedItem();
		int index = goalVaultTable.getSelectionModel().getSelectedIndex();
		goalList.getGoalData().remove(inv);
		
    	Goal goal = new Goal( " ", " ", " ", " ", " ", " ", " ", " ", " ");
		goal.setGoalTitle(goalTitleField.getText());
		goal.setGoalMotivation(goalMotivationField.getText());
		goal.setGoalReminderQueue(goalReminderQueue.getText());
		goal.setGoalSubstituteField(goalSubstituteField.getText());
		goal.setDailyRewardField(dailyRewardField.getText());
		goal.setProjectDue(projectDurationField.getText());
		goal.setCurrentDay("1");
		goal.setTotalDays(projectDurationField.getText());
		goal.setGoalStatus("in");
		
		//============================
		goalList.getGoalData().add(index, goal);
		
		
	}
	
	@FXML
	public void displayGoal() {
		
		Goal goal = goalVaultTable.getSelectionModel().getSelectedItem();
		
		goalTitleField.setText(goal.getGoalTitle());
		goalMotivationField.setText(goal.getGoalMotivation());
		goalReminderQueue.setText(goal.getGoalReminderQueue());
		goalSubstituteField.setText(goal.getGoalSubstituteField());
		dailyRewardField.setText(goal.getDailyRewardField());
		projectDurationField.setText(goal.getProjectDue());
		
		
	}
	
	@FXML
	public void handleAddDungeon() {
		Goal myGoal = goalVaultTable.getSelectionModel().getSelectedItem();
		myMainList.getGoalData().add(myGoal);
	}
	
    @FXML
    public void handleAddVault() {
    	Goal goal = new Goal( " ", " ", " ", " ", " ", " ", " ", " ", " ");
    		goal.setGoalTitle(goalTitleField.getText());
    		goal.setGoalMotivation(goalMotivationField.getText());
    		goal.setGoalReminderQueue(goalReminderQueue.getText());
    		goal.setGoalSubstituteField(goalSubstituteField.getText());
    		goal.setDailyRewardField(dailyRewardField.getText());
    		goal.setProjectDue(projectDurationField.getText());
    		goal.setCurrentDay("1");
    		goal.setTotalDays(projectDurationField.getText());
    		goal.setGoalStatus("in");
    		
    		//============================
    		goalList.getGoalData().add(goal);
    		
    		
    		
    		//addVault();
    		//initialize(null, null);
    }
    
	@FXML // Button
	public void handleDelete() {
		
		int index = goalVaultTable.getSelectionModel().getSelectedIndex(); // an item is selected
		goalList.getGoalData().remove(index); // selected item is removed

	}
	


	

	}

	
	



