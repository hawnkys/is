package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.User;
import ejb.UsersEJBInterface;

@WebServlet("/SubscribeCar")
public class SubscribeCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UsersEJBInterface ejbremote;

	public SubscribeCar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		response.setContentType("text/html");
		
		User user = ejbremote.getUser((long) request.getSession(true).getAttribute("userId"));
		
		request.setAttribute("email", user.getEmail());
		
		dispatcher = request.getRequestDispatcher("/subscribeCar.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}