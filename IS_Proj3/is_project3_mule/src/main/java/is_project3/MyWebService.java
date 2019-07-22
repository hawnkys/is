package is_project3;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MyWebService {
	
	@WebMethod
	public String addFollower(String email, String brand, int minPrice, int maxPrice) {
		return "";
	}
	
	@WebMethod
	public String deleteFollower(String email, String brand) {
		return "";
	}
	
	@WebMethod
	public String listAll(){
		return "";
	}
	
	@WebMethod
	public String listUsers(){
		return "";
	}
}
