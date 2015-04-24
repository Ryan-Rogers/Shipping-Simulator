/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Gui;

/**
 * @author Ryan Rogers
 */

// WindowThread runs window
public class WindowThread extends Thread {
    
    Window threadWindow;
    
    // Consutrctor
    public WindowThread(Window window) {
        threadWindow = window;
        threadWindow.setWindowThread(this);
    }
    
    @Override
    public void run() {
        threadWindow.main((String[]) null);
        System.err.println("Window thread has stopped");
    }
}