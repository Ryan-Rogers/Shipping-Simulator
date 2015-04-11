
/*
 * Display class created as beginning of GUI
 * Holds the loaded-from-file version of the terrain map
 * Will load map from file and display the map images within the GUI
 */

package Gui;

// Java
import java.io.File;

// Java Util
import java.util.Scanner;
import java.util.ArrayList;

// Java Awt
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Javax Swing
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JApplet;

// Javafx
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Ryan Rogers
*/

public class Display extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Loading fxml file
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        // Window Properties
        primaryStage.setTitle("Wizard Treasure Chase");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }
    
    // Credit Button Click
    static class CreditClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    public boolean loadMap() {
        int columns = 54; // Default column number
        int rows = 36; // Default row number
        Scanner mapReader;
        char[][] mapHolder = new char[columns][rows];
        String fileName = "complex";
        try{
            mapReader = new Scanner(new File(fileName + ".map.txt"));
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Exception occured while loading map!");
            return false;
        }
        String line;
        String[] splitLine;
        int currentRow;
        int currentColumn;
        char code;
        while(mapReader.hasNextLine()) {
            line = mapReader.nextLine();
            splitLine = line.split(",");
            currentRow = Integer.parseInt(splitLine[0]);
            currentColumn = Integer.parseInt(splitLine[1]);
            code = splitLine[2].charAt(0);
            mapHolder[currentRow][currentColumn] = code;
        }
        // map = mapHolder;
        return true;
    }
    
    public void printMapToConsole() {
        // Uses printMap method to print the map to System.out
        int currentColumn;
        int currentRow = 0;
        String line = "";
        while(currentRow < 36) {
            // Incrementing through the rows
            currentColumn = 0;
            while(currentColumn < 54) {
                // Incrementing througsh the columns
                // line += map[currentColumn][currentRow];
                // Adding the next system to the line
                currentColumn ++;
            }
            System.out.println(line);
            line = ""; // Reseting the line for the next row
            currentRow ++;
        }
    }
}
