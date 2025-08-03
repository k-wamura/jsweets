package model.entity;

/**
 * 商品情報に関するentityクラスです
 * 
 * @author wamura
 */
public class Product {
	
	private int id;
	private String name;
	private String description;
	private int price;
	private int stock;
	private String imagePath;
	private int quantity;
	private int status;
	
	
	public Product(int id, String name, String description, int price, int stock, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.imagePath = imagePath;
	}

	public Product(String name, String description, int price, int stock, String imagePath) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", stock="
				+ stock + ", imagePath=" + imagePath + "]";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
