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
    public WindowThread(String newFile, String newTheme) {
        this.file = newFile;
        this.theme = newTheme;
    }
    
    @Override
    public void run() {
        while(restart) {
            restart = false;
            threadWindow = null;
            threadWindow = new Window();
            threadWindow.setWindowThread(this);
            threadWindow.setFileName(file);
            threadWindow.setTheme(theme);
            threadWindow.main((String[]) null);
        }
    }
    
    public void setFile(String newFile) {
        file = newFile;
        restart = true;
    }
    
    public void setTheme(String newTheme) {
        theme = newTheme;
        restart = true;
    }
}