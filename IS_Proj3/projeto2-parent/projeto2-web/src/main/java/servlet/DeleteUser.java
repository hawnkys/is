package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejb.CarsEJBInterface;
import ejb.UsersEJBInterface;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	CarsEJBInterface ejbremote;
	@EJB
	UsersEJBInterface ejbremote2;
	
	public DeleteUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		ejbremote.deleteCars((long) request.getSession(true).getAttribute("userId"));
		ejbremote2.deleteUser((long) request.getSession(true).getAttribute("userId"));
		
		// clear session data
		HttpSession hs = request.getSession(true);
		hs.removeAttribute("name");
		hs.removeAttribute("username");
		hs.removeAttribute("userId");
		
		// Sign Out
		response.sendRedirect("index.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}