package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class RewardController implements Initializable {

	ObservableList<Reward> shop = FXCollections.observableArrayList();
	ObservableList<Reward> inventory = FXCollections.observableArrayList();
	
	@FXML
	public Label gold;
	public TableView<Reward> purchaseTable = new TableView<>();
	public TableView<Reward> sellTable = new TableView<>();
	public TextArea label;
	public TextArea description;
	public TextArea cost;
	public TableColumn<Reward, String> pRewardCol;
	public TableColumn<Reward, String> pDescriptionCol;
	public TableColumn<Reward, Integer> pCostCol;
	public TableColumn<Reward, String> sRewardCol;
	public TableColumn<Reward, String> sDescriptionCol;
	public TableColumn<Reward, Integer> sCostCol;
	
	public void createReward() {
		Reward newR = new Reward(label.getText(), description.getText(), Integer.parseInt(cost.getText()));
		shop.add(newR);
		
		
		

		purchaseTable.setItems(shop);
		
		
		
		label.clear();
		description.clear();
		cost.clear();
		
	}
	
	public void buyReward() {
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
	
	public void sellReward() {
		ObservableList<Reward> selected, allItems;
		Reward sell = sellTable.getSelectionModel().getSelectedItem();
		allItems = sellTable.getItems();
		selected = sellTable.getSelectionModel().getSelectedItems();
		
		selected.forEach(allItems::remove);
		
		int rewardCost = sell.getCost();
		
		int goldBalance = Integer.parseInt(gold.getText()) + rewardCost;
		
		gold.setText(String.valueOf(goldBalance));
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		pRewardCol.setCellValueFactory(new PropertyValueFactory<Reward, String>("name"));
		pDescriptionCol.setCellValueFactory(new PropertyValueFactory<Reward, String>("description"));
		pCostCol.setCellValueFactory(new PropertyValueFactory<Reward, Integer>("cost"));
		
		sRewardCol.setCellValueFactory(new PropertyValueFactory<Reward, String>("name"));
		sDescriptionCol.setCellValueFactory(new PropertyValueFactory<Reward, String>("description"));
		sCostCol.setCellValueFactory(new PropertyValueFactory<Reward, Integer>("cost"));
		
		gold.setText("100");
		
	}
	
	
	
	
	
	
}
