package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.BaseCar;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;
import christmasRaces.repositories.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository){
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        Driver driver1 = new DriverImpl(driver);

        for(Driver driver2 : this.driverRepository.getAll()){
            if (driver2.getName().equals(driver1.getName())){
                throw new IllegalArgumentException(String.format("Driver %s is already created.", driver1.getName()));
            }
        }
        this.driverRepository.add(driver1);
        return String.format("Driver %s is created.", driver1.getName());
    }

    @Override
    public String createCar(String type, String model, int horsePower) {

        if (this.carRepository.getByName(model) != null){
            throw new IllegalArgumentException(String.format("Car %s is already created", model));
        }

        Car car = null;

        if (type.equals("Sports")){
             car = new SportsCar(model, horsePower);
        }
        if (type.equals("Muscle")){
            car = new MuscleCar(model, horsePower);
        }
        carRepository.add(car);
        return String.format("%sCar %s is created", type, model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {

        Driver driver = null;
        Car car = null;

        for (Driver driver1 : this.driverRepository.getAll()){
            if (driver1.getName().equals(driverName)){
                driver = driver1;
                break;
            }
        }
        if (driver == null){
            throw new IllegalArgumentException(String.format("Driver %s could not be found.",driverName));
        }
        for (Car car1 : this.carRepository.getAll()){
            if (car1.getModel().equals(carModel)){
                car = car1;
                break;
            }
        }
        if (car == null){
            throw new IllegalArgumentException(String.format("Car %s could not be found",carModel));
        }
        driver.addCar(car);           //----------------------
        return String.format("Driver %s received car %s", driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {

        Race race = this.raceRepository.getByName(raceName);
        if (race == null){
            throw  new IllegalArgumentException(String.format("Race %s could not be found.", raceName));
        }

        Driver driver = this.driverRepository.getByName(driverName);
        if (driver == null){
            throw new IllegalArgumentException(String.format("Driver %s could not be found.", driverName));
        }
        race.addDriver(driver);
        return String.format("Driver %s added in %s race.", driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {

        Race race = this.raceRepository.getByName(raceName);

        if (race == null){
            throw new IllegalArgumentException(String.format("Race %s could not be found.",raceName));
        }
        if (race.getDrivers().size() < 3){
            throw new IllegalArgumentException(String.format("Race %s cannot start with less than 3 participants.",raceName));
        }
        Collection<Driver> drivers = race.getDrivers();
        int laps = race.getLaps();

        List<Driver>winners = drivers.stream().sorted((firstDriver, secondDriver) ->
                (int) (secondDriver.getCar().calculateRacePoints(laps) - firstDriver.getCar().calculateRacePoints(laps)))
                .limit(3).collect(Collectors.toList());

        this.raceRepository.remove(race);

        Driver firstDriver = winners.get(0);
        Driver secondDriver = winners.get(1);
        Driver thirdDriver = winners.get(2);

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Driver %s wins %s race.%n",firstDriver.getName(), race.getName()));
        builder.append(String.format("Driver %s is second in %s race.%n",secondDriver.getName(), race.getName()));
        builder.append(String.format("Driver %s is third in %s race.",thirdDriver.getName(), race.getName()));

        return builder.toString();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = this.raceRepository.getByName(name);

        if (race == null){
            race = new RaceImpl(name, laps);
        }
        else {
            throw new IllegalArgumentException(String.format("Race %s is already created.",name));
        }
        this.raceRepository.add(race);
        return String.format("Race %s is created.",name);
    }
}
