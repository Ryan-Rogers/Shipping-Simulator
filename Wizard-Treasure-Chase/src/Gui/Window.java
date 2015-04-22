
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
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Ryan Rogers
 */

// Application
public class Window extends Application {
    
    static char[][] terrainMap; // [row][column]
    static char[][] mapList = new char[36][54]; // [row][column]
    static ArrayList<ShipBasic> mapMoveList = new ArrayList<>();
    static ArrayList<Location> locationList = new ArrayList<>();
    static ArrayList<ArrayList<Button>> mapButtons = new ArrayList<>();
    static GridPane mapPane = new GridPane();
    static WindowThread windowThread;
    
    // Images
    Image water;
    Image land;
    Image ship;
    Image entity;
    
    // Application loop
    public void main(String[] args) {
        System.out.println("The gui has started");
        launch(args); // Launching application
    }
    
    // Window
    @Override
    public void start(Stage primaryStage) {

        // Update task
        Task updateTask = new Task() {
            @Override protected Integer call() throws Exception {
                System.out.println("Update thread has started");
                int updateCount;
                for (updateCount = 0; updateCount > -1; updateCount++) {
                    if(!windowThread.isAlive()) {
                        System.out.println("Update thread has stopped");
                        return 0;
                    }
                    if(mapUpdate()) {
                        System.out.println("Task update completed!");
                    } else {
                        System.err.println("--->  Window: Sleep");
                        Thread.sleep(1000); // DEFAULT time between empty update
                    }
                }
                return 0;
            }
        };
        
        // Setup
        StackPane root = new StackPane(); // Creating window pane
        Scene scene = new Scene(root, 972, 648); // Creating scene
        primaryStage.setScene(scene); // Adding scene to window pane
        primaryStage.setTitle("Wizard Treasure Chase"); // Setting itle
        primaryStage.show(); // Setting window to be visible
        // primaryStage.setFullScreen(true); // Setting window to fullscreen
        
        // Images
        water = new Image("FILE:water.png");
        land = new Image("FILE:land.png");
        ship = new Image("FILE:ship.png");
        entity = new Image("FILE:entity.png");
        
        // Map
        mapPane.setAlignment(Pos.CENTER);
        root.getChildren().add(mapPane);
        mapPane.setMinWidth(972);
        mapPane.setMinHeight(648);
        
        loadMapToMap();
        createMapButtons();
        populateMapPane();
        new Thread(updateTask).start();
        
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
        terrainMap = mapList;
    }
    
    // Creates buttons from map
    public void createMapButtons() {
        for(int row = 0; row < 36; row++) {
            for(int column = 0; column < 54; column++) {
                
                // Button
                Button button = customButton();
                button.setGraphic(customImageView(mapList[row][column]));
                
                // Adding buttons to mapButtons<<>>
                if(mapButtons.size() <= row) {
                    mapButtons.add(new ArrayList<>());
                }
                mapButtons.get(row).add(button);
            }
        }
    }
    
    // Returns a new button with custom defaults
    public Button customButton() {
        Button newButton = new Button();
        newButton.setPadding(new Insets(0, 0, 0, 0));
        newButton.setOnAction((ActionEvent event) -> {
            System.out.println("[" + newButton.getLayoutY()/18 + "][" 
                    + newButton.getLayoutX()/18 + "]");
            event.consume();
        });
        newButton.setGraphic(customImageView('E'));
        return newButton;
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
    public synchronized void mapMove(ShipBasic ship, Location location) {
        System.err.println("mapMove()");
        mapMoveList.add(ship);
        locationList.add(location);
    }
    
    // Processing queue
    public boolean mapUpdate() {
        System.err.println("---> Update called");
        
        if(!mapMoveList.isEmpty()) {
            System.out.println("mapMoveList is empty -- update aborted");
            if(!mapButtons.isEmpty()) {
                mapButtons.get(locationList.get(0).getY()).get(locationList
                        .get(0).getX()).setGraphic(customImageView(ship));
                Location previousLocation = mapMoveList.get(0).getLocation();
                mapButtons.get(previousLocation.getY()).get(previousLocation.
                        getX()).setText(String.valueOf(terrainMap
                                [previousLocation.getY()]
                                [previousLocation.getX()]));
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
    
    // Returns a default ImageView
    public ImageView customImageView(Image image) {
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(18);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        return imageView;
    }
    
    // Returns a default ImageView
    public ImageView customImageView(char type) {
        return customImageView(charToImage(type));
    }
    
    // Converts a char to the appropriate Image
    public Image charToImage(char type) {
        if(".".equals(String.valueOf(type))) {
            return water;
        }
        if("*".equals(String.valueOf(type))) {
            return land;
        }
        if("S".equals(String.valueOf(type))) {
            return ship;
        }
        return entity; // No image exists for the given char
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
    
    // Saves the recieved window thread
    public void setWindowThread(WindowThread inputThread) {
        windowThread = inputThread;
    }
    
    
    
}
