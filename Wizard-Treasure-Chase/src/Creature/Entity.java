/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Creature;

import Map.Location;
import Map.MapItem;
import ThreadMaster.*;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Mason Moreland
 * 
 * This class describes what an entity on the map needs to exist.
 * 
 * TODO: finish life cycle
 * Life Cycle: 
 * Construct -> Start -> onStart() -> ... loops continually ... -> [1,2,3]
 * 1: kill() -> ???
 * 2: end() -> Runs out of scope?
 * 3: ???
 */
public class Entity extends MapItem implements Runnable
{
    /**
     * Signifies the state of the object, always lowercase.
     */
    protected String mentalState;

    /**
     * True: The Entity is actively and continually running
     * False: The Entity will finish it's next cycle before ending
     */
    protected boolean running;

    /**
     * Includes what the entity knows about its surroundings and target.
     * [North, East, South, West]
     */
    protected HashMap<MapItem, Location> neighbors;
    
    /**
     * Events are handed to the relay for handling and disbursement.
     */
    protected RelayMaster relay;
    
    /**
     * Location where entity is moving towards
     */
    private Location target; 
    
    /**
     * In milliseconds, how long the Entity will stall.
     */
    private long sleepTime;

    /**
     * TODO: comment here
     * @return A new location representing the current location of self.
     * Null if not on the map.
     */
    @Override
    public Location getLocation()
    {
        super.getLocation();
        //TODO: protect location by using relative values
//        //northern location (could be null)
//        Location north = neighbors.values().iterator().next();
//        //southern location (could be null)
//        Location south = neighbors.values().iterator().next();
//        //right location (could be null)
//        Location east = neighbors.values().iterator().next();
//        //left location (could be null)
//        Location west = neighbors.values().iterator().next();
//        
//        if(north != null)
//        {
//            if(south != null)
//        }
        return null;
    }
    
    
    public synchronized Location getTarget()
    {
        return target;
    }
    
    public synchronized void setTarget(Location target)
    {
        this.target = target;
    }
    
    /**
     * Builds a new Entity.
     * @param location Location of the new entity
     * @param relay The relay responsible for handling triggered events
     * @param sleepTime The target time in msecs to sleep the thread.
     */
    public Entity(Location location, RelayMaster relay, long sleepTime)
    {
        super();
        //Default the state
        mentalState = "wait";
        //Gives the entity a location
        this.location = location;
        //ALL entities need a relay
        this.relay = relay;
        //All entities need a sleep time
        this.sleepTime = sleepTime;
        //Default class variables need to be set on creation
        //location = new Location(-1, -1);
    }
    
    /**
     * Updates the object's knowledge of avaliable surrounding locations.
     * @param moveKnowledge [North, East, South, West]
     */
     public synchronized void inform(HashMap<MapItem, Location> moveKnowledge)
     {
         this.neighbors = moveKnowledge;
         //TODO: remove this test
         Location l1 = moveKnowledge.values().iterator().next();
         Location l2 = moveKnowledge.values().iterator().next();
         location = new Location(l1.getX(), l1.getY() + l2.getY()/2);
     }
     
     // <editor-fold defaultstate="collapsed" desc="Old location methods">
//     /**
//      * Gives a pointer to the Entity's location.
//      * @return The location of the entity
//      */
//     public Location getLocationShallow()
//     {
//         return this.location;
//     }
//     
//     /**
//      * Produces a deep copy of the location of self.
//      * @return A Location() object representing the current object's coordinates.
//      */
//     public Location getLocationDeep()
//     {
//         return new Location(this.location);
//     }
    // </editor-fold>

    /**
     * Allows the constant execution loop to run to an end.
     */
    public synchronized void end()
    {
        //Allow self to run out of scope
        running = false;
    }
    
//    /**
//     * Starts the entity as a thread.
//     * Note: This must be called before the entity will begin running.
//     * Hint: This method will call the <code>onStart()</code> method.
//     */
//    @Override
//    public synchronized void start()
//    {
//        //Running variable will only be set to false when the 
//        running = true;
//        //Starts the innter workings of the thread
//        super.start();
//        //Starts the entity's execution
//        onStart();
//    }
    
//    /**
//     * Commands the entity to start its execution.
//     */
//    protected void onStart()
//    {
//        System.err.println("onStart()");
//        sleep();
//    }
    
    /**
     * Sleeps the Entity.
     */
    protected void sleep()
    {
//        printStackTrace();
        System.err.println("SLEEP()");
        try
        {
            //Puts the Thread to sleep to space cycles out
            Thread.sleep(sleepTime);
            makeAction();
        }
        //Catch interrupt?
        catch(Exception e)
        {
            //printStackTrace();
            System.err.println("Interrupted! Running: " + running);
            if(running != false)
            {
                sleep();
            }
        }
    }
    
    /**
     * Controls the flow of actions the Entity will use.
     * The "mental state" (mentalState) of the object decides what the Entity
     * does after sleeping.
     */
    protected void makeAction()
    {
//        System.err.println("makeAction()");
        switch(mentalState)
        {
            case "wait": /*sleep();*/ break;
            case "chase": chaseTarget(); break;
            default:
                System.err.println("Unknown mental state: " + mentalState);
        }

        sleep();
    }
    
    
    protected void sailToTarget()
    {
        /*
        SailToTarget() looks at the (need to add this next thing) times it has
        been to each location and prioritizes in sublists per moves count on 
        spaces.
        
        Recorded 
        
        UnMovedSpots
        -Best
        -SecondBest
        OneMovedSpots
        -Best
        -SecondBest
        TwiceMovedSpots
        -Best
        -SecondBest
        ...
        ...
        
        
        */
        printStackTrace();
        throw new UnsupportedOperationException("WE DIDN'T DO THIS YET!!");
    }
    
    //TODO: fix this shit
    protected void chaseTarget()
    {
        System.err.println("chaseTarget()");
        //double heading = Map.MapAbstract.bearing(location, target);
        
        //?
//        double distToTarget = Map.MapMetrics.distance(location, target);
        
        MoveEvent mvEvt = new MoveEvent(this);
        
        ArrayList<Double> dist = new ArrayList<Double>();
        Location[] moveChoice = new Location[4];
        
        int c = 0;
        for(Location loca :neighbors.values())
        {
            dist.add(Map.MapMetrics.distance(loca, target));
            moveChoice[c] = loca;
            c++;
        }
        
        for(int i = 0; i < dist.size() - 1; i++)
        {
            for(int u = i + 1; u < dist.size(); u++)
            {
                if(dist.get(u) < dist.get(i))
                {
                    double temp = dist.get(i);
                    dist.set(i, dist.get(u));
                    dist.set(u, temp);
                    Location temp2 = moveChoice[i];
                    moveChoice[i] = moveChoice[u];
                    moveChoice[u] = temp2;
                }
            }
        }
        
        mvEvt.setMoves(moveChoice);
        
//        System.err.println("FIRE RELAY!");
        relay.fireRelay(mvEvt);
    }

    /**
     * Informs the caller of the Entity's 'mental' state
     * @return the operating mentalState of the object
     */
    public String getMentalState()
    {
        return mentalState;
    }

    /**
     * Assigns a new operating mentalState for the object.
     * @param mentalState Pre-defined mentalState
     */
    public void setMentalState(String mentalState)
    {
        this.mentalState = mentalState.toLowerCase();
    }

    @Override
    public void run()
    {
//        System.err.println("RUN()");
        running = true;
        sleep();
        
    }
}
