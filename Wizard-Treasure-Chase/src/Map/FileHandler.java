
/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Map;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import Moveable.Move;
import Moveable.Port;

/**
 * @author Ryan Rogers
 */

public class FileHandler {
    private FileHandler fileHandler;
    String fileName;
    Scanner portReader;
    Scanner mapReader;
    Scanner moveableReader;
    public FileHandler(String inputName) {
        fileName = inputName;
        try{
            portReader = new Scanner(new File(fileName + ".port.txt"));
        } catch(Exception e) {
        }
        try{
            mapReader = new Scanner(new File(fileName + ".map.txt"));
        } catch(Exception e) {
        }
        try{
            moveableReader = new Scanner(new File(fileName + ".ship.txt"));
        } catch(Exception e) {
        }
    }
    
    public void printPortFile() {
        while(portReader.hasNext()) {
            System.out.println(portReader.next());
        }
    }
    
    public char[][] loadMap() {
        char[][] mapHolder = new char[54][36];
        String line;
        String[] splitLine;
        int row;
        int column;
        char code;
        while(mapReader.hasNextLine()) {
            line = mapReader.nextLine();
            splitLine = line.split(",");
            row = Integer.parseInt(splitLine[0]);
            column = Integer.parseInt(splitLine[1]);
            code = splitLine[2].charAt(0);
            mapHolder[row][column] = code;
        }
        return mapHolder;
    }
    
    public void saveMap(char[][] inputMap) {
        PrintWriter write;
        int row = 0;
        int column = 0;
        try {
            write = new PrintWriter(fileName + ".map.txt");
            while(row != 54 || column != 35) {
                if(row == 54) {
                    row = 0;
                    column ++;
                }
                write.print(row + "," + column + "," + inputMap[row][column]);
                write.println();
                row ++;
            }
            write.close();
        } catch(Exception e) {
            System.out.println("Exception occured attempting to save map!");
        }
    }
    
    public void saveShip(ArrayList<Move> moveableList) {
        PrintWriter write;
        try {
            write = new PrintWriter(fileName + ".ship.txt");
            moveableList.stream().forEach((currentShip) -> {
                write.print(currentShip.toString() + "\n");
            });
            write.close();
        } catch(Exception e) {
            System.out.println("Exception occured attempting to save moveable!");
        }
    }
    
    public void savePort(Port port) {
        PrintWriter write;
        try {
            write = new PrintWriter(fileName + ".port.txt");
            write.println(port.toString());
            port.getDockList().stream().forEach((dock) -> {
                write.println(dock.toString());
            });
            port.getCargoList().stream().forEach((cargo) -> {
                write.println(cargo.toString());
            });
            write.close();
        } catch(Exception e) {
            System.out.println("Exception occured attempting to save port!");
        }
    }
    
    public void saveShip(Move moveable) {
        ArrayList<Move> moveableList = new ArrayList<>();
        moveableList.add(moveable);
        saveShip(moveableList);
    }
    
    public ArrayList<String> loadShip() {
        ArrayList<String> moveableHolder = new ArrayList<>();
        for(int i = 0; moveableReader.hasNextLine(); i++) {
            moveableHolder.add(moveableReader.nextLine());
        }
        return moveableHolder;
    }
    
    public ArrayList<String> loadPort() {
        ArrayList<String> portHolder = new ArrayList<>();
        for(int i = 0; portReader.hasNextLine(); i++) {
            portHolder.add(portReader.nextLine());
        }
        return portHolder;
    }
}
