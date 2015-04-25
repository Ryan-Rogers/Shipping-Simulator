
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
        
    // Example moveable
        Moveable moveableTest1 = new Moveable(new Location(1, 1), 
                new Location(10, 10), window, windowThread);
        window.mapMove(moveableTest1, moveableTest1.getLocation());
        Thread moveableTest1Thread = new Thread(moveableTest1);
        moveableTest1Thread.start();
        
    // Moveable Delay Sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            System.err.println(
                    "WizardTreasureChase Moveable Delay Sleep exception");
        }
        
    // Example moveable
        Moveable moveableTest2= new Moveable(new Location(20, 20), 
                new Location(30, 30), window, windowThread);
        window.mapMove(moveableTest2, moveableTest2.getLocation());
        Thread moveableTest2Thread = new Thread(moveableTest2);
        moveableTest2Thread.start();
        
    }
}
