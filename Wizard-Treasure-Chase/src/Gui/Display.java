/*
 * Display class created as beginning of GUI
 * Holds the loaded-from-file version of the terrain map
 * Will load map from file and display the map images within the GUI
 */

package Gui;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * @author Ryan Rogers
*/

public class Display {
    private Display display;
    char[][] map;
    
    public void Display() {
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
    
    public void printMap() {
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
