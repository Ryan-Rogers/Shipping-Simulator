/*
 * Temporary main used for testing Gui features for development
 */

package Gui;

import Map.Location;
import Ship.ShipBasic;

/**
 * @author Ryan Rogers
 */

public class DebugMain {
    
    private static Object thread;
    
    public static void main(String[] args) { // Main loop
        System.out.println("Main thread has started");
        
        // Window Creation
        Window window = new Window();
        
        // Starting window thread
        WindowThread windowThread = new WindowThread(window);
        windowThread.start();
        
        // Test ship
        ShipBasic shipBasic = new ShipBasic(new Location(11, 10), null, 0);
        ShipBasic shipBasic2 = new ShipBasic(new Location(21, 20), null, 0);
        
        // Test move of test
        window.mapMove(shipBasic, new Location(10, 10));
        window.mapMove(shipBasic, new Location(9, 10));
        window.mapMove(shipBasic2, new Location(20, 20));
        window.mapMove(shipBasic2, new Location(19, 20));
        
    }
}

