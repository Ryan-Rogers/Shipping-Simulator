/*
 * Temporary main used for testing Gui features for development
 */

package Gui;

/**
 * @author Ryan Rogers
 */

public class DebugMain {
    public static void main(String[] args) {
        
        Display display = new Display(); // GUI window
        
        // Loading map
        if(display.loadMap()) {
            System.out.println("Map loaded successfully");
        } else { // loadMap returned false
            System.out.println("Could not load the map!");
        }
        
        display.printMapToConsole();
        
    } // end main       
    
} // end DebugMain
