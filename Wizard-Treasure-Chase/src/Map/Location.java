/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Map;

/**
 *
 * @author mason
 */
public class Location
{
    private int x;
    private int y;

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

    protected void setX(int x)
    {
        this.x = x;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    protected void setY(int y)
    {
        this.y = y;
    }
}
