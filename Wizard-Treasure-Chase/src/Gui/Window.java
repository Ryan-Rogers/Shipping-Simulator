
package Gui;

import Map.Location;
import Ship.ShipBasic;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;

/**
 * @author Ryan Rogers
 */

// Application
public class Window extends Application {
    
    // DEFAULT Variables
    static double iconSize = 20;
    static int rows = 36;
    static int columns = 54;
    
    // Variables
    static char[][] terrainMap; // [row][column]
    static char[][] mapList = new char[rows][columns]; // [row][column]
    static ConcurrentLinkedQueue<ShipBasic> shipList 
            = new ConcurrentLinkedQueue<>();
    static ConcurrentLinkedQueue<Location> locationList 
            = new ConcurrentLinkedQueue<>();
    static ArrayList<ArrayList<Button>> mapButtons = new ArrayList<>();
    static GridPane mapPane = new GridPane();
    static WindowThread windowThread;
    
    // Images
    static Image water;
    static Image land;
    static Image ship;
    static Image entity;
    static Image logo;
    
    // Application loop
    public void main(String[] args) {
        System.err.println("The gui has started");
        launch(args); // Launching application
    }
    
    // Window
    @Override
    public void start(Stage primaryStage) {
        
        // Setup
        GridPane root = new GridPane(); // Creating window pane
        Scene scene = new Scene(root, 1280, 720); // Creating scene
        primaryStage.setScene(scene); // Adding scene to window pane
        primaryStage.setTitle("Wizard Treasure Chase"); // Setting title
        
        // Static GUI Code
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        iconSize = 720/rows;
        
        /*
        // Dynamic Fullscreen Code
        primaryStage.setFullScreen(true);
        primaryStage.setX(Screen.getPrimary().getBounds().getMinX());
        primaryStage.setY(Screen.getPrimary().getBounds().getMinY());
        primaryStage.setWidth(Screen.getPrimary().getBounds().getWidth());
        primaryStage.setHeight(Screen.getPrimary().getBounds().getHeight());
        iconSize = Screen.getPrimary().getBounds().getHeight()/rows;
                */
        
        // Images
        water = new Image("FILE:water.png");
        land = new Image("FILE:land.png");
        ship = new Image("FILE:ship.png");
        entity = new Image("FILE:entity.png");
        logo = new Image("FILE:logo.png");
        
        // Map pane
        mapPane.setAlignment(Pos.BOTTOM_LEFT);
        root.add(mapPane, 0, 0);
        mapPane.setMinWidth(iconSize*columns);
        mapPane.setMinHeight(iconSize*rows);
        
        // Right Pane
        GridPane rightPane = new GridPane();
        rightPane.setAlignment(Pos.TOP_RIGHT);
        rightPane.setMinWidth(iconSize*columns*0.15625);
        rightPane.setMinHeight(iconSize*rows);
        root.add(rightPane, 1, 0);
        
        // Logo
        ImageView imageView = new ImageView();
        imageView.setImage(logo);
        imageView.setFitWidth(iconSize*columns*0.15625);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        Button logoButton = new Button();
        logoButton.setPadding(new Insets(0, 0, 0, 0));
        logoButton.setGraphic(imageView);
        rightPane.add(logoButton, 0, 0);
        
        // Text Area
        String output;
        output = "Sample output text";
        Label outputLabel = new Label();
        outputLabel.setText(output);
        rightPane.add(outputLabel, 0, 2);
        
        // fileMenuAccordion Items
        TitledPane openPane = new TitledPane();
        openPane.setText("Open");
        TitledPane closePane = new TitledPane();
        closePane.setText("Close");
        TitledPane snapShotPane = new TitledPane();
        snapShotPane.setText("Snap Shot");
        
        // Exit button
        TitledPane exitPane = new TitledPane();
        exitPane.setText("Exit");
        Button exitButton = new Button("Exit");
        exitButton.setOnAction((ActionEvent event) -> {
            System.err.println("Exit button pressed");
        });
        exitPane.contentProperty().set(exitButton);
        
        // fileMenuAccordion
        Accordion fileMenuAccordion = new Accordion();
        fileMenuAccordion.getPanes().addAll(openPane, closePane, 
                snapShotPane, exitPane);
        
        // fileMenu
        TitledPane fileMenu = new TitledPane();
        fileMenu.setText("File Menu");
        fileMenu.setContent(fileMenuAccordion);
        
        // Ship Menu
        // Ship Menu > Generate Ships
        TitledPane generateShipsPane = new TitledPane();
        generateShipsPane.setText("Generate Ships");
        GridPane generateShipMenu = new GridPane();
        generateShipsPane.setContent(generateShipMenu);
        Label generateShipLabel = new Label("Number of ships to generate:");
        generateShipMenu.addRow(0, generateShipLabel);
        TextField generateShipNumber = new TextField("10");
        generateShipMenu.addRow(1, generateShipNumber);
        Button generateShipButton = new Button("Generate");
        generateShipMenu.addRow(2, generateShipButton);
        
        // Ship Menu > Update Ships
        TitledPane updateShipsPane = new TitledPane();
        updateShipsPane.setText("Update Ships");
        
        // Ship Menu > Display All Ships
        TitledPane displayAllShips = new TitledPane();
        displayAllShips.setText("Display All Ships");
        
        // Ship Menu > Remove All Ships
        TitledPane removeAllShips = new TitledPane();
        removeAllShips.setText("Remove All Ships");
        
        // shipMenuAccordion
        Accordion shipMenuAccordion = new Accordion();
        shipMenuAccordion.getPanes().addAll(generateShipsPane, updateShipsPane, 
                displayAllShips, removeAllShips);
        
        // shipMenu
        TitledPane shipMenu = new TitledPane();
        shipMenu.setText("Dock Menu");
        shipMenu.setContent(shipMenuAccordion);
        
        // portMenuAccordion Items
        TitledPane unloadShipPane = new TitledPane();
        unloadShipPane.setText("Unload Ship");
        TitledPane updateDockPane = new TitledPane();
        updateDockPane.setText("Update Dock");
        TitledPane displayAllDocks = new TitledPane();
        displayAllDocks.setText("Display All Docks");
        TitledPane displayAllCargos = new TitledPane();
        displayAllCargos.setText("Display All Cargos");
        
        // portMenuAccordion
        Accordion portMenuAccordion = new Accordion();
        portMenuAccordion.getPanes().addAll(unloadShipPane, updateDockPane, 
                displayAllDocks, displayAllCargos);
        
        // portMenu
        TitledPane portMenu = new TitledPane();
        portMenu.setText("Port Menu");
        portMenu.setContent(portMenuAccordion);
        
        // portMenuAccordion Items
        TitledPane generateMonstersPane = new TitledPane();
        generateMonstersPane.setText("Generate Monsters");
        TitledPane updateMonstersPane = new TitledPane();
        updateMonstersPane.setText("Update Monsters");
        TitledPane displayAllMonstersPane = new TitledPane();
        displayAllMonstersPane.setText("Display All Monsters");
        TitledPane removeAllMonstersPane = new TitledPane();
        removeAllMonstersPane.setText("Remove All Monsters");
        TitledPane summonGodzillaPane = new TitledPane();
        summonGodzillaPane.setText("Summon Godzilla");
        
        // portMenuAccordion
        Accordion monsterMenuAccordion = new Accordion();
        monsterMenuAccordion.getPanes().addAll(generateMonstersPane, 
                updateMonstersPane, displayAllMonstersPane, 
                removeAllMonstersPane, summonGodzillaPane);
        
        // portMenu
        TitledPane monsterMenu = new TitledPane();
        monsterMenu.setText("Monster Menu");
        monsterMenu.setContent(monsterMenuAccordion);
        
        // aboutAccordion Items
        TitledPane aboutPane = new TitledPane();
        aboutPane.setText("Team");
        
        GridPane aboutGridPane = new GridPane();
        aboutPane.setContent(aboutGridPane);
        
        Label aboutLabel = new Label();
        aboutLabel.setText("Space Wizard\n"
                + "Treasure Hunters\n"
                + "CSE 1325-002\n"
                + "April 28, 2015\n"
                + "Name: Raith\n"
                + "      Hamzard\n"
                + "ID: 1001117012\n"
                + "Name: Ryan\n"
                + "      Rogers\n"
                + "ID: 1000663599\n"
                + "Name: Mason\n"
                + "      Moreland\n"
                + "ID: 1001059961\n\n");
        aboutGridPane.addRow(0, aboutLabel);
        
        Button aboutButton = new Button("Popout");
        aboutButton.setOnAction((ActionEvent event) -> {
            final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(primaryStage);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Label(
                        "Space Wizard Treasure Hunters\n"
                        + "CSE 1325-002\n"
                        + "April 28, 2015\n"
                        + "Name: Raith Hamzard\n"
                        + "ID: 1001117012\n"
                        + "Name: Ryan Rogers\n"
                        + "ID: 1000663599\n"
                        + "Name: Mason Moreland\n"
                        + "ID: 1001059961"));
                Scene dialogScene = new Scene(dialogVbox, 200, 175);
                dialog.setScene(dialogScene);
                dialog.show();
        });
        aboutGridPane.addRow(1, aboutButton);
        
