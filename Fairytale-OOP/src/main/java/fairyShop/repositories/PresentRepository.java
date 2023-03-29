package fairyShop.repositories;

import fairyShop.models.Present;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PresentRepository<P> implements Repository<Present> {
        private Collection<Present>presents = new ArrayList<>();



    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableCollection(presents);
    }

    @Override
    public void add(Present model) {
        this.presents.add(model);
    }

    @Override
    public boolean remove(Present model) {
        return this.presents.remove(model);
    }

    @Override
    public Present findByName(String name) {
            for (Present present : this.getModels()){
                if (present.getName().equals(name)){
                    return present;
                }
            }
        return null;
    }
}
