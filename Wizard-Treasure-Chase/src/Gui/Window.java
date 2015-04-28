
/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Gui;

// Map
import Map.FileHandler;
import Map.Location;
import Map.MapMetrics;
import Moveable.Cargo;

// Moveable
import Moveable.ContainerShip;
import Moveable.Kraken;
import Moveable.Leviathan;
import Moveable.Move;
import Moveable.OilTanker;
import Moveable.CargoShip;
import Moveable.Crane;
import Moveable.Dock;
import Moveable.Godzilla;
import Moveable.Pier;
import Moveable.Port;
import Moveable.SeaMonster;
import Moveable.SeaSerpent;

// Java
// Java > IO
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// Java > Util
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

// JavaFX
// JavaFX > Application
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.application.Platform;

// JavaFX > Event
import javafx.event.ActionEvent;

// JavaFX > Geometry
import javafx.geometry.Insets;
import javafx.geometry.Pos;

// JavaFX > Scene
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;

// JavaFX > Stage
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Ryan Rogers
 * JavaFX GUI
 */
public class Window extends Application {
    
// Window Variables
    public static final int rightPaneWidth = 200; // DEFAULT
    static double iconSize = 20;
    static final int rows = 36;
    static final int columns = 54;

// GUI Variables
    GridPane rightPane;
    ScrollPane outputScroll;
    static String fileName;
    static String theme;
    /**
     * Holds only the terrain cSym that were found at map file load.
     * Format:
     * [row/y][column/x]
     */
    static char[][] terrainMap;
    static char[][] mapList = new char[rows][columns]; // [row][column]
    static ConcurrentLinkedQueue<Move> shipList
            = new ConcurrentLinkedQueue<>();
    /**
     * All of the Movers on the map
     */
    static ConcurrentLinkedQueue<Move> mapObjects
            = new ConcurrentLinkedQueue<>();
    static ConcurrentLinkedQueue<Location> locationList
            = new ConcurrentLinkedQueue<>();
    static ArrayList<ArrayList<Button>> mapButtons = new ArrayList<>();
    static GridPane mapPane = new GridPane();
    static WindowThread windowThread;
    static Label outputLabel;
    private static ConcurrentLinkedQueue<Location> waterLocations
            = new ConcurrentLinkedQueue<>();
    static Port port = new Port();
    static ConcurrentLinkedQueue<CargoShip> currentShips;

// Files
// Files > Images
    static Image splash;
    static Image water;
    static Image wateralt;
    static Image wateralt2;
    static Image land;
    static Image landalt;
    static Image landalt2;
    static Image landalt3;
    static Image sand;
    static Image oilTanker;
    static Image containerShip;
    static Image cargoShip;
    static Image entity;
    static Image logo;
    static Image godzilla;
    static Image seaserpent;
    static Image leviathan;
    static Image kraken;
    static Image dock;
    static Image crane;
    static Image pier;
    static Image dockship;
    static Image craneship;
    static Image piership;

// Files > Sounds
    static Media krakenSound = new Media(new File("Sounds/Kraken.wav").toURI()
            .toString());
    static Media leviathanSound = new Media(new File("Sounds/Leviathan.wav")
            .toURI()
            .toString());
    static Media godzillaKillsSound = new Media(new File(
            "Sounds/GodzillaKills.wav").toURI().toString());
    static Media seaSerpentSound = new Media(new File("Sounds/SeaSerpent.wav")
            .toURI().toString());
    static Media godzillaSummonSound = new Media(new File(
            "Sounds/GodzillaSummon.wav").toURI().toString());

// Application loop
    public void main(String[] args) {
        System.err.println("The gui is loading...");
        launch(args); // Launching application
    }

// Window
    @Override
    public void start(Stage windowStage) {
    // Variable Initializations
        theme = "Theme/Past/";
        
    // Setup
        GridPane root = new GridPane(); // Creating window pane
        Scene scene = new Scene(root, 1280, 720); // Creating scene
        windowStage.setScene(scene); // Adding scene to window pane
        windowStage.initStyle(StageStyle.UNDECORATED);
        windowStage.setTitle("Wizard Treasure Chase"); // Setting title

    // Static GUI Code
        windowStage.setWidth(1280); // DEFAULT
        windowStage.setHeight(720); // DEFAULT

    // Files
        String fileHeader = "FILE:";
        String fileFooter = ".png";
        splash = new Image(fileHeader + "LoadingScreen" + fileFooter); // No theme
        entity = new Image(fileHeader + "entity" + fileFooter); // No theme
        water = new Image(fileHeader + theme + "water" + fileFooter);
        wateralt = new Image(fileHeader + theme + "wateralt" + fileFooter);
        wateralt2 = new Image(fileHeader + theme + "wateralt2" + fileFooter);
        land = new Image(fileHeader + theme + "land" + fileFooter);
        landalt = new Image(fileHeader + theme + "landalt" + fileFooter);
        landalt2 = new Image(fileHeader + theme + "landalt2" + fileFooter);
        landalt3 = new Image(fileHeader + theme + "landalt3" + fileFooter);
        sand = new Image(fileHeader + theme + "sand" + fileFooter);
        oilTanker = new Image(fileHeader + theme + "oiltanker" + fileFooter);
        containerShip = new Image(fileHeader + theme + "containership"
                + fileFooter);
        cargoShip = new Image(fileHeader + theme + "cargoship" + fileFooter);
        logo = new Image(fileHeader + theme + "logo" + fileFooter);
        godzilla = new Image(fileHeader + theme + "godzilla" + fileFooter);
        seaserpent = new Image(fileHeader + theme + "seaserpent" + fileFooter);
        leviathan = new Image(fileHeader + theme + "leviathan" + fileFooter);
        kraken = new Image(fileHeader + theme + "kraken" + fileFooter);
        dock = new Image(fileHeader + theme + "dock" + fileFooter);
        crane = new Image(fileHeader + theme + "crane" + fileFooter);
        pier = new Image(fileHeader + theme + "pier" + fileFooter);
        dockship = new Image(fileHeader + theme + "shipdock" + fileFooter);
        craneship = new Image(fileHeader + theme + "shipcrane" + fileFooter);
        piership = new Image(fileHeader + theme + "shippier" + fileFooter);

// Main Pane
    // Map pane
        mapPane.setAlignment(Pos.TOP_LEFT);
        root.add(mapPane, 0, 0);
        root.setStyle("-fx-base: #000;"); // Black
        mapPane.setMaxWidth(1080);
        mapPane.setMaxHeight(720);
        mapPane.setMinWidth(1080);
        mapPane.setMinHeight(720);
        mapPane.setPrefWidth(1080);
        mapPane.setPrefHeight(720);
        ImageView splashScreen = new ImageView();
        splashScreen.setImage(splash);
        splashScreen.setFitWidth(1080);
        splashScreen.setPreserveRatio(true);
        splashScreen.setSmooth(true);
        splashScreen.setCache(true);
        mapPane.add(splashScreen, 0, 0);

    // Right Pane
        rightPane = new GridPane();
        rightPane.setAlignment(Pos.TOP_RIGHT);
        rightPane.setStyle("-fx-base: #3771c8ff;"); // light blue
        rightPane.setMinHeight(iconSize * rows);
        rightPane.setMaxWidth(rightPaneWidth);
        root.add(rightPane, 1, 0);

// Logo
    // Logo Image
        ImageView imageView = new ImageView();
        imageView.setImage(logo);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setStyle("-fx-base: #3771c8ff;"); // light blue

    // Logo Image Label
        Label logoButton = new Label();
        logoButton.setPadding(new Insets(15, 15, 15, 15));
        logoButton.setAlignment(Pos.CENTER);
        logoButton.setStyle("-fx-base: #3771c8ff;"); // light blue
        logoButton.setStyle("-fx-background-color: #3771c8ff;"); // Dark blue
        logoButton.setPrefWidth(rightPaneWidth);
        logoButton.setMaxWidth(rightPaneWidth);
        logoButton.setGraphic(imageView);
        rightPane.add(logoButton, 0, 0);

    // Text Area
        outputLabel = new Label("Welcome!");
        outputLabel.setAlignment(Pos.TOP_LEFT);
        outputLabel.setPrefWidth(rightPaneWidth - 14);
        outputLabel.setMaxWidth(rightPaneWidth - 14);
        outputLabel.setMinWidth(rightPaneWidth - 14);
        outputLabel.setPrefHeight(2000);
        outputLabel.setWrapText(true);
        outputLabel.setStyle("-fx-base: #3771c8ff; -fx-text-fill: white;"
                + " -fx-background-color: #003380ff;");
        outputLabel.setTextFill(Paint.valueOf("000"));
        outputScroll = new ScrollPane();
        outputScroll.setContent(outputLabel);
        outputScroll.setPrefHeight(200);
        rightPane.add(outputScroll, 0, 2);

// File Menu
    // File Menu Area
        GridPane fileMenuArea = new GridPane();
        fileMenuArea.setPadding(new Insets(0, 0, 0, 0));

    // File Menu > Open
        TitledPane openPane = new TitledPane();
        openPane.setText("Open");
        openPane.setStyle("-fx-base: #003380ff;"); // Dark blue
        GridPane openMenu = new GridPane();
        openPane.setContent(openMenu);
        Label openLabel = new Label("Filename:");
        openMenu.addRow(0, openLabel);
        TextField openText = new TextField("complex");
        openMenu.addRow(1, openText);
        Button openButton = new Button("Load");
        openButton.setOnAction((ActionEvent event) -> {
            mapList = new char[36][54]; // Loading map in fileName to mapList
            mapObjects.clear(); // Removing all Move from mapObjects
            mapButtons.clear();
            populateMapPane();
            mapPane.add(splashScreen, 0, 0);
            textOutput("Loading new files"); // Notifying user of load
            fileName = openText.getText(); // Saving new fileName from GUI
            loadMapList(); // Loading map in fileName to mapList
            createMapButtons(); // Drawing mapList to mapButtons
            populateMapPane(); // Adding mapButtons to mapPane
            loadPortToMap(); // Loading Port from file
        });
        openMenu.addRow(2, openButton);

    // File Menu > Snap Shot
        TitledPane snapShotPane = new TitledPane();
        snapShotPane.setText("Snap Shot");
        snapShotPane.setStyle("-fx-base: #003380ff;");
        GridPane snapShotMenu = new GridPane();
        snapShotPane.setContent(snapShotMenu);
        Label snapShotLabel = new Label("File path:");
        snapShotMenu.addRow(0, snapShotLabel);
        TextField snapShotText = new TextField("SnapShots/newComplex");
        snapShotMenu.addRow(1, snapShotText);
        Button snapShotButton = new Button("Save Snap Shot");
        snapShotMenu.addRow(2, snapShotButton);
        snapShotButton.setOnAction((ActionEvent event) -> {
            System.err.println("Snap Shot button pressed");
            FileHandler fileHandler = new FileHandler(snapShotText.getText());
            fileHandler.saveMap(terrainMap);
            fileHandler.savePort(port);
        });

    // File Menu > Button Area > Close button
        Button closeButton = new Button("Close");
        closeButton.setPrefWidth(rightPaneWidth);
        closeButton.setMaxWidth(rightPaneWidth);
        closeButton.setStyle("-fx-base: #003380ff;");
        closeButton.setAlignment(Pos.BASELINE_LEFT);
        closeButton.setOnAction((ActionEvent event) -> {
            System.err.println("Close button pressed");
            mapList = new char[36][54]; // Loading map in fileName to mapList
            mapObjects.clear(); // Removing all Move from mapObjects
            mapButtons.clear();
            populateMapPane();
            mapPane.add(splashScreen, 0, 0);
        });
        fileMenuArea.addRow(1, closeButton);

    // File Menu > Button Area > Exit button
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(rightPaneWidth);
        exitButton.setMaxWidth(rightPaneWidth);
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
        fileMenu.setStyle("-fx-base: #3771c8ff;"); // light blue
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
        generateShipButton.setOnAction((ActionEvent event) -> {
            System.err.println("Generate ships button pressed");
            for(int remaining = Integer.valueOf(generateShipNumber
                    .getText()); remaining > 0; remaining--) {
                newRandomShip();
            }
        });
        generateShipMenu.addRow(2, generateShipButton);

    // Ship Menu > Accordion > Update Ships
        TitledPane updateShipsPane = new TitledPane();
        updateShipsPane.setStyle("-fx-base: #003380ff;");
        updateShipsPane.setText("Update Ships");
        GridPane updateShip = new GridPane();
        updateShip.setStyle("-fx-base: #003380ff");
        updateShipsPane.setContent(updateShip);
        
        Label updateShipLabel = new Label("Index:");
        updateShip.addRow(0, updateShipLabel);
        TextField updateShipID = new TextField("0");
        updateShipID.setMaxWidth(30);
        updateShip.addRow(0, updateShipID);
        
        /*
        // Ship select button
        Button selectShipButton = new Button("Select");
        selectShipButton.setOnAction((ActionEvent event) -> {
            System.err.println("Update ships button pressed");
            CargoShip editShip = (CargoShip)currentShips.toArray()
                    [Integer.getInteger(updateShipID.getText())];
        });
        updateShip.addRow(0, selectShipButton);
        */
        
        Label shipNameLabel = new Label("Name:");
        updateShip.addRow(1, shipNameLabel);
        TextField shipNameText = new TextField("");
        shipNameText.setMaxWidth(100);
        updateShip.addRow(1, shipNameText);
        
        Label shipCountryLabel = new Label("Country:");
        updateShip.addRow(2, shipCountryLabel);
        TextField shipCountryText = new TextField("");
        shipCountryText.setMaxWidth(100);
        updateShip.addRow(2, shipCountryText);
        
        Label shipTransponderLabel = new Label("Transponder:");
        updateShip.addRow(3, shipTransponderLabel);
        TextField shipTransponderText = new TextField("");
        shipTransponderText.setMaxWidth(50);
        updateShip.addRow(3, shipTransponderText);
        
        Label shipLengthLabel = new Label("Length:");
        updateShip.addRow(4, shipLengthLabel);
        TextField shipLengthText = new TextField("");
        shipLengthText.setMaxWidth(50);
        updateShip.addRow(4, shipLengthText);
        
        Label shipDraftLabel = new Label("Draft:");
        updateShip.addRow(5, shipDraftLabel);
        TextField shipDraftText = new TextField("");
        shipDraftText.setMaxWidth(50);
        updateShip.addRow(5, shipDraftText);
        
        Label shipCapacityLabel = new Label("Capacity:");
        updateShip.addRow(6, shipCapacityLabel);
        TextField shipCapacityText = new TextField("");
        shipCapacityText.setMaxWidth(50);
        updateShip.addRow(6, shipCapacityText);
        
        Label shipLongitudeLabel = new Label("Longitude:");
        updateShip.addRow(7, shipLongitudeLabel);
        TextField shipLongitudeText = new TextField("");
        shipLongitudeText.setMaxWidth(50);
        updateShip.addRow(7, shipLongitudeText);
        TextField shipYText = new TextField("");
        shipYText.setMaxWidth(50);
        updateShip.addRow(7, shipYText);
        
        Label shipLatitudeLabel = new Label("Latitude:");
        updateShip.addRow(8, shipLatitudeLabel);
        TextField shipLatitudeText = new TextField("");
        shipLatitudeText.setMaxWidth(50);
        updateShip.addRow(8, shipLatitudeText);
        TextField shipXText = new TextField("");
        shipXText.setMaxWidth(50);
        updateShip.addRow(8, shipXText);
        
        Label shipCargoLabel = new Label("Cargo:");
        updateShip.addRow(9, shipCargoLabel);
        TextField shipCargoText = new TextField("");
        shipCargoText.setMaxWidth(100);
        updateShip.addRow(9, shipCargoText);
        
        Label shipCargoWeightLabel = new Label("Weight:");
        updateShip.addRow(10, shipCargoWeightLabel);
        TextField shipCargoWeightText = new TextField("");
        shipCargoWeightText.setMaxWidth(50);
        updateShip.addRow(10, shipCargoWeightText);
        
    // Ship Menu > Update Ship > Save Button
        Button saveShipButton = new Button("Save");
        saveShipButton.setOnAction((ActionEvent event) -> {
            System.err.println("Save ship button pressed");
            updateCurrentShips(false); // Updating currentShips
            for(int counter = Integer.valueOf(updateShipID.getText()); 
                    counter > 0; counter--) {
                currentShips.poll();
                // Removing ships from currentShips until we're at index
            }
            CargoShip editShip = currentShips.poll(); // Setting ship to edit
            
            // Saving new values from GUI
            editShip.setName(shipNameText.getText());
            editShip.setCountryReg(shipCountryText.getText());
            editShip.setTransponder(Long.valueOf(
                    shipTransponderText.getText()));
            editShip.setLength(Double.valueOf(shipLengthText.getText()));
            editShip.setDraft(Double.valueOf(shipDraftText.getText()));
            editShip.setCapacity(Double.valueOf(shipCapacityText.getText()));
            if(shipYText.getText().equals("") // Checking if X, Y empty in GUI
                    || shipXText.getText().equals("")) {
                editShip.setLocation(new Location(
                    Integer.valueOf(shipXText.getText()), 
                    Integer.valueOf(shipYText.getText())));
            } else { // Setting location by lat, lon if X, Y empty in GUI
                editShip.setLongitude(Double.valueOf(
                        shipLongitudeText.getText()));
                editShip.setLatitude(Double.valueOf(
                        shipLatitudeText.getText()));
            }
            editShip.getCargo().setLabel(shipCargoText.getText());
            editShip.getCargo().setWeight(Double.valueOf(
                    shipCargoWeightText.getText()));
        });
        updateShip.addRow(11, saveShipButton);
        
        
    // Ship Menu > Update Ship > Load Button
        Button updateShipButton = new Button("Load");
        updateShipButton.setOnAction((ActionEvent event) -> {
            System.err.println("Load ship button pressed");
            updateCurrentShips(false); // Updating currentShips
            for(int counter = Integer.valueOf(updateShipID.getText()); 
                    counter > 0; counter--) {
                currentShips.poll();
                // Removing ships from currentShips until we're at index
            }
            CargoShip editShip = currentShips.poll(); // Setting ship to edit
            
            // Updating GUI to new values
            shipNameText.setText(editShip.getName());
            shipCountryText.setText(editShip.getCountryReg());
            shipTransponderText.setText(
                    String.valueOf(editShip.getTransponder()));
            shipLengthText.setText(
                    String.valueOf(editShip.getLength()));
            shipDraftText.setText(
                    String.valueOf(editShip.getDraft()));
            shipCapacityText.setText(
                    String.valueOf(editShip.getCapacity()));
            shipLongitudeText.setText(
                    String.valueOf(editShip.getLongitude()));
            shipYText.setText(
                    String.valueOf(editShip.getLocation().getY()));
            shipLatitudeText.setText(
                    String.valueOf(editShip.getLatitude()));
            shipXText.setText(
                    String.valueOf(editShip.getLocation().getX()));
            shipCargoText.setText(editShip.getCargo().getLabel());
            shipCargoWeightText.setText(String.valueOf(
                    editShip.getCargo().getWeight()));
        });
        updateShip.addRow(11, updateShipButton);
        
    // Ship Menu > Accordion
        Accordion shipMenuAccordion = new Accordion();
        shipMenuAccordion.getPanes().addAll(generateShipsPane, updateShipsPane);
        shipMenuArea.addRow(0, shipMenuAccordion);

    // Ship Menu > Button Area > Display All Ships
        Button displayAllShips = new Button("Display All Ships");
        displayAllShips.setPrefWidth(rightPaneWidth);
        displayAllShips.setMaxWidth(rightPaneWidth);
        displayAllShips.setStyle("-fx-base: #003380ff;");
        displayAllShips.setAlignment(Pos.BASELINE_LEFT);
        displayAllShips.setOnAction((ActionEvent event) -> {
            System.err.println("Display all ships button pressed");
            updateCurrentShips(true);
        });
        shipMenuArea.addRow(1, displayAllShips);

    // Ship Menu > Button Area > Remove All Ships
        Button removeAllShips = new Button("Remove All Ships");
        removeAllShips.setPrefWidth(rightPaneWidth);
        removeAllShips.setMaxWidth(rightPaneWidth);
        removeAllShips.setStyle("-fx-base: #003380ff;");
        removeAllShips.setAlignment(Pos.BASELINE_LEFT);
        removeAllShips.setOnAction((ActionEvent event) -> {
            System.err.println("Remove all ships button pressed");
            removeAllShips();
        });
        shipMenuArea.addRow(2, removeAllShips);

    // shipMenu
        TitledPane shipMenu = new TitledPane();
        shipMenu.setText("Ship Menu");
        shipMenu.setStyle("-fx-base: #3771c8ff;");
        shipMenu.setContent(shipMenuArea);

// Port Menu
    // Port Menu Area
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
        displayAllDocks.setPrefWidth(rightPaneWidth);
        displayAllDocks.setMaxWidth(rightPaneWidth);
        displayAllDocks.setStyle("-fx-base: #003380ff;");
        displayAllDocks.setAlignment(Pos.BASELINE_LEFT);
        displayAllDocks.setOnAction((ActionEvent event) -> {
            System.err.println("Display all docks button pressed");
            for(Dock currentDock : port.getDockList()) {
                textOutput(currentDock.toString());
            }
        });
        portMenuArea.addRow(1, displayAllDocks);

    // Port Menu > Display All Cargos
        Button displayAllCargos = new Button("Display All Cargos");
        displayAllCargos.setPrefWidth(rightPaneWidth);
        displayAllCargos.setMaxWidth(rightPaneWidth);
        displayAllCargos.setStyle("-fx-base: #003380ff;");
        displayAllCargos.setAlignment(Pos.BASELINE_LEFT);
        displayAllCargos.setOnAction((ActionEvent event) -> {
            System.err.println("Display all cargos button pressed");
            for(Cargo currentCargo : port.getCargoList()) {
                textOutput(currentCargo.toString());
            }
        });
        portMenuArea.addRow(2, displayAllCargos);

    // portMenu
        TitledPane portMenu = new TitledPane();
        portMenu.setText("Port Menu");
        portMenu.setStyle("-fx-base: #3771c8ff;");
        portMenu.setContent(portMenuArea);

// Monster Menu
    // Monster Menu Area
        GridPane monsterMenuArea = new GridPane();
        monsterMenuArea.setPadding(new Insets(0, 0, 0, 0));

    // Monster Menu Pane
        TitledPane monsterMenu = new TitledPane();
        monsterMenu.setText("Monster Menu");
        monsterMenu.setStyle("-fx-base: #3771c8ff;");
        monsterMenu.setContent(monsterMenuArea);

    // Monster Menu > Accordion > Generate Monsters
        TitledPane generateMonstersPane = new TitledPane();
        generateMonstersPane.setStyle("-fx-base: #003380ff;");
        generateMonstersPane.setText("Generate Monsters");
        GridPane generateMonsterMenu = new GridPane();
        generateMonstersPane.setContent(generateMonsterMenu);
        Label generateMonsterLabel = new Label("Number of monsters to generate:");
        generateMonsterMenu.addRow(0, generateMonsterLabel);
        TextField generateMonsterNumber = new TextField("10");
        generateMonsterMenu.addRow(1, generateMonsterNumber);
        Button generateMonsterButton = new Button("Generate");
        generateMonsterButton.setOnAction((ActionEvent event) -> {
            System.err.println("Generate monsters button pressed");
            for(int remaining = Integer.valueOf(generateMonsterNumber.getText()); remaining > 0; remaining--) {
                newRandomMonster();
            }
        });
        generateMonsterMenu.addRow(2, generateMonsterButton);

    // Monster Menu > Accordion > Update Monsters
        TitledPane updateMonstersPane = new TitledPane();
        updateMonstersPane.setStyle("-fx-base: #003380ff;");
        updateMonstersPane.setText("Update Monsters");

    // Monster Menu > Summon Godzilla
        TitledPane summonGodzilla = new TitledPane();
        summonGodzilla.setText("Summon Godzilla");
        summonGodzilla.setStyle("-fx-base: #003380ff;");
        GridPane summonGodzillaMenu = new GridPane();
        summonGodzilla.setContent(summonGodzillaMenu);
        Label summonGodzillaLabelX = new Label("Location X:");
        summonGodzillaMenu.addRow(0, summonGodzillaLabelX);
        TextField summonGodzillaTextX = new TextField("1");
        summonGodzillaMenu.addRow(1, summonGodzillaTextX);
        Label summonGodzillaLabelY = new Label("Location Y:");
        summonGodzillaMenu.addRow(2, summonGodzillaLabelY);
        TextField summonGodzillaTextY = new TextField("1");
        summonGodzillaMenu.addRow(3, summonGodzillaTextY);
        Button summonGodzillaButton = new Button("Summon");
        summonGodzillaButton.setOnAction((ActionEvent event) -> {
            System.err.println("Summon godzilla button pressed");
            new MediaPlayer(godzillaSummonSound).play();
            Location inputLocation = new Location(
                        Integer.valueOf(summonGodzillaTextX.getText()), 
                        Integer.valueOf(summonGodzillaTextY.getText()));
            Boolean godzillaExists = false;
            for(Move mapObject : mapObjects) { // Checking if Godzilla exists
                if(mapObject instanceof Godzilla) { // If Godzilla found
                    mapMove(mapObject, inputLocation); // Move to input X, Y
                    godzillaExists = true;
                }
            }
            if(!godzillaExists) { // Godzilla does not yet exist; must create
                Godzilla newGodzilla = new Godzilla(this, windowThread);
                mapObjects.add(newGodzilla); // Adding godzilla to map objects
                mapMove(newGodzilla, inputLocation); // Initial draw
                new Thread(newGodzilla).start(); // Starting godzilla's thread
            }
        });
        summonGodzillaMenu.addRow(4, summonGodzillaButton);
        
    // Monster Menu > Accordion
        Accordion monsterMenuAccordion = new Accordion();
        monsterMenuAccordion.getPanes().addAll(generateMonstersPane,
                updateMonstersPane, summonGodzilla);
        monsterMenuArea.addRow(0, monsterMenuAccordion);

// Monster Menu Buttons
    // Monster Menu > Display All Monsters
        Button displayAllMonsters = new Button("Display All Monsters");
        displayAllMonsters.setPrefWidth(rightPaneWidth);
        displayAllMonsters.setMaxWidth(rightPaneWidth);
        displayAllMonsters.setStyle("-fx-base: #003380ff;");
        displayAllMonsters.setAlignment(Pos.BASELINE_LEFT);
        displayAllMonsters.setOnAction((ActionEvent event) -> {
            System.err.println("Display all mosnters button pressed");
        });
        monsterMenuArea.addRow(1, displayAllMonsters);

    // Monster Menu > Remove All Monsters
        Button removeAllMonsters = new Button("Remove All Monsters");
        removeAllMonsters.setPrefWidth(rightPaneWidth);
        removeAllMonsters.setMaxWidth(rightPaneWidth);
        removeAllMonsters.setStyle("-fx-base: #003380ff;");
        removeAllMonsters.setAlignment(Pos.BASELINE_LEFT);
        removeAllMonsters.setOnAction((ActionEvent event) -> {
            System.err.println("Remove all monsters button pressed");
        });
        monsterMenuArea.addRow(2, removeAllMonsters);

// About Menu
    // About Area
        TitledPane aboutPane = new TitledPane();
        aboutPane.setText("Team");
        aboutPane.setStyle("-fx-base: #003380ff;");

    // About Pane
        GridPane aboutGridPane = new GridPane();
        aboutGridPane.setPadding(new Insets(0, 0, 0, 0));
        aboutPane.setContent(aboutGridPane);

    // About > Team
        Label aboutLabel = new Label();
        aboutLabel.setText("Space Wizard Treasure Hunters\n"
                + "CSE 1325-002\n"
                + "April 28, 2015\n"
                + "Name: Raith Hamzad\n"
                + "ID: 1001117012\n"
                + "Name: Ryan Rogers\n"
                + "ID: 1000663599\n"
                + "Name: Mason Moreland\n"
                + "ID: 1001059961");
        aboutLabel.setTextAlignment(TextAlignment.LEFT);
        aboutLabel.setWrapText(true);
        aboutLabel.setPadding(new Insets(5, 5, 5, 5));
        aboutLabel.setAlignment(Pos.TOP_LEFT);
        aboutLabel.setMinHeight(170); // Lines * Font
        aboutGridPane.addRow(1, aboutLabel);

    // About > Team > Popout
        Button aboutButton = new Button("Popout");
        aboutButton.setStyle("-fx-base: #003380ff;");
        aboutButton.setPrefWidth(rightPaneWidth);
        aboutButton.setAlignment(Pos.TOP_LEFT);
        aboutButton.setMaxWidth(rightPaneWidth);
        aboutButton.setOnAction((ActionEvent event) -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(windowStage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setStyle("-fx-base: #3771c8ff; -fx-text-fill: white; -fx-background-color: #003380ff;");
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
        aboutGridPane.addRow(0, aboutButton);

    // About > GUI
        TitledPane guiPane = new TitledPane();
        guiPane.setStyle("-fx-base: #003380ff;");
        guiPane.setText("GUI");
        Label aboutGuiLabel = new Label("The GUI is done entirely with JavaFX, "
                + "requires at least 3 threads to run, but is still threadsafe "
                + "for the movement AI.");
        aboutGuiLabel.setTextAlignment(TextAlignment.LEFT);
        aboutGuiLabel.setWrapText(true);
        aboutGuiLabel.setPadding(new Insets(5, 5, 5, 5));
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

    // Adding main menu accordion to rightPane
        rightPane.add(menuAccordion, 0, 1);
        menuAccordion.getPanes().addAll(fileMenu, shipMenu, portMenu,
                monsterMenu, about);
        
    // Showing GUI
        windowStage.show(); // Setting window to be visible
        
    }

// Add text to output area
    public void textOutput(String newOutput) {
        outputLabel.setText(newOutput +"\n"+ outputLabel.getText());
        
        /*
        // CSV Version
        String[] deliminatedNewOutputs = newOutput.split(",");
        for(String deliminatedOutput : deliminatedNewOutputs) {
            outputLabel.setText(deliminatedOutput +"\n"+ outputLabel.getText());
        }
        */
    }

// Add text to output area to multiple lines
    public void textOutput(String[] newOutput)
    {
        for(int line = newOutput.length; line > 0; line--) {
            outputLabel.setText(newOutput[line - 1] + "\n"
                    + outputLabel.getText());
        }
    }

    /**
     * Adds mapButtons to the mapPane after clearing it.
     */
    public void populateMapPane() {
        mapPane.getChildren().clear();
        for(ArrayList<Button> y : mapButtons) {
            for(Button x : y) {
                mapPane.add(x, y.indexOf(x), mapButtons.indexOf(y));
            }
        }
    }

// Add Sand to Terrain Map
    public void addSand() {
    // Up to 1
        for(int row = 1; row < 35; row++) {
            for(int column = 1; column < 53; column++) {
            // Directional up to 1
                if ('*' == terrainMap[row][column]) {
                    if ('.' == terrainMap[row + 1][column]) {
                        terrainMap[row][column] = 'o';
                    } else if ('.' == terrainMap[row - 1][column]) {
                        terrainMap[row][column] = 'o';
                    } else if ('.' == terrainMap[row][column + 1]) {
                        terrainMap[row][column] = 'o';
                    } else if ('.' == terrainMap[row][column - 1]) {
                        terrainMap[row][column] = 'o';
                    }
                // Diagonal up to 1
                    else if('.' == terrainMap[row + 1][column + 1]) {
                    terrainMap[row][column] = 'o';
                    }
                    else if('.' == terrainMap[row - 1][column - 1]) {
                    terrainMap[row][column] = 'o';
                    }
                    else if('.' == terrainMap[row + 1][column - 1]) {
                    terrainMap[row][column] = 'o';
                    }
                    else if('.' == terrainMap[row - 1][column + 1]) {
                    terrainMap[row][column] = 'o';
                    }
                }
            }
        }
    // Up to 2
        for(int row = 2; row < 34; row++) {
            for(int column = 2; column < 52; column++) {
            // Directional up to 2
                if ('*' == terrainMap[row][column]) {
                    if ('.' == terrainMap[row + 2][column]) {
                        terrainMap[row][column] = 'o';
                    } else if ('.' == terrainMap[row - 2][column]) {
                        terrainMap[row][column] = 'o';
                    } else if ('.' == terrainMap[row][column + 2]) {
                        terrainMap[row][column] = 'o';
                    } else if ('.' == terrainMap[row][column - 2]) {
                        terrainMap[row][column] = 'o';
                    }
                }
            }
        }
    }

    /**
     * Loads the map from fileName to mapList and waterLocations.
     * Creates an extra copy of the terrain to remain unchanged in terrainMap.
     */
    public void loadMapList(){
        Scanner mapReader = null;
        try {
            mapReader = new Scanner(new File(fileName + ".map.txt"));
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Exception occured while loading map!");
        }
        String line;
        String[] splitLine;
        int row;
        int column;
        while (mapReader.hasNextLine()) {
            line = mapReader.nextLine();
            splitLine = line.split(",");
            column = Integer.parseInt(splitLine[0]);
            row = Integer.parseInt(splitLine[1]);
            mapList[row][column] = splitLine[2].charAt(0);
            if (splitLine[2].charAt(0) == '.') {
                waterLocations.add(new Location(column, row));
            }
        }
        terrainMap = mapList;
        addSand();
    }

    public ArrayList<Location> getWaterLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        waterLocations.stream().forEach((lo) -> {
            locations.add(lo);
        });
        return locations;
    }
        
    public ArrayList<Location> getDockLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        for(Move move : mapObjects) {
            if (move instanceof Dock) {
                locations.add(move.getLocation());
            }
        }
        return locations;
    }
    
