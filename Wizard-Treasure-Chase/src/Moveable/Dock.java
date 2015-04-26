package Moveable;

import Gui.Window;
import Map.Location;

/************************
 * Class : CSE 1325-002 * 
 * Name  : Raith Hamzah *
 * ID    : 1001117012   *
 * File  : Dock.java    *
 ************************
 * @author Raith Hamzah *
 *************************/

/**
* The Dock class, to be instantiated
* in the main class, to which the
* ship class will interact with.
*/
public class Dock extends Move
{
    /** Class data members. */
    protected String name;
    protected char section;
    protected int number;
    protected double depth;
    protected double length;
    protected double width;
    protected double longitude;
    protected double latitude;
    protected char dockSymbol;
    
//    protected Location location;
    
//    protected String type;
    
    
//    public Dock(Window newWindow, Thread newGuiThread)
//    {
//        super(newWindow, newGuiThread);
//        
//        this.name       = "Rudolph's Dock";
//        this.section    = 'N';
//        this.number     = 100;
//        this.depth      = 15;
//        this.length     = 100;
//        this.width      = 6;
////        this.longitude  = -2.977838;
////        this.latitude   = 53.410777;
//        location = getValidSpawn();
//        this.dockSymbol = 'D';
//        type = "Dock";
//    }

//    /**
//     * Constructor with parameters.
//     * @param name
//     * @param section
//     * @param number
//     * @param depth
//     * @param length
//     * @param width
////     * @param longitude
//     * @param dockSymbol
//     */
//    public Dock(String name, char section, int number, double depth, 
//                double length, double width, double longitude,
//                char dockSymbol) 
//    {
//        this.name = name;
//        this.section = section;
//        this.number = number;
//        this.depth = depth;
//        this.length = length;
//        this.width = width;
////        this.longitude = longitude;
//        this.dockSymbol = dockSymbol;
//    }
    
    
    /**
     * Class constructor based on a string input.
     * @param input
     */
    public Dock(String input, Window newWindow, Thread newGuiThread)
    {
        super(newWindow, newGuiThread, false);
        
        String tokens[];
        tokens = input.split(",");
        this.name       = tokens[0];
        this.section    = tokens[1].charAt(0);
        this.number     = Integer.parseInt(tokens[2]);
        this.depth      = Double.parseDouble(tokens[3]);
        this.length     = Double.parseDouble(tokens[4]);
        this.width      = Double.parseDouble(tokens[5]);
        this.longitude  = Double.parseDouble(tokens[6]);
        this.latitude   = Double.parseDouble(tokens[7]);
        currentLocation = new Location(longitude, latitude);
        
        this.cSym = 'D';
    }
    
    /**
    * Function to return the dock's current ID.
    * @return Returns the dock's current ID.
    */
    public int getNum()
    {
        return this.number;
    }
    
    /**
    * Function to reassign a new ID number
    * to the dock.
    * @param newNum The new ID to be assigned.
    */
    public void setNum(int newNum)
    {
        this.number = newNum;
    }
    
    /**
    * Function to return the dock's current depth.
    * @return Returns the dock's current depth.
    */
    public double getDepth()
    {
        return this.depth;
    }
    
    /**
    * Function to assign a new value
    * to the depth of the dock.
    * @param newDepth The new depth to be assigned.
    */
    public void setDepth(double newDepth)
    {
        this.depth = newDepth;
    }
    
    /**
    * Function to return the dock's current length.
    * @return Returns the dock's current length.
    */
    public double getLength()
    {
        return this.length;
    }
    
    /**
    * Function to assign a new value
    * to the length of the dock.
    * @param newLength The new length to be assigned.
    */
    public void setLength(double newLength)
    {
        this.length = newLength;
    }
    
    /**
    * Function to return the dock's current width.
    * @return Returns the dock's current width.
    */
    public double getWidth()
    {
        return this.width;
    }
    
    /**
    * Function to assign a new value
    * to the width of the dock.
    * @param newWidth The new length to be assigned.
    */
    public void setWidth(double newWidth)
    {
        this.width = newWidth;
    }
    
//    /**
//    * Function to return the dock's current longitudinal position.
//    * @return Returns the dock's current longitudinal position.
//    */
//    public double getLongitude()
//    {
//        return this.longitude;
//    }
    
//    /**
//    * Function to assign a new value
//    * to the longitudinal position of the dock.
//    * @param newLong The new longitude to be assigned.
//    */
//    public void setLongitude(double newLong)
//    {
//        this.longitude = newLong;
//    }
    
//    /**
//    * Function to return the dock's current latitudinal position.
//    * @return Returns the dock's current latitudinal position.
//    */
//    public double getLatitude()
//    {
//        return this.latitude;
//    }
//    
//    /**
//    * Function to assign a new value
//    * to the latitudinal position of the dock.
//    * @param newLat The new latitude to be assigned.
//    */
//    public void setLatitude(double newLat)
//    {
//        this.latitude = newLat;
//    }

    /**
     * Gets the dock's name.
     * @return
     */
    public String getName() 
    {
        return this.name;
    }
    
    /**
     * Set's the dock's name.
     * @param newName
     */
    public void setName(String newName) 
    {
        this.name = newName;
    }

    /**
     * Get's the dock's section.
     * @return
     */
    public char getSection() 
    {
        return this.section;
    }

    /**
     * Set's the dock's section.
     * @param newSection
     */
    public void setSection(char newSection) 
    {
        this.section = newSection;
    }

    /**
     *
     * @return
     */
    public char getDockSymbol() {
        return dockSymbol;
    }

    /**
     *
     * @param dockSymbol
     */
    public void setDockSymbol(char dockSymbol) {
        this.dockSymbol = dockSymbol;
    }
     
    
    /**
    * Function to display the dock's current stats.
    */
    public void display()
    {
        System.out.println("---------------------");
        System.out.println("Name: " + this.name);
        System.out.println("Dock Number: " + this.section + this.number); 
        System.out.printf("Size: %3.0fx%3.0fx%3.0f\n", this.length, this.depth, this.width);
        System.out.printf("Location: (%f, %f)\n", this.longitude, this.latitude);
        System.out.printf("Location: (%s)\n", currentLocation.toString());

    }
    
    @Override
    public String toString()
    {
        String returnString = String.format("%s,%c,%d,%f,%f,%f,%f,%f\n",
                               this.name, this.section, this.number,
                               this.length, this.width, this.depth,
                               this.longitude, this.latitude);
        
        return returnString;
    }

}
