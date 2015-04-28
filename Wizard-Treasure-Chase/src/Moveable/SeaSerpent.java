
/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */

package Moveable;

import Gui.Window;
import Map.Location;


/**
 * @author Ryan Rogers
 */

public class SeaSerpent extends SeaMonster {
    public SeaSerpent(Window newWindow, Thread newGuiThread) {
        super(newWindow, newGuiThread);
        this.cSym = 's';
        this.type = "SeaSerpent";
    }
    
    @Override
    public String battlecry() {
        return "Suddenly, you hear bagpipes!";
    }
}