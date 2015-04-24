
package Gui;

import Map.Location;
import MoveableObject.Moveable;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;

/**
 * @author Ryan Rogers
 */

// Application
public class Window extends Application {
    
    public static final int MENU_WIDTH = 1000;
    
    GridPane rightPane;
    
    ScrollPane outputScroll;
    
// DEFAULT Variables
    static double iconSize = 20;
    static final int rows = 36;
    static final int columns = 54;
    
// Variables
    static char[][] terrainMap; // [row][column]
    static char[][] mapList = new char[rows][columns]; // [row][column]
    static ConcurrentLinkedQueue<Moveable> shipList 
            = new ConcurrentLinkedQueue<>();
    static ConcurrentLinkedQueue<Moveable> mapObjects;
    static ConcurrentLinkedQueue<Location> locationList 
            = new ConcurrentLinkedQueue<>();
    static ArrayList<ArrayList<Button>> mapButtons = new ArrayList<>();
    static GridPane mapPane = new GridPane();
    static WindowThread windowThread;
    
// Images
    static Image water;
    static Image land;
    static Image oilTanker;
    static Image containerShip;
    static Image cargoShip;
    static Image entity;
    static Image logo;
    
// Application loop
    public void main(String[] args) {
        System.err.println("The gui is loading...");
        launch(args); // Launching application
    }
    
// Window
    @Override
    public void start(Stage windowStage) {
        
    // Setup
        GridPane root = new GridPane(); // Creating window pane
        Scene scene = new Scene(root, 1280, 720); // Creating scene
        windowStage.setScene(scene); // Adding scene to window pane
        windowStage.setTitle("Wizard Treasure Chase"); // Setting title
        
    // Static GUI Code
        windowStage.setWidth(1280);
        windowStage.setHeight(720);
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
        oilTanker = new Image("FILE:oiltanker.png");
        containerShip = new Image("FILE:containership.png");
        cargoShip = new Image("FILE:cargoship.png");
        entity = new Image("FILE:entity.png");
        logo = new Image("FILE:logo.png");
        
    // Map pane
        mapPane.setAlignment(Pos.BOTTOM_LEFT);
        root.add(mapPane, 0, 0);
        mapPane.setMinWidth(iconSize*columns);
        mapPane.setMinHeight(iconSize*rows);
        
    // Right Pane
        rightPane = new GridPane();
        
        rightPane.setAlignment(Pos.TOP_RIGHT);
        //rightPane.setMinWidth(iconSize*columns*0.15625);
        
        rightPane.setStyle("-fx-background-color: #000000;"); // Black
        rightPane.setMinHeight(iconSize*rows);
        rightPane.setLayoutX(MENU_WIDTH);
        rightPane.setMaxWidth(MENU_WIDTH);
        
        
        root.add(rightPane, 1, 0);
        
    // Logo
        ImageView imageView = new ImageView();
        imageView.setImage(logo);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        Label logoButton = new Label();
        logoButton.setPadding(new Insets(0, 0, 0, 0));
        logoButton.setStyle("-fx-background-color: #000000;"); // Black
        logoButton.setGraphic(imageView);
        rightPane.add(logoButton, 0, 0);
        
    // Text Area
        String output;
        output = "Sample output text";
        Label outputLabel = new Label();
        outputLabel.setText(output);
        outputLabel.setAlignment(Pos.TOP_LEFT);
        outputLabel.setPrefSize(MENU_WIDTH, 1000);
        outputLabel.setMaxWidth(MENU_WIDTH);
        logoButton.setStyle("-fx-background-color: #000000;"); // Black
        outputScroll = new ScrollPane();
        outputScroll.setContent(outputLabel);
        outputScroll.setPrefHeight(300);
        rightPane.add(outputScroll, 0, 2);
        
    // File Menu
    // File Menu > Open
        TitledPane openPane = new TitledPane();
        openPane.setText("Open");
        openPane.setStyle("-fx-base: #003380ff;");
        GridPane openMenu = new GridPane();
        openPane.setContent(openMenu);
        Label openLabel = new Label("Filename:");
        openMenu.addRow(0, openLabel);
        TextField openText = new TextField("complex");
        openMenu.addRow(1, openText);
        Button openButton = new Button("Load");
        openMenu.addRow(2, openButton);
        
    // File Menu > Snap Shot
        TitledPane snapShotPane = new TitledPane();
        snapShotPane.setText("Snap Shot");
        snapShotPane.setStyle("-fx-base: #003380ff;");
        GridPane snapShotMenu = new GridPane();
        snapShotPane.setContent(snapShotMenu);
        Label snapShotLabel = new Label("File path:");
        snapShotMenu.addRow(0, snapShotLabel);
        TextField snapShotText = new TextField("snapshots/complex");
        snapShotMenu.addRow(1, snapShotText);
        Button snapShotButton = new Button("Save Snap Shot");
        snapShotMenu.addRow(2, snapShotButton);
        
    // File Menu Area
        GridPane fileMenuArea = new GridPane();
        fileMenuArea.setPadding(new Insets(0, 0, 0, 0));
        
    // File Menu > Button Area > Close button
        Button closeButton = new Button("Close");
        closeButton.setPrefWidth(MENU_WIDTH);
        closeButton.setMaxWidth(MENU_WIDTH);
        closeButton.setStyle("-fx-base: #003380ff;");
        closeButton.setAlignment(Pos.BASELINE_LEFT);
        closeButton.setOnAction((ActionEvent event) -> {
            System.err.println("Close button pressed");
        });
        fileMenuArea.addRow(1, closeButton);
        
    // File Menu > Button Area > Exit button
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(MENU_WIDTH);
        exitButton.setMaxWidth(MENU_WIDTH);
        exitButton.setStyle("-fx-base: #003380ff;");
        exitButton.setAlignment(Pos.BASELINE_LEFT);
        exitButton.setOnAction((ActionEvent event) -> {
            windowStage.close();
        });
        fileMenuArea.addRow(2, exitButton);
        
    // fileMenuAccordion
        Accordion fileMenuAccordion = new Accordion();
        fileMenuAccordion.getPanes().addAll(openPane, 
                snapShotPane);
        fileMenuArea.addRow(0, fileMenuAccordion);
        
    // fileMenu
        TitledPane fileMenu = new TitledPane();
        fileMenu.setStyle("-fx-base: #3771c8ff;");
        fileMenu.setText("File Menu");
        fileMenu.setContent(fileMenuArea);
        
    // Ship Menu
    // Ship Menu Area
        GridPane shipMenuArea = new GridPane();
        shipMenuArea.setPadding(new Insets(0, 0, 0, 0));
        
    // Ship Menu > Accordion > Generate Ships
        TitledPane generateShipsPane = new TitledPane();
        generateShipsPane.setText("Generate Ships");
        generateShipsPane.setStyle("-fx-base: #003380ff;");
        GridPane generateShipMenu = new GridPane();
        generateShipsPane.setContent(generateShipMenu);
        Label generateShipLabel = new Label("Number of ships to generate:");
        generateShipMenu.addRow(0, generateShipLabel);
        TextField generateShipNumber = new TextField("10");
        generateShipMenu.addRow(1, generateShipNumber);
        Button generateShipButton = new Button("Generate");
        generateShipMenu.addRow(2, generateShipButton);
        
    // Ship Menu > Accordion > Update Ships
        TitledPane updateShipsPane = new TitledPane();
        updateShipsPane.setStyle("-fx-base: #003380ff;");
        updateShipsPane.setText("Update Ships");
        
    // Ship Menu > Accordion
        Accordion shipMenuAccordion = new Accordion();
        shipMenuAccordion.getPanes().addAll(generateShipsPane, updateShipsPane);
        shipMenuArea.addRow(0, shipMenuAccordion);
        
    // Ship Menu > Button Area > Display All Ships
        Button displayAllShips = new Button("Display All Ships");
        displayAllShips.setPrefWidth(MENU_WIDTH);
        displayAllShips.setMaxWidth(MENU_WIDTH);
        displayAllShips.setStyle("-fx-base: #003380ff;");
        displayAllShips.setAlignment(Pos.BASELINE_LEFT);
        shipMenuArea.addRow(1, displayAllShips);
        
    // Ship Menu > Button Area > Remove All Ships
        Button removeAllShips = new Button("Remove All Ships");
        removeAllShips.setPrefWidth(MENU_WIDTH);
        removeAllShips.setMaxWidth(MENU_WIDTH);
        removeAllShips.setStyle("-fx-base: #003380ff;");
        removeAllShips.setAlignment(Pos.BASELINE_LEFT);
        shipMenuArea.addRow(2, removeAllShips);
        
    // shipMenu
        TitledPane shipMenu = new TitledPane();
        shipMenu.setText("Ship Menu");
        shipMenu.setStyle("-fx-base: #3771c8ff;");
        shipMenu.setContent(shipMenuArea);
        
    // Port Menu
        GridPane portMenuArea = new GridPane();
        portMenuArea.setPadding(new Insets(0, 0, 0, 0));
       
    // Port Menu > Accordion > Unload Ship
        TitledPane unloadShipPane = new TitledPane();
        unloadShipPane.setStyle("-fx-base: #003380ff;");
        unloadShipPane.setText("Unload Ship");
        
    // Port Menu > Accordion > Update Dock
        TitledPane updateDockPane = new TitledPane();
        updateDockPane.setStyle("-fx-base: #003380ff;");
        updateDockPane.setText("Update Dock");
        
    // Port Menu > Accordion
        Accordion portMenuAccordion = new Accordion();
        portMenuAccordion.getPanes().addAll(unloadShipPane, updateDockPane);
        portMenuArea.addRow(0, portMenuAccordion);
        
    // Port Menu > Display All Docks
        Button displayAllDocks = new Button("Display All Docks");
        displayAllDocks.setPrefWidth(MENU_WIDTH);
        displayAllDocks.setMaxWidth(MENU_WIDTH);
        displayAllDocks.setStyle("-fx-base: #003380ff;");
        displayAllDocks.setAlignment(Pos.BASELINE_LEFT);
        displayAllDocks.setOnAction((ActionEvent event) -> {
            System.err.println("Display all docks button pressed");
        });
        portMenuArea.addRow(1, displayAllDocks);
        
    // Port Menu > Display All Cargos
        Button displayAllCargos = new Button("Display All Cargos");
        displayAllCargos.setPrefWidth(MENU_WIDTH);
        displayAllCargos.setMaxWidth(MENU_WIDTH);
        displayAllCargos.setStyle("-fx-base: #003380ff;");
        displayAllCargos.setAlignment(Pos.BASELINE_LEFT);
        displayAllCargos.setOnAction((ActionEvent event) -> {
            System.err.println("Display all cargos button pressed");
        });
        portMenuArea.addRow(2, displayAllCargos);
        
    // portMenu
        TitledPane portMenu = new TitledPane();
        portMenu.setText("Port Menu");
        portMenu.setStyle("-fx-base: #3771c8ff;");
        portMenu.setContent(portMenuArea);
    // End of Port Menu
        
    // Monster Menu
        GridPane monsterMenuArea = new GridPane();
        monsterMenuArea.setPadding(new Insets(0, 0, 0, 0));
        
    // Monster Menu
        TitledPane monsterMenu = new TitledPane();
        monsterMenu.setText("Monster Menu");
        monsterMenu.setStyle("-fx-base: #3771c8ff;");
        monsterMenu.setContent(monsterMenuArea);
        
    // Monster Menu > Accordion > Generate Monsters
        TitledPane generateMonstersPane = new TitledPane();
        generateMonstersPane.setStyle("-fx-base: #003380ff;");
        generateMonstersPane.setText("Generate Monsters");
        
    // Monster Menu > Accordion > Update Monsters
        TitledPane updateMonstersPane = new TitledPane();
        updateMonstersPane.setStyle("-fx-base: #003380ff;");
        updateMonstersPane.setText("Update Monsters");
        
    // Monster Menu > Summon Godzilla
        TitledPane summonGodzilla = new TitledPane();
        summonGodzilla.setText("Summon Godzilla");
        /*
        summonGodzillaMenu.setStyle("-fx-base: #003380ff;");
        GridPane openMenu = new GridPane();
        summonGodzillaPane.setContent(summonGodzillaMenu);
        Label openLabel = new Label("Location X:");
        summonGodzillaMenu.addRow(0, openLabel);
        TextField openText = new TextField("1");
        summonGodzillaMenu.addRow(1, openText);
        TextField openText = new TextField("1");
        summonGodzillaMenu.addRow(2, openText);
        Button openButton = new Button("Summon");
        summonGodzillaMenu.addRow(3, openButton);
                */
        
    // Monster Menu > Accordion
        Accordion monsterMenuAccordion = new Accordion();
        monsterMenuAccordion.getPanes().addAll(generateMonstersPane, 
                updateMonstersPane, summonGodzilla);
        monsterMenuArea.addRow(0, monsterMenuAccordion);
        
    // Monster Menu Buttons
    // Monster Menu > Display All Monsters
        Button displayAllMonsters = new Button("Display All Monsters");
        displayAllMonsters.setPrefWidth(MENU_WIDTH);
        displayAllMonsters.setMaxWidth(MENU_WIDTH);
        displayAllMonsters.setStyle("-fx-base: #003380ff;");
        displayAllMonsters.setAlignment(Pos.BASELINE_LEFT);
        displayAllMonsters.setOnAction((ActionEvent event) -> {
            System.err.println("Display all mosnters button pressed");
        });
        monsterMenuArea.addRow(1, displayAllMonsters);
        
    // Monster Menu > Remove All Monsters
        Button removeAllMonsters = new Button("Remove All Monsters");
        removeAllMonsters.setPrefWidth(MENU_WIDTH);
        removeAllMonsters.setMaxWidth(MENU_WIDTH);
        removeAllMonsters.setStyle("-fx-base: #003380ff;");
        removeAllMonsters.setAlignment(Pos.BASELINE_LEFT);
        removeAllMonsters.setOnAction((ActionEvent event) -> {
            System.err.println("Remove all monsters button pressed");
        });
        monsterMenuArea.addRow(2, removeAllMonsters);
        
    // About
        TitledPane aboutPane = new TitledPane();
        aboutPane.setText("Team");
        aboutPane.setStyle("-fx-base: #003380ff;");
        
    // About Area
        GridPane aboutGridPane = new GridPane();
        aboutGridPane.setPadding(new Insets(0, 0, 0, 0));
        aboutPane.setContent(aboutGridPane);
        
    // About > Team
        Label aboutLabel = new Label();
        aboutLabel.setText("Space Wizard\n"
                + "Treasure Hunters\n"
                + "CSE 1325-002\n"
                + "April 28, 2015\n"
                + "Name: Raith Hamzad\n"
                + "ID: 1001117012\n"
                + "Name: Ryan Rogers\n"
                + "ID: 1000663599\n"
                + "Name: Mason Moreland\n"
                + "ID: 1001059961\n\n");
        aboutLabel.setTextAlignment(TextAlignment.LEFT);
        aboutLabel.setWrapText(true);
        aboutLabel.setAlignment(Pos.TOP_LEFT);
        aboutLabel.setMinHeight(200); // Lines * Font
        aboutGridPane.addRow(0, aboutLabel);
        
    // About > Team > Popout
        Button aboutButton = new Button("Popout");
        aboutButton.setStyle("-fx-base: #003380ff;");
        aboutButton.setPrefWidth(MENU_WIDTH);
        aboutButton.setMaxWidth(MENU_WIDTH);
        aboutButton.setOnAction((ActionEvent event) -> {
            final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(windowStage);
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
                Scene dialogScene = new Scene(dialogVbox, 200, 165);
                dialog.setScene(dialogScene);
                dialog.show();
        });
        aboutGridPane.addRow(1, aboutButton);
        
    // About > GUI
        TitledPane guiPane = new TitledPane();
        guiPane.setStyle("-fx-base: #003380ff;");
        guiPane.setText("GUI");
        Label aboutGuiLabel = new Label("The GUI is done entirely with JavaFX, "
                + "requires at least 3 threads to run, but is still threadsafe "
                + "for the movement AI.");
        aboutGuiLabel.setTextAlignment(TextAlignment.LEFT);
        aboutGuiLabel.setWrapText(true);
        aboutGuiLabel.setAlignment(Pos.TOP_LEFT);
        aboutGuiLabel.setMinHeight(80); // Lines * Font
        guiPane.setContent(aboutGuiLabel);
        
    // aboutAccordion
        Accordion aboutAccordion = new Accordion();
        aboutAccordion.getPanes().addAll(aboutPane, guiPane);
        
    // about
        TitledPane about = new TitledPane();
        about.setText("About");
        about.setStyle("-fx-base: #3771c8ff;");
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
        
        windowStage.show(); // Setting window to be visible
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
    public void mapMove(Moveable ship, Location location) {
<<<<<<< HEAD
        
        // new JFXPanel();
=======

>>>>>>> Ryan-Develop
        Platform.runLater(() -> {
            shipList.add(ship);
            locationList.add(location);
            mapUpdate();
        });
    }
    
// Processing queue
    public static boolean mapUpdate() {
        if(!shipList.isEmpty()) { // Ships need to be updated
            if(!mapButtons.isEmpty()) { // GUI buttons are loaded
                Moveable currentShip = shipList.remove();
                Location newLocation = locationList.remove();
                mapButtons.get(newLocation.getY())
                        .get(newLocation.getX())
                        .setGraphic(customImageView(oilTanker));
                
                Location previousLocation = currentShip.getLocation();
                if(currentShip.getLocation().getX() != newLocation.getX()
                        || currentShip.getLocation().getY() 
                        != newLocation.getY()) {
                    mapButtons.get(previousLocation.getY())
                            .get(previousLocation.getX())
                            .setGraphic(customImageView(terrainMap
                            [previousLocation.getX()]
                            [previousLocation.getY()]));
                    currentShip.setLocation(newLocation);
                    }
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
            return oilTanker;
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
    public ConcurrentLinkedQueue<Moveable> getMapMoveList() {
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
