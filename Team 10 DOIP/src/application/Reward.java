package application;

public class Reward {
	
	private String description;
	private int cost;
	private String name;
	
	public Reward(String name, String description, int cost) {
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

}
