/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Map;

import java.util.HashMap;

/**
 *
 * @author mason
 */
public class MapItem
{
    //Obsolete code worth mentioning
//    /**
//     * Y-axis location variable
//     * Note: Longitude runs North-South
//     */
//    protected int longitude;
//    
//    /**
//     * X-axis location variable
//     * Note: Latitude runs East-West
//     */
//    protected int latitude;
    
    private char sym;
    
    /**
     * Location of self
     */
    protected Location location;
    
    /**
     * Produces a pointer to the MapItem's location object
     * @return 
     */
    protected Location getLocation()
    {
        return location;
    }
    
    public char getSym()
    {
        return sym;
    }

    public MapItem()
    {
        sym = '#';
    }
    
    public void inform(HashMap<MapItem, Location> hm)
    {
        //TODO: throw an exception
    }
    
}
