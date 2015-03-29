/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Map;

import Creature.Entity;
import FixedObject.Water;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mason
 */
public class MapAbstract
{
    protected MapItem[][] oMap;

    /**
     * Creates a new fully water map.
     * @param width X - Axis
     * @param height Y - Axis
     */
    public MapAbstract(int width, int height)
    {
        oMap = new MapItem[height][width];
        
        //Set every object location to null.
        for(Object[] oList: oMap)
        {
            for(Object obj: oList)
            {
                obj = new Water();
            }
        }
        
        for(int i = 0; i < oMap.length; i++)
        {
            for(int k = 0; k < oMap[0].length; k++)
            {
                oMap[i][k] = new Water();
            }
        }
    }

    /**
     * Places a MapItem at a set location on the map.
     * @param entity New object for the specified location
     * @param location Specifically where to place the Entity
     * @return The previous value at the location (possibly null)
     */
    public Object placeMapItem(Entity entity, Location location)
    {
        //Save the previous object occupying this location
        MapItem wasHere = oMap[location.getY()][location.getX()];
        
        //Set the new object to this location
        oMap[location.getY()][location.getX()] = entity;
        
        //Take advantage of the protected methods to update the entity's location
        // NOTE: Entites *now* get their location from the locations of their
        // surroudings.
//        entity.getLocationShallow().
        
        //inform the Entity of it's new neighbors and target location
        entity.inform(getSurroundings(location));
        
        //give the caller access to whatever (possibly null) was there
        return wasHere;
    }

    /**
     * Gets the bordering objects and their locations.
     * Note: Locations are DEEP copied and only act as informative information.
     * @param location The Location of the MapItem
     * @return { [Up<MapItem, Location>], [Down<MapItem, Location>],
     *           [Right<MapItem, Location>]], [Left<MapItem, Location>]] }
     */
    public HashMap<MapItem, Location> getSurroundings(Location location)
    {
        HashMap<MapItem, Location> hMap = new HashMap<MapItem, Location>();

        System.err.println("Lo: " + location);
        
        //Get what's above the location
        if(location.getY() != 0)
        {
//            list[0] = oMap[location.getY() - 1][location.getX()];
//            list[1] = new Location(location.getX(), location.getY() - 1);
            hMap.put((MapItem) oMap[location.getY() - 1][location.getX()],
                    new Location(location.getX(), location.getY() - 1));
        }

        //Get what's below
        if(location.getY() != oMap.length - 1)
        {
//            list[2] = oMap[location.getY() + 1][location.getX()];
//            list[3] = new Location(location.getX(), location.getY() + 1);
            hMap.put((MapItem) oMap[location.getY() + 1][location.getX()],
                    new Location(location.getX(), location.getY() + 1));
        }

        //Get what's to the right
        if(location.getX() != (oMap[0].length - 1))
        {
//            list[4] = oMap[location.getY()][location.getX() + 1];
//            list[5] = new Location(location.getX() + 1, location.getY());
            hMap.put((MapItem) oMap[location.getY()][location.getX() + 1],
                    new Location(location.getX() + 1, location.getY()));
        }

        //Get what's to the left
        if(location.getX() != 0)
        {
//            list[6] = oMap[location.getY()][location.getX() - 1];
//            list[7] = new Location(location.getX() - 1, location.getY());
            hMap.put((MapItem) oMap[location.getY()][location.getX() - 1],
                    new Location(location.getX() - 1, location.getY()));
        }

        return hMap;
    }
    
}
