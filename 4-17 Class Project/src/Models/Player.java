package Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import classes
import application.MainWindowController;
import application.Reward;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

/*================================================================================
* Class Description: Player - a class containing attributes of the player model 
* 
*///===============================================================================
public class Player implements Serializable{
	
	// declare properties
	private String playerName;
	private int playerLevel;
	private int playerGold;
	private int playerExperience;
	
	// declare controller
	public MainWindowController controller;
	
	// set the controller so its components can be used
	public void setMainWindowController(MainWindowController controller) {
		this.controller = controller;
	}
	

	
	// constructor
	public Player(String playerName, int playerLevel, int playerGold, int playerExperience) {
		this.playerName = playerName;
		this.playerLevel = playerLevel;
		this.playerGold = playerGold;
		this.playerExperience = playerExperience;
	}
	
	/*================================================================================
	* Method Description: calculateLevel - calculates player level 
	* 
	* Arguments: none
	* 
	* Returns: none
	*///===============================================================================
	
	public void calculateLevel() {   
		
			if (playerExperience < 200) playerLevel = 1;
		
			if ((playerExperience > 200) && (playerExperience < 400)) playerLevel = 2;

			if ((playerExperience > 400) && (playerExperience < 600)) playerLevel = 3;
				
			if ((playerExperience > 600) && (playerExperience < 800)) playerLevel = 4;
			
			if ((playerExperience > 1000) && (playerExperience < 1200)) playerLevel = 5;
				
			if ((playerExperience > 1200) && (playerExperience < 1400)) playerLevel = 6;
				
			if ((playerExperience > 1400) && (playerExperience < 1600)) playerLevel = 7;
				
			if ((playerExperience > 1600) && (playerExperience < 1800)) playerLevel = 8;
				
			if ((playerExperience > 1800) && (playerExperience < 2000)) playerLevel = 9;
				
			if (playerExperience > 2000) playerLevel = 10;
	}
	
	
	/*================================================================================
	* Method Description: changeAvatar - changes player avatar image 
	* 
	* Arguments: none
	* 
	* Returns: none
	*///===============================================================================
	
	public void changeAvatar() {   
		Image image1 = new Image("\\images\\level 1 .png"); 
		Image image2 = new Image("\\images\\level 1 .png"); 
		Image image3 = new Image("\\images\\level 2 .png"); 
		Image image4 = new Image("\\images\\level 2 .png"); 
		Image image5 = new Image("\\images\\level 3 .png"); 
		Image image6 = new Image("\\images\\level 3 .png"); 
		Image image7 = new Image("\\images\\level 4 .png"); 
		Image image8 = new Image("\\images\\level 4 .png"); 
		Image image9 = new Image("\\images\\level 5 .png"); 
		Image image10 = new Image("\\images\\level 5 .png"); 
		
	
		if( playerLevel == 1) controller.setLevelImage(image1);
		if( playerLevel == 2) controller.setLevelImage(image2);
		if( playerLevel == 3) controller.setLevelImage(image3);
		if( playerLevel == 4) controller.setLevelImage(image4);
		if( playerLevel == 5) controller.setLevelImage(image5);
		if( playerLevel == 6) controller.setLevelImage(image6);
		if( playerLevel == 7) controller.setLevelImage(image7);
		if( playerLevel == 8) controller.setLevelImage(image8);
		if( playerLevel == 9) controller.setLevelImage(image9);
		if( playerLevel == 10) controller.setLevelImage(image10);
		
	}
	
	// getters ====================================================
	public String getPlayerName() { return playerName; }
	public int getPlayerLevel() { return playerLevel; }
	public int getPlayerGold() { return playerGold; }
	public int getPlayerExperience() { return playerExperience; }
	
	
	// setters ====================================================
	public void setPlayerName(String playerName) { this.playerName = playerName; }
	public void setPlayerLevel(int playerLevel) { this.playerLevel = playerLevel; }
	public void setPlayerGold(int playerGold) { this.playerGold = playerGold; }
	public void setPlayerExperience(int playerExperience) { this.playerExperience = playerExperience; }
		
	}
	

	


