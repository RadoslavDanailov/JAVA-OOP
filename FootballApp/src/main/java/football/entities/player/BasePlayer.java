package football.entities.player;

import football.common.ExceptionMessages;
import football.entities.supplement.BaseSupplement;

public abstract class BasePlayer implements Player{
    private String name;
    private String nationality;
    private double kg;
    private int strength;

    public BasePlayer(String name, String nationality, double kg, int strength){
        this.setName(name);
        this.setNationality(nationality);
        this.kg = kg;
        this.setStrength(strength);
    }

    public String getName(){
        return this.name;
    }
    @Override
    public void setName(String name){
        if (name == null || name.trim().equals("")){
            throw new NullPointerException(ExceptionMessages.PLAYER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public String getNationality(){
        return this.nationality;
    }
    public void setNationality(String nationality){
        if (nationality == null || nationality.trim().equals("")){
            throw new NullPointerException(ExceptionMessages.PLAYER_NATIONALITY_NULL_OR_EMPTY);
        }
        this.nationality = nationality;
    }
    @Override
    public double getKg(){
        return this.kg;
    }

    @Override
    public int getStrength(){
        return this.strength;
    }


    public void setStrength(int strength){
        if (strength <= 0){
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO);
        }
        this.strength = strength;
    }




}
