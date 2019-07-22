package ejb;

import javax.ejb.Local;

import data.User;

@Local
public interface UsersEJBInterface {
 public User verifyLogin(String username, String password);
 public User getUser(long id);
 public User addUser(String name, String email, String username, String password);
 public void updateUser(User u);
 public void deleteUser(long id);
}
