/************************
 * Class : CSE 1325-002 * 
 * Name  : Raith Hamzah *
 * ID    : 1001117012   *
 * File  : Map.java     *
 ************************
 * @author Raith Hamzah *
 *************************/
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;
/**
 * Map Class: Encapsulates the list of offloaded cargo
 * and also the character array for the map.
 * 
 */
public class Map 
{
    private char water;
    private char land;
    private char emptyDock;
    private char shipAtSea;
    private char shipInDock;
    private char unsafeShip;
    
    private Port port;
    private char originalMap[][];
    private ArrayList<Ship> currentShips;
    
    private Formatter out;
    
    /**
     * Class constructor.
     */
    public Map()
    {
        this.water      = '.';
        this.land       = '*';
        this.emptyDock  = 'D';
        this.shipAtSea  = 'S';
        this.shipInDock = '$';
        this.unsafeShip = 'X';
        
        this.out = new Formatter(System.out);
        this.originalMap = new char[36][54]; 
        this.port = new Port();
        this.currentShips = new ArrayList<>();
    }
    
    /**
     * Get's the map's port.
     * @return Returns the Map's Port
     * @throws NullPointerException
     * @throws Exception
     */
    public Port getPort() throws NullPointerException, Exception
    {
        return port;
    }

    /**
     * Sets the map's port.
     * @param port
     * @throws NullPointerException
     * @throws Exception
     */
    public void setPort(Port port) throws NullPointerException, Exception
    {
        this.port = port;
    }

    /**
     * Gets the current ship arraylist.
     * @return
     * @throws NullPointerException
     * @throws Exception
     */
    public ArrayList<Ship> getCurrentShips() throws NullPointerException, Exception
    {
        return currentShips;
    }

    /**
     * Sets the current ship arraylist.
     * @param currentShips
     * @throws NullPointerException
     * @throws Exception
     */
    public void setCurrentShips(ArrayList<Ship> currentShips) throws NullPointerException, Exception
    {
        this.currentShips = currentShips;
    }
    
    /**
     * Shows the map as an ascii graphic.
     * @throws NullPointerException
     * @throws Exception
     */
    public void show() throws NullPointerException, Exception
    {
        out.format("Mark Docks\n");
        out.format("Mark Ships\n");
        displayReport();
        for (int i = 0; i < 36; i++)
        {
            for (int j = 0; j < 54; j++)
            {
                char outSymbol = report(i,j);
                System.out.printf("%c", outSymbol);
            }
            System.out.printf("\n");
        }
    }

    /**
     * Returns original map.
     * @return
     * @throws NullPointerException
     * @throws Exception
     */
    public char[][] getOriginalMap() throws NullPointerException, Exception
    {
        return originalMap;
    }

    /**
     * Sets original map.
     * @param originalMap
     * @throws NullPointerException
     * @throws Exception
     */
    public void setOriginalMap(char[][] originalMap) throws NullPointerException, Exception
    {
        this.originalMap = originalMap;
    }
     
    /**
     * Checks the status of a particular column and row, and returns the necessary character.
     * @param row
     * @param column
     * @return character
     * @throws NullPointerException
     * @throws Exception
     */
    public char report(int row, int column) throws NullPointerException, Exception
    {
        char original = originalMap[row][column];
        int shipCount   = 0;
        int dockCount   = 0;
        int unsafeCount = 0;
        ArrayList<Dock> dockList = port.getDockList();
        for (Dock d: dockList)
        {
            if (MapConverter.lon2col(d.getLongitude()) == column
                  && MapConverter.lat2row(d.getLatitude())== row)
            {
                dockCount++;
            }
        }
        for (Ship s: this.currentShips)
        {
            if (MapConverter.lon2col(s.getLongitude()) == column
                  && MapConverter.lat2row(s.getLatitude())== row)
            {
                shipCount++;
                if ((shipCount > 1))
                {
                    unsafeCount++;
                }
                else
                {
                    for (Dock d: dockList)
                    {   
                        
                        if (MapConverter.lon2col(d.getLongitude()) == column
                              && MapConverter.lat2row(d.getLatitude())== row)
                        {
                            if (s.getBeam() > d.getWidth() || 
                                    s.getDraft() > d.getDepth())
                            {
                                unsafeCount++;
                            }
                            if (s instanceof Ship && !(d instanceof Dock))
                            {
                                unsafeCount++;
                            }
                            if (s instanceof OilTanker && !(d instanceof Pier))
                            {
                                unsafeCount++;  
                            }
                            if (s instanceof ContainerShip && !(d instanceof Crane))
                            {
                                unsafeCount++;
                            }
                        }
                        
                    }
                }
            }            
        }        
        if (unsafeCount > 0)
        {
            return this.unsafeShip;
        }
        else if (shipCount == 1 && dockCount == 0 && original != '*')
        {
            char returnSymbol = 'S';
            for (Ship s: this.currentShips)
            {
                if (MapConverter.lon2col(s.getLongitude()) == column
                  && MapConverter.lat2row(s.getLatitude())== row)
                {
                    returnSymbol = s.getShipSymbol();
                }
            }
            return returnSymbol;
        }
        else if (shipCount == 1 && dockCount == 1)
        {
            return this.shipInDock;
        }
        else if (shipCount == 0 && dockCount == 1)
        {
            char returnSymbol = 'D';
            for (Dock d: dockList)
            {
                if (MapConverter.lon2col(d.getLongitude()) == column
                  && MapConverter.lat2row(d.getLatitude())== row)
                {
                    returnSymbol = d.getDockSymbol();
                }
            }
            return returnSymbol;
        }
        else
        {
            return original;
        }
        
    }
    
