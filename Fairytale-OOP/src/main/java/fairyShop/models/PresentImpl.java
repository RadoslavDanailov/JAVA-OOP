package fairyShop.models;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;

public class PresentImpl implements Present{
    private String name;
    private int energyRequired;

    public PresentImpl(String name, int energyRequired){
        this.setName(name);
        this.setEnergyRequired(energyRequired);
    }

    private void setEnergyRequired(int energyRequired) {
        this.energyRequired = energyRequired;
    }

    private void setName(String name) {
        if (name == null || name.equals("")){
            throw new NullPointerException(ExceptionMessages.PRESENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    @Override
    public boolean isDone() {
        return this.getEnergyRequired() <= 0;
    }

    @Override
    public void getCrafted() {
        if (this.getEnergyRequired() - 10 < 0){
            this.setEnergyRequired(0);
        }
        else {
            this.setEnergyRequired(this.getEnergyRequired() - 10);
        }
    }
}
