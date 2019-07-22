package is.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Distance {
	private int value;
	private String distanceUnit;
	
	public Distance() {
		this.value = -1;
		this.distanceUnit = "Na";
	}
	
	public Distance(int value, String distanceUnit) {
		this.value = value;
		this.distanceUnit = distanceUnit;
	}
	
	@XmlValue
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@XmlAttribute(name = "unit")
	public String getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	};
}
