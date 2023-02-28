package glacialExpedition.repositories;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StateRepository implements Repository{
    private List<State>states;

    public StateRepository() {
        this.states = new ArrayList<>();
    }

    @Override
    public List<State> getCollection() {
        return Collections.unmodifiableList(this.states);
    }

    @Override
    public void add(Object entity) {
        this.states.add((State) entity);
    }

    @Override
    public boolean remove(Object entity) {
        return this.states.remove((State) entity);
    }

    @Override
    public State byName(String name) {
        for (State state : this.states){
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
}
