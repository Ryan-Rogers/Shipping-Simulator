
/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Moveable;

import Gui.Window;

/**
 * @author Ryan Rogers
 */

public class Godzilla extends SeaMonster {
    public Godzilla(Window newWindow, Thread newGuiThread) {
        super(newWindow, newGuiThread);
        this.setTarget(newWindow.getPreyMonster(currentLocation));
        this.cSym = 'G';
        this.type = "Godzilla";
        sleepTime = 100;
    }
}