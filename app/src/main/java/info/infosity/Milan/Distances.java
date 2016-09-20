package info.infosity.Milan;

/**
 * Created by andrea on 20/09/16.
 */
/**
 * Created by andrea on 20/09/16.
 */
public class Distances {

    private double NS;
    private double EW;

    public Distances(double _NS, double _EW)
    {
        NS = _NS;
        EW = _EW;
    }

    public double getNS()
    {
        return NS;
    }

    public double getEW()
    {
        return EW;
    }

    public void setEW(double EW) {
        this.EW = EW;
    }

    public void setNS(double NS) {
        this.NS = NS;
    }
}
