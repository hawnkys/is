package newData;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ListUsers")
public class ListUsers {
	List<UserXML> users;
	
	public ListUsers() {
		this.users = new ArrayList<>();
	}

	public List<UserXML> getUsers() {
		return users;
	}

	public void setUsers(List<UserXML> users) {
		this.users = users;
	}
	
	
}
