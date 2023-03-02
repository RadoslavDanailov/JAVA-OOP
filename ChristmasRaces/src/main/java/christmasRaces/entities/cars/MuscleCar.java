package christmasRaces.entities.cars;

public class MuscleCar extends BaseCar{
   public MuscleCar(String model, int horsePower){
       super(model, horsePower, 5000);
   }

   @Override
    public void setHorsePower(int horsepower){
       if (horsepower < 400 || horsepower > 600){
           throw new IllegalArgumentException(String.format("Invalid horse power: %d.", horsepower));
       }
       super.setHorsePower(horsepower);
   }
}
