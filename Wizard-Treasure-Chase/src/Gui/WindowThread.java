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
    
// Variables
    Window threadWindow;
    
// Consutrctor
    public WindowThread() {
    }
    
    @Override
    public void run() {
        threadWindow = new Window();
        threadWindow.setWindowThread(this);
        threadWindow.main((String[]) null);
    }
    
}