package info.infosity.Milan;

/**
 * Created by andrea on 20/09/16.
 */
public class Coordinate {

    private double latitude;
    private double longitude;

    public Coordinate(double _latitude, double _longitude)
    {
        latitude = _latitude;
        longitude = _longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }


    public double getLongitude()
    {
        return longitude;
    }

}