    public void loadPortToMap()
    {

        FileReader portReader = null;
        try {
            portReader = new FileReader(new File(fileName + ".port.txt"));
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Exception occured while loading map!");
        }

        String str = "";
        int c;
        try {
            while ((c = portReader.read()) != -1) {
                str += (char) c;
            }
        } catch (IOException ex) {
            System.err.println("loadPortToMap: Failed to read port.");
        }

        String[] fLines = str.split("\n");

        String[] header = fLines[0].split(",");
        //name = header[0];

        int i = Integer.parseInt(header[1].substring(1)); //skips the leading space
        //i += 1; //offset so that x is line #
        for(int x = 1; x <= i; x++) {
            //System.err.println(i);
            mapObjects.add(new Dock(fLines[x], this, windowThread));
        }
        //System.err.println(docks.size());

        int y = Integer.parseInt(header[2]);
        for(int x = i + 1; x <= (i + y); x++) {
            mapObjects.add(new Crane(fLines[x], this, windowThread));
        }
        //System.err.println(docks.size());

        int u = Integer.parseInt(header[3]);
        for(int x = i + y + 1; x <= (i + y + u); x++) {
            mapObjects.add(new Pier(fLines[x], this, windowThread));
        }

        for(Move temp : mapObjects) {
            if (temp instanceof Dock) {
                port.getDockList().add(Dock.class.cast(temp));
                mapMove(temp, temp.getLocation());
                //terrainMap[temp.getLocation().getY()][temp.getLocation().getX()] = temp.getCSym();
            }
        }
    }
    
