package info.infosity.Milan.Global;

import android.content.Context;
import android.util.Log;

import java.util.Vector;

import info.infosity.Milan.Attrazioni;
import info.infosity.Milan.generalDBHelper.GodOfDb;

/**
 * Created by andrea on 24/04/16.
 */
public class Globals {

    private static GodOfDb god;
    private static Context context;
    private static Vector<Attrazioni> serie = new Vector<Attrazioni>();

    public static void create(Context _context)
    {
        context = _context;
        god = new GodOfDb(context);
        serie = god.getSerie(context);
    }

    public static Vector<Attrazioni> getSerie()
    {
        return serie;
    }

}
