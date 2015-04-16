/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Gui;

import static Gui.Window.mapMoveList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan Rogers
 */

// WindowThread runs window
class WindowUpdateThread extends Thread {
    
    static Window threadWindow;
    
    // Constructor
    public WindowUpdateThread(Window window) {
        threadWindow = window;
    }
    
    @Override
    public void run() {
        System.out.println("The window updater is starting on a new thread...");
        while(true) {
            System.out.println("WindowUpdateThread Updating...");
            updateCheck();
        }
    }
    
    // Processing queue
    public static boolean mapUpdate() {
        if(!threadWindow.getMapMoveList().isEmpty()) {
            if(!threadWindow.getMapButtons().isEmpty()) {
                threadWindow.getMapButtons().get(threadWindow.getLocationList().get(0).getY()).
                    get(threadWindow.getLocationList().get(0).getX()).setText("S");
                threadWindow.getMapMoveList().remove(0);
                threadWindow.getLocationList().remove(0);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    void updateCheck() {
        // if(!threadWindow.mapUpdate()) {
        if(!mapUpdate()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println("WindowUpdateThread Sleep Exception");
                Logger.getLogger(WindowUpdateThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}