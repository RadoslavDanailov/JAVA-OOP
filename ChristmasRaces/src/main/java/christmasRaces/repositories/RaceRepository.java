package christmasRaces.repositories;

import christmasRaces.entities.races.Race;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RaceRepository implements Repository<Race> {

    private List<Race>raceList = new ArrayList<>();


    @Override
    public Race getByName(String name) {
        for (Race race : raceList){
            if (race.getName().equals(name)){
                return race;
            }
        }
        return null;
    }

    @Override
    public Collection<Race> getAll() {
        return raceList;
    }

    @Override
    public void add(Race model) {
        raceList.add(model);
    }

    @Override
    public boolean remove(Race model) {
        return raceList.remove(model);
    }
}
