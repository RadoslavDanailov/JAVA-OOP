package viceCity.models.guns;

public class Pistol extends BaseGun{
    public Pistol(String name) {
        super(name, 10, 100);
    }
    @Override
    public int fire(){
        this.setBulletsPerBarrel(this.getBulletsPerBarrel() - 1);
        if (getBulletsPerBarrel() <= 0){
            this.setTotalBullets(this.getTotalBullets() - 10);
            this.setBulletsPerBarrel(10);
        }
        return 1;
    }
}