    public void moveEnd(Move chaser, Move target) {
        Platform.runLater(() -> {
            if(chaser.getClass().isInstance(CargoShip.class)) {
                port.getCargoList().add(((CargoShip)chaser).getCargo());
                chaser.setDestination(new Location(10, 10));
            }
        });
    }
    
    public void resetMapButtonImages()
    {
        for(int row = 0; row < rows; row++)
        {
            for(int column = 0; column < columns; column++)
            {
                Button button = mapButtons.get(row).get(column);
                button.setGraphic(customImageView(mapList[row][column]));
            }
        }
    }
    
    /**
     * Draws mapList to mapButtons.
     * Clears existing buttons before creating new ones.
     */
    public void createMapButtons() {
        mapButtons.clear();
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {

            // Button
                Button button = customButton();
                button.setGraphic(customImageView(mapList[row][column]));

            // Adding buttons to mapButtons<<>>
                if (mapButtons.size() <= row) {
                    mapButtons.add(new ArrayList<>());
                }
                mapButtons.get(row).add(button);
            }
        }
    }
    
    /**
     * Returns the closest ship to the passed location.
     * @param location Location, usually of self, to find the nearest ship.
     * @return The nearest ship to the location
     */
    public Move getPreyMonster(Location location)
    {
        //A list is needed to store the ships
        ArrayList<SeaMonster> monsters = new ArrayList<>();
        
        //Add all the ships to the list ships
        for(Move mover: mapObjects)
        {
            if(mover instanceof SeaMonster)
            {
                monsters.add((SeaMonster)mover);
            }
        }
        
        SeaMonster monstChoice;
        
        //Decide which ship is closest
        if(monsters.isEmpty())
        {
            monstChoice = null;
        }
        else
        {
            //Default
            monstChoice = monsters.get(0);
        }
        
        Map.MapMetrics mm = new MapMetrics();
        
        //Replace the default choice with a closer ship when possible
        for(int i = 0; i < monsters.size() - 1; i++)
        {
            if(mm.distance(monsters.get(i).getLocation(), location)
                    <
               mm.distance(monstChoice.getLocation(), location))
            {
                monstChoice = monsters.get(i);
            }
        }
        
        return monstChoice;
    }
    
    /**
     * Returns the closest ship to the passed location.
     * @param location Location, usually of self, to find the nearest ship.
     * @return The nearest ship to the location
     */
    public Move getPreyShip(Location location)
    {
        //A list is needed to store the ships
        ArrayList<CargoShip> ships = new ArrayList<>();
        
        //Add all the ships to the list ships
        for(Move mover: mapObjects)
        {
            if(mover instanceof CargoShip)
            {
                ships.add((CargoShip)mover);
            }
        }
        
        CargoShip shipChoice;
        
        //Decide which ship is closest
        if(ships.isEmpty())
        {
            shipChoice = null;
        }
        else
        {
            //Default
            shipChoice = ships.get(0);
        }
        
        Map.MapMetrics mm = new MapMetrics();
        
        //Replace the default choice with a closer ship when possible
        for(int i = 0; i < ships.size() - 1; i++)
        {
            if(mm.distance(ships.get(i).getLocation(), location)
                    <
               mm.distance(shipChoice.getLocation(), location))
            {
                shipChoice = ships.get(i);
            }
        }
        
        return shipChoice;
    }
    
