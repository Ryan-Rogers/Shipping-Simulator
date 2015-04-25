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
    boolean restart = true;
    String file;
    String theme;
    
    // Consutrctor
    public WindowThread(Window window, String newFile, String newTheme) {
        threadWindow = window;
        this.file = newFile;
        this.theme = newTheme;
        threadWindow.setWindowThread(this);
    }
    
    @Override
    public void run() {
        if(restart) {
            restart = false;            
            threadWindow.main((String[]) null);
        }
    }
    
    public void setFile(String newFile) {
        file = newFile;
        restart = true;;
    }
    
    public void setTheme(String newTheme) {
        theme = newTheme;
        restart = true;
    }
}