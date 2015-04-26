
/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Moveable;

import Gui.Window;
import java.util.Random;


/**
 * @author Ryan Rogers
 */
// Location newLocation, Move newTarget, Window newWindow, Thread newGuiThread
public class SeaMonster extends Move {
    
    public SeaMonster(Window newWindow, Thread newGuiThread) {
        super(newWindow, newGuiThread);
//        this.setLocation(newWindow.getWaterLocations().get(newWindow
//                .getWaterLocations().size() - 1));
        
//        this.setTarget(newWindow.getPreyShip(this.getClass()));
        
        this.setTarget(newWindow.getPreyShip(currentLocation));
        
        
        
        sleepTime -= (new Random()).nextInt(300);
        this.cSym = 'E';
        this.type = "SeaMonster";
    }
}