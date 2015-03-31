/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package ThreadMaster;

import java.awt.Event;
import java.util.EventListener;

/**
 *
 * @author mason
 */
public interface RelayListener extends EventListener
{
    public void onRelay(Event evt);
    
    public void onRelay(MoveEvent evt);
    
    //TODO: overload for special event types
}
