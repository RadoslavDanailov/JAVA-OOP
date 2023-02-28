package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.interfaces.Fresh;
import restaurant.entities.drinks.interfaces.Smoothie;
import restaurant.entities.healthyFoods.interfaces.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.Salad;
import restaurant.entities.healthyFoods.interfaces.VeganBiscuits;
import restaurant.entities.tables.interfaces.InGarden;
import restaurant.entities.tables.interfaces.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {
        private HealthFoodRepository<HealthyFood>healthFoodRepository;
        private BeverageRepository<Beverages>beverageRepository;
        private TableRepository<Table>tableRepository;
        private double allMoney;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    protected HealthyFood findHealthyFood(String type, double price, String name){
        HealthyFood healthyFood = null;
        if (type.equals("Salad")){
            healthyFood = new Salad(name, price);
        }
        else if (type.equals("VeganBiscuits")){
            healthyFood = new VeganBiscuits(name, price);
        }
        return healthyFood;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood healthyFood = findHealthyFood(type, price, name);

        for (HealthyFood healthyFood1 : this.healthFoodRepository.getAllEntities()) {
            if (healthyFood1.getName().equals(healthyFood.getName())) {
                throw new IllegalArgumentException(String.format("%s is already in the healthy menu!", name));
            }
        }
        this.healthFoodRepository.add(healthyFood);
        return String.format("Added %s to the healthy menu!", name);
    }

    protected Beverages findHBeverage(String type, int counter, String brand, String name){
        Beverages beverages = null;
        if (type.equals("Fresh")){
            beverages = new Fresh(name, counter, brand);
        }
        else if (type.equals("Smoothie")){
            beverages = new Smoothie(name, counter, brand);
        }
        return beverages;
    }
    @Override
    public String addBeverage(String type, int counter, String brand, String name){
       Beverages beverages = findHBeverage(type, counter, brand, name);
       for (Beverages beverages1 : this.beverageRepository.getAllEntities()) {
           if (beverages1.getBrand().equals(beverages.getBrand())) {
               throw new IllegalArgumentException(String.format("%s is already in the beverage menu!", brand));
           }
       }
       this.beverageRepository.add(beverages);
        return String.format("Added %s - %s to the beverage menu!",type, brand);
    }

    protected Table findTable(String type, int tableNumber, int capacity){
        Table table = null;
        if (type.equals("Indoors")) {
            table = new Indoors(tableNumber, capacity);
        }
        else if (type.equals("InGarden")){
            table = new InGarden(tableNumber,capacity);
        }
        return table;
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = findTable(type, tableNumber, capacity);

            for (Table table1 : this.tableRepository.getAllEntities()){
                if (table1.getTableNumber() == (table.getTableNumber())){
                    throw new IllegalArgumentException(String.format("Table %s is already added to the healthy restaurant!", tableNumber));
                }
            }

        this.tableRepository.add(table);
        return String.format("Added table number %d in the healthy restaurant!",tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        for (Table table: this.tableRepository.getAllEntities()){
            if (!table.isReservedTable() && table.getSize() >= numberOfPeople){
                table.reserve(numberOfPeople);
                return String.format("Table %d has been reserved for %d people.",table.getTableNumber(),numberOfPeople);
            }
        }
        return String.format("There is no such table for %s people.", numberOfPeople);
    }


    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = this.tableRepository.byNumber(tableNumber);
        HealthyFood healthyFood = this.healthFoodRepository.foodByName(healthyFoodName);
        if (table == null){
            return String.format("Could not find table %d.",tableNumber);
        }
        if (healthyFood == null){
            return String.format("No %s in the healthy menu.",healthyFoodName);
        }
        table.orderHealthy(healthyFood);
        return String.format("%s ordered from table %d.",healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tableRepository.byNumber(tableNumber);
        Beverages beverages = this.beverageRepository.beverageByName(name, brand);
        if (table == null){
            return String.format("Could not find table %d.",tableNumber);
        }
        if (beverages == null){
            return String.format("No %s - %s in the beverage menu.",name, brand);
        }
        table.orderBeverages(beverages);
        return String.format("%s ordered from table %d.",name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table foundTable = getTableByNumber(tableNumber);
        double bill = foundTable.bill();
        bill =+ foundTable.allPeople();
        allMoney += bill;
        foundTable.clear();
        return String.format("Table: %d with bill: %.2f.",tableNumber, bill);
    }

    private Table getTableByNumber(int tableNumber) {
        Table foundTable = null;
        for (Table table : this.tableRepository.getAllEntities()){
            if (table.getTableNumber() == tableNumber){
                foundTable = table;
            }
        }
        return foundTable;
    }


    @Override
    public String totalMoney() {
        return String.format("Total money for the restaurant: %.2flv.", allMoney);
    }
}