// Returns a new button with custom defaults
    public Button customButton()
    {
        Button newButton = new Button();
        newButton.setPadding(new Insets(0, 0, 0, 0));
        newButton.setOnAction((ActionEvent event) -> 
        {
            System.err.println("[" + newButton.getLayoutY() / iconSize + "]["
                    + newButton.getLayoutX() / iconSize + "]");
            event.consume();
        });
        newButton.setGraphic(customImageView('E'));
        return newButton;
    }

    /**
     * Increments through mapObjects and places every item to their current
     * location on the map.
     */
    public void drawMapObjects() {
       for(Move mapObject : mapObjects) {
           mapMove(mapObject, mapObject.getLocation());
       }
    }
    
    /**
     * Removes all ships from mapObjects.
     */
    public void removeAllShips() {
        
        updateCurrentShips(false);
        
        for(CargoShip currentShip : currentShips)
        {
            currentShip.end();
        }
        
        shipList.clear();
        locationList.clear();
        
        for(Move mover : mapObjects)
        {
            mapList[mover.getLocation().getY()][mover.getLocation().getX()] 
                = terrainMap[mover.getLocation().getY()][mover.getLocation().getX()];
            
            if(mover instanceof CargoShip)
                mapObjects.remove(mover);
        }
        
        updateCurrentShips(false);
        
        drawMapObjects();
        
        resetMapButtonImages();
        
        
        
    }

