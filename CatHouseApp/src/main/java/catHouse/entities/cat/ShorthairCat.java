package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        this.setKilograms(7);
    }
    @Override
    public void eating() {
        this.setKilograms(this.getKilograms() + 1);
    }
}
