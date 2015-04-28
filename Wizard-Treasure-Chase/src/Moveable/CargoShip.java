package Moveable;

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
import java.util.Random;
/**
 * The Ship class, with which the main 
 * body of the program dictates.
 * <p>
 * It instantiates, as a data member,
 * the cargo class and also is used in
 * the main class to judge against the
 * dock class. 
 */
public class CargoShip extends Move
{
    /** Class data members */
    protected String name;
    protected String countryReg;
    protected String[] firstNames = {"Red", "Green", "Dark", "Light", "Day", 
        "Night", "Savanah", "Mountain", "Captain's", "Admiral's"};
    protected String[] lastNames = {"Buffalo", "Pastures", "Knight", "Wave", 
        "Star", "Moon", "Lion", "Goat", "Pride", "Joy"};
    protected long transponder;
    
    protected double capacity;
    protected double length;
    protected double beam;
    protected double draft;
    
    protected Cargo cargo;
    
    /**
     * Generates a default ship.
     * @param newWindow
     * @param newGuiThread 
     */
    public CargoShip(Window newWindow, Thread newGuiThread)
    {
        super(newWindow, newGuiThread);
        type = "Ship";
        cSym = 'S';
        Random randomized = new Random();
        while(transponder > 9999999 || transponder < 1000000) {
            transponder = Math.abs(randomized.nextLong());
            transponder = (transponder/1000000000)/1000;
        }
        name = firstNames[randomized.nextInt(10)] + " " +
                lastNames[randomized.nextInt(10)];
        this.countryReg = "Ruritania";
        this.transponder = 0;
        this.capacity    = 10;
        this.length      = 90;
        this.beam        = 10;
        this.draft       = 5;
        this.cargo       = new Cargo();
    }
    
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
        return cSym;
    }

    /**
     *
     * @param shipSymbol
     */
    public void setShipSymbol(char symbol) 
    {
        this.cSym = symbol;
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
    public String toStringArray() {
        // String[] returnString;
        String returnString = new String();
        returnString += String.format("Cargo Ship: %s\n", this.name);
        returnString += String.format("Country of Origin: %s\n", this.countryReg);
        returnString += String.format("Transponder: %d\n", this.transponder);
        returnString += String.format("Length: %2.2f metres\n", this.length);
        returnString += String.format("Beam: %2.2f metres\n", this.beam);
        returnString += String.format("Draft: %2.2f metres\n", this.draft);       
        returnString += String.format("Capacity: %2.2f tons\n", this.capacity);       
        returnString += String.format("Location: (%d, %d)\n", this.currentLocation.getX(), this.currentLocation.getY());
        returnString += String.format("Cargo: ");
        /*
        if (this.cargo != null)
            returnString += this.cargo.display();
        else
            returnString += String.format("Empty\n");
                */
        return returnString;
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
