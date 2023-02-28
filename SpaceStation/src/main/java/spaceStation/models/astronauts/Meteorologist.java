package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut {
    private static final double METEO_OXYGEN = 90;

    public Meteorologist(String name) {
        super(name, METEO_OXYGEN);
    }
}
