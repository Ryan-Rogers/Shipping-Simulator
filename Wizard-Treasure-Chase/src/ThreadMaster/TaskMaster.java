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
import Gui.*;
import Map.MapItem;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author mason
 */
public class TaskMaster implements RelayListener
{
    
    static Window window;
    
    private MapMaster map;
    
    //TODO: remove line
    //ThreadTestGUI TTG;
    
    public static void main(String[] args)
    {
       // TaskMaster tM = new TaskMaster(n);
        //tM.testLines();
    }

    public TaskMaster()
    {
        //TODO: RYAN: unlock map size by making button generation dynamic
        map = new MapMaster(8, 10);
        
        // GUI
        window = new Window();
        WindowThread windowThread = new WindowThread(window);
        windowThread.start();
        
        //TTG = new ThreadTestGUI();
        
        // <editor-fold defaultstate="collapsed" desc="Set buttons to 'O'">        
//        int c = -1;
//        for(JButton[] buttonRow: TTG.getButtons())
//        {
//            for(JButton button: buttonRow)
//            {
//                c++;
//                //button.setText(""+(char)(45 + c));
//                button.setText("O");
//            }
//        }
//        TTG.setVisible(true);
        // </editor-fold>
        
    }
    
    /**
     * TODO: remove this method
     */
    public void testLines()
    {
        
        // Relay Creation
        RelayMaster relay = new RelayMaster();
        relay.addListener(this);
        
        // Sleeping while GUI loads
        try {
            Thread.sleep(10000); // DEFAULT: 10 seconds
        } catch (InterruptedException ex) {
            System.err.println("TaskMaster failed to sleep while GUI loaded");
        }
        
        // DEBUG Ship Creation
        ShipBasic debugShip = new ShipBasic(new Location(1, 1), relay, 1000);
        debugShip.setTarget(new Location(10, 10));
        debugShip.setMentalState("chase");
        debugShip.inform(map.getSurroundings(new Location(1, 1)));
        new Thread(debugShip).start();
        
        /*
        // DEBUG Second Ship Creation
        ShipBasic debugShip2 = new ShipBasic(new Location(5, 5), relay, 1000);
        debugShip2.setTarget(new Location(36, 36));
        debugShip2.setMentalState("chase");
        debugShip2.inform(map.getSurroundings(new Location(5, 5)));
        new Thread(debugShip).start();
        */
    }

    @Override
    public void onRelay(Event evt)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void onRelay(MoveEvent evt)
    {
        // System.err.println("TM: onRelay()");
        
//        //TODO: erase this test
//        for(Location location: evt.getMovePriority())
//        {
//            System.err.println("TEST-LOCATE: " + location);
//        }
        
        for(Location location: evt.getMovePriority())
        {
//            System.err.println("Locate: " + location);
            //Stop checking and inform the mover about it's current,
            // but unmoved, neighbors (that may possible have changed)
            //Null refers to an unmovable location (off map)
            if(location == null)
            {
                System.err.println("null location");
                ((Entity)evt.getSource()).inform(map.getSurroundings(location));
                break;
            }
            
            if(map.getAtLocation(location) instanceof Water)
            {
//                System.err.println("found some water");
                //TTG.getButtons()[location.getY()][location.getX()].setText("X");
                
                //TODO: check for hiddenReef (doCrash)
                
                //TODO: intead of casting to Entity, cast to specific object type
                map.placeMapItem((MapItem)evt.getSource(), location);
                //TODO: Ryan: Remove casting on next line
//                System.err.println((ShipBasic)evt.getSource());
                
//                System.err.println(ship);
                window.mapMove((ShipBasic)evt.getSource(), location);
                //Return on move made
                break;
            }
//            System.err.println("");
//            System.err.println("LOOP END (1)");
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
