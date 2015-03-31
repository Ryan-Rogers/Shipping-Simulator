/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package ThreadMaster;

import Map.Location;
import java.util.EventObject;

/**
 *
 * @author mason
 */
public class MoveEvent extends EventObject
{
    private Location[] movePriority;
    
    public MoveEvent(Object source)
    {
        super(source);
        movePriority = new Location[4];
        for(Location loc: movePriority)
        {
            loc = null;
        }
    }

    public void addMove(Location move)
    {
        for(Location location: movePriority)
        {
            if(location == null)
            {
                location = move;
                return;
            }
        }
        //TODO: consider throwing an exception
        System.err.println("Move list full!");
    }
    
    public void setMoves(Location[] moves)
    {
        movePriority = moves;
    }
    
    
    /**
     * Signifies the source's chosen move preference.
     * Note: A null can appear in the list if the move was never avaliable.
     * @return A Location[] of the desired moves.
     */
    public Location[] getMovePriotity()
    {
        return movePriority;
    }
}
