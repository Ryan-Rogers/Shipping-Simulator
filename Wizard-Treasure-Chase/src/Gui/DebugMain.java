/*
 * Temporary main used for testing Gui features for development
 */

package Gui;

import Creature.Entity;
import Map.Location;
import Map.MapItem;
import Ship.ShipBasic;
import ThreadMaster.RelayMaster;

/**
 * @author Ryan Rogers
 */

public class DebugMain {
    public static void main(String[] args) {
        
        Display display = new Display(); // GUI window
        
        ShipBasic ship = new ShipBasic(new Location(5, 5),
                new RelayMaster(), 1);
        
        display.setMap(ship, new Location(5, 5));
        
    } // end main       
    
} // end DebugMain
