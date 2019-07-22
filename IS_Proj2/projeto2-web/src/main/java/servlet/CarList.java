package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Car;
import ejb.CarsEJBInterface;

@WebServlet("/CarList")
public class CarList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	CarsEJBInterface ejbremote;

	public CarList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		response.setContentType("text/html");
		
		List<Car> lc;
		
		if (request.getParameter("action")!=null && request.getParameter("action").compareTo("search") == 0){
			
			String text = (String) (request.getParameter("text").contentEquals("") ? null : request.getParameter("text"));
			String minPrice = (String) (request.getParameter("minPrice").contentEquals("") ? null : request.getParameter("minPrice"));
			String maxPrice = (String) (request.getParameter("maxPrice").contentEquals("") ? null : request.getParameter("maxPrice"));
			String minKm = (String) (request.getParameter("minKm").contentEquals("") ? null : request.getParameter("minKm"));
			String maxKm = (String) (request.getParameter("maxKm").contentEquals("") ? null : request.getParameter("maxKm"));
			
			String orderBy = (String) (request.getParameter("orderBy").contentEquals("") ? null : request.getParameter("orderBy"));
			String orderType = (String) (request.getParameter("orderType").contentEquals("") ? null : request.getParameter("orderType"));
			
			//String fromDate = (String) (request.getParameter("fromDate").contentEquals("") ? null : request.getParameter("fromDate"));
			
			lc = ejbremote.searchCars(text, minPrice, maxPrice, minKm, maxKm, orderBy, orderType, null);
			
		} else {
			lc = ejbremote.getAllCars();
		}
		
		
		request.setAttribute("lc", lc);

		dispatcher = request.getRequestDispatcher("/carList.jsp");
		
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}