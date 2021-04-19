package Models;

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
	
	
	/*================================================================================
	* Method Description: mainGoalList - values for the default empty goal list
	* 
	* Returns: nothing
	*///===============================================================================
	public void mainGoalList() {
		
		// an item is added
		 goalData.add(new Goal("Runnning", "start running", "for health", "1", "3",1,4));
		 

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


}
