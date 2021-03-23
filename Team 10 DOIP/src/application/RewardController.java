package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RewardController {

	ObservableList<Reward> shop = FXCollections.observableArrayList();
	ObservableList<Reward> inventory = FXCollections.observableArrayList();
	
	@FXML
	TableView<Reward> purchaseTable = new TableView<>();
	TableView<Reward> sellTable = new TableView<>();
	TextField label;
	TextField description;
	TextField cost;
	
	
	
	public void createReward() {
		ObservableList<Reward> rewards = FXCollections.observableArrayList();
		rewards.add(new Reward(label.getText(), description.getText(), Integer.parseInt(cost.getText())));
		purchaseTable.setItems(rewards);
	}
	
	
	
	
}
