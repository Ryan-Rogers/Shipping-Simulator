
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
    
    static String file;
    static String theme;
    
    public static void main(String[] args) {
        
    // GUI Window
        file = "complex"; // Default files
        theme = "Theme/Future/"; // Default theme
        WindowThread windowThread = new WindowThread(file, theme);
        windowThread.start();
        
    }
}
