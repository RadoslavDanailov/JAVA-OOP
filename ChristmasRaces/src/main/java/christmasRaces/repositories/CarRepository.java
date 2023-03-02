package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CarRepository implements Repository<Car> {
    private List<Car>carsList = new ArrayList<>();

    @Override
    public Car getByName(String name) {
        for (Car car : carsList){
            if (car.getModel().equals(name)){
                return car;
            }
        }

        return null;
    }

    @Override
    public Collection<Car> getAll() {
        return carsList;
    }

    @Override
    public void add(Car model) {
        carsList.add(model);
    }

    @Override
    public boolean remove(Car model) {
        return carsList.remove(model);
    }
}
