/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamhaz
 */
package Creature;

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
public abstract class Entity extends Thread
{
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
     * Events are handed to the relay for handling and disbursement.
     */
    protected RelayMaster relay;

    /**
     * Builds a new Entity.
     * @param relay The relay responsible for handling triggered events
     */
    public Entity(RelayMaster relay, long sleepTime)
    {
        //ALL entities need a relay
        this.relay = relay;
        //All entities need a sleep time
        this.sleepTime = sleepTime;
        //Default class variables
        longitude = -1;
        latitude = -1;
    }
    
    /**
     * Allows the constant execution loop to run to an end.
     */
    public void end()
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
    protected abstract void onStart();
    
    /**
     * Sleeps the Entity.
     */
    private void sleep()
    {
        try
        {
            //Puts the Thread to sleep to space cycles out
            Thread.sleep(sleepTime);
        }
        //Catch interrupt?
        catch(Exception e)
        {
            System.err.println("Failed to sleep.");
            e.printStackTrace();
        }
    }
}
