/*
 * This is an assigned group project for CSE1325
 * Members: Mason Moreland, Ryan Rogers, Raith Hamzah
 */
package Map;

/**
 *
 * @author mason
 */
public class MapMetrics
{
    /**
     * TODO: TEST THIS METHOD
     * Should? Calculate the heading between the two locations
     * @param source Bearing being taken from
     * @param target Bearing towards
     * @return Degrees 0-359 of heading/bearing
     */
    public static double bearing(Location source, Location target)
    {

        int longDiff= source.getY() - target.getY();
        double y = Math.sin(longDiff) * Math.cos(target.getX());
        double x = Math.cos(source.getX()) * Math.sin(target.getX()) - Math.sin(source.getX()) 
                * Math.cos(target.getX()) * Math.cos(longDiff);

        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    /**
     * Gives the distance between two locations.
     * @param source Start point
     * @param target End point
     * @return A direct distance with no relation to moves (pure distance)
     */
    public static double distance(Location source, Location target)
    {
        //(X2-X1)^2
        double Xs = Math.pow((target.getX() - source.getX()), 2.0);
        //(Y2-Y1)^2
        double Ys = Math.pow((target.getY() - source.getY()), 2.0);
        
        return Math.sqrt(Xs + Ys);
    }
}
