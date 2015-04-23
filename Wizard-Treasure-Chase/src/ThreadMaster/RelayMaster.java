/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package ThreadMaster;

/**
 *
 * @author mason
 */
public class RelayMaster
{
    /**
     * A list class that produces a list where odd index objects are the 
     * listeners of events sent to this event relay.
     */
    private javax.swing.event.EventListenerList listenerList;

    public RelayMaster()
    {
        listenerList = new javax.swing.event.EventListenerList();
    }
    
    public void addListener(RelayListener relayListener)
    {
        listenerList.add(RelayListener.class, relayListener);
    }
    
    public void removeListener(RelayListener relayListener)
    {
        listenerList.remove(RelayListener.class, relayListener);
    }
    
    /**
     *
     * @param evt
     * TODO: make generic
     */
    public synchronized void fireRelay(MoveEvent evt)
    {
        //System.err.println("fireRelay()");
        Object[] listeners = listenerList.getListenerList();
        
        //Only gives the odds indexes
        for(int i = 1; i < listeners.length; i += 2)
        {
            //Calls the event occurance method
            ((RelayListener)listeners[i]).onRelay(evt);
        }
        //System.err.println("fireRelay() end");
    }
}
