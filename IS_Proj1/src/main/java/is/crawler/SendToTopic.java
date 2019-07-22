package is.crawler;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SendToTopic {
	private ConnectionFactory cf;
    private Destination d;

    public SendToTopic() throws InterruptedException, NamingException{
    	this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
    	this.d = InitialContext.doLookup("jms/topic/PlayTopic");
    }
    
    public void send(String msg) {
    	  try (JMSContext cntx = this.cf.createContext("ruben", "br1o+sa")) {
    		  JMSProducer prod = cntx.createProducer();
    		  prod.send(d, msg);
    	  }
   }
}
