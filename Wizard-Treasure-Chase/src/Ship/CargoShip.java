package Ship;

/************************
 * Class : CSE 1325-002 * 
 * Name  : Raith Hamzah *
 * ID    : 1001117012   *
 * File  : Ship.java    *
 ************************
 * @author Raith Hamzah *
 *************************/
import Gui.Window;
import Map.Location;
/**
 * The Ship class, with which the main 
 * body of the program dictates.
 * <p>
 * It instantiates, as a data member,
 * the cargo class and also is used in
 * the main class to judge against the
 * dock class. 
 */
public class CargoShip extends Moveable
{
    /** Class data members */
    protected String name;
    protected String countryReg;
    protected char shipSymbol;
    
    protected long transponder;
    
    protected double capacity;
    protected double length;
    protected double beam;
    protected double draft;
    
    protected Cargo cargo;
    
    //No empty params allowable
//    /** Default class constructor. */
//    public Ship()
//    {
//        super(location, relay, sleepTime);
//        this.name        = "Zenda";
//        this.countryReg  = "Ruritania";
//        this.transponder = 0;
//        this.capacity    = 10;
//        this.length      = 90;
//        this.beam        = 10;
//        this.draft       = 5;
//        this.shipSymbol  = 'S';
//        this.cargo       = new Cargo();
//    }
    
    /**
     * Class constructor based on string input.
     * @param input
     * @param newLocation
     * @param newDestination
     * @param newWindow
     * @param newGuiThread
     */
    public CargoShip(String input, Location newLocation, Location newDestination, 
            Window newWindow, Thread newGuiThread)
    {
        super(newLocation, newDestination, newWindow, newGuiThread);
        type = "Ship";
        
        String[] tokens = input.split(",");
        
        if (tokens.length == 8)
        {
            this.name        = tokens[0];
            this.countryReg  = tokens[1];
            this.transponder = Long.parseLong(tokens[2]);
            this.capacity    = Double.parseDouble(tokens[3]);
            this.length      = Double.parseDouble(tokens[4]);
            this.beam        = Double.parseDouble(tokens[5]);
            this.draft       = Double.parseDouble(tokens[6]);
            this.cargo       = new Cargo(tokens[7]);
        }
        else
        {
            this.name        = tokens[0];
            this.countryReg  = tokens[1];
            this.transponder = Long.parseLong(tokens[2]);
            this.capacity    = Double.parseDouble(tokens[3]);
            this.length      = Double.parseDouble(tokens[4]);
            this.beam        = Double.parseDouble(tokens[5]);
            this.draft       = Double.parseDouble(tokens[6]);
            this.cargo       = null;
        }
    }

//    /**
//     * Parameter Based Constructor.
//     * @param name
//     * @param countryReg
//     * @param shipSymbol
//     * @param transponder
//     * @param capacity
//     * @param length
//     * @param beam
//     * @param draft
//     * @param longitute
//     * @param latitude
//     * @param cargo
//     */
//    public Ship(String name, String countryReg, char shipSymbol, long transponder, double capacity, double length, double beam, double draft, double longitute, double latitude, Cargo cargo, Location location, long sleepTime)
//    {
//        //super(location, sleepTime);
//        this.name = name;
//        this.countryReg = countryReg;
//        this.shipSymbol = shipSymbol;
//        this.transponder = transponder;
//        this.capacity = capacity;
//        this.length = length;
//        this.beam = beam;
//        this.draft = draft;
//        this.cargo = cargo;
//    }
    
    
    
    /**
    * Function to return the ship's current name.
    * @return Returns the ship's current name.
    */
    public String getName()
    {
        return this.name;
    }
    
    /**
    * Function to assign a new name
    * to the ship.
    * @param name The new name to be assigned.
    */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
    * Function to return the ship's current country of registration.
    * @return Returns the ship's current country of registration.
    */
    public String getCountryReg()
    {
        return this.countryReg;
    }
    
