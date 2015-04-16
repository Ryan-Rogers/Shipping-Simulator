
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
    
    static ArrayList<ShipBasic> mapMoveList = new ArrayList<>();
    static ArrayList<Location> locationList = new ArrayList<>();
    static ArrayList<ArrayList<Button>> mapButtons = new ArrayList<>();
    
    // Application loop
    public void main(String[] args) {
        launch(args); // Launching application
    }
    
    // Window
    @Override
    public void start(Stage primaryStage) {
        
        // Setup
        StackPane root = new StackPane(); // Creating window pane
        Scene scene = new Scene(root, 1024, 768); // Creating scene
        primaryStage.setScene(scene); // Adding scene to window pane
        primaryStage.setTitle("Wizard Treasure Chase"); // Setting itle
        primaryStage.show(); // Setting window to be visible
        // primaryStage.setFullScreen(true); // Setting window to fullscreen
        
        // Map
        GridPane mapPane = new GridPane(); // Creating a grid pane
        mapPane.setAlignment(Pos.CENTER);
        root.getChildren().add(mapPane);
        int rowCount = 0; // Current row holder
        int columnCount = 0; // Current column holder
        loadMap();
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
    
    // Returns loaded buttonList
    public void loadMap() {
        Scanner mapReader = null; // Scanner holder
        String fileName = "complex"; // DEFAULT VALUE
        try{ // Attempting to create a scanner from the filename
            mapReader = new Scanner(new File(fileName + ".map.txt"));
        } catch(Exception e) { // Exception occurance
            System.out.println(e); // Printing exception
            System.out.println("Exception occured while loading map!");
        }
        String line; // Read line holder
        String[] splitLine; // Delimated line holder
        int row; // Current row counter for new array creation
        while(mapReader.hasNextLine()) { // Checking if file is finished
            line = mapReader.nextLine(); // Getting line
            splitLine = line.split(","); // Deliminating line
            row = Integer.parseInt(splitLine[1]); // Counting rows
            if(mapButtons.size() <= row) { // Checking if row is finished
                mapButtons.add(new ArrayList<>()); // Creating a new row
            } else { // Current row is not finished
                Button newButton = new Button(
                        String.valueOf(splitLine[2].charAt(0)));
                mapButtons.get(row).add(newButton); // Adding new button to list                        
            }
        }
    }
    
    // Adding move to queue
    public void mapMove(ShipBasic ship, Location location) {
        mapMoveList.add(ship);
        locationList.add(location);
    }
    
    // Processing queue
    public static boolean mapUpdate() {
        if(!mapMoveList.isEmpty()) {
            if(!mapButtons.isEmpty()) {
                mapButtons.get(locationList.get(0).getY()).
                    get(locationList.get(0).getX()).setText("S");
                mapMoveList.remove(0);
                locationList.remove(0);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public ArrayList<ArrayList<Button>> getMapButtons() {
        return mapButtons;
    }
    
    public ArrayList<ShipBasic> getMapMoveList() {
        return mapMoveList;
    }
    
    public ArrayList<Location> getLocationList() {
        return locationList;
    }
}
