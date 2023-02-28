package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission {
    @Override
    public void explore(Planet planet, List<Astronaut> astronauts) {

        for (int i = 0; i < astronauts.size(); i++) {
            Astronaut currentAstronaut = astronauts.get(i);
            for (int j = 0; j < planet.getItems().size(); j++) {
                String item = planet.getItems().get(i);
                currentAstronaut.getBag().getItems().add(item);     //it may not collect items if is 0
                planet.getItems().remove(item);
                currentAstronaut.breath();
                j--;
                if (!currentAstronaut.canBreath()){
                    astronauts.remove(currentAstronaut);
                    i--;
                    break;
                }
            }
        }
    }
}
