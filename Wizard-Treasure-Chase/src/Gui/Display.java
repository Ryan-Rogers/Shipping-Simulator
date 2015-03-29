
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
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.ImageObserver;

// Javax
import javax.imageio.*;

// Javax Swing
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/**
 * @author Ryan Rogers
*/

public class Display {
    private char[][] map; // Holds the loaded-from-file terrain map
    private String landImg = "land.png"; // Default land image
    private String ship1Img = "ship1.png"; // Default ship 1 image
    private String waterImg = "water.png"; // Default water image
    private JFrame window;
    private ArrayList<ArrayList<JButton>> buttonList;
    
    public Display() {
        // Creates and displays the window with predefined name and size
        window = new JFrame();
        window.setVisible(true);
        window.setTitle("Wizard Treasure Chase"); // Default window title
        window.setMinimumSize(new Dimension(960, 540)); // Default width, height
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X quits run
        
        // Window Division 1
        GridLayout division1Layout = new GridLayout();
        division1Layout.setColumns(1);
        division1Layout.setRows(2);
        division1Layout.setHgap(0);
        division1Layout.setVgap(0);
        
        JPanel division1 = new JPanel(division1Layout);
        division1.setMinimumSize(new Dimension(960, 540));
        window.add(division1);
        
        // Bar Menu
        JPanel barMenu = new JPanel();
        
        barMenu.setBackground(Color.green); // DEBUG
        barMenu.setMinimumSize(new Dimension(960, 36));
        
        division1.add(barMenu);
        
        // Window Division 2
        GridLayout division2Layout = new GridLayout();
        division2Layout.setColumns(2);
        division2Layout.setRows(1);
        division2Layout.setHgap(0);
        division2Layout.setVgap(0);
        
        JPanel division2 = new JPanel(division2Layout);
        division1.add(division2);
        
        // Window Map
        GridLayout windowMapLayout = new GridLayout();
        windowMapLayout.setColumns(54);
        windowMapLayout.setRows(36);
        windowMapLayout.setHgap(0);
        windowMapLayout.setVgap(0);
        
        JPanel windowMap = new JPanel(windowMapLayout);
        windowMap.setBackground(Color.lightGray); // DEBUG
        windowMap.setMinimumSize(new Dimension(756, 504));
        
        division2.add(windowMap);
        
        
        // Map buttinList
        buttonList = new ArrayList<>(); // Allocating buttonList columns
        for(int i = 0; i < 36; i++) {
            // Allocating buttonList rows
            buttonList.add(new ArrayList<JButton>(36));
        }
        loadMap();
        for(ArrayList<JButton> column : buttonList) { 
            for(int i = 0; i < 54; i++) { // Incrementing column
                JButton newButton = new JButton(); // Allocating new button
                if(map[i][buttonList.indexOf(column)] == '.') { // Water
                    try {
                        ImageIcon icon = new ImageIcon(waterImg);
                        newButton.setIcon(icon);
                    } catch (Exception e) {
                        System.out.println("Could not read the water file!");
                    }
                } else { // Land
                    try {
                        ImageIcon icon = new ImageIcon(landImg);
                        newButton.setIcon(icon);
                    } catch (Exception e) {
                        System.out.println("Could not read the land file!");
                    }
                }
                
                newButton.setBorder(null); // Removing borders
                newButton.setBackground(Color.lightGray); // Matching background
                column.add(newButton); // Adding new button to buttonList
                windowMap.add(column.get(i)); // Adding new button to map gui
            }
        }
        
        // Window Divison 3
        JPanel division3 = new JPanel();
        division3.setLayout(new GridLayout(2, 1));
        division2.add(division3);
        
        // Detailed Menu
        JPanel detailedMenu = new JPanel();
        detailedMenu.setBackground(Color.yellow);
        division3.add(detailedMenu);
        
        // Detailed Art
        JPanel detailedArt = new JPanel();
        detailedArt.setBackground(Color.gray);
        division3.add(detailedArt);
        
        
    } // End Display() Constructor
    
    // Button Click Event
    static class buttonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button pressed");
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
        map = mapHolder;
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
                line += map[currentColumn][currentRow];
                // Adding the next system to the line
                currentColumn ++;
            }
            System.out.println(line);
            line = ""; // Reseting the line for the next row
            currentRow ++;
        }
    }
    
    public void addMapToGui(JPanel panel) {
        // Uses printMap method to print the map to System.out
        int currentColumn;
        int currentRow = 0;
        while(currentRow < 36) {
            // Incrementing through the rows
            currentColumn = 0;
            while(currentColumn < 54) {
                // Incrementing througsh the columns
                panel.add(new JButton(String.valueOf(
                        map[currentColumn][currentRow])));
                // Adding the next system to the line
                currentColumn ++;
            }
            currentRow ++;
        }
    }
}