    /**
    * Function to assign a country of registration
    * to the ship.
    * @param registration The new country of registration to be assigned.
    */
    public void setCountryReg(String registration)
    {
        this.countryReg = registration;
    }
    
    /**
    * Function to return the ship's current transponder.
    * @return Returns the ship's current transponder.
    */
    public long getTransponder()
    {
        return this.transponder;
    }
    
    /**
    * Function to assign a new value
    * to the transponder of the ship.
    * @param transponder The new transponder to be assigned.
    */
    public void setTransponder(long transponder)
    {
        this.transponder = transponder;
    }
    
    /**
    * Function to return the ship's current weight capacity.
    * @return Returns the ship's current weight capacity.
    */
    public double getCapacity()
    {
        return this.capacity;
    }
    
    /**
    * Function to assign a new value
    * to the weight capacity of the ship.
    * @param capacity The new weight capacity to be assigned.
    */
    public void setCapacity(double capacity)
    {
        this.capacity = capacity;
    }
    
    /**
    * Function to return the ship's current length.
    * @return Returns the ship's current length.
    */
    public double getLength()
    {
        return this.length;
    }
    
    /**
    * Function to assign a new value
    * to the length of the ship.
    * @param length The new length to be assigned.
    */
    public void setLength(double length)
    {
        this.length = length;
    }
    
    /**
    * Function to return the ship's current beam.
    * @return Returns the ship's current beam.
    */
    public double getBeam()
    {
        return this.beam;
    }
    
    /**
    * Function to assign a new value
    * to the beam of the ship.
    * @param beam The new beam to be assigned.
    */
    public void setBeam(double beam)
    {
        this.beam = beam;
    }
    
    /**
    * Function to return the ship's current draft.
    * @return Returns the ship's current draft.
    */
    public double getDraft()
    {
        return this.draft; 
    }
    
    /**
    * Function to assign a new value
    * to the draft of the ship.
    * @param draft The new draft to be assigned.
    */
    public void setDraft(double draft)
    {
        this.draft = draft;
    }
    
    /**
    * Function to return the ship's current cargo.
    * @return Returns the ship's current cargo.
    */
    public Cargo getCargo()
    {
        return this.cargo;
    }
    
    /**
     *
     * @param newCargo
     */
    public void setCargo(Cargo cargo)
    {
        this.cargo = cargo;
    }

    /**
     *
     * @return
     */
    public char getShipSymbol() 
    {
        return shipSymbol;
    }

    /**
     *
     * @param shipSymbol
     */
    public void setShipSymbol(char symbol) 
    {
        this.shipSymbol = symbol;
    }
    
    
    
    /**
    * Function to display the ship's current stats.
    */
    public void display()
    {
        System.out.println("---------------------------");
        System.out.printf("%s: %s\n", type,this.name);
        System.out.printf("Country of Origin: %s\n", this.countryReg);
        System.out.printf("Transponder: %d\n", this.transponder);
        System.out.printf("Length: %2.2f metres\n", this.length);
        System.out.printf("Beam: %2.2f metres\n", this.beam);
        System.out.printf("Draft: %2.2f metres\n", this.draft);       
        System.out.printf("Barrel Capacity: 700000\n");       
        System.out.printf("Location: (%s)\n", currentLocation.toString());
        System.out.printf("Cargo: ");
        if (this.cargo != null)
            this.cargo.display();
        else
            System.out.printf("This %s is empty.\n", type);
    }
    
    @Override
    public String toString()
    {
        String returnString = String.format("Cargo Ship,%s,%s,%d,%f,%f,%f,%f,&f,&f", 
                this.name, this.countryReg, this.transponder,
                this.length, this.beam, this.draft, this.capacity,
                this.currentLocation.getX(), this.currentLocation.getY());
        if (this.cargo != null)
        {
            returnString = returnString + "," + this.cargo.toString();
        }
        else
        {
            returnString = returnString + "\n";
        }
        return returnString;
    }
    
}
