package application;

import javafx.scene.image.ImageView;

public class Reward {
	
	private String description;
	private int cost;
	private String name;
	private ImageView img;
	
	public Reward(ImageView img, String name, String description, int cost) {
		this.img = img;
		this.name = name;
		this.description = description;
		this.cost = cost;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getCost() {
		return cost;
	}

	public ImageView getImg() {
		return img;
	}

}
