package is.requester;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CarRequester {
	private ConnectionFactory cf;
	private Destination d;

	public CarRequester() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/queue/PlayQueue");
	}

	public void sendAndRecive(String text) { 
		try (JMSContext jcontex = this.cf.createContext("ruben", "br1o+sa")) {
			JMSProducer mp = jcontex.createProducer();
            TextMessage msg = jcontex.createTextMessage(text);
            Destination tempQueue = jcontex.createTemporaryQueue();

            msg.setJMSReplyTo(tempQueue);
            mp.send(d, msg);

            JMSConsumer mc = jcontex.createConsumer(tempQueue);
            TextMessage reply = (TextMessage) mc.receive();

            System.out.println("Sender got back: \n" + reply.getText());
            
		} catch (JMSException e) {
            e.printStackTrace();
        }
	}
	
	public static void main() throws NamingException {
		CarRequester r = new CarRequester();
		userMainMenu(r);
	}
	
	public static void userMainMenu(CarRequester r){
		String search;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Search: ");
		System.out.println("0. Exit");
		System.out.println("1. Select cars by brand");
		System.out.println("2. Select cars by value");
		System.out.println("3. Select cars of a specific year");
		System.out.println("4. Select cars by type of fuel");

		search = sc.nextLine();

		switch (search) {
			case "0":
				System.exit(0);
				break;
			case "1":
				System.out.println("Brand name:");
				search += "/" + sc.nextLine();
				
				r.sendAndRecive(search);
				userMainMenu(r);
				break;
			case "2":
				System.out.println("Choose a range of car value:");
				System.out.println("Minimum value:");
				search += "/" + sc.nextLine();
				System.out.println("Max value");
				search += "/" + sc.nextLine();
	
				r.sendAndRecive(search);
				userMainMenu(r);
				break;
			case "3":
				System.out.println("Year value:");
				search += "/" + sc.nextLine();
	
				r.sendAndRecive(search);
				userMainMenu(r);
				break;
			case "4":
				System.out.println("Fuel type (Gasolina or Diesel):");
				search += "/" + sc.nextLine();
	
				r.sendAndRecive(search);
				userMainMenu(r);
				break;
			default:
				System.out.println("Invalid choice !!!");
				userMainMenu(r);
				break;
		}
		
		sc.close();
	}

}
