
/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Moveable;

import Gui.Window;
import Map.Location;


/**
 *
 * @author Ryan Rogers
 */

public class SeaMonster extends CargoShip {
    public SeaMonster(Window newWindow, Thread newGuiThread) {
        super(newWindow, newGuiThread);
        this.cSym = 'E';
        this.type = "SeaMonster";
    }
}