
/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package MoveableObject;

import Gui.Window;
import Map.Location;

/**
 * @author Ryan Rogers
 */

public class Moveable implements Runnable {
    
// Variables
    Location currentLocation;
    Location destination = null;
    boolean atDestination;
    Window guiWindow;
    Thread guiThread;
    int sleepTime = 500;
    
// Constructor
    public Moveable(Location newLocation, Location newDestination, 
            Window newWindow, Thread newGuiThread) {
        currentLocation = newLocation;
        destination = newDestination;
        guiWindow = newWindow;
        guiThread = newGuiThread;
    }

// Runnable
    @Override
    public void run() {
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
                if(xDifference * xDifference > yDifference * yDifference) {
                    
                // Moveable is to the right of destination
                    if(currentLocation.getX() > destination.getX()) {
                        
                    // Moving moveable left
                        guiWindow.mapMove(this, new Location(
                                currentLocation.getX() - 1, 
                                currentLocation.getY()));
                    
                // Moveable is to the left of destination
                    } else {
                        
                    // Moving moveable right
                        guiWindow.mapMove(this, new Location(
                                currentLocation.getX() + 1, 
                                currentLocation.getY()));
                    }
                    
            // Moveable is farther from destination y than x
                } else {
                    
                // Moveable is above destination
                    if(currentLocation.getY() > destination.getY()) {
                        
                    // Moving moveable down
                        guiWindow.mapMove(this, new Location(
                                currentLocation.getX(), 
                                currentLocation.getY() - 1));
                        
                // Moveable is below destination
                    } else {
                        
                    // Moving moveable up
                        guiWindow.mapMove(this, new Location(
                                currentLocation.getX(),
                                currentLocation.getY() + 1));
                    }
                }
            }
        }
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

// Set Location
    public void setLocation(Location newLocation) {
        currentLocation = newLocation;
    }
}

/*
// Proof of concept movement code
if(this.currentLocation.getX() == destination.getX()) { // current X == destination X
    if(this.currentLocation.getY() == destination.getY()) { // curent Y == destination Y
        atDestination = true; // Reached destination & breaking loop
    } else { // current Y != destination Y
        if(this.currentLocation.getY() > destination.getY()) { // current Y > destination Y
            nextMove = new Location(this.currentLocation.getX(), this.currentLocation.getY() - 1);
            guiWindow.mapMove(this, nextMove);
        } else { // current Y < destination Y
            nextMove = new Location(this.currentLocation.getX(), this.currentLocation.getY() + 1);
            guiWindow.mapMove(this, nextMove);
        }
    }
} else { // current X != destination X
    if(this.currentLocation.getX() > destination.getX()) { // current X > destination X
        nextMove = new Location(this.currentLocation.getX() - 1, this.currentLocation.getY());
        guiWindow.mapMove(this, nextMove);
    } else { // current X < destination X
        nextMove = new Location(this.currentLocation.getX() + 1, this.currentLocation.getX());
        guiWindow.mapMove(this, nextMove);
    }
}
*/