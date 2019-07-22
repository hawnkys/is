package message;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import is.data.CarCatalog;

public class Unmarshall {
	public CarCatalog run(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(CarCatalog.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		StringReader reader = new StringReader(xmlString);
		CarCatalog catalog = (CarCatalog) unmarshaller.unmarshal(reader);
		
		return catalog;
	}
	
	
	
	
}