        // About > GUI
        TitledPane guiPane = new TitledPane();
        guiPane.setText("GUI");
        guiPane.setContent(new Label("The GUI is done\n"
                + "entirely with JavaFX,\n"
                + "requires at least 3\n"
                + "threads to run,\n"
                + "but is still\n"
                + "threadsafe for\n"
                + "the movement AI."));
        
        // aboutAccordion
        Accordion aboutAccordion = new Accordion();
        aboutAccordion.getPanes().addAll(aboutPane, guiPane);
        
        // about
        TitledPane about = new TitledPane();
        about.setText("About");
        about.setContent(aboutAccordion);
        
        // Menu Accordion
        Accordion menuAccordion = new Accordion();
        rightPane.add(menuAccordion, 0, 1);
        menuAccordion.getPanes().addAll(fileMenu, shipMenu, portMenu, 
                monsterMenu, about);
        
        // Map population
        loadMapToMap();
        createMapButtons();
        populateMapPane();
        
        primaryStage.show(); // Setting window to be visible
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
            System.err.println(e);
            System.err.println("Exception occured while loading map!");
        }
        String line;
        String[] splitLine;
        int row;
        int column;
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
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                
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
            System.err.println("[" + newButton.getLayoutY()/iconSize + "][" 
                    + newButton.getLayoutX()/iconSize + "]");
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
            System.err.println(e); // Printing exception
            System.err.println("Exception occured while loading map!");
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
        Platform.runLater(() -> {
            mapUpdate();
        });
        shipList.add(ship);
        locationList.add(location);
    }
    
    // Processing queue
    public static boolean mapUpdate() {
        if(!shipList.isEmpty()) { // Ships need to be updated
            if(!mapButtons.isEmpty()) { // GUI buttons are loaded
                ShipBasic currentShip = shipList.remove();
                Location currentLocation = locationList.remove();
                mapButtons.get(currentLocation.getY())
                        .get(currentLocation.getX())
                        .setGraphic(customImageView(ship));
                
                Location previousLocation = currentShip.getLocation();
                mapButtons.get(previousLocation.getY())
                        .get(previousLocation.getX())
                        .setGraphic(customImageView(terrainMap
                        [previousLocation.getY()]
                        [previousLocation.getX()]));
                
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    // Returns a default ImageView
    public static ImageView customImageView(Image image) {
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(iconSize);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        return imageView;
    }
    
    // Returns a default ImageView
    public static ImageView customImageView(char type) {
        return customImageView(charToImage(type));
    }
    
    // Converts a char to the appropriate Image
    public static Image charToImage(char type) {
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
        for(int row = 0; row < rows; row++) {
            System.err.println("");
            for(int column = 0; column < columns; column++) {
                System.err.print(map[row][column]);
            }
        }
        System.err.println();
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
    public ConcurrentLinkedQueue<ShipBasic> getMapMoveList() {
        return shipList;
    }
    
    // Returns locationList
    public ConcurrentLinkedQueue<Location> getLocationList() {
        return locationList;
    }
    
    // Saves the recieved window thread
    public void setWindowThread(WindowThread inputThread) {
        windowThread = inputThread;
    }
    
    
    
}
