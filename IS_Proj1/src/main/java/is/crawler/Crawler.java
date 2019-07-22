package is.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.io.Files;

import is.data.*;

public class Crawler {

	public static void main() throws NamingException, InterruptedException, FileNotFoundException {	
		xmlToTopic();
	}
	
	public static void xmlToTopic() throws NamingException, InterruptedException, FileNotFoundException {
		String xmlString = "";
	
		File file = new File("CrawlerGeneratedXML.txt");

		//if file exists read it and send it
		if(file.exists()) {
			System.out.println("File exists, reading file...");
			xmlString = readFromFile(file);
			System.out.println("File read sucessfull");
			
			try{
				System.out.println("Sending xml to topic...");
				new SendToTopic().send(xmlString);
				System.out.println("XML Sended succesfully");
				file.delete();
			} catch(Exception e) {
				System.out.println("Fail to send XML to topic, storing it to file...");
				writeToFile(xmlString);
			}
		}
		
		else {
			try {
				System.out.println("File doesn't exist, crawling the web pages....");
				
				//missing getting this links automatically
				String url_1 = "https://www.standvirtual.com/destaques/";
				String url_2 = "https://www.standvirtual.com/destaques/?page=2";
				String url_3 = "https://www.standvirtual.com/destaques/?page=3";
				
				CarCatalog catalog = new CarCatalog();
				catalog.add(crawlMainPage(url_1));
				catalog.add(crawlMainPage(url_2));
				catalog.add(crawlMainPage(url_3));
				
				System.out.println("Pages crawled sucessfully" + "\n" + "Creating XML...");
				Marshall marshall = new Marshall();
			
				xmlString = marshall.run(catalog);
				System.out.println("XML created sucessfully");
				
				try{
					System.out.println("Sending xml to topic...");
					new SendToTopic().send(xmlString);
					System.out.println("XML Sended succesfully");
					file.delete();
				} catch(Exception e) {
					System.out.println("Fail to send XML to topic, storing it to file...");
					writeToFile(xmlString);
				}
			}catch (JAXBException e) {
				System.out.println("Fail to crawl the pages (Possible web pages no longer exist) !!!");
			}
		}
		
		System.out.println("Done!!!");
	}
	
	public static ArrayList<Car> crawlMainPage(String url) {
		ArrayList<Car> list = new ArrayList<>();
		
		try {
        	Document doc = (Document) Jsoup.connect(url).get();
        	Elements hrefList = doc.select("div[class=item-body] > a");
			
        	for (Element e : hrefList) {
        		list.add(crawlCarDetails(e.attr("href")));
        	}
        	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static Car crawlCarDetails (String url) {
		Car car = null;
		
		try {
			car = new Car();
        	Document doc = (Document) Jsoup.connect(url).get();
        	
        	//set car url
        	car.setUrl(url);
        	
        	//car price information
        	Elements priceInfo = doc.select("span[class=offer-price__number]");
        	getValuesFromString(priceInfo.text(), 1, car);
        	
        	//car image url 
        	Elements image = doc.select("div[class=om-offer-photos om-offer-photos-slick]");
        	car.setImageURL(image.get(0).child(0).child(0).attr("src"));
        	
        	//car details
        	Elements detailList = doc.select("div[class=offer-params] > ul > li");
        	for (Element e : detailList) { 
        		compareDetails(e.child(0).text(), e.child(1).text(), car);
        	}
        	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return car;
	}
	
	public static void compareDetails(String detail, String value, Car car) {
		switch(detail) {
			case "Marca":
				car.setBrand(value);
				break;
			case "Modelo":
				car.setModel(value);
				break;
			case "Combustível":
				car.setFuel(value);
				break;
			case "Mês de Registo":
				car.setRegisterMonth(value);
				break;
			case "Ano de Registo":
				car.setRegisterYear(value);
				break;
			case "Quilómetros":
				getValuesFromString(value, 3, car);
				break;
			case "Potência":
				getValuesFromString(value, 4, car);
				break;
			case "Cilindrada":
				getValuesFromString(value, 2, car);
				break;
			default:
				break;
		}
	}
	
	public static void getValuesFromString(String str, int chosenClass, Car car) {
		//1 - PriceInfo, 2 - CCInfo, 3 - DistanceInfo, 4 - Power
		switch(chosenClass) {
			case 1:
				String auxPrice = str.replaceAll(" ", "");
	        	String priceValue = auxPrice.substring(0, auxPrice.length() - 3);
	        	String priceUnit = auxPrice.substring(auxPrice.length() - 3, auxPrice.length());
	        	
	        	car.setPriceInfo(new Price(Integer.parseInt(priceValue),  priceUnit));
	        	break;
			case 2:
				String auxCC = str.replaceAll(" ", "");
	        	String ccValue = auxCC.substring(0, auxCC.length() - 3);
	        	String ccUnit = auxCC.substring(auxCC.length() - 3, auxCC.length());
	        	
	        	car.setCCInfo((new CylinderCapacity(Integer.parseInt(ccValue),  ccUnit)));
	        	break;
			case 3:
				String auxDistance = str.replaceAll(" ", "");
	        	String distanceValue = auxDistance.substring(0, auxDistance.length() - 2);
	        	String distanceUnit = auxDistance.substring(auxDistance.length() - 2, auxDistance.length());
	        	
	        	car.setDistanceInfo((new Distance(Integer.parseInt(distanceValue),  distanceUnit)));
	        	break;
			case 4:
				String auxPower = str.replaceAll(" ", "");
	        	String powerValue = auxPower.substring(0, auxPower.length() - 2);
	        	String powerUnit = auxPower.substring(auxPower.length() - 2, auxPower.length());
	        	
	        	car.setPowerInfo((new Power(Integer.parseInt(powerValue),  powerUnit)));
	        	break;
	        default:
	        	break;
		}
	}
	
	public static String readFromFile(File file){
		String aux = null;
		
		try {
			byte[] encoded = Files.toByteArray(file);
			
			aux = new String(encoded);
		} catch (Exception e) {
			System.out.println("Failed to read from file (File not found) !!!");
		}
		
		return aux;
	}
	
	public static void writeToFile(String text) {
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter("CrawlerGeneratedXML.txt"));
		    out.write(text);                         
		    out.close();
		}
		catch (IOException e)
		{
		    System.out.println("Exception on writing to file function !!!");
		    e.printStackTrace();
		}
	}
}