// Creating a ship at a random watery location
    public void newRandomShip() {
        Move newShip;
        Random random = new Random();
        if (random.nextInt(3) == 0) {
            newShip = new CargoShip(this, windowThread);
        } else {
            if (random.nextInt(2) == 0) {
                newShip = new OilTanker(this, windowThread);
            } else {
                newShip = new ContainerShip(this, windowThread);
            }
        }
        mapObjects.add(newShip);
        new Thread(newShip).start();
    }    

// Creating a monster at a random watery location
    public void newRandomMonster()
    {
        Move newMonster;
        Random random = new Random();
        if (random.nextInt(3) == 0) {
            newMonster = new SeaSerpent(this, windowThread);
        } else {
            if (random.nextInt(2) == 0) {
                newMonster = new Leviathan(this, windowThread);
            } else {
                newMonster = new Kraken(this, windowThread);
           }
        }
        mapObjects.add(newMonster);
        new Thread(newMonster).start();
    }
    
    /**
     * Populates currentShips from mapObjects. Adds currentShips' toStrings
     * to textOutput.
     * @param printing Whether or not to print to the console
     */
    public void updateCurrentShips(boolean printing) {
        currentShips = new ConcurrentLinkedQueue<>();
        for(Move mapObject : mapObjects) {
            if(mapObject instanceof CargoShip) {
                currentShips.add((CargoShip)mapObject);
            }
        }
        
        if(printing) {
            int index = 0;
            for(CargoShip currentShip : currentShips) {
                textOutput("\nShip Index: "+ index 
                        +"\n"+ currentShip.toStringArray());
                index++;
            }
        }
    }

