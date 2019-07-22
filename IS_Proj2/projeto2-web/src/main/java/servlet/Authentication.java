package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.User;
import ejb.UsersEJBInterface;

// http://localhost:8080/ServerPlayers-Web/Index

@WebServlet("/Authentication")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	UsersEJBInterface ejbremote;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentication() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher;
		response.setContentType("text/html");

		if (request.getParameter("action").compareTo("login") == 0){
			// login
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if(ejbremote.verifyLogin(username, password) != null) {
				
				User user = ejbremote.verifyLogin(username, password);
				
				// Add user to session
				HttpSession hs = request.getSession(true);
				hs.setAttribute("name", user.getName());
				hs.setAttribute("username", user.getUsername());
				hs.setAttribute("userId", user.getId());

				response.sendRedirect("home.jsp");
			
			} else {
				String error = "Incorrect parameters";
				request.setAttribute("error", error);

				dispatcher = request.getRequestDispatcher("/index.jsp");
				
				dispatcher.forward(request, response);
			}
			
		}
		else {
			// register

			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			//String birthDate = request.getParameter("birthDate");
			
			//XXX FIRST PROTECT THE PASSWORD
			
			// Add it to the DB
			User user = ejbremote.addUser(name, email, username, password);
			
			if(user != null) {
				
				// Add user to session
				HttpSession hs = request.getSession(true);
				hs.setAttribute("name", user.getName());
				hs.setAttribute("username", user.getUsername());
				hs.setAttribute("userId", user.getId());

				response.sendRedirect("home.jsp");
			}
			
			// ERROR
			String error = "Something went wrong";
			request.setAttribute("error", error);
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}