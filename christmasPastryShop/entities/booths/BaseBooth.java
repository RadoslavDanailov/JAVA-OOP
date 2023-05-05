package christmasPastryShop.entities.booths;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseBooth implements Booth {
    private Collection<Delicacy>delicacyOrders;
    private Collection<Cocktail>cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public Collection<Delicacy> getDelicacyOrders() {
        return delicacyOrders;
    }

    public Collection<Cocktail> getCocktailOrders() {
        return cocktailOrders;
    }

    @Override
    public int getBoothNumber() {
        return boothNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public BaseBooth(int boothNumber, int capacity, double pricePerPerson){
        this.delicacyOrders = new ArrayList<>();
        this.cocktailOrders =  new ArrayList<>();
        this.setBoothNumber(boothNumber);
        this.setCapacity(capacity);
        this.setPricePerPerson(pricePerPerson);
        this.isReserved = false;
        this.price = 0;
        this.numberOfPeople = 0;
    }

    public void setBoothNumber(int boothNumber){
        this.boothNumber = boothNumber;
    }
    public void setCapacity(int capacity){
        if (capacity < 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }
    public void setNumberOfPeople(int numberOfPeople){
        if (numberOfPeople <= 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }
    public void setPricePerPerson(double pricePerPerson){
        this.pricePerPerson = pricePerPerson;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void reserve(int numberOfPeople){
        if (numberOfPeople > 0){
            this.isReserved = true;
            setNumberOfPeople(numberOfPeople);
            setPrice(numberOfPeople * pricePerPerson);
        }
    }
    public double getBill(){
        double sum = 0;
        for (Cocktail cocktail : cocktailOrders){
            sum += cocktail.getPrice();
        }
        for (Delicacy delicacy : delicacyOrders){
            sum += delicacy.getPrice();
        }
        return sum;
    }
    public void clear(){
        this.delicacyOrders.forEach(delicacy -> delicacyOrders.remove(delicacy));
        this.cocktailOrders.forEach(cocktail -> cocktailOrders.remove(cocktail));
        this.isReserved = false;
        this.numberOfPeople = 0;
        this.price = 0;
    }

}
