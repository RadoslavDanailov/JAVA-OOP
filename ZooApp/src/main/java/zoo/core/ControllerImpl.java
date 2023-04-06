package zoo.core;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private List<Area>areas;

    public ControllerImpl(){
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }


    @Override
    public String addArea(String areaType, String areaName) {
        Area area = null;
        switch (areaType){
            case "WaterArea":{
                area = new WaterArea(areaName);
                this.areas.add(area);
                return String.format("Successfully added %s.",areaType);

            }
            case "LandArea":{
                area = new LandArea(areaName);
                this.areas.add(area);
                return String.format("Successfully added %s.",areaType);

            }
            default:{
                throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
            }
        }
    }

    @Override
    public String buyFood(String foodType) {
        Food food = null;
        if (foodType.equals("Vegetable")){
            food= new Vegetable();
            this.foodRepository.add(food);
        }
        else if (foodType.equals("Meat")){
            food = new Meat();
            this.foodRepository.add(food);
        }
        else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }

        return String.format("Successfully added %s.",foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Area area = null;
        Food food = this.foodRepository.findByType(foodType);
        String result = null;

        for (Area area1 : this.areas){
            if (area1.getName().equals(areaName)){
                area = area1;
            }
        }
        if (food != null){
            assert area != null;
            area.addFood(food);
            this.foodRepository.remove(food);
            result = String.format("Successfully added %s to %s.",foodType, areaName);
        }
        else {
            result = String.format("There isn't a food of type %s.",foodType);
        }

        return result;
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal ;
        Area area = null;
        String result = null;
        if (animalType.equals("AquaticAnimal")){
            animal = new AquaticAnimal(animalName, kind, price);
        }
        else if (animalType.equals("TerrestrialAnimal")){
            animal = new TerrestrialAnimal(animalName, kind, price);
        }
        else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }
        for (Area area1 : this.areas){
            if (area1.getName().equals(areaName)){
                area  = area1;
            }
        }
        assert area != null;
        if (area.getCapacity() <= area.getAnimals().size()){
            result = "Not enough capacity.";
        }

        if (animal.getClass().getSimpleName().equals("AquaticAnimal")) {
            if (area.getClass().getSimpleName().equals("WaterArea")) {
                area.addAnimal(animal);
                result = String.format("Successfully added %s to %s.", animalType, areaName);
            }
        }
        else if (animal.getClass().getSimpleName().equals("TerrestrialAnimal")) {
                if (area.getClass().getSimpleName().equals("LandAnimal")) {
                    area.addAnimal(animal);
                    result = String.format("Successfully added %s to %s.",animalType, areaName);
                }
            }
        else {
            result = "The external living environment is not suitable.";
        }

        return result;
    }


    @Override
    public String feedAnimal(String areaName) {
        int feededAnimals = 0;
        for (Area area : areas){
            if (area.getName().equals(areaName)){
                for (Animal animal : area.getAnimals()){
                    animal.eat();
                    feededAnimals++;
                }
                break;
            }
        }
        return String.format("Animals fed: %s",feededAnimals);
    }

    @Override
    public String calculateKg(String areaName) {
        double result = 0;
        Area area = null;
        for (Area area1 : areas){
            if (area1.getName().equals(areaName)){
                area = area1;
            }
        }
        assert area != null;
        for (Animal animal : area.getAnimals()){
            result += animal.getKg();
        }
        return String.format("The kilograms of Area %s is %.2f.",areaName, result);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        for (Area area : areas){
            builder.append(area.getInfo()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
