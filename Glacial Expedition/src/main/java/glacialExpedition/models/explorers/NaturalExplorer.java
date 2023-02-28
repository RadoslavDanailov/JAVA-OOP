package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    public NaturalExplorer(String name) {
        super(name, 60);
    }

    @Override
    public void search() {
        this.setEnergy(this.getEnergy() - 7);
        if (this.getEnergy() < 0){
            this.setEnergy(0);
        }
    }
}
