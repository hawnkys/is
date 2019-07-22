package ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.User;

@Stateless
public class UsersEJB implements UsersEJBInterface {
 @PersistenceContext(name="Users")
 EntityManager em;
 
 private static final Logger LOGGER = Logger.getLogger(UsersEJB.class.getName());

    /**
     * Default constructor. 
     */
    public UsersEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public static Date getDate(int day, int month, int year) {
    	
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);

		Date d = cal.getTime();
		return d;
		
    }
    
    public List<User> getAllUsers() {
		LOGGER.info("****** userEJB - Getting all Users from database ******");
		try{
			Query q = em.createQuery("from User");
			@SuppressWarnings("unchecked")
			List<User> result = q.getResultList();
			return result;
		} catch (NoResultException e) {
			return null;
		}
    }
    
    public User verifyLogin(String username, String password) {

		String enc_pass = encript(password);
    	
		LOGGER.info("****** UsersEJB - Verifying Login credentials ******");
    		try {
		    	Query q = em.createQuery("from User p where p.username = :a and password = :b");
		    	q.setParameter("a", username);
		    	q.setParameter("b", enc_pass);
		    	User user = (User) q.getSingleResult();
	    	
		    	return user;
		    	
    		} catch (NoResultException e) {
				return null;
			}
	}
    
    public User getUser(long id) {

		LOGGER.info("****** UsersEJB - Geting specific user from database ******");
	    	try {
	    		User user = em.find(User.class, id);
		    	
		    	return user;
	    	} catch (NoResultException e) {
				return null;
			}
    }

	@Override
	public User addUser(String name, String email, String username, String password) {

		String enc_pass = encript(password);
		LOGGER.info("****** UsersEJB - Inserting new user in the database ******");
		User u = new User(username, enc_pass, name, email, getDate(05,10,1988));
 		em.persist(u);
 		
		return u;
	}

	@Override
	public void updateUser(User u) {
		
		LOGGER.info("****** UsersEJB - Updating user data ******");
		em.merge(u);
		
//		Query q = em.createQuery("UPDATE User set name=:a, email = :b, username = :c, password = :d where id = :e");
//    	q.setParameter("a", u.getName());
//    	q.setParameter("b", u.getEmail());
//    	q.setParameter("c", u.getUsername());
//    	q.setParameter("d", u.getPassword());
//    	q.setParameter("e", u.getId());
//    		
//    	q.executeUpdate();
    		
	}

	@Override
	public void deleteUser(long id) {

		LOGGER.info("****** UsersEJB - Deleting user from database ******");
		
		Query query = em.createQuery("Delete From User where  id = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
		
	}

	public String encript(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
}