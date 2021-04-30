package Models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

// classes to be imported
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/*================================================================================
*Class Description: GoalList
* 
*///===============================================================================
public class GoalList {
	
	// declare an observable List named "goal data"
    private ObservableList<Goal> goalData = FXCollections.observableArrayList();
	Saves save2 = new Saves();
	
	private ObservableList<GoalSkeleton> skeleton = FXCollections.observableArrayList();
	
	/*================================================================================
	* Method Description: mainGoalList - values for the default empty goal list
	* 
	* Returns: nothing
	*///===============================================================================
	public void mainGoalList() {
		
		
		try {
			FileInputStream fis = new FileInputStream("goalSkeletons.ser");
			ObjectInputStream in = new ObjectInputStream(fis);
			List<GoalSkeleton> list = (List<GoalSkeleton>) in.readObject();
			skeleton = FXCollections.observableList(list);
			in.close();
			fis.close();
			System.out.println("SKELETON LOADED");
		}
		catch (Exception e) {
			e.printStackTrace(); System.out.println("SKELETON PROBLEM"); 
		}
		for( GoalSkeleton goalskeleton : skeleton ) {
			Goal goal = new Goal(goalskeleton.skeletonGoalTitle,
					//goalskeleton.skeletonGoalDescription,
					goalskeleton.getSkeletonGoalMotivation(),
					goalskeleton.getSkeletonGoalReminder(),
					goalskeleton.getSkeletonGoalSubstitute(),
					goalskeleton.getSkeletonDailyReward(),
					goalskeleton.getSkeletonProjectDue(),
					goalskeleton.getSkeletonCurrentDay(),
					goalskeleton.getSkeletonTotalDay(),
					goalskeleton.getSkeletonGoalStatus()
					);
			goalData.add(goal);
		}
			
			
			System.out.println("GOALS TRANSFERRED"); 
	}
		 

	
	
	/*================================================================================
	* Method Description: getGoalData - a method that returns this list
	* 
	* Arguments: none
	* 
	* Returns: Observable List
	*///===============================================================================
	public ObservableList<Goal> getGoalData() {
		
		return goalData; // this list is returned
	}
	
	/*================================================================================
	* Method Description: setGoalData - a method that returns this list
	* 
	* Arguments: none
	* 
	* Returns: Observable List
	*///===============================================================================
	
	public void setGoalData (ObservableList goalList) {
		this.goalData = goalList;
	}
}
















