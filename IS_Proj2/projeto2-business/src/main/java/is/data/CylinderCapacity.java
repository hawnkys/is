package is.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class CylinderCapacity {
	private int value;
	private String ccUnit;
	
	public CylinderCapacity() {
		this.value = -1;
		this.ccUnit = "Na";
	}

	public CylinderCapacity(int value, String ccUnit) {
		this.value = value;
		this.ccUnit = ccUnit;
	}

	@XmlValue
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@XmlAttribute(name = "unit")
	public String getCCUnit() {
		return ccUnit;
	}

	public void setCCUnit(String ccUnit) {
		this.ccUnit = ccUnit;
	};
}
