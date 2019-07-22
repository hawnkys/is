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
import data.User;
import ejb.UsersEJBInterface;

@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	UsersEJBInterface ejbremote;

	public MyProfile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		response.setContentType("text/html");
		
		if (request.getParameter("action")!=null && request.getParameter("action").compareTo("edit") == 0){
			
			// edit profile
			
			User u = new User();
			u.setId((long) request.getSession(true).getAttribute("userId"));
			u.setName(request.getParameter("name"));
			u.setUsername(request.getParameter("username"));
			u.setPassword(request.getParameter("password"));
			u.setEmail(request.getParameter("email"));
			
			ejbremote.updateUser(u);
			
		}
		
		long id = (long) request.getSession(true).getAttribute("userId");
		User user = ejbremote.getUser(id);
		
		//List<Car> myCars = user.getMyCars();
		
		request.setAttribute("user", user);
		//request.setAttribute("myCars", myCars);

		dispatcher = request.getRequestDispatcher("/myprofile.jsp");
		
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}