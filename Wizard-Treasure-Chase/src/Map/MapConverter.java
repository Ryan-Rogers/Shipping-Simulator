package Map;

/**
 *
 * @author beckerxx
 */
public class MapConverter {

    private static final double minLon = -3.0350;
    private static final double maxLon = -2.9876;
    private static final double difLon = maxLon - minLon;

    private static final double minLat = 53.3967;
    private static final double maxLat = 53.4593;
    private static final double difLat = maxLat - minLat;

    private static final int minCol = 0;
    private static final int maxCol = 54;
    private static final int minRow = 0;
    private static final int maxRow = 36;

    private static final int difCol = maxCol - minCol;
    private static final int difRow = maxRow - minRow;

    private static final double col2lonSlope = difLon / difCol;
    private static final double row2latSlope = difLat / difRow;
    private static final double lon2colSlope = difCol / difLon;
    private static final double lat2rowSlope = difRow / difLat;

    /**
     *
     * @param lat
     * @return
     */
    public static int lat2row(double lat) 
    {
        int row = 0;
        double temp3=( (lat-minLat)*lat2rowSlope+minRow);
        row=(int) Math.floor(temp3+0.5);
        return row;
    }

    /**
     *
     * @param lon
     * @return
     */
    public static int lon2col(double lon) {
        int col = 0;
        double temp3= ((lon-minLon)*lon2colSlope+minCol);
        col=(int) Math.floor(temp3+0.5);
        return col;
    }

    /**
     *
     * @param row
     * @return
     */
    public static double row2lat(int row) {
        double lat = 0.0;
        lat=row*row2latSlope+minLat;
        return lat;
    }

    /**
     *
     * @param col
     * @return
     */
    public static double col2lon(int col) {
        double lon = 0.0;
        lon=col*col2lonSlope+minLon;
        return lon;
    }
    
    public static void main(String[] args)
    {
        System.out.println(new Location(-3.030611111, 53.405394444));
    }

}