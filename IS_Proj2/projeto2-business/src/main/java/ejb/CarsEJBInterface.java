package ejb;

import java.util.List;

import javax.ejb.Local;

import data.Car;

@Local
public interface CarsEJBInterface {
 public Car getCar(long id);
 public List<Car> getAllCars();
 public List<Car> searchCars(String text, String minPrice, String maxPrice, String minKm, String maxKm, String orderBy, String orderType, String fromDate);
public void deleteCars(long id);
public boolean setCar(Car car);
public void updateCar(Car car);
}
