
package Gui;

import Map.Location;
import Ship.ShipBasic;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Ryan Rogers
 */

// Application
public class Window extends Application {
    
    static char[][] mapList = new char[36][54]; // [row][column]
    static ArrayList<ShipBasic> mapMoveList = new ArrayList<>();
    static ArrayList<Location> locationList = new ArrayList<>();
    static ArrayList<ArrayList<Button>> mapButtons = new ArrayList<>();
    static GridPane mapPane = new GridPane();
    
    // Application loop
    public void main(String[] args) {
        launch(args); // Launching application
    }
    
    // Window
    @Override
    public void start(Stage primaryStage) {
        
        // Setup
        StackPane root = new StackPane(); // Creating window pane
        Scene scene = new Scene(root, 1300, 800); // Creating scene
        primaryStage.setScene(scene); // Adding scene to window pane
        primaryStage.setTitle("Wizard Treasure Chase"); // Setting itle
        primaryStage.show(); // Setting window to be visible
        // primaryStage.setFullScreen(true); // Setting window to fullscreen
        
        // Map
        mapPane.setAlignment(Pos.CENTER);
        root.getChildren().add(mapPane);
        loadMapToMap();
        printMap();
        createMapButtons();
        populateMapPane();
        
        Task<Integer> update = new Task<Integer>() {
            @Override protected Integer call() throws Exception {
                int iterations;
                for (iterations = 0; iterations < 10000000; iterations++) {
                     if (isCancelled()) {
                         updateMessage("Cancelled");
                        break;
                     }
                    updateMessage("Iteration " + iterations);
                    updateProgress(iterations, 10000000);
                }
                return iterations;
            }
        };
        
        // Example button and action
        /* Button mapButton = new Button();
         mapButton.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        root.getChildren().add(mapButton);
        */
    }
    
    // Populates mapPane with mapButtons
    public void populateMapPane() {
        for(ArrayList<Button> y : mapButtons) {
            for(Button x : y) {
                mapPane.add(x, y.indexOf(x), mapButtons.indexOf(y));
            }
        }
    }
    
    // Creates loaded char map
    public void loadMapToMap() {
        Scanner mapReader = null;
        String fileName = "complex";
        try {
            mapReader = new Scanner(new File(fileName + ".map.txt"));
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Exception occured while loading map!");
        }
        String line;
        String[] splitLine;
        int row = 0;
        int column = 0;
        while(mapReader.hasNextLine()) {
            line = mapReader.nextLine();
            splitLine = line.split(",");
            column = Integer.parseInt(splitLine[0]);
            row = Integer.parseInt(splitLine[1]);
            mapList[row][column] = splitLine[2].charAt(0);
        }
    }
    
    // Creates buttons from map
    public void createMapButtons() {
        for(int row = 0; row < 36; row++) {
            for(int column = 0; column < 54; column++) {
                Button button = new Button(String.valueOf(
                        mapList[row][column]));
                if(mapButtons.size() <= row) {
                    mapButtons.add(new ArrayList<>());
                }
                mapButtons.get(row).add(button);
            }
        }
    }
    
    // Creates loaded buttonList
    public void loadMapToButtonArray() {
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
        int row = 0; // Current row counter for new array creation
        int column = 0; // Current column counter
        while(mapReader.hasNextLine()) { // Checking if file is finished
            line = mapReader.nextLine(); // Getting line
            splitLine = line.split(","); // Deliminating line
            row = Integer.parseInt(splitLine[1]); // Counting rows
            column = Integer.parseInt(splitLine[0]); // Counting columns
            if(mapButtons.size() <= row) { // Checking if row is finished
                mapButtons.add(new ArrayList<>()); // Creating a new row
            } else { // Current row is not finished
                mapList[row][column] = splitLine[2].charAt(0);
                Button newButton = 
                        new Button(String.valueOf(mapList[row][column]));
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
    
    // Prints the given map to the console
    public void printMap(char[][] map) {
        for(int row = 0; row < 36; row++) {
            System.out.println("");
            for(int column = 0; column < 54; column++) {
                System.out.print(map[row][column]);
            }
        }
        System.out.println();
    }
    
    // Prints the current mapList to the console
    public void printMap() {
        printMap(mapList);
    }
    
    // Returns map
    public char[][] getMapList() {
        return mapList;
    } 
    
    // Saves the recieved mapList
    public void setMapList(char[][] newMap) {
        mapList = newMap;
    }
    
    // Returns mapButtons
    public ArrayList<ArrayList<Button>> getMapButtons() {
        return mapButtons;
    }
    
    // Returns mapMoveList
    public ArrayList<ShipBasic> getMapMoveList() {
        return mapMoveList;
    }
    
    // Returns locationList
    public ArrayList<Location> getLocationList() {
        return locationList;
    }
    
}
