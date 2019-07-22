package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String username;
	private String password;
	private String name;
	private String email;
	private Date birthDate;
	
	@ManyToMany(fetch=FetchType.EAGER)
	List<CarDTO> subscribedCars;
	
	@OneToMany(mappedBy = "user")
	private List<CarDTO> myCars;
	
	@OneToMany(fetch=FetchType.EAGER)
	public List<CarDTO> getMyCars() {
		return myCars;
	}

	public void setMyCars(List<CarDTO> myCars) {
		this.myCars = myCars;
	}

	public UserDTO() {
		super();
	}

	public UserDTO(String username, String password, String name, String email, Date birthDate) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<CarDTO> getSubscribedCars() {
		return subscribedCars;
	}

	public void setSubscribedCars(List<CarDTO> subscribedCars) {
		this.subscribedCars = subscribedCars;
	}
}
