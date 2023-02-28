package restaurant.entities.healthyFoods.interfaces;

public abstract class Food implements HealthyFood{
    private String name;
    private double portion;
    private double price;

    public Food(String name, double portion, double price) {
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or white space!");
        }
        this.name = name;
    }

    protected void setPortion(double portion) {
        if (portion <= 0){
            throw new IllegalArgumentException("Portion cannot be less or equal to zero!");
        }
        this.portion = portion;
    }

    protected void setPrice(double price) {
        if (price <= 0){
            throw new IllegalArgumentException("Price cannot be less or equal to zero!");
        }
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPortion() {
        return this.portion;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
