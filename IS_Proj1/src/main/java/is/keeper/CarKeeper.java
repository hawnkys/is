package is.keeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import is.data.*;

public class CarKeeper implements MessageListener{
	private ConnectionFactory cf;
	private Topic d;
	private Destination qd;
	CarCatalog catalog;

	public CarKeeper() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/topic/PlayTopic");
		this.qd = InitialContext.doLookup("jms/queue/PlayQueue");
	}

	@Override
	public void onMessage(Message msg) {
		TextMessage tmsg = (TextMessage) msg;
	
		try {
			System.out.println("Queue message: " + tmsg.getText());
			
			try(JMSContext jcontex = cf.createContext("ruben", "br1o+sa")){
	            TextMessage reply = jcontex.createTextMessage(processRequest(tmsg.getText()));
	            JMSProducer producer = jcontex.createProducer();

	            producer.send(tmsg.getJMSReplyTo(), reply);
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	  
	public void launch_and_wait() {
		try (JMSContext jcontext = cf.createContext("ruben", "br1o+sa");) {
			System.out.println("[Car Keeper] Waitting for messages ...");
			
			jcontext.setClientID("Keeper");
			
			//queue
			JMSConsumer queueConsumer = jcontext.createConsumer(qd);
			queueConsumer.setMessageListener(this);
			
			//durable topic
			JMSConsumer topicConsumer = jcontext.createDurableConsumer(d, "Keeper");
			
			while(true) {
				//receives topic messages
				TextMessage topicMessage = (TextMessage) topicConsumer.receive();
				System.out.println("Topic msg = " + topicMessage.getText());
				
				//validates XML
				createXMLFile(topicMessage.getText());
				System.out.println("Validating XML...");
				if(validateXMLSchema()) {
					System.out.println("XML validated sucessfully");
					try {
						catalog = new Unmarshall().run(topicMessage.getText());
					} catch (JAXBException e) {
						System.out.println("Couldn't process the XML into java classes !!!");
					}
				} 
				else {
					System.out.println("Incorrect XML !!!");
				}
			}
		} catch (JMSRuntimeException | JMSException e) {
			e.printStackTrace();
		}
	}

	public static void main() throws NamingException {
		CarKeeper r = new CarKeeper();
		r.launch_and_wait();
	}
	
	public void createXMLFile(String xmlString) {
		 try {
			 PrintWriter writer = new PrintWriter("KeeperGeneratedXML.xml", "UTF-8");
            writer.print(xmlString);
            writer.close();
        } catch (FileNotFoundException e) {
       	 System.out.println("Failed to create XML file !!!");
        } catch (UnsupportedEncodingException e) {
       	 System.out.println("Unsupported encoding on XML file !!!");
        }
	 }
	
	public static boolean validateXMLSchema(){
		 try {
			 SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			 Schema schema = factory.newSchema(new File("xml_schema.xsd"));
			 Validator validator = schema.newValidator();
			 validator.validate(new StreamSource(new File("KeeperGeneratedXML.xml")));
		 } catch (IOException e){
			 System.out.println("Exception: " + e.getMessage());
			 return false;
		 }catch(SAXException e){
			 System.out.println("SAX Exception: " + e.getMessage());
			 return false;
		 }

		 return true;
	 }
	
	public String processRequest(String request) {
		String[] parts = request.split("/");
		
		return getReply(parts);
	}
	
	public String getReply(String[] parts) {
		String choice = parts[0];
		String reply = "";
		int flag = 0;
		
		switch (choice) {
			case "1":
				String brand = parts[1];
				
				
				for(Car car : catalog.getCatalog()) {
					if(car.getBrand().toLowerCase().equals(brand.toLowerCase())) {
						flag = 1;
						
						reply += "Brand = " + car.getBrand() +
								 ", Model = " + car.getModel() + 
								 ", Fuel = " + car.getFuel() +
								 ", Regitation = " + car.getRegisterMonth() + "/" + car.getRegisterYear() +
								 ", Distance = " + car.getDistanceInfo().getValue() + " " + car.getDistanceInfo().getDistanceUnit() +
								 ", Power = " + car.getPowerInfo().getValue() + " " + car.getPowerInfo().getPowerUnit() +
								 ", CC = " + car.getCCInfo().getValue() + " " + car.getCCInfo().getCCUnit() +
								 ", Price = " + car.getPriceInfo().getValue() + " " + car.getPowerInfo().getPowerUnit() +
								 "\n";
					}
				}
				
				if(flag == 1)
					return reply;
				else
					return "No Cars with that brand exists !!!";
			
			case "2":
				for(Car car : catalog.getCatalog()) {
					if(car.getPriceInfo().getValue() > Integer.parseInt(parts[1]) && car.getPriceInfo().getValue() < Integer.parseInt(parts[2])) {
						flag = 1;
						
						reply += "Brand = " + car.getBrand() +
								 ", Model = " + car.getModel() + 
								 ", Fuel = " + car.getFuel() +
								 ", Regitation = " + car.getRegisterMonth() + "/" + car.getRegisterYear() +
								 ", Distance = " + car.getDistanceInfo().getValue() + " " + car.getDistanceInfo().getDistanceUnit() +
								 ", Power = " + car.getPowerInfo().getValue() + " " + car.getPowerInfo().getPowerUnit() +
								 ", CC = " + car.getCCInfo().getValue() + " " + car.getCCInfo().getCCUnit() +
								 ", Price = " + car.getPriceInfo().getValue() + " " + car.getPowerInfo().getPowerUnit() +
								 "\n";
					}
				}
				
				if(flag == 1)
					return reply;
				else
					return "No Cars found within those values !!!";
				
			case "3":
				for(Car car : catalog.getCatalog()) {
					if(car.getRegisterYear().equals(parts[1])) {
						flag = 1;
						
						reply += "Brand = " + car.getBrand() +
								 ", Model = " + car.getModel() + 
								 ", Fuel = " + car.getFuel() +
								 ", Regitation = " + car.getRegisterMonth() + "/" + car.getRegisterYear() +
								 ", Distance = " + car.getDistanceInfo().getValue() + " " + car.getDistanceInfo().getDistanceUnit() +
								 ", Power = " + car.getPowerInfo().getValue() + " " + car.getPowerInfo().getPowerUnit() +
								 ", CC = " + car.getCCInfo().getValue() + " " + car.getCCInfo().getCCUnit() +
								 ", Price = " + car.getPriceInfo().getValue() + " " + car.getPowerInfo().getPowerUnit() +
								 "\n";
					}
				}
				
				if(flag == 1)
					return reply;
				else
					return "No Cars found on that year !!!";
				
			case "4":
				for(Car car : catalog.getCatalog()) {
					if(car.getFuel().toLowerCase().equals(parts[1].toLowerCase())) {
						flag = 1;
						
						reply += "Brand = " + car.getBrand() +
								 ", Model = " + car.getModel() + 
								 ", Fuel = " + car.getFuel() +
								 ", Regitation = " + car.getRegisterMonth() + "/" + car.getRegisterYear() +
								 ", Distance = " + car.getDistanceInfo().getValue() + " " + car.getDistanceInfo().getDistanceUnit() +
								 ", Power = " + car.getPowerInfo().getValue() + " " + car.getPowerInfo().getPowerUnit() +
								 ", CC = " + car.getCCInfo().getValue() + " " + car.getCCInfo().getCCUnit() +
								 ", Price = " + car.getPriceInfo().getValue() + " " + car.getPowerInfo().getPowerUnit() +
								 "\n";
					}
				}
				
				if(flag == 1)
					return reply;
				else
					return "No Cars found on that year !!!";
				
			default:
				return "Invalid Request";
		}
	}
}
