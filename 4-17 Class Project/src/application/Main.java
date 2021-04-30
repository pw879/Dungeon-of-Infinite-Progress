
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
import javafx.stage.StageStyle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import Models.Goal;
import Models.Player;
import Models.QuartzTimerJob;
import Models.QuartzListener;
import Models.junk.Person;

/*================================================================================
* Class Description: Main - main class
*
*///==============================================================================

public class Main extends Application {
	private Stage primarystage;  // declare stage
	MainWindowController myController;
	public String test;
	public void setTime(String time) {
		
		myController.myTimer.setText(time);
	}
	
	
	
	
	/*================================================================================
	* Method Description: start
	* 
	* Arguments: - stage
	* 
	* Returns: - nothing
	*///===============================================================================
	
	@Override  
	public void start(Stage primaryStage) throws SchedulerException /* throws SchedulerException */ {
		this.primarystage = primaryStage;    // passed stage is set to primary stage
		primaryStage.initStyle(StageStyle.UTILITY);
		mainWindow();                        // method main window is run
		String cronExpression = "0 0/1 * 1/1 * ? *"; // CHANGE BACK
		
		JobKey jobKey = new JobKey("QuartzTimerJob", "group1");  
		//define a job and tie it to our job class 
		JobDetail myJob = JobBuilder.newJob(QuartzTimerJob.class).withIdentity(jobKey).build(); 
		
		
		//draggable

		
		
		//trigger for quartz
		//simple trigger
		//Trigger t1 = TriggerBuilder.newTrigger().withIdentity("Simple Trigger").startNow().build();
		
		// cron trigger 1 minute //BELOW THIS ONE
		Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
		
		// cron trigger every 5 seconds
		//Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger", "group1").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(05).repeatForever()).build();
		
		//create a scheduler
		Scheduler sc = StdSchedulerFactory.getDefaultScheduler(); //CHANGE BACK
		//sc.getContext().put ());  
		sc.getListenerManager().addJobListener(new QuartzListener(this, myController), KeyMatcher.keyEquals(jobKey)); 
		
		
		//start your scheduler
		sc.start(); 
		sc.scheduleJob(myJob, t1); 

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
			myController = controller;
			
			
			

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
	
	/*================================================================================
	* Method Description: getStage. gets satage for close in mainController
	* 
	* Arguments: - String[] args
	* 
	* Returns: - nothing
	*///===============================================================================
	public Stage getStage() {
		return primarystage;
	}
}
	
