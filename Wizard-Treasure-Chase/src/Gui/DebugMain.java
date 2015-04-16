/*
 * Temporary main used for testing Gui features for development
 */

package Gui;

import Map.Location;
import Ship.ShipBasic;

/**
 * @author Ryan Rogers
 */

public class DebugMain { // Main class
    public static void main(String[] args) { // Main loop
        
        // Window Creation
        Window window = new Window();
        
        // Starting window thread
        WindowThread windowThread = new WindowThread(window);
        windowThread.start();
        WindowUpdateThread windowUpdateThread = new WindowUpdateThread(window);
        windowUpdateThread.start();
        
        // Test ship
        ShipBasic shipBasic = new ShipBasic(new Location(4, 5), null, 0);
        
        // Test move of test 
        window.mapMove(shipBasic, new Location(10, 10));
        
        System.out.println("Threading test"); // DEBUG
        
    }
}

