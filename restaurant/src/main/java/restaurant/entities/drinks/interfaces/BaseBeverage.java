package restaurant.entities.drinks.interfaces;

public abstract class BaseBeverage implements Beverages{
    private String name;
    private int counter;
    private double price;
    private String brand;

    public BaseBeverage(String name, int counter, double price, String brand) {
        this.setName(name);
        this.setCounter(counter);
        this.setPrice(price);
        this.setBrand(brand);
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or white space!");
        }
        this.name = name;
    }

    protected void setCounter(int counter) {
        if (counter <= 0){
            throw new IllegalArgumentException("Counter cannot be less or equal to zero!");
        }
        this.counter = counter;
    }

    protected void setPrice(double price) {
        if (counter <= 0){
            throw new IllegalArgumentException("Price cannot be less or equal to zero!");
        }
        this.price = price;
    }

    protected void setBrand(String brand) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Brand cannot be null or white space!");
        }
        this.brand = brand;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCounter() {
        return this.counter;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }
}
