package christmasPastryShop.entities.cocktails;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;

public abstract class BaseCocktail implements Cocktail {
    private String name;
    private int size;
    private double price;
    private String brand;

    public BaseCocktail(String name, int size, double price, String brand){
        this.setName(name);
        this.setSize(size);
        this.setPrice(price);
        this.setBrand(brand);
    }

    public String getName(){
        return this.name;
    }
    public int getSize(){
        return this.size;
    }
    public double getPrice(){
        return this.price;
    }
    public String getBrand(){
        return this.brand;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSize(int size){
        this.size = size;
    }
    public void setPrice(double price){
        this. price = price;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }

    public String toString(){
        return String.format("%s %s - %dml - %.2flv",this.name, this.brand,this.size, this.price);
    }

}
