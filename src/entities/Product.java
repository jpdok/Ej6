package entities;

public class Product {
	
	private int id,stock;
	private String name,description;
	private double price;
	private boolean shippingIncluded;
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean getShippingIncluded() {
		return shippingIncluded;
	}
	public void setShippingIncluded(boolean shippingIncluded) {
		this.shippingIncluded = shippingIncluded;
	}
	
	//toString para mostrar los datos de productos
	@Override
	public String toString() {
		return "\nProduct [id=" + id + ", stock=" + stock + ", name=" + name + ", description=" + description + ", price="
				+ price + ", shippingIncluded=" + shippingIncluded + "]";
	}
	
	
	public Product() {}
	
	//Constructor sin setear ID
	public Product(String name, String description, double price,int stock, boolean shippingIncluded) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.shippingIncluded = shippingIncluded;
	}
	
	
	

}
