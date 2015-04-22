/* 
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package main;

import Gui.Window;
import Gui.WindowThread;
import ThreadMaster.TaskMaster;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mason
 */
public class WizardTreasureChase
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(WizardTreasureChase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TaskMaster tM = new TaskMaster();
        tM.testLines();
        
    }
    
}
