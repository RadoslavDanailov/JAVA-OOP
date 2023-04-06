package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{
    public TerrestrialAnimal(String name, String kind,  double price){
        super(name, kind, 5.50, price);
    }
    public void eat(){
        this.setKg(this.getKg() + 5.70);
    }
}
