/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Creature;

import FixedObject.Water;
import Map.Location;
import ThreadMaster.*;

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
public class Entity extends Thread
{
    /**
     * Signifies the state of the object, always lowercase.
     */
    protected String mentalState;
    
    /**
     * Y-axis location variable
     * Note: Longitude runs North-South
     */
    protected int longitude;
    
    /**
     * X-axis location variable
     * Note: Latitude runs East-West
     */
    protected int latitude;
    
    /**
     * In milliseconds, the time to remain sleeping.
     */
    protected long sleepTime;

    /**
     * True: The Entity is actively and continually running
     * False: The Entity will finish it's next cycle before ending
     */
    protected boolean running;
    
    /**
     * Includes what the entity knows about its surroundings and target.
     * [North, East, South, West]
     */
    protected Object[] neighbors;
    
    /**
     * Events are handed to the relay for handling and disbursement.
     */
    protected RelayMaster relay;
    
    /**
     * Location of self
     */
    protected Location location;
    
    /**
     * Location where entity is moving towards
     */
    private Location target; 
    
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
        //Default the state
        mentalState = "wait";
        //Gives the entity a location
        this.location = location;
        //ALL entities need a relay
        this.relay = relay;
        //All entities need a sleep time
        this.sleepTime = sleepTime;
        //Default class variables
        longitude = -1;
        latitude = -1;
    }
    
    /**
     * Updates the object's knowledge of avaliable surrounding locations.
     * @param moveKnowledge [North, East, South, West]
     */
     public synchronized void inform(Location[] moveKnowledge)
     {
         this.neighbors = moveKnowledge;
         sleep();
     }
     
     
     /**
      * Gives a pointer to the Entity's location.
      * @return The location of the entity
      */
     public Location getLocationShallow()
     {
         return this.location;
     }
     
     /**
      * Produces a deep copy of the location of self.
      * @return A Location() object representing the current object's coordinates.
      */
     public Location getLocationDeep()
     {
         return new Location(this.location);
     }
    
    /**
     * Allows the constant execution loop to run to an end.
     */
    public synchronized void end()
    {
        //Allow self to run out of scope
        running = false;
    }
    
    /**
     * Starts the entity as a thread.
     * Note: This must be called before the entity will begin running.
     * Hint: This method will call the <code>onStart()</code> method.
     */
    @Override
    public synchronized void start()
    {
        //Running variable will only be set to false when the 
        running = true;
        //Starts the innter workings of the thread
        super.start();
        //Starts the entity's execution
        onStart();
    }
    
    /**
     * Commands the entity to start its execution.
     */
    protected void onStart()
    {
        //TODO: start ?
    }
    
    /**
     * Sleeps the Entity.
     */
    protected void sleep()
    {
        try
        {
            //Puts the Thread to sleep to space cycles out
            Thread.sleep(sleepTime);
            makeAction();
        }
        //Catch interrupt?
        catch(Exception e)
        {
            System.err.println("Failed to sleep.");
            e.printStackTrace();
        }
    }
    
    /**
     * Controls the flow of actions the Entity will use.
     * The "mental state" (mentalState) of the object decides what the Entity
     * does after sleeping.
     */
    protected void makeAction()
    {
        switch(mentalState)
        {
            case "wait": sleep(); break;
            case "chase": chaseTarget(); break;
            default:
                System.err.println("Unknown mental state: " + mentalState);
        }
    }
    
    protected void chaseTarget()
    {
        //double heading = Map.MapAbstract.bearing(location, target);
        
        //?
        //double distToTarget = Map.MapMetrics.distance(location, target);
        
        for(int i = 0; i < neighbors.length - 1; i++)
        {
            for(int t = i + 1; t < neighbors.length; t++)
            {
                if(neighbors[i] instanceof Water)
                {
                    if(Map.MapMetrics.distance((neighbors[i], target))
                    {
                        
                    }
                }
            }
        }
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
}
