package ejbb;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Car;
import ejbb.CarsEJB;
import ejbb.CarsEJBInterface;

@Stateless
public class CarsEJB implements CarsEJBInterface {
 @PersistenceContext(name="Car")
 EntityManager em;
 
 private static final Logger LOGGER = Logger.getLogger(CarsEJB.class.getName());

    /**
     * Default constructor. 
     */
    public CarsEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public Car getCar(long id) {

		LOGGER.info("****** CarsEJB - Getting a car from database ******");
	    	try {
	    		Car car = em.find(Car.class, id);
		    	
		    	return car;
	    	} catch (NoResultException e) {
				return null;
			}
    }
    
    public List<Car> getAllCars() {
		LOGGER.info("****** CarsEJB - Getting all Cars from database ******");
    		try{
    			Query q = em.createQuery("from Car");
    			@SuppressWarnings("unchecked")
				List<Car> result = q.getResultList();
    			return result;
    		} catch (NoResultException e) {
			return null;
		}
    }
    
    public List<Car> searchCars(String text, String minPrice, String maxPrice, String minKm, String maxKm, String orderBy, String orderType, String fromDate) {
		
		LOGGER.info("****** CarsEJB - Searching specific Cars in the database ******");
		
		StringBuilder stringBuilder = new StringBuilder().append("from Car where 1=1");

        if (text != null) {
            stringBuilder.append("and brand = :a OR model = :b ");
        }
    		if (minPrice != null) {
            stringBuilder.append("and price > :c ");
        }
        if (maxPrice != null) {
            stringBuilder.append("and price < :d ");
        }
        if (minKm != null) {
            stringBuilder.append("and distance > :e ");
        }
        if (maxKm != null) {
            stringBuilder.append("and distance < :f ");
        }
        if (orderBy.compareToIgnoreCase("price")==0) {
            stringBuilder.append("order by ABS(price)");
        } else {
        		stringBuilder.append("order by brand");
        }
        if (orderType.compareToIgnoreCase("ASC")==0) {
            stringBuilder.append(" ASC");
        } else {
        		stringBuilder.append(" DESC");
        }
        
    
        Query q = em.createQuery(stringBuilder.toString());

        if (text != null) {
            q.setParameter("a", text);
            q.setParameter("b", text);
        }
        if (minPrice != null) {
            q.setParameter("c", minPrice);
        }
        if (maxPrice != null) {
            q.setParameter("d", maxPrice);
        }
        if (minKm != null) {
            q.setParameter("e", minKm);
        }
        if (maxKm != null) {
            q.setParameter("f", maxKm);
        }
        
        try {
        	 @SuppressWarnings("unchecked")
			List<Car> result = q.getResultList();
            return result;
        } catch (NoResultException e) {
            return null;

        }
    }

	public void deleteCars(long id) {

		LOGGER.info("****** CarsEJB - Deleting Car from database ******");
		
		Query query = em.createQuery("Delete From Car where  user.id = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
	}

	@Override
	public boolean setCar(Car car) {

		LOGGER.info("****** CarsEJB - Inserting Car in database ******");
		
		try {
			em.persist(car);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public void updateCar(Car car) {

		LOGGER.info("****** CarsEJB - Updating Car data ******");
		em.merge(car);
		
	}
}
    
 
    
