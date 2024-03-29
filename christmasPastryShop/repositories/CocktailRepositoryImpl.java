package christmasPastryShop.repositories;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.repositories.interfaces.CocktailRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CocktailRepositoryImpl implements CocktailRepository<Cocktail> {
    private Collection<Cocktail> cocktails;

    public CocktailRepositoryImpl(){
        this.cocktails = new ArrayList<>();
    }

    @Override
    public Cocktail getByName(String name) {
        for (Cocktail cocktail : this.cocktails){
            if (cocktail.getName().equals(name)){
                return cocktail;
            }
        }
        return null;
    }

    @Override
    public Collection<Cocktail> getAll() {
        return Collections.unmodifiableCollection(cocktails);
    }

    @Override
    public void add(Cocktail cocktail) {
        this.cocktails.add(cocktail);
    }
}
