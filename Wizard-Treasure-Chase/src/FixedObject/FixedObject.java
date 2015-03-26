/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package FixedObject;

import Map.Location;

/**
 *
 * @author mason
 */
public abstract class FixedObject
{
    protected Location location;

    public FixedObject()
    {
        location = new Location(-1, -1);
    }
    
    public FixedObject(int x, int y)
    {
        location = new Location(x, y);
    }

    public Location getLocation()
    {
        return new Location(this.location);
    }
}
