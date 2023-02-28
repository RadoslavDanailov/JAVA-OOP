package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{

    public MissionImpl() {
    }

    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        List<String>removedEx = new ArrayList<>();
        for (Explorer explorer : explorers){
          while (explorer.canSearch()){
                for (String exhibit : state.getExhibits()){
                    explorer.search();
                    explorer.getSuitcase().getExhibits().add(exhibit);
                    removedEx.add(exhibit);
                }
          }
            for (String exhibit : removedEx){
                state.getExhibits().remove(exhibit);    /////////////////////////////////////
            }
        }
    }
}
