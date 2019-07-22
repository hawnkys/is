package is.creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class HTML_Creator implements MessageListener{
	private ConnectionFactory cf;
	private Topic d;

	 public HTML_Creator() throws NamingException {
		  this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		  this.d = InitialContext.doLookup("jms/topic/PlayTopic");
	 }
	 
	 @Override
	 public void onMessage(Message msg) {
		  TextMessage tmsg = (TextMessage) msg;
		  try {
			  System.out.println("Got message: " + tmsg.getText());
			  
			  createXMLFile(tmsg.getText());
			  System.out.println("Validating XML...");
			  if(validateXMLSchema()) {
				  System.out.println("XML validated sucessfully");
				  System.out.println("Creating HTML file...");
				  xmlToHtml();
				  System.out.println("HTML created sucessfully"); 
			  } 
			  else {
				  System.out.println("Incorrect XML !!!");
			  }
			  
		  } catch (JMSException e) {
			  e.printStackTrace();
		  }
	 }
	 
	 public void launch_and_wait() {
		  try (JMSContext jcontext = cf.createContext("ruben", "br1o+sa");) {
			  jcontext.setClientID("HTML Creator");
			  JMSConsumer consumer = jcontext.createDurableConsumer(d, "HTML Creator");
			  
			  consumer.setMessageListener(this);
			  System.out.println("[HTML Creator] Waitting for messages ...");
			  System.in.read();
		  } catch (JMSRuntimeException | IOException re) {
			  re.printStackTrace();
		  }
	 }

	 public void createXMLFile(String xmlString) {
		 try {
			 PrintWriter writer = new PrintWriter("creatorGeneratedXML.xml", "UTF-8");
             writer.print(xmlString);
             writer.close();
         } catch (FileNotFoundException e) {
        	 System.out.println("Failed to create XML file !!!");
         } catch (UnsupportedEncodingException e) {
        	 System.out.println("Unsupported encoding on XML file !!!");
         }
	 }
	 
	 public void xmlToHtml(){		
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss");
	     String currentTime  = dateFormat.format(new Date());

	     try {
	    	 TransformerFactory tFactory=TransformerFactory.newInstance();
	    	 Source xslDoc=new StreamSource("xsl_File.xsl");
	         Source xmlDoc=new StreamSource("creatorGeneratedXML.xml");
	
	         String outputFileName="cars_" + currentTime + ".html";
	         
	         OutputStream htmlFile = new FileOutputStream(outputFileName);
	         Transformer trasform=tFactory.newTransformer(xslDoc);
	         trasform.transform(xmlDoc, new StreamResult(htmlFile));
	            
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
	 }
	 
	 public static boolean validateXMLSchema(){
		 try {
			 SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			 Schema schema = factory.newSchema(new File("xml_schema.xsd"));
			 Validator validator = schema.newValidator();
			 validator.validate(new StreamSource(new File("creatorGeneratedXML.xml")));
		 } catch (IOException e){
			 System.out.println("Exception: " + e.getMessage());
			 return false;
		 }catch(SAXException e){
			 System.out.println("SAX Exception: " + e.getMessage());
			 return false;
		 }

		 return true;
	 }
	 
	 public static void main() throws NamingException {
		 HTML_Creator r = new HTML_Creator();
		 r.launch_and_wait();
	 }
}
