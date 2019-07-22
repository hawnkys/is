package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Car;
import data.User;
import ejb.CarsEJBInterface;
import ejb.UsersEJBInterface;

@WebServlet("/SetCar")
public class SetCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	CarsEJBInterface ejbremote;
	@EJB
	UsersEJBInterface ejbremote2;

	public SetCar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		response.setContentType("text/html");
		
		
		Car car = new Car();
		car.setBrand((String) (request.getParameter("brand").contentEquals("") ? null : request.getParameter("brand")));
		car.setModel((String) (request.getParameter("model").contentEquals("") ? null : request.getParameter("model")));
		car.setCylinderCapacity((String) (request.getParameter("cylinderCapacity").contentEquals("") ? null : request.getParameter("cylinderCapacity")));
		car.setDistance((String) (request.getParameter("distance").contentEquals("") ? null : request.getParameter("distance")));
		car.setFuel((String) (request.getParameter("fuel").contentEquals("") ? null : request.getParameter("fuel")));
		car.setPower((String) (request.getParameter("power").contentEquals("") ? null : request.getParameter("power")));
		car.setPrice((String) (request.getParameter("price").contentEquals("") ? null : request.getParameter("price")));
		car.setRegisterDate((String) (request.getParameter("registerDate").contentEquals("") ? null : request.getParameter("registerDate")));
		car.setImageURL((String) (request.getParameter("imageURL").contentEquals("") ? null : request.getParameter("imageURL")));
		
		User user = ejbremote2.getUser((long) request.getSession(true).getAttribute("userId"));
		car.setUser(user);
		
		if(ejbremote.setCar(car)) {
			
			request.setAttribute("car", car);
			
			dispatcher = request.getRequestDispatcher("/carDetails.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}