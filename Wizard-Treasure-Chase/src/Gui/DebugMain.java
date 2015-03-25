/*
 * Temporary main used for testing Gui features for development
 */

package Gui;
import javax.swing.JFrame;
import java.awt.*;

/**
 * @author Ryan Rogers
 */

public class DebugMain {
    public static void main(String[] args) {
        Display testDisplay = new Display();
        testDisplay.loadMap();
        testDisplay.printMap();
        
        createWindow();
        
    } // end main
    
    public static void createWindow() {
        JFrame window = new JFrame();
        window.setVisible(true);
        window.setTitle("Wizard Treasure Chase");
        window.setSize(960, 540);
        window.setLocationRelativeTo(null); // Centers the window on the screen
        Button testButton = new Button();
        //ActionListener testActionListener = new ActionListener;
        //testButton.addActionListener();
    }
    
} // end DebugMain
