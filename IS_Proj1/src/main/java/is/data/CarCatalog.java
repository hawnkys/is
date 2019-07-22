package is.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="catalog")
public class CarCatalog {
//	@XmlElement(name="car")
	ArrayList<Car> catalog;
	
	public CarCatalog() {
		catalog = new ArrayList<Car>();
	}
	
	@XmlElement(name="car")
	public ArrayList<Car> getCatalog() {
		return catalog;
	}

	public void setCatalog(ArrayList<Car> catalog) {
		this.catalog = catalog;
	}
	
	public void add(ArrayList<Car> list) {
		catalog.addAll(list);
	}
}
