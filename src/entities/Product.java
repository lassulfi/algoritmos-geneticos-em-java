package entities;

public class Product {

	private String name;
	private double volume;
	private double price;
	
	public Product(String name, double volume, double price) {
		this.name = name;
		this.volume = volume;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product - name: " + name + ", volume: " + String.format("%.2f", volume) + ", price: " + String.format("%.2f", price);
	}
	
	
}
