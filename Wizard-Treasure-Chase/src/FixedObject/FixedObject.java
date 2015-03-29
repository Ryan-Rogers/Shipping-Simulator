/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package FixedObject;

import Map.Location;
import Map.MapItem;

/**
 *
 * @author mason
 */
public abstract class FixedObject extends MapItem
{
    public FixedObject()
    {
        location = new Location(-1, -1);
    }
    
    public FixedObject(int x, int y)
    {
        location = new Location(x, y);
    }

//    /**
//     * Gives a reference to the object's location on the grid.
//     * @return Pointer to the object's Location object
//     */
//    @Override
//    public Location getLocation()
//    {
//        return this.location;
//    }
}
