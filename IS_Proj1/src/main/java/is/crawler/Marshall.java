package is.crawler;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import is.data.*;

public class Marshall {
	public String run(CarCatalog catalog) throws JAXBException {
		java.io.StringWriter sw = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(CarCatalog.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(catalog, sw);
		
		return sw.toString();
	}
}
