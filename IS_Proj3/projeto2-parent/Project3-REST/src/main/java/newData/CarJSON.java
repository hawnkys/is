package newData;

import java.io.Serializable;

public class CarJSON implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String imageURL;
	private String brand;
	private String model;
	private String fuel;
	private String registerDate;
	private String distance;
	private String power;
	private String cylinderCapacity;
	private String price;

	public CarJSON() {
		super();
	}

	public CarJSON(long id, String imageURL, String brand, String model, String fuel, String registerDate, String distance, String power, String cylinderCapacity, String price) {
		super();
		this.id = id;
		this.imageURL = imageURL;
		this.brand = brand;
		this.model = model;
		this.fuel = fuel;
		this.registerDate = registerDate;
		this.distance = distance;
		this.power = power;
		this.cylinderCapacity = cylinderCapacity;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getCylinderCapacity() {
		return cylinderCapacity;
	}

	public void setCylinderCapacity(String cylinderCapacity) {
		this.cylinderCapacity = cylinderCapacity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
