package message;

import java.util.ArrayList;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;

import data.Car;
import is.data.CarCatalog;
import is.data.CarXML;


/**
 * Message-Driven Bean implementation class for: MessageBean
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/PlayTopic"),
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "topic/PlayTopic")
public class MessageBean implements MessageListener {
	CarCatalog catalog;
	@PersistenceContext(name="Car")
	EntityManager em;
	
    public MessageBean() {
    }
	
    public void onMessage(Message message) {
    	try {
    		try {
				catalog = new Unmarshall().run(((TextMessage) message).getText());
				addCarsToDB(catalog.getCatalog());
			} catch (JAXBException e) {
				e.printStackTrace();
			}
    	} catch (JMSException e) {
    		e.printStackTrace();
    	}
    }

    public void addCarsToDB(ArrayList<CarXML> carCatalog) {
    	for(int i=0;i<carCatalog.size();i++) {
    		Query q = em.createQuery("from Car where imageURL = :a");
    		q.setParameter("a", carCatalog.get(i).getImageURL());
    		
            if(q.getResultList().isEmpty()) {
            	String imageURL = carCatalog.get(i).getImageURL();
        		String brand = carCatalog.get(i).getBrand();
        		String model = carCatalog.get(i).getModel();
        		String fuel = carCatalog.get(i).getFuel();
        		String registerDate = carCatalog.get(i).getRegisterMonth() + "/" + carCatalog.get(i).getRegisterYear();
        		int distance =carCatalog.get(i).getDistanceInfo().getValue();
        		int power = carCatalog.get(i).getPowerInfo().getValue();
        		int cylinderCapacity = carCatalog.get(i).getCCInfo().getValue();
        		int price = carCatalog.get(i).getPriceInfo().getValue();
        		
        		//Car(String imageURL, String brand, String model, String fuel, String registerDate, int distance, int power, int cylinderCapacity, int price, List<Person> subscribers) 
        		//em.persist(new Car(imageURL, brand, model, fuel, registerDate, distance, power, cylinderCapacity, price));
            }
    	}
    }
}
