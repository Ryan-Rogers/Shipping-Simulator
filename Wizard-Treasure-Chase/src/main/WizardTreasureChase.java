
/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package main;

import Gui.Window;
import Gui.WindowThread;
import Map.Location;
import Ship.Moveable;

/**
 * @author Ryan Rogers
 */

public class WizardTreasureChase {
    public static void main(String[] args) {
        
    //123 GO GO GO
        
    // Window Creation
        Window window = new Window();
        
    // Starting window thread
        WindowThread windowThread = new WindowThread(window);
        windowThread.start();
    
    // GUI Loading Sleep
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.err.println(
                    "WizardTreasureChase GUI Loading Sleep exception");
        }
        
    // Example CargoShip
        Moveable CargoShip = new Moveable(new Location(1, 1), 
                new Location(10, 10), window, windowThread);
        window.mapMove(CargoShip, CargoShip.getLocation());
        Thread moveableTest1Thread = new Thread(CargoShip);
        moveableTest1Thread.start();
        
    // Example ContainerShip
        Moveable ContainerShip = new Moveable(new Location(20, 20), 
                new Location(15, 20), window, windowThread);
        window.mapMove(ContainerShip, ContainerShip.getLocation());
        Thread moveableTest2Thread = new Thread(ContainerShip);
        moveableTest2Thread.start();
        
    }
}
