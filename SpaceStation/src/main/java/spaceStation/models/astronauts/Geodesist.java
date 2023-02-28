package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut{
    private static final double GEO_OXYGEN = 50;

    public Geodesist(String name) {
        super(name, GEO_OXYGEN);
    }

}
