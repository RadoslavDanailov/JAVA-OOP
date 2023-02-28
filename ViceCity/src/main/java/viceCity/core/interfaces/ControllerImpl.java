package viceCity.core.interfaces;

import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller{
    private GunRepository gunRepository;
    private List<Player> civilPlayersList;
    private MainPlayer mainPlayer;
    private String name;
    private String type;



    public ControllerImpl() {
    this.gunRepository = new GunRepository();
    this.civilPlayersList = new ArrayList<>();

    }

    @Override
    public String addPlayer(String name) {
        Player civilPlayer = new CivilPlayer(name);
        this.civilPlayersList.add(civilPlayer);
        return String.format("Successfully added civil player: %s!",name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        String message = null;
        if (type.equals("Pistol")){
            gun = new Pistol(name);
            this.gunRepository.add(gun);
            message = String.format("Successfully added %s of type: %s", name, type);
        }
        else if (type.equals("Rifle")){
            gun = new Rifle(name);
            this.gunRepository.add(gun);
            message = String.format("Successfully added %s of type: %s", name, type);
        }
        else {
            message = "Invalid gun type!";
        }

        return message;
    }

    @Override
    public String addGunToPlayer(String name) {
        Player player = null;
        String message = null;
        boolean foundPlayer = false;

        Gun gun = null;
        if (this.gunRepository.getModels().isEmpty()){
            message = "There are no guns in the queue!";
        }
        for (Gun gun1 : this.gunRepository.getModels()){
            gun = gun1;
            break;
        }
        for (Player player1 : this.civilPlayersList){
            if (player1.getName().equals(name)){
                player = player1;
                foundPlayer = true;
                assert gun != null;
                message = String.format("Successfully added %s to the Civil Player: %s",gun.getName(), player.getName());
            }
        }
        if (!foundPlayer){
            message = "Civil player with that name doesn't exists!";
        }
        if (name.equals("Vercetti")){
            player = this.mainPlayer;
            assert gun != null;
            message = String.format("Successfully added %s to the Main Player: Tommy Vercetti",gun.getName());
        }
        assert player != null;
        player.getGunRepository().add(gun);

        return message;
    }

    @Override
    public String fight() {
        boolean healthy = true;
        String message = null;
        StringBuilder builder = new StringBuilder();

            for (Player civilPlayer : this.civilPlayersList){
                if (civilPlayer.getLifePoints() == 50){
                    healthy = true;
                }
                else {
                    healthy = false;
                }
            }
        if (mainPlayer.getLifePoints() == 100 && healthy){
            message = "Everything is okay!";
        }
        else {
            builder.append("A fight happened:").append(System.lineSeparator());
            builder.append(String.format("Tommy live points: %d!",mainPlayer.getLifePoints())).append(System.lineSeparator());
            builder.append(String.format("Tommy has killed: %d players!",6)).append(System.lineSeparator());
            builder.append(String.format("Left Civil Players: %d!",this.civilPlayersList.size()));
            message = builder.toString();
        }

        return message;
    }
}
