package zoo.repositories;

import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodRepositoryImpl implements FoodRepository{
    private List<Food>foods;

    public FoodRepositoryImpl() {
        this.foods = new ArrayList<>();
    }



    @Override
    public void add(Food food) {
        this.foods.add(food);
    }

    @Override
    public boolean remove(Food food) {
        return this.foods.remove(food);
    }

    @Override
    public Food findByType(String type) {
        for (Food food :this.foods){
            if (food.getClass().getSimpleName().equals(type)){
                return food;
            }
        }
        return null;
    }
}
