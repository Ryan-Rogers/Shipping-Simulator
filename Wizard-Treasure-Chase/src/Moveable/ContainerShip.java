package Moveable;

import Gui.Window;

/*******************************
 * Class : CSE 1325-002        * 
 * Name  : Raith Hamzah        *
 * ID    : 1001117012          *
 * File  : ContainerShip.java  *
 *******************************
 * @author Raith Hamzah        *
 *******************************/
public class ContainerShip extends CargoShip
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
    
    @Override
    public String toStringArray()
    {
        String returnString = new String();
        returnString += String.format("Container Ship: %s\n", this.name);
        returnString += String.format("Country of Origin: %s\n", this.countryReg);
        returnString += String.format("Transponder: %d\n", this.transponder);
        returnString += String.format("Length: %2.2f metres\n", this.length);
        returnString += String.format("Beam: %2.2f metres\n", this.beam);
        returnString += String.format("Draft: %2.2f metres\n", this.draft);       
        returnString += String.format("Number of Holds: 1\n");       
        returnString += String.format("Location: (%d, %d)\n", this.currentLocation.getX(), this.currentLocation.getY());
        returnString += String.format("Cargo: ");
        /*
        if (this.cargo != null)
            returnString += this.cargo.display();
        else
            returnString += String.format("Empty\n");
                */
        return returnString;
    }
}
