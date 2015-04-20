/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Gui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan Rogers
 */

// WindowThread runs window
class WindowUpdateThread extends Thread {
    
    static Window threadWindow;
    static WindowThread threadWindowThread;
    
    // Constructor
    public WindowUpdateThread(Window window, WindowThread windowThread) {
        threadWindow = window;
        threadWindowThread = windowThread;
    }
    
    @Override
    public void run() {
        System.out.println("The window updater is starting on a new thread...");
        while(threadWindowThread.isAlive()) {
            System.out.println("WindowUpdateThread Updating...");
            updateCheck();
        }
    }
    
    // Processing queue
    public static boolean mapUpdate() {
        if(!threadWindow.getMapMoveList().isEmpty()) {
            System.out.println("  mapMoveList is NOT empty");
            if(!threadWindow.getMapButtons().isEmpty()) {
                System.out.println("  mapButtons is NOT empty");
                char[][] newMap = threadWindow.getMapList();
                newMap[threadWindow.getLocationList().get(0).getY()]
                        [threadWindow.getLocationList().get(0).getX()] = 'S';
                threadWindow.getMapMoveList().remove(0);
                threadWindow.getLocationList().remove(0);
                threadWindow.printMap(newMap);
                threadWindow.setMapList(newMap);
                threadWindow.setMapList(new char[36][54]);
                threadWindow.getMapButtons().get(10).get(10).setText("S");
                System.out.println("  Update successfull");
                return true;
            } else {
                System.out.println("  mapButtons IS empty");
                System.out.println("  Update aborted");
                return false;
            }
        } else {
            System.out.println("  mapMoveList IS empty");
            System.out.println("  Update aborted");
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