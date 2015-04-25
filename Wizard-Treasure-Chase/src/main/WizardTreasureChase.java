
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
        
        file = "complex";
        theme = "Theme/Past/";
        
    // Window Creation
        Window window = new Window();
        window.setFileName(file);
        window.setTheme(theme);
        
    // Starting window thread
        WindowThread windowThread = new WindowThread(window, file, theme);
        windowThread.start();
        
    }
}
