package Moveable;

import Gui.Window;

/************************
 * Class : CSE 1325-002 * 
 * Name  : Raith Hamzah *
 * ID    : 1001117012   *
 * File  : Crane.java    *
 ************************
 * @author Raith Hamzah *
 *************************/
public class Crane extends Dock
{

//    /**
//     * Default class constructor.
//     */
//    public Crane() 
//    {
//        super();
//        this.dockSymbol = 'C';
//        this.type = "Crane";
//    }

    /**
     * CSV Constructor
     * @param input
     * @param newWindow
     * @param newGuiThread
     */
    public Crane(String input, Window newWindow, Thread newGuiThread) 
    {
        super(input, newWindow, newGuiThread);
        this.dockSymbol = 'C';
        type = "Crane";
    }

//    /**
//     * Parameter based constructor
//     * @param name
//     * @param section
//     * @param number
//     * @param depth
//     * @param length
//     * @param width
//     * @param longitude
//     * @param dockSymbol
//     */
//    public Crane(String name, char section, 
//           int number, double depth, double length,
//           double width, double longitude, char dockSymbol)
//    {
//        super(name, section, number, depth, 
//                length, width, longitude, dockSymbol);
//    }
    
//    /**
//    * Function to display the dock's current stats.
//    */
//    public void display()
//    {
//        System.out.println("---------------------");
//        System.out.println("Name: " + this.name);
//        System.out.println("Crane Number: " + this.section + this.number);
//        System.out.printf("Size: %3.0fx%3.0fx%3.0f\n", this.length, this.depth, this.width);
//        System.out.printf("Location: (%f, %f)\n", this.longitude, this.latitude);
//        System.out.printf("Location: (%d, %d)\n", MapConverter.lon2col(this.longitude), MapConverter.lat2row(this.latitude));
//
//    }
    
    
    
    
    
    
}
