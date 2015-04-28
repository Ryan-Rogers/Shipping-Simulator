/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Map;

/**
 * x, y
 * @author mason
 */
public class Location
{
    private int x;
    private int y;
    
    /**
     * x, y
     * @param x
     * @param y 
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Location(Location location)
    {
        this.x = location.getX();
        this.y = location.getY();
    }
    
    /**
     * Takes longitude and latitude to create a location
     * @param longitude
     * @param latitude 
     */
    public Location(Double longitude, Double latitude)
    {
        this.x = Map.MapConverter.lon2col(longitude);
        this.y = Map.MapConverter.lat2row(latitude);
    }

//    protected void setX(int x)
//    {
//        this.x = x;
//    }
    
    /**
     * Rewrites a location object to be another location.
     * @param location The NEW version of the object to begin using immediately.
     */
    protected void changeLocation(Location location)
    {
        x = location.x;
        y = location.y;
    }

    @Override
    public boolean equals(Object obj)
    {
        return ( (((Location)obj).x == x) && (((Location)obj).y == y) );
    }
    
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

//    protected void setY(int y)
//    {
//        this.y = y;
//    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    
}
