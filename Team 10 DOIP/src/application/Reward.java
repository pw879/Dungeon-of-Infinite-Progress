package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.image.ImageView;

public class Reward implements Serializable{
	
	private String description;
	private int cost;
	private String name;
	private transient ImageView img;
	
	public Reward(ImageView img, String name, String description, int cost) {
		
		this.img = img;
		img.setFitHeight(90);
		img.setFitHeight(90);
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
	
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
	    in.defaultReadObject();
	    String url = (String) in.readObject();
	    if (url != null) {
	        img = new ImageView(url);
	    }
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
	    out.defaultWriteObject();
	    out.writeObject(img == null ? null : img.getId());
	}

}
