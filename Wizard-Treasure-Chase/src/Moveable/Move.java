
/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Moveable;

import Gui.Window;
import Map.Location;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Ryan Rogers
 */

public class Move implements Runnable {
    
// Variables
    protected Location currentLocation;
    protected Location destination;
    protected boolean atDestination;
    protected Move target;
    protected Window guiWindow;
    protected Thread guiThread;
    protected int sleepTime;
    protected char cSym;
    
    /**
     * Case sensitive representation of the class name 
     */
    protected String type;
    
// New moveable with a destination
    public Move(Location newLocation, Location newDestination, 
            Window newWindow, Thread newGuiThread) {
        destination = null;
        sleepTime = 500 + new Random().nextInt(500); //TODO: have child classes set this by weight
        currentLocation = newLocation;
        destination = newDestination;
        guiWindow = newWindow;
        guiThread = newGuiThread;
        type = "Moveable";
        cSym = 'E';
    }

    /**
     * 
     * @param newWindow
     * @param newGuiThread 
     */
    public Move(Window newWindow, Thread newGuiThread)
    {
        guiWindow = newWindow;
        guiThread = newGuiThread;
        
        sleepTime = 500 + new Random().nextInt(500);
        currentLocation = getValidSpawn(); // DEBUG DEFAULT UNUSED
        destination = getValidDestination(); // DEBUG DEFAULT UNUSED
        
        type = "Moveable";
        cSym = 'E';
    }
    
        /**
     * For creating dock only
     * @param newWindow
     * @param newGuiThread 
     */
    public Move(Window newWindow, Thread newGuiThread, boolean temp)
    {
        guiWindow = newWindow;
        guiThread = newGuiThread;
        
        sleepTime = 500 + new Random().nextInt(500);
        currentLocation = getValidSpawn(); // DEBUG DEFAULT UNUSED
        destination = null; // DEBUG DEFAULT UNUSED
        
        type = "Moveable";
        cSym = 'E';
    }
    
    public void setSleepTime(int newTime)
    {
        sleepTime = newTime;
    }

// New moveable with a target
    public Move(Location newLocation, Move newTarget, Window newWindow,
            Thread newGuiThread) {
        sleepTime = 500 + new Random().nextInt(500);
        currentLocation = newLocation;
        target = newTarget;
        guiWindow = newWindow;
        guiThread = newGuiThread;
        type = "Moveable";
        cSym = 'E';
    }
    
    protected Location getValidSpawn()
    {
        ArrayList<Location> waterLocations;
        
        waterLocations = guiWindow.getWaterLocations();
        
        Random rand = new Random();
        
        int index = rand.nextInt(waterLocations.size());
        
        return waterLocations.get(index);
    }
    
    protected Location getValidDestination()
    {
        ArrayList<Location> dockLocations;
        
        dockLocations = guiWindow.getDockLocations();
        
        Random rand = new Random();
        
        int index = rand.nextInt(dockLocations.size());
        
        return dockLocations.get(index);
    }

// Runnable
    @Override
    public void run() {
        if(target != null) { // Moveable has a target
            destination = target.getLocation();
        }
        if(destination != null) { // Moveable has a destination
            
        // Loops while moveable is not at destination and GUI is open
            while((currentLocation.getX() != destination.getX()
                    && currentLocation.getY() != destination.getY())
                    && guiThread.isAlive()) {
                
                int xDifference; // Difference in current x and destination x
                int yDifference; // Difference in current y and destination y
                
            // Sleeping
                try { 
                    Thread.sleep(sleepTime);
                    
            // Sleep exception
                } catch (InterruptedException ex) {
                    System.err.println("ShipBasic run() failed to sleep");
                }
                
            // Calculating differenecs in x's and y's
                xDifference = currentLocation.getX() - destination.getX();
                yDifference = currentLocation.getY() - destination.getY();
            
                // Moveable is farther from destination x than y
                    if((xDifference * xDifference) > (yDifference * yDifference)) {

                    // Moveable is to the right of destination
                        if(currentLocation.getX() > destination.getX()) {

                        // Moving moveable left
                            moveMe(-1, 0); // x - 1, y

                    // Moveable is to the left of destination
                        } else {

                        // Moving moveable right
                            moveMe(1, 0); // x + 1, y
                        }

                // Moveable is farther from destination y than x
                    } else {

                    // Moveable is above destination
                        if(currentLocation.getY() > destination.getY()) {

                        // Moving moveable down
                            moveMe(0, -1); // x, y - 1

                    // Moveable is below destination
                        } else {

                        // Moving moveable up
                            moveMe(0, 1); // x, y + 1
                        }
                    }
            }
        }
    }
    
// Call Window to Move Ship
    private void moveMe(int byX, int byY) {
        guiWindow.mapMove(this, new Location(currentLocation.getX() + byX, 
                currentLocation.getY() + byY));
    }
    
// Set Destination
    public void setDestination(Location newDestination) {
        destination = newDestination;
        atDestination = false;
    }

// Get Current Location
    public Location getLocation() {
        return currentLocation;
    }

// Get Current Destination
    public Location getDestination() {
        return destination;
    }

// Set Location
    public void setLocation(Location newLocation) {
        currentLocation = newLocation;
    }
    
    /**
     * Set target
     * @param newTarget 
     */
    public void setTarget(Move newTarget) {
        target = newTarget;
    }
    
// Get cSym
    public char getCSym() {
        return cSym;
    }
    
// toStringArray
    public String toStringArray() {
        return "Moveable\n";
    }
}