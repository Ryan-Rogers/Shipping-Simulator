
/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package main;

import Gui.Window;
import Gui.WindowThread;

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
        
    }
}
