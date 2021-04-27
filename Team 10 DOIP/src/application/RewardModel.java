package application;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RewardModel {
	
	static Alert a = new Alert(AlertType.NONE);
	
	public static void createNewReward(TextArea label, TextArea description, TextArea cost, File file,
										ObservableList<Reward> shop, TableView<Reward> purchaseTable) throws MalformedURLException{
   
		try {
			ImageView img1 = new ImageView(new Image(file.toURI().toURL().toExternalForm()));
			img1.setPreserveRatio(true);
			Reward newR = new Reward(img1, label.getText(), description.getText(), Integer.parseInt(cost.getText()));

			shop.add(newR);
			purchaseTable.setItems(shop);
			

		} catch (NullPointerException n) {
			a.setAlertType(AlertType.WARNING); // makes the alarm a warning type of alarm
			a.setContentText("The image wasn't found or wasn't dragged into the program.");
			a.show();
		} catch (IllegalArgumentException e) {
			a.setAlertType(AlertType.WARNING); // makes the alarm a warning type of alarm
			a.setContentText("You inputted a non numeric value in the cost field.");
			a.show();
		}
		
	}
	
	
	public static boolean validInput( TextArea label, TextArea description, TextArea cost) {
		if(label.getText().trim().isEmpty() || description.getText().trim().isEmpty() || cost.getText().trim().isEmpty()) {
			a.setAlertType(AlertType.WARNING); // makes the alarm a warning type of alarm
        	a.setContentText("Please don't leave any of the text fields empty");
        	a.show();
        	return false;
        } 
		
		return true;
	}
	
	public static void purchaseReward(TableView<Reward> purchaseTable, ObservableList<Reward> inventory, 
										TableView<Reward> sellTable, Label gold) {
		ObservableList<Reward> selected, allItems;
		Reward inv = purchaseTable.getSelectionModel().getSelectedItem();
		allItems = purchaseTable.getItems();
		selected = purchaseTable.getSelectionModel().getSelectedItems();
		
		inventory.add(inv);
		
		
		
		sellTable.setItems(inventory);
		selected.forEach(allItems::remove);
		
		
		
		int rewardCost = inv.getCost();
		
		int goldBalance = Integer.parseInt(gold.getText()) - rewardCost;
		
		gold.setText(String.valueOf(goldBalance));
	}

	public static void sellReward(TableView<Reward> purchaseTable, ObservableList<Reward> inventory,
									TableView<Reward> sellTable, Label descriptionLabel, Label gold) {
		
		ObservableList<Reward> selected, allItems;
		Reward sell = sellTable.getSelectionModel().getSelectedItem();
		allItems = sellTable.getItems();

		descriptionLabel.setText(sell.getDescription());
		selected = sellTable.getSelectionModel().getSelectedItems();

		selected.forEach(allItems::remove);

		int rewardCost = sell.getCost();

		int goldBalance = Integer.parseInt(gold.getText()) + rewardCost;

		gold.setText(String.valueOf(goldBalance));

		descriptionLabel.setText("");
	}
	
	public static void showRewardDescription(TableView<Reward> purchaseTable, Label descriptionLabel) {
		Reward inv = purchaseTable.getSelectionModel().getSelectedItem();
		
		descriptionLabel.setText(inv.getDescription());
	}
	
	public static void getSuggestedGold(TextArea lastTime, TextArea cost, Label suggestedGold) {
		int last, dollars;
		if(!lastTime.getText().isEmpty() || !lastTime.getText().matches("[0-9]") &&
				!cost.getText().isEmpty() || !cost.getText().matches("[0-9]")) {
			last = Integer.parseInt(lastTime.getText());
			dollars = Integer.parseInt(cost.getText());
			
			
			int result = last * 40 + (dollars * 80);
			suggestedGold.setText(String.valueOf(result));
		} 
	}
}
