package Moveable;

/************************
 * Class : CSE 1325-002 * 
 * Name  : Raith Hamzah *
 * ID    : 1001117012   *
 * File  : Port.java   *
 ************************
 * @author Raith Hamzah *
 *************************/
import java.util.ArrayList;
/**
 * Port class: Encapsulates offloaded cargo
 * and the current docks it holds.
 */
public class Port 
{
    private String name;
    private ArrayList<Dock> dockList;
    private ArrayList<Cargo> cargoList;

    /**
     * Default Class constructor.
     */
    public Port()
    {
        this.name = "Liverpool";
        this.dockList = new ArrayList<>();
        this.cargoList = new ArrayList<>();
    }
    
    /**
     * String input class Constructor.
     * @param input
     */
    public Port(String input)
    {
        String tokens[] = new String[3];
        
        
    }

    /**
     * Gets the port's name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set's the Port's name.
     * @param name
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Get's the port's docl list. 
     * @return
     * @throws NullPointerException
     */
    public ArrayList<Dock> getDockList()
    {
        return dockList;
    }

    /**
     * Set's the port's docklist.
     * @param dockList
     * @throws NullPointerException
     * @throws Exception
     */
    public void setDockList(ArrayList<Dock> dockList) throws NullPointerException, Exception
    {
        this.dockList = dockList;
    }

    /**
     * Gets the port's list of offloaded cargo.
     * @return
     * @throws NullPointerException
     * @throws Exception
     */
    public ArrayList<Cargo> getOffloadedCargo() throws NullPointerException, Exception
    {
        return cargoList;
    }

    /**
     * Sets the port's list of offloaded cargo.
     * @param offloadedCargo
     * @throws NullPointerException
     * @throws Exception
     */
    public void setOffloadedCargo(ArrayList<Cargo> offloadedCargo) throws NullPointerException, Exception
    {
        this.cargoList = offloadedCargo;
    }
    
    @Override
    public String toString() 
    {
        String returnString = String.format("%s,%d,%d\n", this.name,
                               this.dockList.size(), 
                               this.cargoList.size());
        for (Dock d: this.dockList)
        {
            returnString = returnString + d.toString();
        }
        for (Cargo c: this.cargoList)
        {
            returnString = returnString + c.toString();
        }
        return returnString;
        
    }
    
    public ArrayList<Cargo> getCargoList() {
        return cargoList;
    }
    
}
