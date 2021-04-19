package Models;

//imported classes
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;

/*================================================================================
* Class Description: Goal - Defines model for the Goals class
* 
* 
*///===============================================================================
public class Goal {
	
	// declare properties
	private String goalTitle;         // A short description of the goal
	private String goalDescription;   // A longer description opf the goal
	private String goalMotivation;    // A description of motivations for the goal
	private String habitDuration;     // the length of days a habit will be maintained
	private String projectDue;        // the amount of days till a project is completed
	private ProgressBar progress;     // a progress bar that tracks daily progress
	private CheckBox complete;        // a check box to track completion 
	private CheckBox inProgress;      // a check box to track progress for a project
	private int totalDays = 0;        // total days till completion
	private int currentDay = 0;        // current day 
	
	// constructor
	public Goal(String goalTitle, String goalDescription, String goalMotivation, 
			String HabitDuration, String ProjectDue, int currentDay, int totalDays) {
		this. goalTitle = goalTitle;               
		this. goalDescription = goalDescription;   
		this. goalMotivation =  goalMotivation;
		this. habitDuration = habitDuration;
		this. projectDue =  projectDue;
		this.progress = new ProgressBar();
		this.complete = new CheckBox();
		this.inProgress = new CheckBox();
		this.currentDay = currentDay;
		this.totalDays = totalDays;
	}
	
	// getters
	public String getGoalTitle()       { return goalTitle; }
	public String getGoalDescription() { return goalDescription; }
	public String getGoalMotivation()  { return  goalMotivation; }
	public String getHabitDuration()      { return habitDuration; }
	public String getProjectDue()         { return projectDue; }
	public ProgressBar getProgress()   {return progress; }
	public CheckBox getComplete ()     {return complete; }
	public CheckBox getInProgress ()   {return inProgress; }
	public int getCurrentDay ()        { return currentDay; }
	public int getTotalDays ()         {return totalDays;}
	
	
	// setters
	public void setGoalTitle(String goalTitle) { this.goalTitle = goalTitle; }
	public void setGoalDescription(String goalDescription) { this.goalDescription = goalDescription; }
	public void setGoalMotivation(String goalMotivation) { this.goalMotivation = goalMotivation; }
	public void setHabitDuration(String habitDuration ) { this.habitDuration = habitDuration; }
	public void setProjectDue(String projectDue) { this.projectDue = projectDue; }
	public void setProgress(ProgressBar progress) { this.progress = progress;}
	public void setComplete(CheckBox complete) { this.complete = complete;}
	public void setInProgress(CheckBox inProgress) { this.inProgress = inProgress;}
	public void setCurrentDay (int currentday) {this.currentDay = currentday;}
	public void setTotalDays (int totalDays) {this.totalDays = totalDays;}
		
	}
	

	


