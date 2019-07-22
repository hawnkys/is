package is.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="catalog")
public class CarCatalog {
//	@XmlElement(name="car")
	ArrayList<CarXML> catalog;
	
	public CarCatalog() {
		catalog = new ArrayList<CarXML>();
	}
	
	@XmlElement(name="car")
	public ArrayList<CarXML> getCatalog() {
		return catalog;
	}

	public void setCatalog(ArrayList<CarXML> catalog) {
		this.catalog = catalog;
	}
	
	public void add(ArrayList<CarXML> list) {
		catalog.addAll(list);
	}
}
