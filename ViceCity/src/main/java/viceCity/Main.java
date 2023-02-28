package viceCity;

import viceCity.core.EngineImpl;
import viceCity.core.interfaces.Controller;
import viceCity.core.interfaces.ControllerImpl;
import viceCity.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {                         //65/100
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}