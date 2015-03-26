/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Map;

import Creature.Entity;
import FixedObject.Water;

/**
 *
 * @author mason
 */
public class MapAbstract
{
    protected Object[][] oMap;

    /**
     * Creates a new fully water map.
     * @param width X - Axis
     * @param height Y - Axis
     */
    public MapAbstract(int width, int height)
    {
        oMap = new Object[height][width];
        
        //Set every object location to null.
        for(Object[] oList: oMap)
        {
            for(Object obj: oList)
            {
                obj = new Water();
            }
        }
    }
    
    /**
     * Places an object at a set location on the map.
     * @param entity New object for the specified location
     * @param row X value
     * @param col Y value
     * @return The previous value at the location (possibly null)
     */
    public Object placeEntity(Entity entity, int row, int col)
    {
        //Save the previous object occupying this location
        Object wasHere = oMap[col][row];
        
        //Set the new object to this location
        oMap[col][row] = entity;
        
        //Take advantage of the protected methods to update the entity's location
        entity.getLocationShallow().setX(row);
        entity.getLocationShallow().setY(col);
        
        //inform the Entity of it's new neighbors and target location
        entity.inform(getSurroundings(entity.getLocationDeep()));
        
        //give the caller access to whatever (possibly null) was there
        return wasHere;
    }
    
    /**
     * Gets the bordering objects.
     * @param location The Location of the Entity or FixedObject
     * @return [North Object, North Location, East Object, East Location,
     *          South Object, South Location, West Object, West Location]
     *///TODO: make this list [obj1, obj1 location, obj2, ob...
    public Object[] getSurroundings(Location location)
    {
        Object[] list = {null, null, null, null, null, null, null, null};
        
        
        if(location.getY() != 0)
        {
            list[0] = oMap[location.getY() - 1][location.getX()];
            list[1] = new Location(location.getX(), location.getY() - 1);
        }
        
        if(location.getY() != oMap.length - 1)
        {
            list[2] = oMap[location.getY() + 1][location.getX()];
            list[3] = new Location(location.getX(), location.getY() + 1);
        }
        
        
        if(location.getX() != (oMap[0].length - 1))
        {
            list[5] = oMap[location.getY()][location.getX() + 1];
        }
        
        if(location.getX() != 0)
        {
            list[7] = oMap[location.getY()][location.getX() - 1];
        }

        return list;
    }
    
}
