
/* *********************************************************************************
* Written by Paul Wilson with support from team.
*///********************************************************************************
package application;

// imported classes
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import Models.Goal;
import Models.Player;
import Models.junk.Person;

/*================================================================================
* Class Description: Main - main class
*
*///===============================================================================

public class Main extends Application {
	private Stage primarystage;  // declare stage
	
	
	/*================================================================================
	* Method Description: start
	* 
	* Arguments: - stage
	* 
	* Returns: - nothing
	*///===============================================================================
	
	@Override  
	public void start(Stage primaryStage) {
		this.primarystage = primaryStage;    // passed stage is set to primary stage
		mainWindow();                        // method main window is run
	}

	/*================================================================================
	* Method Description: mainWindow - Opens the initial screen of the program
	* 
	* Arguments: - none
	* 
	* Returns: - nothing
	*///===============================================================================
	
	public void mainWindow() {
		try {
			
			// make scene
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainWindowView.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// set controller
			MainWindowController controller = loader.getController();
			controller.setMain(this);
			

			// put scene onto stage
			primarystage.setScene(scene);
			primarystage.setResizable(false);
			primarystage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*================================================================================
	* Method Description: main - the main method. it is overriden by "start"
	* 
	* Arguments: - String[] args
	* 
	* Returns: - nothing
	*///===============================================================================
	public static void main(String[] args) {
		launch(args);
		

	}
}