// Adding move to queue
    public void mapMove(Move ship, Location newLocation)
    {
        if(newLocation != null) {
            Platform.runLater(() -> {
                shipList.add(ship);
                locationList.add(newLocation);
                mapUpdate();
            });
    // Removing ship from the map when mapMove gets null for newLocation
        } else { // newLocation == null
            ship.end();
            mapObjects.remove(ship); // Removing ship from mapObjects
            Move otherMoveAtShipLocation = null; // Holds Move at newLocation
            for(Move mapObject : mapObjects) { // Saving Move at newLocation
                if(mapObject.getLocation().equals(ship.getLocation())) {
                    otherMoveAtShipLocation = mapObject;
                }
            }
        // Setting image at newLocation for other Move at newLocation
            if(otherMoveAtShipLocation != null) { // Other Move found
                mapButtons.get(newLocation.getY()).get(newLocation.getX())
                        .setGraphic(customImageView(otherMoveAtShipLocation
                        .getCSym()));
            } else { // Setting image at newLocation for terrain
                mapButtons.get(newLocation.getY()).get(newLocation.getX())
                        .setGraphic(customImageView(terrainMap
                        [newLocation.getY()][newLocation.getX()]));
            }
            updateCurrentShips(false);
        }
    }

// Prints the given map to the console

    public void printMap(char[][] map)
    {
        for(int row = 0; row < rows; row++) {
           System.err.println("");
           for(int column = 0; column < columns; column++) {
               System.err.print(map[row][column]);
           }
       }
       System.err.println();
    }
    
