package Ship;

/************************
 * Class : CSE 1325-002 * 
 * Name  : Raith Hamzah *
 * ID    : 1001117012   *
 * File  : Cargo.java   *
 ************************
 * @author Raith Hamzah *
 *************************/

/** Cargo class, to be instantiated in ship class */
public class Cargo 
{
    /** Class data members. */
    protected String label;
    private double weight;
    
    /** Default class constructor. */
    public Cargo()
    {
        this.label = "Bananas!";
        this.weight = 10;
    }
   
    /**
     * Input based class constructor.
     * @param label
     * @param weight 
     */
    public Cargo(String label, double weight)
    {
        this.label = label;
        this.weight = weight;
    }

    /**
     * Class constructor based on string input.
     * @param input
     */
    public Cargo(String input) 
    {
        String tokens[];
        tokens = input.split(",");
        this.label = tokens[0];
        this.weight = Double.parseDouble(tokens[1]);
        
    }
    
    /**
    * Function to return the cargo's current label.
    * @return Returns the cargo's current label.
    */
    public String getLabel()
    {
        return this.label;
    }
    
    /**
    * Function to reassign a new label
    * to the cargo.
    * @param newLabel The label to be assigned.
    */
    public void setLabel(String newLabel)
    {
        this.label = newLabel;
    }
    
    /**
    * Function to return the cargo's current weight.
    * @return Returns the cargo's current weight.
    */
    public double getWeight()
    {
        return this.weight;
    }
    
    /**
    * Function to reassign a new weight
    * to the cargo.
    * @param newWeight The weight to be assigned.
    */
    public void setWeight(double newWeight)
    {
        this.weight = newWeight;
    }
    
    /**
    * Function to display the cargo's current stats.
    */
    public void display()
    {
        System.out.printf("%2.0f tons of %s\n", this.weight, this.label);
    }
    
    @Override
    public String toString()
    {
        String returnString = String.format("%s,%f\n", this.label, this.weight);
        return returnString;
    }
}
