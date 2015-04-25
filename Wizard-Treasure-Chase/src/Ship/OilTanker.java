package Ship;

import Gui.Window;

/*******************************
 * Class : CSE 1325-002        * 
 * Name  : Raith Hamzah        *
 * ID    : 1001117012          *
 * File  : OilTanker.java      *
 *******************************
 * @author Raith Hamzah        *
 *******************************/
public class OilTanker  extends CargoShip
{

    /**
     * CSV Constructor.
     * @param input
     * @param newLocation
     * @param newDestination
     * @param newWindow
     * @param newGuiThread
     */
    public OilTanker(String input, Map.Location newLocation, Map.Location newDestination, 
            Window newWindow, Thread newGuiThread)
    {
        super(input, newLocation, newDestination, newWindow, newGuiThread);

        this.shipSymbol = 'T';
        this.type = "OilTanker";
    }
}
