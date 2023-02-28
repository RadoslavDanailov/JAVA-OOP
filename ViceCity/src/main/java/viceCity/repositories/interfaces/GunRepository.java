package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GunRepository implements Repository<Gun> {
    List<Gun>models = new ArrayList<>();

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableList(this.models);
    }

    @Override
    public void add(Gun model) {
        this.models.add(model);

//        for (Gun gun : this.models){
//            if (gun.getName().equals(model.getName())){
//                continue;
//            }
//            else {
//                this.models.add(model);
//            }
//        }
    }

    @Override
    public boolean remove(Gun model) {
        return this.models.remove(model);
    }

    @Override
    public Gun find(String name) {
        Gun gun = null;
        for (Gun gun1 : this.models){
            if (gun1.getName().equals(name)){
                gun = gun1;
            }
        }
        return gun;
    }
}
