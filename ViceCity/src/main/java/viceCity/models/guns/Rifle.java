package viceCity.models.guns;

public class Rifle extends BaseGun{
    public Rifle(String name) {
        super(name, 50, 500);
    }

    @Override
    public int fire(){
        this.setBulletsPerBarrel(this.getBulletsPerBarrel() - 5);
        if (getBulletsPerBarrel() <= 0){
            this.setTotalBullets(this.getTotalBullets() - 50);
            this.setBulletsPerBarrel(10);
        }
        return 5;
    }

}
