package application;


import java.io.File;
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
		
		if(label.getText().trim().isEmpty() || description.getText().trim().isEmpty() || cost.getText().trim().isEmpty()) {
        	a.setAlertType(AlertType.WARNING); // makes the alarm a warning type of alarm
        	a.setContentText("Please don't leave any of the text fields empty");
        	a.show();
        } else {
        	
        		    try {
        			ImageView img1 = new ImageView(new Image(file.toURI().toURL().toExternalForm()));
        			 img1.setFitHeight(200);
        			 img1.setFitWidth(200);
        			 img1.setPreserveRatio(true);
					Reward newR = new Reward(img1, label.getText(), description.getText(), Integer.parseInt(cost.getText()));
					
//					try {
//				         FileOutputStream fileOut = new FileOutputStream("/tmp/employee.ser");
//				         ObjectOutputStream out = new ObjectOutputStream(fileOut);
//				         currentReward.add(newR);
//				         out.writeObject(currentReward);
//				         out.close();
//				         fileOut.close();
//				         System.out.printf("Serialized data is saved in /tmp/employee.ser");
//				      } catch (IOException i) {
//				         i.printStackTrace();
//				      }
					
					shop.add(newR);
					
					
					purchaseTable.setItems(shop);
					
	                imageBox.setStyle("-fx-background-color: white;");
					
	                
	                
					label.clear();
					description.clear();
					cost.clear();
					filePath.setText("");
					
        		    } catch(NullPointerException n) {
        		    	a.setAlertType(AlertType.WARNING); // makes the alarm a warning type of alarm
        	        	a.setContentText("The image wasn't found or wasn't dragged into the program.");
        	        	a.show();
        		    } catch(IllegalArgumentException e) {
        		    	a.setAlertType(AlertType.WARNING); // makes the alarm a warning type of alarm
        	        	a.setContentText("You inputted a non numeric value in the cost field.");
        	        	a.show();
        		    } 
        		}
        
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

		descriptionLabel.setText(sell.getDescription());
		selected = sellTable.getSelectionModel().getSelectedItems();
	
		selected.forEach(allItems::remove);
		
		
		int rewardCost = sell.getCost();
		
		int goldBalance = Integer.parseInt(gold.getText()) + rewardCost;
		
		gold.setText(String.valueOf(goldBalance));
		
		descriptionLabel.setText("");
	}
	
	@FXML
	public void showDescription() {
		Reward inv = purchaseTable.getSelectionModel().getSelectedItem();
		
		descriptionLabel.setText(inv.getDescription());
	}
	
	@FXML
	public void suggestedGoldCalc() {
		int last, dollars;
		if(!lastTime.getText().isEmpty() || !lastTime.getText().matches("[0-9]") &&
				!cost.getText().isEmpty() || !cost.getText().matches("[0-9]")) {
			last = Integer.parseInt(lastTime.getText());
			dollars = Integer.parseInt(cost.getText());
			
			
			int result = last * 40 + (dollars * 80);
			suggestedGold.setText(String.valueOf(result));
		} 
			
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
		
//		try {
//			if (save.createNewFile()) {
//		        System.out.println("File created: " + save.getName());
//		      } else {
//		        System.out.println("File already exists.");
//		      }
//	         FileInputStream fileIn = new FileInputStream(save);
//	         ObjectInputStream in = new ObjectInputStream(fileIn);
//	         Reward r = null;
//	         Reward[] rArr = new Reward[5];
//	         
//	         ArrayList<Reward> rewardStorage = new ArrayList<>();
//	         rewardStorage = (ArrayList<Reward>) in.readObject();
//	         
//	         for(int i = 0; i < rewardStorage.size();i++) {
//	        	 rewardStorage.get(i).getCost();
//	        	 rewardStorage.get(i).getDescription();
//	        	 rewardStorage.get(i).getImg();
//	        	 rewardStorage.get(i).getName();
//	         }
//	         in.close();
//	         fileIn.close();
//	      } catch (IOException | ClassNotFoundException i) {
//	         i.printStackTrace();
//	         return;
//	      } 
		gold.setText("100");
		
		
		
	}
	
	
	
	
	
	
	
	
}
