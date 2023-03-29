package fairyShop.models;

import fairyShop.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseHelper implements Helper {
    private String name;
    private int energy;
    private Collection<Instrument>instruments;

    public BaseHelper(String name, int energy){
        this.setName(name);
        this.setEnergy(energy);
        this.instruments = new ArrayList<>();
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setName(String name){
        if (name == null || name.equals("")){
            throw new NullPointerException(ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void work() {
        if (this.getEnergy() - 10 < 0){
            this.setEnergy(0);
        }
        else {
            this.setEnergy(this.getEnergy() - 10);
        }
    }

    @Override
    public void addInstrument(Instrument instrument) {
        this.getInstruments().add(instrument);
    }

    @Override
    public boolean canWork() {
        return this.getEnergy() > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }
}
