package glacialExpedition.repositories;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ExplorerRepository implements Repository{
    private List<Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new ArrayList<>();
    }

    @Override
    public List<Explorer> getCollection() {
        return Collections.unmodifiableList(this.explorers);
    }

    @Override
    public void add(Object entity) {
        Explorer explorer = (Explorer) entity;
        if (entity.getClass().getSimpleName().equals("AnimalExplorer")){
            explorer = new AnimalExplorer(((Explorer) entity).getName());
        }
        else if (entity.getClass().getSimpleName().equals("GlacierExplorer")){
            explorer = new AnimalExplorer(((Explorer) entity).getName());
        }
        else if (entity.getClass().getSimpleName().equals("NaturalExplorer")){
            explorer = new AnimalExplorer(((Explorer) entity).getName());
        }
        this.explorers.add(explorer);
    }

    @Override
    public boolean remove(Object entity) {
        return this.explorers.remove((Explorer) entity);
    }

    @Override
    public Explorer byName(String name) {
        for (Explorer explorer : this.explorers){
            if (explorer.getName().equals(name)) {
                return explorer;
            }
        }
        return null;
    }
}
