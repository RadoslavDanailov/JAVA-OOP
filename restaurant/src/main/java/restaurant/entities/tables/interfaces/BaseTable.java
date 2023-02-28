package restaurant.entities.tables.interfaces;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table{
    private Collection<HealthyFood>healthyFood;
    private Collection<Beverages>beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;
    private BaseTable table;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.isReservedTable = false;
//        this.allPeople = allPeople;

    }

    protected Collection<HealthyFood> getHealthyFood() {
        return healthyFood;
    }

    protected void setHealthyFood(Collection<HealthyFood> healthyFood) {
        this.healthyFood = healthyFood;
    }

    protected Collection<Beverages> getBeverages() {
        return beverages;
    }

    protected void setBeverages(Collection<Beverages> beverages) {
        this.beverages = beverages;
    }

    protected int getNumber() {
        return number;
    }

    protected void setNumber(int number) {
        this.number = number;
    }

    protected void setSize(int size) {
        if (size < 0){
            throw new IllegalArgumentException("Size has to be greater than 0!");
        }
        this.size = size;
    }

    protected int getNumberOfPeople() {
        return numberOfPeople;
    }

    protected void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople < 0){/////////////////////////////////////////////////////////////////////////////////
            throw new IllegalArgumentException("Cannot place zero or less people!");
        }
        this.numberOfPeople = numberOfPeople;
    }

    protected double getPricePerPerson() {
        return pricePerPerson;
    }

    protected void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    protected void setReservedTable(boolean reservedTable) {
        isReservedTable = reservedTable;
    }

    protected double getAllPeople() {
        return allPeople;
    }

    protected void setAllPeople(double allPeople) {
        this.allPeople = allPeople;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        if (getNumberOfPeople() >= 1){
            return true;
        }
        ////////////////////////////////////////////////////////////////
        return false;
    }

    @Override
    public double allPeople() {
        int people = getNumberOfPeople();
        return people * pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {
        isReservedTable = true;
        setNumberOfPeople(numberOfPeople);/////////////////////////////////////////////////
    }

    @Override
    public void orderHealthy(HealthyFood food) {
       this.getHealthyFood().add(food);
    }

    @Override
    public void orderBeverages(Beverages beverage) {
        this.beverages.add(beverage);
    }

    @Override
    public double bill() {
        double sum = 0;

        for (Beverages beverage : this.getBeverages()){
            sum += beverage.getPrice();
        }
        for (HealthyFood food : this.getHealthyFood()) {
            sum += food.getPrice();
        }
        return sum;
    }

    @Override
    public void clear() {
        this.getBeverages().removeAll(beverages);
        this.getHealthyFood().removeAll(healthyFood);
        this.setReservedTable(false);
        this.setNumberOfPeople(0);
        this.setPricePerPerson(0);
    }

    @Override
    public String tableInformation() {

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Table - %d",this.getNumber())).append(System.lineSeparator());
        builder.append(String.format("Size - %d",this.getSize())).append(System.lineSeparator());
        builder.append(String.format("Type - %s",this.getClass().getSimpleName())).append(System.lineSeparator());
        builder.append(String.format("All price - %f",this.getPricePerPerson())).append(System.lineSeparator());
        /////////////////////////////////// for current table
        return builder.toString().trim();
    }
}
