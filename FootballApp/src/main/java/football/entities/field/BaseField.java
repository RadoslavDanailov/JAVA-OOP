package football.entities.field;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.BaseSupplement;
import football.entities.supplement.Supplement;

import java.util.*;

public abstract class BaseField implements Field{

    private String name;
    private int capacity;
    private Collection<Supplement>supplements;
    private Collection<Player>players;

    public BaseField(String name,  int capacity){
        this.name = name;
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public int sumEnergy(){
        int sum = 0;
    for (Supplement supplement : this.getSupplements()){
        sum += supplement.getEnergy();
    }
    return sum;
    }

    @Override
    public void addPlayer(Player player){
        if (this.getCapacity() > getPlayers().size()){
            this.getPlayers().add(player);
        }
        else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
    }
    @Override
    public void removePlayer(Player player){
        for (Player player1 : this.getPlayers()){
            if (player1.getName().equals(player.getName())){
                this.getPlayers().remove(player1);
                break;
            }
        }
    }
    @Override
    public void addSupplement(Supplement supplement){
        this.getSupplements().add(supplement);
    }

    @Override
    public void drag(){
        for (Player player : this.getPlayers()){
            player.stimulation();
        }
    }
    @Override
    public String getInfo(){
        StringBuilder builder = new StringBuilder();
        if (this.getPlayers().size() == 0){
            return "none";
        }
        builder.append(String.format("%s (%s):", getName(), getClass().getSimpleName())).
                append(System.lineSeparator());
        if (getPlayers().size() == 0){
            builder.append("none").append(System.lineSeparator());
        }
        else {
            List<String >playersNames = new ArrayList<>();
            builder.append("Player: ");
            for (Player player : getPlayers()) {
               playersNames.add(player.getName());
            }
            builder.append(String.join(" ", playersNames)).append(System.lineSeparator());
        }
        builder.append(String.format("Supplement: %d", getSupplements().size())).
        append(System.lineSeparator());
        int sumEnergy = 0;
        for (Supplement supplement :getSupplements()){
            sumEnergy += supplement.getEnergy();
        }
        builder.append(String.format("Energy: %d",sumEnergy));

        return builder.toString().trim();
    }
}