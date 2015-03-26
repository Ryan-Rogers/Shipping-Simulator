/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package ThreadMaster;

import Creature.Entity;
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
    
    public static void main(String[] args)
    {
        
        TaskMaster tM = new TaskMaster();
        
        
        
        
        
    }
    
    

    public TaskMaster()
    {
        //TODO: RYAN: unlock map size by making button generation dynamic
        map = new MapMaster(8, 10);
        
        ThreadTestGUI TTG = new ThreadTestGUI();
        
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

        ShipBasic ship = new ShipBasic(new Location(0, 0), relay, 500);

        ship.setTarget(new Location(7, 9));
        
        ship.setMentalState("chase");
        
        ship.inform(map.getSurroundings(ship.getLocationShallow()));

        ship.start();
    }

    @Override
    public void onRelay(Event evt)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onRelay(MoveEvent evt)
    {
        for(Location location: evt.getMovePriotity())
        {
            //Stop checking and inform the 
            if(location == null)
            {
                ((Entity)( evt.getSource() ))
                .inform(map.getSurroundings(((Entity)evt.getSource())
                .getLocationShallow()));
            }
            
            if(map.getAtLocation(location) == null)
            {
                //TODO: ?
            }
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