// Processing queue
    public static void mapUpdate() {
        if (!shipList.isEmpty()) { // Ships need to be updated
            if (!mapButtons.isEmpty()) { // GUI buttons are loaded
                Move currentShip = shipList.remove();
                Location newLocation = locationList.remove();
                mapButtons.get(newLocation.getY())
                        .get(newLocation.getX())
                        .setGraphic(customImageView(currentShip.getCSym()));
                
                Location previousLocation = new Location(currentShip
                        .getLocation());
                if (currentShip.getLocation().getX() != newLocation.getX()
                        || currentShip.getLocation().getY()
                        != newLocation.getY()) {
                    
                    mapButtons.get(previousLocation.getY())
                            .get(previousLocation.getX())
                            .setGraphic(customImageView(terrainMap
                            [previousLocation.getY()]
                            [previousLocation.getX()]));
                    
                    currentShip.setLocation(newLocation);
                    for(Move otherMover: mapObjects)
                    {
                        if(!currentShip.equals(otherMover)) {
                            if(otherMover.getLocation().getX()
                                    == previousLocation.getX()
                                    && otherMover.getLocation().getY()
                                    == previousLocation.getY()) {
                                mapButtons.get(previousLocation.getY())
                                .get(previousLocation.getX())
                                .setGraphic(customImageView(otherMover
                                        .getCSym()));
                            }
                        }
                    }
                }
            }
        }
    }
    

