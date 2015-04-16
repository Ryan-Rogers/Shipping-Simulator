/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Gui;

import Map.Location;
import Ship.ShipBasic;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Ryan Rogers
 */

// WindowThread runs window
public class WindowThread extends Thread {
    
    Window threadWindow;
    
    // Consutrctor
    public WindowThread(Window window) {
        threadWindow = window;
    }
    
    @Override
    public void run() {
        System.out.println("The window is starting on a new thread...");
        threadWindow.main((String[]) null);
        System.out.println("The window thread has stopped");
    }
}