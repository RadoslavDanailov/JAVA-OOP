package christmasRaces.entities.cars;

public class SportsCar extends BaseCar{

    public SportsCar(String model, int horsePower){
        super(model, horsePower, 3000);
    }

    @Override
    public void setHorsePower(int horsePower){
        if (horsePower < 250 || horsePower > 450){
            throw new IllegalArgumentException(String.format("Invalid horse power: %d.", horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
