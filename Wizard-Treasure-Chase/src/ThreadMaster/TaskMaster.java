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
import javax.swing.JButton;

/**
 *
 * @author mason
 */
public class TaskMaster implements RelayListener
{
    
    private MapMaster map;
    
    private Window window;
    
    //TODO: remove line
    //ThreadTestGUI TTG;
    
    public static void main(String[] args)
    {
       // TaskMaster tM = new TaskMaster(n);
        //tM.testLines();
    }

    public TaskMaster(Window window)
    {
        //TODO: RYAN: unlock map size by making button generation dynamic
        map = new MapMaster(8, 10);
        
        //TTG = new ThreadTestGUI();

        this.window = window;
        
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
        //TODO: remove all this test code
        RelayMaster relay = new RelayMaster();
        
        relay.addListener(this);

        Location loc = new Location(9, 9);
        
        ShipBasic ship1 = new ShipBasic(new Location(1, 1), relay, 500);
        ShipBasic ship2 = new ShipBasic(new Location(4, 1), relay, 200);
        
        ship1.setTarget(new Location(20, 20));
        ship2.setTarget(loc);
        
        Thread thread1 = new Thread(ship1);
        Thread thread2 = new Thread(ship2);
        
        ship1.inform(map.getSurroundings(new Location(1, 1)));
        ship2.inform(map.getSurroundings(new Location(4, 1)));
        
        ship1.setMentalState("chase");
        ship2.setMentalState("chase");
        
        thread1.start();
        //thread2.start();
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
        System.err.println("TM: onRelay()");
        
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
                ( (Entity) evt.getSource()).inform(map.getSurroundings(location));
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
                System.err.println("TM: tell window to move item");
//                System.err.println((ShipBasic)evt.getSource());
                ShipBasic ship = (ShipBasic)evt.getSource();
//                System.err.println(ship);
                System.err.println(location);
                window.mapMove(ship, location);
                System.err.println("$WE MADE IT PAST");
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
