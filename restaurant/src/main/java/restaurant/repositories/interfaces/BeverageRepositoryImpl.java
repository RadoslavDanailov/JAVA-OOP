package restaurant.repositories.interfaces;

import restaurant.entities.drinks.interfaces.Beverages;

import java.util.*;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {
    private List<Beverages> beverages;

    public BeverageRepositoryImpl() {
        this.beverages = new ArrayList<>();
    }

    protected List<Beverages> getBeverages() {
        return beverages;
    }

    protected void setBeverages(List<Beverages> beverages) {
        this.beverages = beverages;
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        for (Beverages beverage : this.getBeverages()){
            if (beverage.getName().equals(drinkName) && beverage.getBrand().equals(drinkBrand)){
                return beverage;
            }
        }
        return null;
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableList(this.beverages);
    }

    @Override
    public void add(Beverages entity) {
        this.beverages.add(entity);

    }
}
