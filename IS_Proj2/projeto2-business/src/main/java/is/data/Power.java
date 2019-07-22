package is.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Power {
	private int value;
	private String powerUnit;
	
	public Power() {
		this.value = -1;
		this.powerUnit = "Na";
	}
	
	public Power(int value, String powerUnit) {
		this.value = value;
		this.powerUnit = powerUnit;
	}

	@XmlValue
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@XmlAttribute(name = "unit")
	public String getPowerUnit() {
		return powerUnit;
	}

	public void setPowerUnit(String powerUnit) {
		this.powerUnit = powerUnit;
	};
}
