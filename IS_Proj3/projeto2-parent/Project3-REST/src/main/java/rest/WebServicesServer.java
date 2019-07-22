package rest;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import data.Car;
import data.User;
import ejbb.CarsEJBInterface;
import ejbb.UsersEJBInterface;
import newData.CarJSON;
import newData.ListUsers;
import newData.UserXML;

@Path("/project3webservices")
public class WebServicesServer {
	@EJB
	UsersEJBInterface ejbremote;
	@EJB
	CarsEJBInterface ejbremote2;
	
	public WebServicesServer() {
		System.out.println("WebServicesServer created");
	}
	
	// http://localhost:8080/Project3-REST-Server/rest/project3webservices/gettext
	@GET
	@Path("gettext")
	@Produces({MediaType.TEXT_PLAIN})
	public String getText() {
		return "Hello World!";
	}
		
	// http://localhost:8080/Project3-REST-Server/rest/project3webservices/getusers
	@GET
	@Path("getusers")
	@Produces({MediaType.APPLICATION_XML})
	public ListUsers getUsers() throws JAXBException {
		System.out.println("getAllUsers started");
		ListUsers lu = new ListUsers();
		List<User> users = ejbremote.getAllUsers();
		
		ArrayList<UserXML> u = new ArrayList<>();
		for(int i=0;i<users.size();i++) {
			System.out.println(users.get(i).getName());
			u.add(new UserXML(users.get(i).getId(), users.get(i).getUsername(), users.get(i).getPassword(), users.get(i).getName(), users.get(i).getEmail(), users.get(i).getBirthDate()));
		}
		
		lu.setUsers(u);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ListUsers.class);
		Marshaller m = jaxbContext.createMarshaller();
		
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(lu, System.out);
		
		return lu;
	}
	
	
	// http://localhost:8080/Project3-REST-Server/rest/project3webservices/getcars
	@GET
	@Path("getcars")
	@Produces({MediaType.APPLICATION_JSON})
	public List<CarJSON> getAllCars() {
		System.out.println("getAllCars started");
		List<Car> cars = ejbremote2.getAllCars();
		
		ArrayList<CarJSON> carList = new ArrayList<>();
		for(int i=0;i<cars.size();i++) {
			carList.add(new CarJSON(cars.get(i).getId(), cars.get(i).getImageURL(), cars.get(i).getBrand(), cars.get(i).getModel(), cars.get(i).getFuel(), cars.get(i).getRegisterDate(), cars.get(i).getDistance(), cars.get(i).getPower(), cars.get(i).getCylinderCapacity(), cars.get(i).getPrice()));
		}
		
		return carList;
		
	}
}
