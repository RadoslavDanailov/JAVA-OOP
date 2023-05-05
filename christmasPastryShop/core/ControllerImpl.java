package christmasPastryShop.core;

import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.OpenBooth;
import christmasPastryShop.entities.booths.PrivateBooth;
import christmasPastryShop.entities.cocktails.Hibernation;
import christmasPastryShop.entities.cocktails.MulledWine;
import christmasPastryShop.entities.delicacies.Gingerbread;
import christmasPastryShop.entities.delicacies.Stolen;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

public  class ControllerImpl implements Controller {

    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;

    private double sum = 0;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy = null;
        switch (type) {
            case "Gingerbread":
                delicacy = new Gingerbread(name, price);
                break;
            case "Stolen":
                delicacy = new Stolen(name, price);
                break;
        }
        for (Delicacy delicacy1 : this.delicacyRepository.getAll()) {
            if (delicacy1.getName().equals(name) && delicacy1.getClass().getSimpleName().equals(type)) {
                throw new IllegalArgumentException(String.format("%s %s is already in the pastry shop!", type, name));
            }
        }
        this.delicacyRepository.add(delicacy);

        return String.format("Added delicacy %s - %s to the pastry shop!", name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail = null;

        switch (type) {
            case "Hibernation":
                cocktail = new Hibernation(name, size, brand);
                break;
            case "MulledWine":
                cocktail = new MulledWine(name, size, brand);
                break;
        }
        for (Cocktail cocktail1 : this.cocktailRepository.getAll()) {
            if (cocktail1.getName().equals(name) && cocktail1.getClass().getSimpleName().equals(type)) {
                throw new IllegalArgumentException(String.format("%s %s is already in the pastry shop!", type, name));
            }
        }
        this.cocktailRepository.add(cocktail);
        return String.format("Added cocktail %s - %s to the pastry shop!", name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {

        Booth booth = boothRepository.getByNumber(boothNumber);

        if (booth == null){
            switch (type) {
                case "OpenBooth":
                    booth = new OpenBooth(boothNumber, capacity);
                    break;
                case "PrivateBooth":
                    booth = new PrivateBooth(boothNumber, capacity);
                    break;
            }
        }
        else {
            throw new IllegalArgumentException(String.format("Booth %d is already added to the pastry shop!", boothNumber));
        }

        this.boothRepository.add(booth);

        return String.format("Added booth number %d in the pastry shop!",boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        for (Booth booth : this.boothRepository.getAll()){
            if (!booth.isReserved() && booth.getCapacity() >= numberOfPeople){
                booth.reserve(numberOfPeople);
                return String.format("Booth %d has been reserved for %d people!", booth.getBoothNumber(), numberOfPeople);
            }
        }
        return String.format("No available booth for %d people!",numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        double boothBill = 0;
        for (Booth booth : this.boothRepository.getAll()){
            if (booth.getBoothNumber() == boothNumber){
                boothBill = booth.getBill() + booth.getPrice();
                sum += boothBill;
                booth.clear();
                break;
            }
        }
        return String.format("Booth: %d\nBill: %.2f", boothNumber, boothBill);
    }

    @Override
    public String getIncome() {
        return String.format("Income: %.2flv",sum);
    }
}
