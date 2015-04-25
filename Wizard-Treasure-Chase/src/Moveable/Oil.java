package Moveable;
/************************
 * Class : CSE 1325-002 * 
 * Name  : Raith Hamzah *
 * ID    : 1001117012   *
 * File  : Oil.java     *
 ************************
 * @author Raith Hamzah *
 *************************/
public class Oil extends Cargo
{
    /** Private data members. */
    private int barrels;
    
    /** Default class constructor. */
    public Oil()
    {
        this.label = "Light Crude";
        this.barrels = 700000;
        
    }
    
    /**
     * Value based class constructor.
     * @param label
     * @param barrels
     */
    public Oil(String label, int barrels)
    {
        this.label = label;
        this.barrels = barrels;
    }
    
    /**
     * String based constructor(CSV).
     * @param input
     */
    public Oil(String input)
    {
        String tokens[];
        tokens = input.split(",");
        this.label = tokens[0];
        this.barrels = Integer.parseInt(tokens[1]);
    }

    /**
     * Returns the Oil's current TEU's.
     * @return
     */
    public int getBarrels() 
    {
        return this.barrels;
    }

    /**
     * Sets the Oil's current TEU's.
     * @param units
     */
    public void setBarrels(int units) 
    {
        this.barrels = units;
    }
    
    /**
    * Displays the Oil's current stats.
    */
    @Override
    public void display()
    {
        System.out.printf("%d barrels of %s\n", this.barrels, this.label);
    }
    
    @Override
    public String toString()
    {
        String returnString = String.format("%s,%d\n", this.label, this.barrels);
        return returnString;
    }
    
    
}
