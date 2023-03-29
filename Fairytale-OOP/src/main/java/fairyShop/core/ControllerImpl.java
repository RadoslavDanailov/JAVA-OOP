package fairyShop.core;

import fairyShop.common.ExceptionMessages;
import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller{
    private HelperRepository<Helper> helperRepository = new HelperRepository<>();
    private PresentRepository<Present> presentRepository = new PresentRepository();
    private Shop shop = new ShopImpl();

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper ;
        switch (type){
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default: throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }
        helperRepository.add(helper);
        return String.format("Successfully added %s named %s!",type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Instrument instrument = new InstrumentImpl(power);
        boolean isFound = false;
        for (Helper helper : this.helperRepository.getModels()){
            if (helper.getName().equals(helperName)){
                helper.getInstruments().add(instrument);
                isFound = true;
                break;
            }
        }
        if (!isFound){
            throw new IllegalArgumentException("The helper you want to add an instrument to doesn't exist!");
        }
        return String.format("Successfully added instrument with power %d to helper %s!",power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);
        return String.format("Successfully added Present: %s!", presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper>suitableHelpers = new ArrayList<>();
        Present present = this.presentRepository.findByName(presentName);
        int brokenInstruments = 0;

        for (Helper helper : this.helperRepository.getModels()){
            if (helper.getEnergy() > 50){
                suitableHelpers.add(helper);
            }
        }
        if (suitableHelpers.size() == 0){
            throw new IllegalArgumentException("There is no helper ready to start crafting!");
        }

        for (Helper helper : suitableHelpers){
            shop.craft(present, helper);
            brokenInstruments += (int) helper.getInstruments().stream().filter(Instrument::isBroken).count();
            if (present.isDone()){
                return String.format("Present %s is %s. %d instrument/s have been broken while working on it!",presentName,  "done", brokenInstruments);
            }
        }

        return String.format("Present %s is not done. %d instrument/s have been broken while working on it!",presentName, brokenInstruments);
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%d presents are done!", this.presentRepository.getModels().size()));
        builder.append(System.lineSeparator());
        builder.append("Helpers info:").append(System.lineSeparator());
        for (Helper helper : this.helperRepository.getModels()){
            builder.append(String.format("Name: %s", helper.getName())).append(System.lineSeparator());
            builder.append(String.format("Energy: %d", helper.getEnergy())).append(System.lineSeparator());
            builder.append(String.format("Instruments: %d not broken left",
                    helper.getInstruments().size() - helper.getInstruments().stream().filter(Instrument::isBroken).count()));
            builder.append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
