package viceCity.models.players;

public class MainPlayer extends BasePlayer{

    public MainPlayer() {
        super("Tommy Vercetti", 100);
    }

    MainPlayer mainPlayer = new MainPlayer();


    private MainPlayer getMainPlayer(){
        return this.mainPlayer;
    }

}
