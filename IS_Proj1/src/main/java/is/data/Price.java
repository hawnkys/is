package is.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Price {
	private int value;
	private String priceUnit;
	
	public Price () {
		this.value = -1;
		this.priceUnit = "Na";
	}
	
	public Price(int value, String priceUnit) {
		this.value = value;
		this.priceUnit = priceUnit;
	}

	@XmlValue
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@XmlAttribute(name = "unit")
	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	};
}
