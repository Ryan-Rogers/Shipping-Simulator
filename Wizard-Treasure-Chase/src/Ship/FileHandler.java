package Ship;

/***************************
 * Class : CSE 1325-002    * 
 * Name  : Raith Hamzah    *
 * ID    : 1001117012      *
 * File  : FileHander.java *
 ***************************
 * @author Raith Hamzah    *
 ***************************/
import com.sun.corba.se.impl.io.TypeMismatchException;
import java.io.*;
import java.util.Scanner;
import java.util.Formatter;

/**
 * FileHandler Class: Manages all loading, saving, and exporting
 * from the program.
 */
public class FileHandler
{
    private Scanner in;
    private Formatter out;
    
    /**
     * Class Constructor.
     */
    public FileHandler()
    {
        this.in  = new Scanner(System.in);
        this.out = new Formatter(System.out);
    }
    
    /**
     * Initializes the map at startup.
     * @param other
     * @throws FileNotFoundException
     * @throws NullPointerException
     * @throws TypeMismatchException
     * @throws Exception
     */
    public void setUp(/* TODO: Add Parameters here */) 
    throws FileNotFoundException, NullPointerException, TypeMismatchException, Exception
    {
        File setUpFile = new File("complex.map.txt");
        Scanner read = new Scanner(setUpFile);
        char mapGrid[][] = new char[36][54];
        while (read.hasNext())
            {
                String tokens[] = read.nextLine().split(",");
                int currColumn  = Integer.parseInt(tokens[0]);
                int currRow     = Integer.parseInt(tokens[1]);
                mapGrid[currRow][currColumn] = tokens[2].charAt(0);

            }
        //other.setOriginalMap(mapGrid);
        read.close();
    }
            
//    /**
//     * Loads files into the program based on an input.
//     * @param other
//     * @throws FileNotFoundException
//     * @throws NullPointerException
//     * @throws TypeMismatchException
//     * @throws Exception
//     */
//    public void load(Map other) throws FileNotFoundException, NullPointerException, TypeMismatchException, Exception
//    {
//        out.format("Please enter a tag for your load files: \n");
//        String inputName = in.nextLine();
//        String stringMap = String.format("%s.map.txt", inputName);
//        String stringPort = String.format("%s.port.txt", inputName);
//        String stringShips = String.format("%s.ships.txt", inputName);
//        File mapFile = new File(stringMap);
//        File portFile = new File(stringPort);
//        File shipFile = new File(stringShips);
//
//        if (shipFile.exists())
//        {
//            Scanner read = new Scanner(shipFile);
//            ArrayList<Ship> shipList = other.getCurrentShips();
//            if (!shipList.isEmpty())
//                shipList.clear();
//
//            while (read.hasNext())
//            {
//                String tokens = read.nextLine();
//                Ship tmpShip = new Ship(tokens);
//                shipList.add(tmpShip);
//                tmpShip = null;
//            }
//            read.close();                
//        }
//        else
//        {
//            out.format("No Ship File found.\n");
//        }
//
//        if (portFile.exists())
//        {
//            Scanner read = new Scanner(portFile);
//            Port mapPort = other.getPort();
//            String portInfo[] = read.nextLine().split(",");
//            mapPort.setName(portInfo[0]);
//            int dockCount = Integer.parseInt(portInfo[1]);
//            int craneCount = Integer.parseInt(portInfo[2]);
//            int pierCount = Integer.parseInt(portInfo[3]);
//            int cargoCount = Integer.parseInt(portInfo[4]);
//            if (!mapPort.getDockList().isEmpty())
//                    mapPort.getDockList().clear();
//
//            for (int i = 0; i < dockCount; i++)
//            {
//                String tokens = read.nextLine();
//                Dock tmpDock = new Dock(tokens);
//                mapPort.getDockList().add(tmpDock);
//            }
//            for (int i = 0; i < craneCount; i++)
//            {
//                String tokens = read.nextLine();
//                Crane tmpCrane = new Crane(tokens);
//                mapPort.getDockList().add(tmpCrane);
//            }
//            for (int i = 0; i < pierCount; i++)
//            {
//                String tokens = read.nextLine();
//                Dock tmpPier = new Pier(tokens);
//                mapPort.getDockList().add(tmpPier);
//            }
//            if (!mapPort.getOffloadedCargo().isEmpty())
//                    mapPort.getOffloadedCargo().clear();
//
//            for (int i = 0; i < cargoCount; i++)
//            {
//                String tokens = read.nextLine();
//                Cargo tmpCargo = new Cargo(tokens);
//                mapPort.getOffloadedCargo().add(tmpCargo);
//                tmpCargo = null;
//            } 
//            read.close();
//        }
//        else
//        {
//            out.format("No Port File Found");
//        }
//
//        if (mapFile.exists())
//        {
//            Scanner read = new Scanner(mapFile);
//            char mapGrid[][] = other.getOriginalMap();
//            while (read.hasNext())
//            {
//                String tokens[] = read.nextLine().split(",");
//                int currColumn  = Integer.parseInt(tokens[0]);
//                int currRow     = Integer.parseInt(tokens[1]);
//                mapGrid[currRow][currColumn] = tokens[2].charAt(0);
//
//            }
//
//
//        }
//        else
//        {
//            out.format("No Map File Found");
//        }  
//    }
    
//    /**
//     * Exports program into three text files, to be loaded.
//     * @param other
//     * @throws FileNotFoundException
//     * @throws NullPointerException
//     * @throws TypeMismatchException
//     * @throws Exception
//     */
//    public void save(Map other) throws FileNotFoundException, NullPointerException, TypeMismatchException, Exception
//    {
//        String comment;
//        out.format("Please enter a comment for the maps: ");
//        comment = in.nextLine();
//        String mapFileName  = comment + ".map.txt";
//        String portFileName = comment + ".port.txt";
//        String shipFileName = comment + ".ships.txt";
//        if (!other.getCurrentShips().isEmpty())
//        {
//            out.format("Saving Ship File...\n");
//            File shipFile = new File(shipFileName);
//            Formatter write = new Formatter(shipFile);
//            for (Ship s: other.getCurrentShips())
//            {
//                write.format("%s", s.toString());
//            }
//            write.flush();
//            write.close();
//
//        }
//        else
//        {
//            out.format("No ships in port.\n");
//            File shipFile = new File(shipFileName);
//            Formatter write = new Formatter(shipFile);
//            write.flush();
//            write.close();
//        }
//
//        if (other.getPort() != null)
//        {
//            out.format("Saving Port File...\n");
//            File portFile = new File(portFileName);
//            Formatter write = new Formatter(portFile);
//            write.format("%s", other.getPort().toString());
//            write.flush();
//            write.close();
//        }
//        else
//        {
//            out.format("No port in map.\n");
//            File portFile = new File(portFileName);
//            Formatter write = new Formatter(portFile);
//            write.format("");
//            write.flush();
//            write.close(); 
//        }
//
//        out.format("Saving Map File...\n");
//        File mapFile = new File(mapFileName);
//        Formatter write = new Formatter(mapFile);
//        write.format("%s", other.toString());
//        write.flush();
//        write.close();
//        out.format("SUCCESS.\n");
//    }
}
