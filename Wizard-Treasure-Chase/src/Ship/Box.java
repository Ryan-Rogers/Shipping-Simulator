/************************
 * Class : CSE 1325-002 * 
 * Name  : Raith Hamzah *
 * ID    : 1001117012   *
 * File  : Box.java     *
 ************************
 * @author Raith Hamzah *
 *************************/
public class Box extends Cargo
{
    /** Private data members. */
    private int units;
    
    /** Default class constructor. */
    public Box()
    {
        this.label = "Marble";
        this.units = 10000;
        
    }
    
    /**
     * Value based class constructor.
     * @param label
     * @param units
     */
    public Box(String label, int units)
    {
        this.label = label;
        this.units = units;
    }
    
    /**
     * String based constructor(CSV).
     * @param input
     */
    public Box(String input)
    {
        String tokens[];
        tokens = input.split(",");
        this.label = tokens[0];
        this.units = Integer.parseInt(tokens[1]);
    }

    /**
     * Returns the Box's current TEU's.
     * @return
     */
    public int getUnits() 
    {
        return this.units;
    }

    /**
     * Sets the Box's current TEU's.
     * @param units
     */
    public void setUnits(int units) 
    {
        this.units = units;
    }
    
    /**
    * Displays the Box's current stats.
    */
    @Override
    public void display()
    {
        System.out.printf("%d teus of %s\n", this.units, this.label);
    }
    
    @Override
    public String toString()
    {
        String returnString = String.format("%s,%d\n", this.label, this.units);
        return returnString;
    }
    
    
}
