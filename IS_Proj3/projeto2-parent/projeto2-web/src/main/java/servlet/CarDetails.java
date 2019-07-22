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

@WebServlet("/CarDetails")
public class CarDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	CarsEJBInterface ejbremote;

	public CarDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		response.setContentType("text/html");
		
		Car car = new Car();
		
		if (request.getParameter("action")!=null && request.getParameter("action").compareTo("edit") == 0){
			
			// edit car
			car = ejbremote.getCar(Long.parseLong(request.getParameter("carId")));
			car.setBrand(request.getParameter("brand"));
			car.setModel(request.getParameter("model"));
			car.setCylinderCapacity(request.getParameter("cylinderCapacity"));
			car.setPower(request.getParameter("power"));
			car.setFuel(request.getParameter("fuel"));
			car.setDistance(request.getParameter("distance"));
			car.setRegisterDate(request.getParameter("registerDate"));
			car.setPrice(request.getParameter("price"));
			car.setImageURL(request.getParameter("imageURL"));
			ejbremote.updateCar(car);
			
		} else {
			car = ejbremote.getCar(Long.parseLong(request.getParameter("carId")));
		}
			request.setAttribute("car", car);

			dispatcher = request.getRequestDispatcher("/carDetails.jsp");
			
			dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}