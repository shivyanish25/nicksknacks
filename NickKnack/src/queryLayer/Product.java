package queryLayer;

public class Product {
	
	int sku, price;
	String name, description, image;
	public Product(String name, int price, int sku, String description, String image){
		this.name = name;
		this.price = price;
		this.sku=sku;
		this.description = description;
		this.image = image;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getImage(){
		return this.image;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public int getSku(){
		return this.sku;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public void setSku(int sku){
		this.sku = sku;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public void setPrice(int price){
		this.price = price;
	}

}
