
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
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

// Javax Swing
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JApplet;

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
        window = new JFrame("Wizard Treasure Chase");
        window.setVisible(true);
        //window.setMinimumSize(new Dimension(960, 540)); // Default width, height
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X quits run
        
        // Layout
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        
        // Window
        window.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        window.setLayout(gridBag);
        
        // Menu Bar
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        JPanel menuBar = new JPanel(gridBag);
        window.add(menuBar, constraints);
        menuBar.add(new JButton("menuBar")); // DEBUG
        
        // Window Map
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight = 2;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        JPanel windowMap = new JPanel(gridBag);
        window.add(windowMap, constraints);
        
        // Detailed Menu
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        JPanel detailedMenu = new JPanel(gridBag);
        window.add(detailedMenu, constraints);
        detailedMenu.add(new JButton("detailedMenu")); // DEBUG
        
        // Detailed Art
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        JPanel detailedArt = new JPanel(gridBag);
        window.add(detailedArt, constraints);
        detailedArt.add(new JButton("detailedArt")); // DEBUG
        
        
        
        /* <editor-fold defaultstate="collapsed" desc="Panel Gui">
        // Window Division 1
        JPanel division1 = new JPanel(new GridLayout(2, 1, 0, 0));
        
        // Bar Menu
        JComboBox barMenu = new JComboBox();
        barMenu.setBackground(Color.green); // DEBUG
        
        // Window Division 2
        JPanel division2 = new JPanel(new GridLayout(1, 2, 0, 0));
        
        // Window Map
        JPanel windowMap = new JPanel(new GridLayout(36, 54, 0, 0));
        windowMap.setBackground(Color.lightGray); // DEBUG
        
        // Adding to window
        window.add(division1);
        division1.add(barMenu);
        division1.add(division2);
        division2.add(windowMap);
        */
        // </editor-fold>
        
        // Map buttinList
        buttonList = new ArrayList<>(); // Allocating buttonList columns
        for(int i = 0; i < 36; i++) {
            // Allocating buttonList rows
            buttonList.add(new ArrayList<JButton>(36));
        }
        
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        
        loadMap();
        for(ArrayList<JButton> column : buttonList) { 
            for(int i = 0; i < 54; i++) { // Incrementing column
                JButton newButton = new JButton(); // Allocating new button
                constraints.gridx = i;
                constraints.gridy = buttonList.indexOf(column);
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
                newButton.addActionListener(new buttonClick());
                
                column.add(newButton); // Adding new button to buttonList
                windowMap.add(newButton, constraints);
                //windowMap.add(column.get(i)); // Adding new button to map gui
            }
        }
        
        // Window Divison 3
        //JPanel division3 = new JPanel();
        //division3.setLayout(new GridLayout(2, 1));
        //division2.add(division3);
        
        // Detailed Menu
        //JPanel detailedMenu = new JPanel();
        //detailedMenu.setBackground(Color.yellow);
        //division3.add(detailedMenu);
        
        // Detailed Art
        //JPanel detailedArt = new JPanel();
        //detailedArt.setBackground(Color.gray);
        //division3.add(detailedArt);
        
        window.pack();
        
    } // End Display() Constructor
    
    // Button Click Event
    static class buttonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] parameters = String.valueOf(e.getSource()).split(",");
            System.out.println((Integer.valueOf(parameters[2]) / 14)
                    +", "+(Integer.valueOf(parameters[1]) / 14));
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
    
}
