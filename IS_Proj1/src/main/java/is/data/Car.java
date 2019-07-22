package is.data;

import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Car {
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
	
	private int id;
	private String imageURL;
	private String brand;
	private String model;
	private String fuel;
	private String registerMonth;
	private String registerYear;
	private String url;
	private Distance km;
	private Power power;
	private CylinderCapacity cc;
	private Price priceInfo;

	public Car() {
		id = ID_GENERATOR.getAndIncrement();
		this.imageURL = "Na";
		this.brand = "Na";
		this.model = "Na";
		this.fuel = "Na";
		this.registerMonth = "Na";
		this.registerYear = "Na";
		this.url = "Na";
		this.km = new Distance();
		this.power = new Power();
		this.cc = new CylinderCapacity();
		this.priceInfo = new Price();
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	@XmlElement(name = "image")
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@XmlElement
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@XmlElement
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@XmlElement
	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	@XmlElement(name = "month")
	public String getRegisterMonth() {
		return registerMonth;
	}

	public void setRegisterMonth(String registerMonth) {
		this.registerMonth = registerMonth;
	}

	@XmlElement(name = "year")
	public String getRegisterYear() {
		return registerYear;
	}

	public void setRegisterYear(String registerYear) {
		this.registerYear = registerYear;
	}
	
	@XmlElement
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@XmlElement(name = "distance")
	public Distance getDistanceInfo() {
		return km;
	}

	public void setDistanceInfo(Distance km) {
		this.km = km;
	}

	@XmlElement(name = "power")
	public Power getPowerInfo() {
		return power;
	}

	public void setPowerInfo(Power power) {
		this.power = power;
	}

	@XmlElement(name = "cc")
	public CylinderCapacity getCCInfo() {
		return cc;
	}

	public void setCCInfo(CylinderCapacity cc) {
		this.cc = cc;
	}

	@XmlElement(name = "price")
	public Price getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(Price priceInfo) {
		this.priceInfo = priceInfo;
	}
}
