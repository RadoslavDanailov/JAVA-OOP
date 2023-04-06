package football.core;


import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private SupplementRepository supplementRepository;
    private Collection<Field>fields;

    public ControllerImpl(){
        this.supplementRepository = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        switch (fieldType){
            case "ArtificialTurf":
                this.fields.add(new ArtificialTurf(fieldName));
                return String.format("Successfully added %s.",fieldType);

            case "NaturalGrass":
                this.fields.add(new NaturalGrass(fieldName));
                return String.format("Successfully added %s.",fieldType);
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }
    }

    @Override
    public String deliverySupplement(String type) {
        if (type.equals("Powdered")){
            supplementRepository.add(new Powdered());
            return String.format("Successfully added %s.",type);
        }
        else if (type.equals("Liquid")){
            supplementRepository.add(new Liquid());
            return String.format("Successfully added %s.",type);
        }
        else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement supplement = null;
        String result = null;
        if (supplementType.equals("Powdered")){
            supplement = new Powdered();
        }
        else if (supplementType.equals("Liquid")){
            supplement = new Liquid();
        }
        else {
            throw new IllegalArgumentException(String.format("There isn't a Supplement of type %s.",supplementType));
        }
        for (Field field : fields){
            if (field.getName().equals(fieldName)){
                field.getSupplements().add(supplement);
                this.supplementRepository.remove(supplement);
                result = String.format("Successfully added %s to %s.",supplementType, fieldName);
            }
        }
        return result;
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player = null;
        String result = null;
        Field field = null;
        for (Field field1 : fields){
            if (field1.getName().equals(fieldName)){
                field = field1;
            }
            break;
        }

                if (playerType.equals("Men")){
                    player = new Men(playerName, nationality, strength);
                    assert field != null;
                    if (field.getClass().getSimpleName().equals("NaturalGrass")){
                        result = String.format("Successfully added %s to %s.",playerType, fieldName);
                        field.addPlayer(player);
                    }
                    else {
                        result = "The pavement of the terrain is not suitable.";
                    }
                }
                else if (playerType.equals("Women")){
                   player = new Women(playerName, nationality, strength);
                    assert field != null;
                    if (field.getClass().getSimpleName().equals("ArtificialTurf")){
                        result = String.format("Successfully added %s to %s.",playerType, fieldName);
                        field.addPlayer(player);
                    }
                   else {
                       result = "The pavement of the terrain is not suitable.";
                   }
                }
                else {
                    throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
                }
        return result;
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field currentField = null;
        for (Field field : fields){
            if (field.getName().equals(fieldName)){
                currentField = field;
                field.drag();
            }
            break;
        }
        assert currentField != null;
        return String.format("Player drag: %d", currentField.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        int sum = 0;
        for (Field field : fields){
            if (field.getName().equals(fieldName)){
                for (Player player : field.getPlayers()){
                    sum += player.getStrength();
                }
            }
            break;
        }
        return String.format("The strength of Field %s is %d.",fieldName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        for (Field field : fields){
            builder.append(field.getInfo());
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