// Returns a default ImageView
    public static ImageView customImageView(Image image)
    {
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(iconSize);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        return imageView;
    }

// Returns a default ImageView
    public static ImageView customImageView(char type)
    {
        return customImageView(charToImage(type));
    }

// Converts a char to the appropriate Image
    public static Image charToImage(char type)
    {
        Random random = new Random();
        if (".".equals(String.valueOf(type))) {
            switch (random.nextInt(100)) {
            // 1/100
                case 0:
                    return wateralt2;
            // 4/100
                case 1:
                    return wateralt;
                case 2:
                    return wateralt;
                case 3:
                    return wateralt;
                case 4:
                    return wateralt;
            // 45/100
                default:
                    return water;
            }
        }
        if ("o".equals(String.valueOf(type))) {
            return sand;
        }
        if ("*".equals(String.valueOf(type))) {
            if (random.nextInt(6) > 0) {
                return land;
            } else {
                return landalt;
            }
        }
        if ("T".equals(String.valueOf(type))) {
            return oilTanker;
        }
        if ("S".equals(String.valueOf(type))) {
            return cargoShip;
        }
        if ("B".equals(String.valueOf(type))) {
            return containerShip;
        }
        if ("s".equals(String.valueOf(type))) {
            return seaserpent;
        }
        if ("L".equals(String.valueOf(type))) {
            return leviathan;
        }
        if ("K".equals(String.valueOf(type))) {
            return kraken;
        }
        if ("D".equals(String.valueOf(type))) {
            return dock;
        }
        if ("C".equals(String.valueOf(type))) {
            return crane;
        }
        if ("P".equals(String.valueOf(type))) {
            return pier;
        }
        if ("G".equals(String.valueOf(type))) {
            return godzilla;
        }
        return entity; // No image exists for the given char
    }
    
    /**
     * Prints the current mapList to the console
     */
    public void printMap() {
        printMap(mapList);
    }
    
    /**
     * @return
     */
    public char[][] getMapList() {
        return mapList;
    }
    
    /**
     * Saves the received mapList
     * @param newMap
     */
    public void setMapList(char[][] newMap)
    {
        mapList = newMap;
    }

// Returns mapButtons
    public ArrayList<ArrayList<Button>> getMapButtons() {
        return mapButtons;
    }

    /**
     * Returns mapMoveList
     * @return
     */
    public ConcurrentLinkedQueue<Move> getMapMoveList()
    {
        return shipList;
    }

// Returns locationList
    public ConcurrentLinkedQueue<Location> getLocationList()
    {
        return locationList;
    }

// Saves the recieved window thread
    public void setWindowThread(WindowThread inputThread)
    {
        windowThread = inputThread;
    }

// Saves the recieved fileName
    public void setFileName(String file)
    {
        fileName = file;
    }

// Saves the recieved theme
    public void setTheme(String newTheme)
    {
        theme = newTheme;
    }
}
