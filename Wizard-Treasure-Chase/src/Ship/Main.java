/************************
 * Class : CSE 1325-002 * 
 * Name  : Raith Hamzah *
 * ID    : 1001117012   *
 * File  : Main.java    *
 ************************
 * @author Raith Hamzah *
 ************************/
import com.sun.corba.se.impl.io.TypeMismatchException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Formatter;

/* Main class. The program will start from here. */
public class Main 
{
    /* Main class data members.  */
    private FileHandler fHandle;
    private Map map;
    static private Scanner in;
    static private Formatter out;
    private static Main main;
    
    /* Coonstructor for the main class. */
    public Main()
    {
        in = new Scanner(System.in);
        out = new Formatter(System.out);
        map = new Map();
        fHandle = new FileHandler();
        
    }
    
    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) 
    {
        try 
        {
            main = new Main();
            main.run();
        }
        catch (TypeMismatchException e)
        {
            out.format("WRONG INPUT!\n");
            e.printStackTrace(System.err);
        }
        catch (NullPointerException e)
        {
            out.format("SEGMENTATION FAULT!\n");
            e.printStackTrace(System.err);
        }
        catch (FileNotFoundException e)
        {
            out.format("FILE NOT FOUND!\n");
            e.printStackTrace(System.err);
        }
        catch (Exception e)
        {
            out.format("SOMETHING WENT WRONG, MAN!\n");
            e.printStackTrace(System.err);
        }
    }
    
    /* Main program logic (run method). */
    private void run() throws NullPointerException, TypeMismatchException, FileNotFoundException, Exception
    {
        fHandle.setUp(map);
        boolean quit = false;
        while (quit == false)
        {
            main.displayMainMenu();
            int choice = in.nextInt();
            in.nextLine();
            switch (choice)
            {
                case 1:
                    main.displayStudent();
                    break;
                case 2:
                    fHandle.load(map);
                    
                    break;
                case 3:
                    main.displayShipMenu();
                    
                    break;
                case 4:
                    main.displayPortMenu();
                    break;
                case 5:
                    map.show();
                    break;
                case 6:
                    main.reportMap();
                    break;
                case 7:
                    quit = true;
                    break;
                default:
                    out.format("Not valid choice! Please enter again.\n");
                    break;
                    
                    
                    
            }
            
        }
        in.close();
    }
    
    /* Displays the main menu text. */
    private void displayMainMenu()
    {
        System.out.println("-------------");
        System.out.println("Main Menu");
        System.out.println("-------------");
        System.out.println("1. Show Student ID");
        System.out.println("2. Load System");
        System.out.println("3. Ship Menu");
        System.out.println("4. Port Map");
        System.out.println("5. Show Map");
        System.out.println("6. Display Report");
        System.out.println("7. Quit");
        System.out.println("-------------");
    }
    
    /* Displays student information. */
    private void displayStudent()
    {
        System.out.println("--------------------");
        System.out.println("Name: Raith Hamzah");
        System.out.println("ID: 1001117012");
        System.out.println("CSE 1325-002");
        System.out.println("March 24th, 2015");
    }

    /* Displays the ship menu. */
    private void displayShipMenu() throws NullPointerException, TypeMismatchException, Exception
    {
        boolean previous = false;
        while (previous == false)
        {
            int choice;
            System.out.println("----------");
            System.out.println("Ship Menu");
            System.out.println("----------");
            System.out.println("1. Generate Ships");
            System.out.println("2. Update Ship");
            System.out.println("3. Display Ships");
            System.out.println("4. Remove All Ships");
            System.out.println("5. Previous Menu");
            
            choice = in.nextInt();
            in.nextLine();
            switch (choice)
            {
                case 1:
                    int howMany = 0;
                    while (howMany < 1 || howMany > 10)
                    {
                        out.format("How many ships would you like to generate? (Between 1 and 10): \n");
                        howMany = in.nextInt();
                        in.nextLine();
                        if (howMany < 1 || howMany > 10)
                            out.format("Not valid range! Try again.\n");
                    }
                    map.generateShips(howMany);
                    break;
                case 2:
                    displayShipUpdateMenu();
                    break;
                case 3:
                    for (Ship s: map.getCurrentShips())
                    {
                        s.display();
                    }
                    break;
                case 4:
                    out.format("--------------------\n");
                    out.format("Removing all ships.\n");
                    map.getCurrentShips().clear();
                    out.format("--------------------\n");
                    break;
                case 5:
                    previous = true;
                    break;
            }
            
        }
    }

  
    /* Displays the update ship menu. */
    private void displaySingleShipUpdateMenu(Ship ship) throws NullPointerException, TypeMismatchException, Exception
    {
        boolean previous = false;
        while (previous == false)
        {
            int choice;
            System.out.println("--------------------");
            System.out.println("Ship Properties Menu");
            System.out.println("--------------------");
            System.out.println("1.  Update Name");
            System.out.println("2.  Update Registration");
            System.out.println("3.  Update Transponder");
            System.out.println("4.  Update Capacity");
            System.out.println("5.  Update Length");
            System.out.println("6.  Update Beam");
            System.out.println("7.  Update Draft");
            System.out.println("8.  Update Position on Map");
            System.out.println("9.  Update Cargo");
            System.out.println("10. Display the ship.");
            System.out.println("11. Previous Menu");
            
            choice = in.nextInt();
            in.nextLine();
            switch (choice)
            {
                case 1:
                    System.out.println("-----------------------");
                    System.out.println("Please enter new value: ");
                    String tmp1 = in.nextLine();
                    ship.setName(tmp1);
                    break;
                case 2:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    String tmp2 = in.nextLine();
                    ship.setCountryReg(tmp2);
                    break;
                case 3:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    long tmp3 = in.nextLong();
                    ship.setTransponder(tmp3);
                    break;
                case 4:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    double tmp4 = in.nextDouble();
                    ship.setCapacity(tmp4);
                    break;
                case 5:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    double tmp5 = in.nextDouble();
                    ship.setLength(tmp5);
                    break;
                case 6:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    double tmp6 = in.nextDouble();
                    ship.setBeam(tmp6);
                    break;
                case 7:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    double tmp7 = in.nextDouble();
                    ship.setDraft(tmp7);
                    break;
                case 8:
                    int which = 0;
                    System.out.println("-------------------------------");
                    out.format("How would you like to change position?\n");
                    out.format("1) By Longitude and Latitude.\n");
                    out.format("2) By Row and Column.\n");
                    which = in.nextInt();
                    in.nextLine();
                    if (which%2 == 1)
                    {
                        System.out.println("-----------------------");
                        System.out.printf ("Please enter new longitude: ");
                        double tmp8 = in.nextDouble();
                        ship.setLongitude(tmp8);
                        System.out.printf ("Please enter new latitude: ");
                        tmp8 = in.nextDouble();
                        ship.setLatitude(tmp8);
                    }
                    else
                    {
                        System.out.println("-----------------------");
                        out.format("Please enter a new column: ");
                        int tmpCol = in.nextInt();
                        in.nextLine();
                        out.format("Please enter a new row: ");
                        int tmpRow = in.nextInt();
                        in.nextLine();
                        ship.setLongitude(MapConverter.col2lon(tmpCol));
                        ship.setLatitude(MapConverter.row2lat(tmpRow));
                    }
                    break;
                case 9:
                    main.displayUpdateCargoMenu(ship);
                    break;
                case 10:
                    ship.display();
                    break;
                case 11:
                    previous = true;
                    break;
            }
            
            
        }
        
    }

    /* Displays the update dock menu. */
    private void displayUpdateDockMenu(Dock dock) throws NullPointerException, TypeMismatchException
    {
        boolean previous = false;
        while (previous == false)
        {
            int choice;
            System.out.println("--------------------");
            System.out.println("Dock Properties Menu");
            System.out.println("--------------------");
            System.out.println("1.  Set the dock name");
            System.out.println("2.  Set the dock section");
            System.out.println("3.  Set the number");
            System.out.println("4.  Set the length");
            System.out.println("5.  Set the width");
            System.out.println("6.  Set the depth");
            System.out.println("7.  Set the longitude and latitude");
            System.out.println("8.  Previous Menu");
            
            choice = in.nextInt();
            in.nextLine();
            switch (choice)
            {
                case 1:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    String tmpName = in.nextLine();
                    dock.setName(tmpName);
                    break;
                case 2:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    char tmpSection = in.nextLine().charAt(0);
                    dock.setSection(tmpSection);
                case 3:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    int tmp1 = in.nextInt();
                    dock.setNum(tmp1);
                    break;
                case 4:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    double tmp2 = in.nextDouble();
                    dock.setLength(tmp2);
                    break;
                case 5:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    double tmp3 = in.nextDouble();
                    dock.setWidth(tmp3);
                    break;
                case 6:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    double tmp4 = in.nextDouble();
                    dock.setDepth(tmp4);
                    break;
                case 7:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new longitude: ");
                    double tmp5 = in.nextDouble();
                    dock.setLongitude(tmp5);
                    System.out.printf ("Please enter new latitude: ");
                    tmp5 = in.nextDouble();
                    dock.setLatitude(tmp5);
                    break;
                case 8:
                    previous = true;
                    break;
                    
            }
        }
    }
    
   /* Displays the update ship cargo menu. */
    private void displayUpdateCargoMenu(Ship ship) throws NullPointerException, TypeMismatchException, Exception
    {
        boolean previous = false;
        while (previous == false)
        {
            int choice;
            System.out.println("---------------------");
            System.out.println("Cargo Properties Menu");
            System.out.println("---------------------");
            System.out.println("1.  Update Description");
            System.out.println("2.  Update Weight");
            System.out.println("3.  Display Cargo");
            System.out.println("4.  Previous Menu");
            
            choice = in.nextInt();
            in.nextLine();
            switch (choice)
            {
                case 1:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    String tmp1 = in.nextLine();
                    ship.getCargo().setLabel(tmp1);
                    break;
                case 2:
                    System.out.println("-----------------------");
                    System.out.printf ("Please enter new value: ");
                    double tmp2 = in.nextDouble();
                    ship.getCargo().setWeight(tmp2);
                    break;
                case 3:
                    System.out.println("-----------------------");
                    ship.getCargo().display();
                    break;
                case 4:
                    previous = true;
                    break;
                    
            }
        }
        
        
    }
    /* Displays ship update menu.*/
    private void displayShipUpdateMenu() throws NullPointerException, TypeMismatchException, Exception
    {
        out.format("-------------------------\n");
        out.format("Choose a ship to update:\n");
        for (int i = 0; i < map.getCurrentShips().size(); i++)
        {
            Ship tmpShip1 = map.getCurrentShips().get(i);
            out.format("%d) %s\n", (i+1), tmpShip1.getName());
        }
        int choice = 1;
        do
        {
            if (choice < 1 || choice > map.getCurrentShips().size())
            {
                out.format("Invalid Input! Please try again.\n");
            }
            choice = in.nextInt();
            in.nextLine();
        } while (choice < 1 || choice > map.getCurrentShips().size());
        
        Ship tmpShip2 = map.getCurrentShips().get((choice-1));
        displaySingleShipUpdateMenu(tmpShip2);
    }
    
    /* Displays the port menu. */
    private void displayPortMenu() throws NullPointerException, TypeMismatchException, Exception
    {
        int choice;
        boolean previous = false;
        while (previous == false)
        {
            System.out.println("---------------------");
            System.out.println("Port Menu");
            System.out.println("---------------------");
            System.out.println("1.  Update Dock");
            System.out.println("2.  Unload Ship");
            System.out.println("3.  Display all Cargos");
            System.out.println("4.  Display All Docks");
            System.out.println("5.  Return to the previous menu");
            
            choice = in.nextInt();
            in.nextLine();
            switch (choice)
            {
                case 1:
                    out.format("-------------------------\n");
                    out.format("Choose a dock to update:\n");
                    for (int i = 0; i < map.getPort().getDockList().size(); i++)
                    {
                        Dock tmpDock1 = map.getPort().getDockList().get(i);
                        out.format("%d) %s\n", (i+1), tmpDock1.getName());
                    }
                    int which = 1;
                    do
                    {
                        if (which < 1 || which > map.getCurrentShips().size())
                        {
                            out.format("Invalid Input! Please try again.\n");
                        }
                        which = in.nextInt();
                        in.nextLine();
                    } while (which < 1 || which > map.getCurrentShips().size());

                    Dock tmpDock2 = map.getPort().getDockList().get((which-1));
                    displayUpdateDockMenu(tmpDock2);
                    break;
                case 2:
                    out.format("----------------------------------\n");
                    out.format("Choose a dock by index to unload:\n");
                    out.format("----------------------------------\n");
                    for (int i = 0; i < map.getPort().getDockList().size(); i++)
                    {
                        Dock tmpDock1 = map.getPort().getDockList().get(i);
                        Ship tmpShip1;
                        int tmpCol = MapConverter.lon2col(tmpDock1.getLongitude());
                        int tmpRow = MapConverter.lat2row(tmpDock1.getLatitude());
                        char checkSymbol = map.report(tmpRow, tmpCol);
                        if (checkSymbol == '$')
                        {
                            for (Ship s: map.getCurrentShips())
                            {
                                if (s.getLongitude() == tmpDock1.getLongitude())
                                {
                                    tmpShip1 = s;
                                    out.format("Dock Index: %d, Dock: %s, Ship: %s \n", (i+1), tmpDock1.getName(), tmpShip1.getName());
                                }
                            }
                            
                        }
                        
                    }
                    int which2 = 1;
                    do
                    {
                        if (which2 < 1 || which2 > map.getPort().getDockList().size())
                        {
                            out.format("Invalid Input! Please try again.\n");
                        }
                        which2 = in.nextInt();
                        in.nextLine();
                    } while (which2 < 1 || which2 > map.getPort().getDockList().size());
                    tmpDock2 = map.getPort().getDockList().get((which2-1));
                    for (Ship s: map.getCurrentShips())
                    {
                        if (s.getLongitude() == tmpDock2.getLongitude())
                            {
                                Cargo tmpCargo = s.getCargo();
                                map.getPort().getOffloadedCargo().add(tmpCargo);
                                    
                            }
                    }
                    break;
                case 3:
                    for (int i = 0; i < map.getPort().getOffloadedCargo().size(); i++)
                    {
                        out.format("%d) ", i);
                        map.getPort().getOffloadedCargo().get(i).display();
                    }
                    break;
                case 4:
                    for (int i = 0; i < map.getPort().getDockList().size(); i++)
                    {
                        System.out.println("---------------------");
                        System.out.printf("       Index %d       \n", (i+1));
                        map.getPort().getDockList().get(i).display();
                    }
                    break;
                case 5: 
                    previous = true;
                    break;
            }
                
        }
    }

    private void reportMap() throws NullPointerException, TypeMismatchException, Exception
    {
        map.statusReport();
    }
    
    

}
