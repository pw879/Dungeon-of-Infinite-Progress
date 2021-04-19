package application;

// imported classes
import Models.Goal;
import Models.GoalList;
import Models.junk.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*================================================================================
* Class Description: New Goal Controller
* This is a controller class that handles the control of the main panel
*
* 	There are no arguments.
*///===============================================================================
public class NewGoalController {
	
	// FXMLdeclarations
    @FXML
    private TextField goalMotivationField;        // a field for user input of a new goal
    
    @FXML
    private TextField goalDescriptionField;       // a field for user input of a new goal

    @FXML
    private TextField goalTitleField;             // a field for user input of a new goal
    
    @FXML
    private TextField projectDurationField;       // a field for user input of a new goal

    @FXML
    private TextField habitDurationField;          // a field for user input of a new goal

	
	// variable declarations
	private MainWindowController controller;  // Main controller window is declared so it can be accessed
	private Stage stage;                      // stage is declared - passed by controller
	private Goal goal;                        // goal is declared - passed by controller
	private GoalList goalList;                // goal list declared - passed by controller
	private Boolean okClicked = false;        //default state   

	
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
			 		Stage stage, Goal goal, GoalList goalList) {
		
		
		this.controller = controller;
		this.stage = stage;
		this.goal = goal;
		this.goalList = goalList;
		
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
	public void fillPersonDetails() {
		goalTitleField.setText(goal.getGoalTitle());
		goalDescriptionField.setText(goal.getGoalDescription());
		goalMotivationField.setText(goal.getGoalMotivation());
		projectDurationField.setText(String.valueOf(goal.getProjectDue()));
		habitDurationField.setText(String.valueOf(goal.getProjectDue()));
	}
	
	
	/*================================================================================
	* Method Description: handleOK - handles OK clicked event
	* 
	* Arguments: none
	* 
	* Returns: nothing
	*///===============================================================================
	
	// buttons
	@FXML
	public void handleOk() {
		
		// if the goal has existing info
		if(goal != null) {
			//from setters in the person model
			goal.setGoalTitle(goalTitleField.getText());
			goal.setGoalDescription(goalDescriptionField.getText());
			goal.setGoalMotivation(goalMotivationField.getText());
			goal.setProjectDue(projectDurationField.getText());
			goal.setHabitDuration(habitDurationField.getText());
			goal.setTotalDays(2);
			goal.setCurrentDay(1);
			okClicked = true;
		} else { // if the goal is new
			Goal newGoal = new Goal(
					goalTitleField.getText(),
					goalDescriptionField.getText(),
					goalMotivationField.getText(),
					projectDurationField.getText(),
					habitDurationField.getText(),
					1,
					4
					);
			goalList.getGoalData().add(newGoal);
		}

		stage.close(); // close the stage
		
	}
	
	
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

}
