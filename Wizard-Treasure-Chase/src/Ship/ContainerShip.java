/*******************************
 * Class : CSE 1325-002        * 
 * Name  : Raith Hamzah        *
 * ID    : 1001117012          *
 * File  : ContainerShip.java  *
 *******************************
 * @author Raith Hamzah        *
 *******************************/
public class ContainerShip extends Ship
{

    /**
     * Default Class Constructor.
     */
    public ContainerShip() 
    {
        super();
        this.cargo = new Box();
        this.shipSymbol = 'B';
    }

    /**
     * CSV Constructor.
     * @param input
     */
    public ContainerShip(String input) 
    {
        String[] tokens = input.split(",",9);
        
        if (tokens.length == 10)
        {
            this.name        = tokens[0];
            this.countryReg  = tokens[1];
            this.transponder = Long.parseLong(tokens[2]);
            this.capacity    = Double.parseDouble(tokens[3]);
            this.length      = Double.parseDouble(tokens[4]);
            this.beam        = Double.parseDouble(tokens[5]);
            this.draft       = Double.parseDouble(tokens[6]);
            this.longitude   = Double.parseDouble(tokens[7]);
            this.latitude    = Double.parseDouble(tokens[8]);
            this.cargo       = new Box(tokens[9]);
            this.shipSymbol  = 'B';
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
            this.longitude   = Double.parseDouble(tokens[7]);
            this.latitude    = Double.parseDouble(tokens[8]);
            this.cargo       = null;
            this.shipSymbol  = 'B';
        }
    }

    /**
     * Parameter Based Constructor.
     * @param name
     * @param countryReg
     * @param shipSymbol
     * @param transponder
     * @param capacity
     * @param length
     * @param beam
     * @param draft
     * @param longitute
     * @param latitude
     * @param cargo
     */
    public ContainerShip(String name, String countryReg, char shipSymbol, long transponder, double capacity, double length, double beam, double draft, double longitute, double latitude, Cargo cargo) 
    {
        super(name, countryReg, shipSymbol, transponder, capacity, length, beam, draft, longitute, latitude, cargo);
    }
    
    
    
    /**
    * Function to display the ship's current stats.
    */
    public void display()
    {
        System.out.println("---------------------------");
        System.out.printf("Container Ship: %s\n", this.name);
        System.out.printf("Country of Origin: %s\n", this.countryReg);
        System.out.printf("Transponder: %d\n", this.transponder);
        System.out.printf("Length: %2.2f metres\n", this.length);
        System.out.printf("Beam: %2.2f metres\n", this.beam);
        System.out.printf("Draft: %2.2f metres\n", this.draft);       
        System.out.printf("Number of Holds: 1\n");       
        System.out.printf("Location: (%f, %f)\n", this.longitude, this.latitude);
        System.out.printf("Cargo: ");
        if (this.cargo != null)
            this.cargo.display();
        else
            System.out.printf("Empty\n");
    }
    
    public String toString()
    {
        String returnString = String.format("Container Ship,%s,%s,%d,%f,%f,%f,%f,&f,&f", 
                this.name, this.countryReg, this.transponder,
                this.length, this.beam, this.draft, this.capacity,
                this.longitude, this.latitude);
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
