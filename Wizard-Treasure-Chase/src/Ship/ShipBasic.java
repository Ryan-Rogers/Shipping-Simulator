/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Ship;

import Creature.Entity;
import Map.Location;
import ThreadMaster.RelayMaster;

/**
 *
 * @author mason
 */
public class ShipBasic extends Entity
{
    public ShipBasic(Location location, RelayMaster relay, long sleepTime)
    {
        super(location, relay, sleepTime);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }
    
    
}
