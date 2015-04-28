
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

public class Leviathan extends SeaMonster {
    public Leviathan(Window newWindow, Thread newGuiThread) {
        super(newWindow, newGuiThread);
        this.cSym = 'L';
        this.type = "Leviathan";
    }
    
    @Override
    public String battlecry() {
        return "Come! Ahab beckons!";
    }
}