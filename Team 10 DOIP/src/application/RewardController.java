package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class RewardController implements Initializable {

	ObservableList<Reward> shop = FXCollections.observableArrayList();
	ObservableList<Reward> inventory = FXCollections.observableArrayList();
	
	@FXML
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
		
		
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		pRewardCol.setCellValueFactory(new PropertyValueFactory<Reward, String>("name"));
		pDescriptionCol.setCellValueFactory(new PropertyValueFactory<Reward, String>("description"));
		pCostCol.setCellValueFactory(new PropertyValueFactory<Reward, Integer>("cost"));
		
	}
	
	
	
	
	
	
}
