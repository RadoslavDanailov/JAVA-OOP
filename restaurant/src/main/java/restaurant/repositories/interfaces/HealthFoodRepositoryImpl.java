package restaurant.repositories.interfaces;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {
    private List<HealthyFood>healthyFoods;

    public HealthFoodRepositoryImpl() {
        this.healthyFoods = new ArrayList<>();
    }

    protected List<HealthyFood> getHealthyFoods() {
        return healthyFoods;
    }

    protected void setHealthyFoods(List<HealthyFood> healthyFoods) {
        this.healthyFoods = healthyFoods;
    }

    @Override
    public HealthyFood foodByName(String name) {

        for (HealthyFood healthyFood : this.getHealthyFoods()){
            if (healthyFood.getName().equals(name)){
                return healthyFood;
            }
        }
        return null;
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableList(this.healthyFoods);
    }

    @Override
    public void add(HealthyFood entity) {
        this.healthyFoods.add(entity);

    }
}
