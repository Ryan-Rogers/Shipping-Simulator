/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package ThreadMaster;

import Creature.Entity;
import FixedObject.Water;
import Map.Location;
import Ship.*;
import java.awt.Event;
import javax.swing.JButton;

/**
 *
 * @author mason
 */
public class TaskMaster implements RelayListener
{
    
    private MapMaster map;
    
    //TODO: remove line
    ThreadTestGUI TTG;
    
    public static void main(String[] args)
    {
        
        TaskMaster tM = new TaskMaster();
        tM.testLines();
        
    }
    
    

    public TaskMaster()
    {
        //TODO: RYAN: unlock map size by making button generation dynamic
        map = new MapMaster(8, 10);
        
        TTG = new ThreadTestGUI();
        
        // <editor-fold defaultstate="collapsed" desc="Set buttons to 'O'">        
        int c = -1;
        for(JButton[] buttonRow: TTG.getButtons())
        {
            for(JButton button: buttonRow)
            {
                c++;
                //button.setText(""+(char)(45 + c));
                button.setText("O");
            }
        }
        TTG.setVisible(true);
        // </editor-fold>
        
        
        
       
        
    }
    
    /**
     * TODO: remove this method
     */
    public void testLines()
    {
        //TODO: remove all this test code
        RelayMaster relay = new RelayMaster();
        
        relay.addListener(this);

        ShipBasic ship = new ShipBasic(new Location(1, 1), relay, 500);

        ship.setTarget(new Location(7, 9));
        
        Thread thread = new Thread(ship);
        
        ship.inform(map.getSurroundings(new Location(1, 1)));
        
        ship.setMentalState("chase");
        
        thread.start();
        
//
//        ship.start();
    }

    @Override
    public void onRelay(Event evt)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void onRelay(MoveEvent evt)
    {
        System.err.println("onRelay()");
        
        //TODO: erase this test
        for(Location location: evt.getMovePriotity())
        {
            System.err.println("TEST-LOCATE: " + location);
        }
        
        for(Location location: evt.getMovePriotity())
        {
            System.err.println("Locate: " + location);
            //Stop checking and inform the mover about it's current,
            // but unmoved, neighbors (that may possible have changed)
            //Null refers to an unmovable location (off map)
            if(location == null)
            {
                System.err.println("null location");
                ( (Entity) evt.getSource()).inform(map.getSurroundings(location));
            }
            
            if(map.getAtLocation(location) instanceof Water)
            {
                System.err.println("found some wwater");
                TTG.getButtons()[location.getY()][location.getX()].setText("X");
                map.placeMapItem((Entity) evt.getSource(), location);
                //Return on move made
                break;
            }
            System.err.println("");
            System.err.println("LOOP END (1)");
        }
    }
    
    private class MapMaster extends Map.MapAbstract
    {
        
        //See: MapAbstract() for methods and members.
        
        public MapMaster(int width, int height)
        {
            super(width, height);
        }
        
        protected Object getAtLocation(Location location)
        {
            return oMap[location.getY()][location.getX()];
        }
    }
}
