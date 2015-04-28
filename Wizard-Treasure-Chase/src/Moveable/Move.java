
/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Moveable;

import Gui.Window;
import Map.Location;
import Map.MapConverter;
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
    protected boolean isRunning;
    protected Location spawn;
    protected boolean paused;
    
    /**
     * Case sensitive representation of the class name 
     */
    protected String type;
    
// New moveable with a destination
    public Move(Location newLocation, Location newDestination, 
            Window newWindow, Thread newGuiThread) {
        destination = null;
        isRunning = false;
        sleepTime = 500 + new Random().nextInt(500); //TODO: have child classes set this by weight
        currentLocation = newLocation;
        getValidTarget();
        guiWindow = newWindow;
        guiThread = newGuiThread;
        type = "Moveable";
        cSym = 'E';
        spawn = newLocation;
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
        spawn = currentLocation;
        getValidTarget();
        
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
        spawn = currentLocation;
        
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
        sleepTime = 700 + new Random().nextInt(100);
        currentLocation = newLocation;
        spawn = currentLocation;
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
        
        int index = rand.nextInt(Math.abs(waterLocations.size()));
        
        return waterLocations.get(index);
    }
    
    protected void getValidTarget() {
        ArrayList<Dock> dockList = guiWindow.getDocks();
        int randomIndex = new Random().nextInt(dockList.size());
        setDestination(dockList.get(randomIndex).getLocation());
        setTarget(dockList.get(randomIndex));
    }
    
// Runnable
    @Override
    public void run() {
        
        isRunning = true;
        if(target != null) { // Moveable has a target
            destination = target.currentLocation;
        }
        if(destination != null) { // Moveable has a destination
            
        // Loops while moveable is not at destination and GUI is open
            while(guiThread.isAlive() && isRunning) {
                if(paused) {
                    try { // Sleeping
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {// Sleep exception
                        System.err.println("ShipBasic run() failed to sleep");
                    }
                } else {
                if (currentLocation.getX() == destination.getX()
                        && currentLocation.getY() == destination.getY()) {
                    end();
                    if(destination.equals(spawn)) {
                        guiWindow.mapMove(this, null);
                        guiWindow.newRandomShip();
                    } else {
                        guiWindow.reachedDestination(this, target);
                    }
                } else {
                        if(target != null) { // Moveable has a target
                            destination = target.currentLocation;
                        }

                        int xDifference; // Difference in current x and destination x
                        int yDifference; // Difference in current y and destination y

                        try { // Sleeping
                            Thread.sleep(sleepTime);
                            if(!isRunning) {
                                continue;
                            }
                        } catch (InterruptedException ex) {// Sleep exception
                            System.err.println("ShipBasic run() failed to sleep");
                        }

                    // Calculating differenecs in x's and y's
                        xDifference = currentLocation.getX() - destination.getX();
                        yDifference = currentLocation.getY() - destination.getY();
                        if((xDifference * xDifference)
                                > (yDifference * yDifference)) {

                        // Moveable is to the right of destination
                            if(currentLocation.getX() > destination.getX()) {
                                moveMe(-1, 0); // x - 1, y

                        // Moveable is to the left of destination
                            } else { // Moving moveable right
                                moveMe(1, 0); // x + 1, y
                            }

                    // Moveable is farther from destination y than x
                        } else { // Moveable is above destination
                            if(currentLocation.getY() > destination.getY()) {
                                moveMe(0, -1); // x, y - 1
                            } else { // Moving moveable up
                                moveMe(0, 1); // x, y + 1
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void end() {
        isRunning = false;
    }
    
    /**
     * Tells window to move this Move by the inputed X, Y differences.
     * If the location is equal to the destination, it stops the thread.
     * @param byX
     * @param byY 
     */
    public void moveMe(int byX, int byY) {
        Location newLocation = new Location(currentLocation.getX() + byX, 
                currentLocation.getY() + byY);
        guiWindow.mapMove(this, newLocation);
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
    
    public void setPaused(boolean newPauseState) {
        this.paused = newPauseState;
    }
    
    /**
     * May need to invert.
     * @return MapConverter conversion of Y to latitude
     */
    public double getLongitude() {
        return MapConverter.row2lat(currentLocation.getY());
    }
    
    /**
     * May need to invert.
     * @return MapConverter conversion of X to longitude
     */
    public double getLatitude() {
        return MapConverter.col2lon(currentLocation.getX());
    }
    
    /**
     * MapConvert conversion of latitude to X
     * May need to invert.
     * @param newLongitude
     */
    public void setLongitude(Double newLongitude) {
        currentLocation = new Location(currentLocation.getX(), 
                MapConverter.lat2row(newLongitude));
    }
    
    /**
     * MapConvert conversion of longitude to X
     * May need to invert.
     * @param newLatitude
     */
    public void setLatitude(Double newLatitude) {
        currentLocation = new Location(MapConverter.lon2col(newLatitude), 
                currentLocation.getY());
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
    
    public Move getTarget() {
        return target;
    }
    
    public Location getSpawn() {
        return spawn;
    }
}