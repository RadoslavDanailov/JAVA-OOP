package christmasRaces.entities.races;

import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RaceImpl implements Race{

    private String name;
    private int laps;
    private List<Driver>drivers;

    public RaceImpl(String name, int laps){
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 5){
            throw new IllegalArgumentException(String.format("Name %s cannot be less than 5 symbols.", name));
        }
        this.name = name;
    }

    public void setLaps(int laps){
        if (laps < 1){
            throw new IllegalArgumentException("Laps cannot be less than 1.");
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null){
            throw new IllegalArgumentException("Driver cannot be null");
        }
        if (!driver.getCanParticipate()){
            throw new IllegalArgumentException(String.format("Driver %s could not participate in race.",driver.getName()));
        }
        for(Driver driver1 : this.drivers){
            if (driver1.getName().equals(driver.getName())){
                throw new IllegalArgumentException(String.format("Driver %s is already added in %s race.",driver1.getName(), getName()));
            }
        }
        this.drivers.add(driver);
    }
}
