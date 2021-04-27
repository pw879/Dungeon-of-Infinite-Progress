package application;


import java.io.File;
import application.RewardModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;


public class RewardController implements Initializable {

	ObservableList<Reward> shop = FXCollections.observableArrayList();
	ObservableList<Reward> inventory = FXCollections.observableArrayList();
	
	
	@FXML
	private Label gold;
	@FXML
	private TableView<Reward> purchaseTable = new TableView<>();
	@FXML
	private TableView<Reward> sellTable = new TableView<>();
	@FXML
	private TextArea label;
	@FXML
	private TextArea description;
	@FXML
	private TextArea cost;
	@FXML
	private TableColumn<Reward, String> pRewardCol;
	@FXML
	private TableColumn<Reward, Integer> pCostCol;
	@FXML
	private TableColumn<Reward, String> sRewardCol;
	@FXML
	private TableColumn<Reward, Integer> sCostCol;
	@FXML
	private TableColumn<Reward, String> pIconCol;
	@FXML
	private TableColumn<Reward, String> sIconCol;
	@FXML
	private VBox imageBox = new VBox();
	@FXML
	private Label filePath;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label suggestedGold;
	@FXML
	private TextArea lastTime;
	@FXML
	private TextArea goldCost;
	
	private File file;
	
	Alert a = new Alert(AlertType.NONE);
	
//	ArrayList<Reward> currentReward = new ArrayList<>();
	
	public void createReward() throws MalformedURLException {
	
		boolean validInput = RewardModel.validInput(label, description, cost);
		if(validInput) {
			RewardModel.createNewReward(label, description, cost, file, shop, purchaseTable);
			imageBox.setStyle("-fx-background-color: white;");
			label.clear();
			description.clear();
			cost.clear();
			filePath.setText("");
		}
					
					
        
	}
	
	public void buyReward() {
		
		RewardModel.purchaseReward(purchaseTable, inventory, sellTable, gold);
		
	}
	
	public void sellReward() {
		RewardModel.sellReward(purchaseTable, inventory, sellTable, descriptionLabel, gold);
	}
	
	@FXML
	public void showDescription() {
		
		RewardModel.showRewardDescription(purchaseTable, descriptionLabel);
		
	}
	
	@FXML
	public void suggestedGoldCalc() {
		
		RewardModel.getSuggestedGold(lastTime, cost, suggestedGold);
			
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		File save = new File("rewardsave.ser");
		
		purchaseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		sellTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		pIconCol.setCellValueFactory(new PropertyValueFactory<>("img"));
		pIconCol.setPrefWidth(90);
		pRewardCol.setCellValueFactory(new PropertyValueFactory<Reward, String>("name"));
		pCostCol.setCellValueFactory(new PropertyValueFactory<Reward, Integer>("cost"));

		sIconCol.setCellValueFactory(new PropertyValueFactory<>("img"));
		sIconCol.setPrefWidth(90);
		sRewardCol.setCellValueFactory(new PropertyValueFactory<Reward, String>("name"));
		sCostCol.setCellValueFactory(new PropertyValueFactory<Reward, Integer>("cost"));
		
		imageBox.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != imageBox
                        && event.getDragboard().hasFiles()) {
                    /* allow for both copying and moving, whatever user chooses */
                	imageBox.setStyle("-fx-border-color: red;"
                            + "-fx-border-width: 5;"
                            + "-fx-background-color: #C6C6C6;"
                            + "-fx-border-style: solid;");
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
		});
		
		imageBox.setOnDragDropped(new EventHandler<DragEvent>() {

	            @Override
	            public void handle(DragEvent event) {
	                Dragboard db = event.getDragboard();
	                boolean success = false;
	                if (db.hasFiles()) {
	                	file = db.getFiles().get(0);
	                	filePath.setText(file.getPath());
	                    success = true;
	                }
	                /* let the source know whether the string was successfully 
	                 * transferred and used */
	                event.setDropCompleted(success);
	                event.consume();
	            }
	        });
		

       
		gold.setText("100");
		
		
		
	}
	
	
	
	
	
	
	
	
}
