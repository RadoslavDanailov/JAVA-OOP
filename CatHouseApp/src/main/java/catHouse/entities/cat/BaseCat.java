package catHouse.entities.cat;

public abstract class BaseCat implements Cat{
    private String name;
    private String breed;
    private int kilograms;
    private double price;

    public BaseCat(String name, String breed, double price) {   ///////kilograms s consstructora
        this.setName(name);
        this.setBreed(breed);
        this.setPrice(price);
                               //            this.setKilograms(kilograms);
    }

    protected String getBreed() {
        return breed;
    }

    protected void setBreed(String breed) {
        if (breed == null || breed.equals("")){
            throw new NullPointerException("Cat breed cannot be null or empty.");
        }
        this.breed = breed;
    }

    protected void setKilograms(int kilograms) {
        this.kilograms = kilograms;
    }

    protected void setPrice(double price) {
        if (price <= 0){
            throw new IllegalArgumentException("Cat price cannot be below or equal to 0.");
        }
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.equals("")){
            throw new NullPointerException("Cat name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public int getKilograms() {
        return kilograms;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public abstract void eating();
}
