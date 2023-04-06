package football.entities.player;

public class Women extends BasePlayer{
    public Women(String name, String nationality, int strength){
        super(name, nationality, 60.00, strength);
    }

    @Override                                                      //play only on ArtificialTurf
    public  void stimulation(){
        this.setStrength(this.getStrength() + 115);
    }
}
