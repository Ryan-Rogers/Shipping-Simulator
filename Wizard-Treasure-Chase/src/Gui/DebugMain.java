/*
 * Temporary main used for testing Gui features for development
 */

package Gui;

import Map.Location;
import Ship.ShipBasic;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * @author Ryan Rogers
 */

public class DebugMain { // Main class
    public static void main(String[] args) { // Main loop
        Window window = new Window();
        Application.launch(Window.class, args);
        //Application.launch(window.getClass(), args);
        Window.setMap(new ShipBasic(new Location(4, 5), null, 0), new Location(5, 5));
        //window.setMap(new ShipBasic(new Location(4, 5), null, 0), new Location(5, 5));
        
    }
}
