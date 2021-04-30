package Models;

import java.io.Serializable;

//imported classes
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;

/*================================================================================
* Class Description: Goal - Defines model for the Goals class
* 
* 
*///===============================================================================
public class Goal implements Serializable {
	

	
	// declare properties
	public String goalStatus; 
	public String goalTitle;         // A short description of the goal
	public String goalMotivation;    // A description of motivations for the goal
	public String goalReminder;
	public String goalSubstitute;
	public String dailyReward;
	public String projectDue;        // the amount of days till a project is completed
	public ProgressBar progress;     // a progress bar that tracks daily progress
	public CheckBox complete;        // a check box to track completion 
	public CheckBox inProgress;      // a check box to track progress for a project
	public String totalDays = "";        // total days till completion
	public String currentDay = "";        // current day 
	
	
	// constructor
	public Goal(String goalTitle, String goalMotivation, String goalReminder, String goalSubstitute,
			 String dailyReward, String ProjectDue, String currentDay, String totalDays, String goalStatus) {
		this.goalTitle = goalTitle;                  
		this.goalMotivation =  goalMotivation;
		this.goalReminder = goalReminder;
		this.goalSubstitute = goalSubstitute;
		this.dailyReward = dailyReward;
		this.projectDue =  projectDue;
		this.progress = new ProgressBar();
		//set progress bar
		this.complete = new CheckBox();
		this.inProgress = new CheckBox();
		this.currentDay = currentDay;
		this.totalDays = totalDays;
		this.goalStatus = goalStatus;
	}
	
	// getters
	public String getGoalTitle()       { return goalTitle; }
	public String getGoalMotivation()  { return  goalMotivation; }
	public String getGoalReminderQueue()  { return  goalReminder; }
	public String getGoalSubstituteField()  { return  goalSubstitute; }
	public String getDailyRewardField()  { return  dailyReward; }
	public String getProjectDue()         { return projectDue; }
	public ProgressBar getProgress()   {return progress; }
	public CheckBox getComplete ()     {return complete; }
	public CheckBox getInProgress ()   {return inProgress; }
	public String getCurrentDay ()        { return currentDay; }
	public String getTotalDays ()         {return totalDays;}
	public String getGoalStatus ()         {return goalStatus;}
	
	
	// setters
	public void setGoalTitle(String goalTitle) { this.goalTitle = goalTitle; }
	public void setGoalMotivation(String goalMotivation) { this.goalMotivation = goalMotivation; }
	public void setGoalReminderQueue(String goalReminder) { this.goalReminder = goalReminder; }
	public void setGoalSubstituteField(String goalSubstitute) { this.goalSubstitute = goalSubstitute; }
	public void setDailyRewardField(String dailyReward) { this.dailyReward = dailyReward; }
	public void setProjectDue(String projectDue) { this.projectDue = projectDue; }
	public void setProgress(ProgressBar progress) { this.progress = progress;}
	public void setComplete(CheckBox complete) { this.complete = complete;}
	public void setInProgress(CheckBox inProgress) { this.inProgress = inProgress;}
	public void setCurrentDay (String currentday) {this.currentDay = currentday;}
	public void setTotalDays (String totalDays) {this.totalDays = totalDays;}
	public void setGoalStatus (String goalStatus) {this.goalStatus = goalStatus;}
		
	}
	

	


