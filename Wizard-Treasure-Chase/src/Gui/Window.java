
package Gui;

import Map.Location;
import Ship.ShipBasic;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Ryan Rogers
 */

// Application
public class Window extends Application {
    
    static ArrayList<ArrayList<Button>> mapButtons;
    
    public static void main(String[] args) { // Application loop
        launch(args); // Launching application
    }
    
    // Window
    @Override
    public void start(Stage primaryStage) {
        
        // Setup
        StackPane root = new StackPane(); // Creating window pane
        Scene scene = new Scene(root, 960, 540); // Creating scene
        primaryStage.setScene(scene); // Adding scene to window pane
        primaryStage.setTitle("Wizard Treasure Chase"); // Setting itle
        primaryStage.show(); // Setting window to be visible
        primaryStage.setFullScreen(true); // Setting window to fullscreen
        
        // Map
        GridPane mapPane = new GridPane(); // Creating a grid pane
        mapPane.setAlignment(Pos.CENTER);
        root.getChildren().add(mapPane);
        int rowCount = 0; // Current row holder
        int columnCount = 0; // Current column holder
        mapButtons = loadMap();
        for(ArrayList<Button> row : mapButtons) { // Row increment
            for(Button button : row) { // Column increment
                mapPane.add(button, columnCount, rowCount); // Adding button
                columnCount++; // Incrementing column counter
            }
            columnCount = 0;
            rowCount++; // Incrementing row counter
        }
        
        
        
        
        
        // Example button and action
        /* Button mapButton = new Button();
         mapButton.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        root.getChildren().add(mapButton);
        */
    }
    
    public static void setMap(ShipBasic ship, Location newLocation) {
        // Location oldLocation = ship.getLocation();
        // int row = newLocation.getY();
        // int column = newLocation.getX();
        mapButtons.get(newLocation.getY()).get(newLocation.getX()).setText("S");
    }
    
    // Returns loaded buttonList
    public static ArrayList<ArrayList<Button>> loadMap() {
        ArrayList<ArrayList<Button>> buttonList = new ArrayList<>();
        Scanner mapReader; // Scanner holder
        String fileName = "complex"; // DEFAULT VALUE
        try{ // Attempting to create a scanner from the filename
            mapReader = new Scanner(new File(fileName + ".map.txt"));
        } catch(Exception e) { // Exception occurance
            System.out.println(e); // Printing exception
            System.out.println("Exception occured while loading map!");
            return buttonList;
        }
        String line; // Read line holder
        String[] splitLine; // Delimated line holder
        int row; // Current row counter for new array creation
        while(mapReader.hasNextLine()) { // Checking if file is finished
            line = mapReader.nextLine(); // Getting line
            splitLine = line.split(","); // Deliminating line
            row = Integer.parseInt(splitLine[1]); // Counting rows
            if(buttonList.size() <= row) { // Checking if row is finished
                buttonList.add(new ArrayList<>()); // Creating a new row
            } else { // Current row is not finished
                Button newButton = new Button(
                        String.valueOf(splitLine[2].charAt(0)));
                buttonList.get(row).add(newButton); // Adding new button to list
                        
            }
        }
        return buttonList; // New buttonList with read map added
    }
}
