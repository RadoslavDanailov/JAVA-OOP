package zoo.entities.areas;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseArea implements Area{
    private String name;
    private int capacity;
    private List<Food>foods;
    private List<Animal>animals;

    public BaseArea(String name, int capacity){
        this.setName(name);
        this.setCapacity(capacity);
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }
    public void setName(String name){
        if (name == null || name.trim().equals("")){
            throw new NullPointerException(ExceptionMessages.AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    @Override
    public int sumCalories(){
        int sum = 0;
        for (Food food : this.getFoods()){
            sum += food.getCalories();
        }
        return sum;
    }
    @Override
    public void addAnimal(Animal animal){
        if (this.getCapacity() > this.getAnimals().size()){
            this.animals.add(animal);
        }
        else {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
    }
    @Override
        public void removeAnimal(Animal animal){
        this.getAnimals().remove(animal);
    }
    @Override
    public void addFood(Food food){
        this.getFoods().add(food);
    }
    @Override
    public void feed(){
        for (Animal animal :this.getAnimals()){
            animal.eat();
        }
    }
    @Override
    public String getInfo(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s (%s):",this.getName(),this.getClass().getSimpleName()));
        builder.append(System.lineSeparator());
        builder.append("Animals: ");
        if (this.getAnimals().size() == 0){
            builder.append("none");
        }
        else {
            List<String >names = new ArrayList<>();
            for (Animal animal : this.getAnimals()){
                names.add(animal.getName());
            }
            builder.append(String.join(" ", names));
        }
        builder.append(System.lineSeparator());
        builder.append("Foods: ").append(this.getFoods().size()).append(System.lineSeparator());
        builder.append("Calories: ").append(sumCalories());

        return builder.toString().trim();
    }
}
