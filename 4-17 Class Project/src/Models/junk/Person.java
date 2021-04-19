package Models.junk;

// imported classes
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;

/*================================================================================
* Class Description: Person - Model class used to hold Person info
* 
* Arguments: - none
* 
* Returns: - nothing
*///===============================================================================
public class Person {
	
	// declare properties
	private String firstName;
	private String lastName;
	private String phone;
	private String city;
	private String postCode;
	private ProgressBar progress;
	private CheckBox complete;
	private CheckBox inProgress;
	private int totalDays = 0;
	private int currentDay = 0;
	
	// constructor
	public Person(String firstName, String lastName, String phone, String city, String postCode, int currentDay, int totalDays) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.city = city;
		this.postCode =  postCode;
		this.progress = new ProgressBar();
		this.complete = new CheckBox();
		this.inProgress = new CheckBox();
		this.currentDay = currentDay;
		this.totalDays = totalDays;
	}
	
	// getters
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getPhone() { return phone; }
	public String getCity() { return city; }
	public String getPostCode() { return postCode; }
	public ProgressBar getProgress() {return progress; }
	public CheckBox getComplete () {return complete; }
	public CheckBox getInProgress () {return inProgress; }
	public int getCurrentDay () { return currentDay; }
	public int getTotalDays () {return totalDays;}
	
	
	// setters
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setCity(String city) { this.city = city; }
	public void setPostCode(String PostCode) { this.postCode = postCode; }
	public void setProgress(ProgressBar progress) { this.progress = progress;}
	public void setComplete(CheckBox complete) { this.complete = complete;}
	public void setInProgress(CheckBox inProgress) { this.inProgress = inProgress;}
	public void setCurrentDay (int currentday) {this.currentDay = currentday;}
	public void setTotalDays (int totalDays) {this.totalDays = totalDays;}
		
	}
	

	


