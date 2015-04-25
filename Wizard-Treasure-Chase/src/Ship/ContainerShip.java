package Ship;

import Gui.Window;

/*******************************
 * Class : CSE 1325-002        * 
 * Name  : Raith Hamzah        *
 * ID    : 1001117012          *
 * File  : ContainerShip.java  *
 *******************************
 * @author Raith Hamzah        *
 *******************************/
public class ContainerShip extends Ship
{

    /**
     * CSV Constructor.
     * @param input
     * @param newLocation
     * @param newDestination
     * @param newWindow
     * @param newGuiThread
     */
    public ContainerShip(String input, Map.Location newLocation, Map.Location newDestination, 
            Window newWindow, Thread newGuiThread)
    {
        super(input, newLocation, newDestination, newWindow, newGuiThread);

        this.cSym = 'B';
        this.type = "ContainerShip";
    }

    public ContainerShip(Window newWindow, Thread newGuiThread)
    {
        super(newWindow, newGuiThread);
        this.cSym = 'B';
        this.type = "ContainerShip";
    }
    
    
}