    /**
     * Randomly generates ships based on an input.
     * @param count
     * @throws NullPointerException
     * @throws Exception
     */
    public void generateShips(int count) throws NullPointerException, Exception
    {
        Random rand = new Random();
        String firstNames[]  = {"Red", "Green", "Dark", "Light", 
                               "Day", "Night", "Savannah", "Mountain",
                               "Captain's", "Admiral's"};
        String secondNames[] = {"Buffalo", "Pastures", "Knight", "Wave",
                                "Star", "Moon", "Lion", "Goat", "Pride",
                                "Joy"};
        double longMin = -3.035, longMax = -2.988478;
        double latMin = 53.396700, latMax = 53.45761;
        
        for (int i = 0; i < count; i++)
        {
            double tmpLongitude = 0;
            double tmpLatitude = 0;
            
            boolean notSuccessful = true;
            while (notSuccessful)
            {
                tmpLongitude = longMin + (longMax - longMin)*rand.nextDouble();
                tmpLatitude  = latMin + (latMax - latMin)*rand.nextDouble();
                int tmpCol = MapConverter.lon2col(tmpLongitude);
                int tmpRow = MapConverter.lat2row(tmpLatitude);
                char symbolChecker = report(tmpRow, tmpCol);
                if (symbolChecker != this.land)
                {
                    notSuccessful = false;
                }
                
            }
            
            long tmpTrans = Math.abs((rand.nextLong()%(9999999 - 1000000))+ 1000000);
            String nameBuffer;
            int fName = rand.nextInt(10);
            int sName = rand.nextInt(10);
            nameBuffer = firstNames[fName] + " " + secondNames[sName];
            int randShipType = rand.nextInt(3) + 1;
            if (randShipType == 1)
            {
                Ship tmpShip = new Ship();
                tmpShip.setName(nameBuffer);
                tmpShip.setLongitude(tmpLongitude);
                tmpShip.setLatitude(tmpLatitude);
                tmpShip.setTransponder(tmpTrans);
                currentShips.add(tmpShip);
            }
            else if (randShipType == 2)
            {
                ContainerShip tmpShip = new ContainerShip();
                tmpShip.setName(nameBuffer);
                tmpShip.setLongitude(tmpLongitude);
                tmpShip.setLatitude(tmpLatitude);
                tmpShip.setTransponder(tmpTrans);
                currentShips.add(tmpShip);
            }
            else if (randShipType == 3)
            {
                OilTanker tmpShip = new OilTanker();
                tmpShip.setName(nameBuffer);
                tmpShip.setLongitude(tmpLongitude);
                tmpShip.setLatitude(tmpLatitude);
                tmpShip.setTransponder(tmpTrans);
                currentShips.add(tmpShip);
            }
        }
    }
    
    public void displayReport() throws NullPointerException, Exception
    {
        out.format("------------------------------\n");
        for (Ship s: currentShips)
        {
            int tmpCol = MapConverter.lon2col(s.getLongitude());
            int tmpRow = MapConverter.lat2row(s.getLatitude());
            char tmpSym = report(tmpRow, tmpCol);
            if (tmpSym == this.shipAtSea)
            {
                out.format("The %s is at sea.\n", s.getName());
            }
            if (tmpSym == this.shipInDock)
            {
                out.format("The %s is in dock.\n", s.getName());
            }
            if (tmpSym == this.unsafeShip)
            {
                out.format("The %s is unsafe.\n", s.getName());
            }
        }
        out.format("------------------------------\n");
    }
    
    /**
     * Prints out the map's current status.
     * @throws NullPointerException
     * @throws Exception
     */
    public void statusReport() throws NullPointerException, Exception
    {
        out.format("Status Report\n");
        out.format("-----------------------------------\n");
        for (int i = 0; i < this.currentShips.size(); i++)
        {
            Ship tmpShip = this.currentShips.get(i);
            out.format("Ship #%d\n", i);
            tmpShip.display();
            int tmpCol = MapConverter.lon2col(tmpShip.getLongitude());
            int tmpRow = MapConverter.lat2row(tmpShip.getLatitude());
            char tmpSym = report(tmpRow, tmpCol);
            if (tmpSym == this.shipAtSea)
            {
                out.format("The %s is at sea.\n", tmpShip.getName());
            }
            if (tmpSym == this.shipInDock)
            {
                out.format("The %s is in dock.\n", tmpShip.getName());
            }
            if (tmpSym == this.unsafeShip)
            {
                out.format("The %s is unsafe.\n", tmpShip.getName());
            }
            out.format("----------------------------------\n");
        }
        out.format("Docks\n---------------------------------\n");
        for (Dock d: this.port.getDockList())
        {
            d.display();
        }
        out.format("\n");
        out.format("Cargos\n----------------------------------\n");
        for (Cargo c: this.port.getOffloadedCargo())
        {
            c.display();
        }
    }
    
    @Override
    public String toString()
    {
        String returnString ="";
        for (int i = 0; i < 36; i++)
        {
            for (int j = 0; j < 54; j++)
            {
                returnString = returnString + String.format("%d,%d,%c\n", i, j, this.originalMap[i][j]);
            }
        }
        return returnString;
    }
}