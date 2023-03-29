package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.ArrayList;
import java.util.Collection;

public class HelperRepository<H> implements Repository<Helper>{
    private Collection<Helper>helpers = new ArrayList<>();



    @Override
    public Collection<Helper> getModels() {
        return this.helpers;
    }

    @Override
    public void add(Helper model) {
        this.getModels().add(model);
    }

    @Override
    public boolean remove(Helper model) {
        return this.getModels().remove(model);
    }

    @Override
    public Helper findByName(String name) {
        for (Helper helper : this.getModels()){
            if (helper.getName().equals(name)) {
                return helper;
            }
        }
        return null;
    }
}
