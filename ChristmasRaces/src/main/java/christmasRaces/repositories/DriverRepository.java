package christmasRaces.repositories;

import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DriverRepository implements Repository<Driver> {
    List<Driver>driversList = new ArrayList<>();


    @Override
    public Driver getByName(String name) {
        for (Driver driver : driversList){
            if (driver.getName().equals(name)){
                return driver;
            }
        }
        return null;
    }

    @Override
    public Collection<Driver> getAll() {
        return driversList;
    }

    @Override
    public void add(Driver model) {
        driversList.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        return driversList.remove(model);
    }
}
