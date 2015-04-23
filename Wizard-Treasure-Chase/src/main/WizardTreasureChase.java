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
        
        TaskMaster tM = new TaskMaster();
        tM.testLines();
        
    }
    
}
