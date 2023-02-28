package catHouse.repositories;

import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;

public class ToyRepository implements Repository{
    private Collection<Toy>toys;

    public ToyRepository() {
        this.toys = new ArrayList<>();
    }

    protected Collection<Toy> getToys() {
        return toys;
    }

    protected void setToys(Collection<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void buyToy(Toy toy) {
        this.toys.add(toy);
    }

    @Override
    public boolean removeToy(Toy toy) {
        return this.toys.remove(toy);
    }

    @Override
    public Toy findFirst(String type) {

       if (type.equals("Ball")){
           for (Toy toy : this.getToys()){
               if (toy.getClass().getSimpleName().equals("Ball")){
                   return toy;
               }
           }
       }
        if (type.equals("Mouse")){
            for (Toy toy : this.getToys()){
                if (toy.getClass().getSimpleName().equals("Mouse")){
                    return toy;
                }
            }
        }
        return null;
    }
}
