package info.infosity.Milan.generalDBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Vector;

import info.infosity.Milan.AtrSeen;
import info.infosity.Milan.Attrazioni;
import info.infosity.Milan.Global.Globals;
import info.infosity.Milan.R;
import info.infosity.Milan.generalDBHelper.*;

public class GeneralDbAdapter extends OftenUsed {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = GeneralDbAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private GeneralDatabaseHelper dbHelper;

    private Cursor cursor;

    // Database fields

    private static final String DATABASE_TABLE = "contact";

    private static final String KEY_NAME = "name";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_DISTANCESNS = "distanceNs";
    private static final String KEY_DISTANCESEW= "distanceEw";
    private static final String KEY_LAT1= "lat1";
    private static final String KEY_LAT2= "lat2";
    private static final String KEY_LAT3= "lat3";
    private static final String KEY_LAT4= "lat4";
    private static final String KEY_LAT5= "lat5";
    private static final String KEY_LAT6= "lat6";
    private static final String KEY_LAT7= "lat7";
    private static final String KEY_LAT8= "lat8";
    private static final String KEY_LAT9= "lat9";
    private static final String KEY_LAT10= "lat10";
    private static final String KEY_LAT11= "lat11";
    private static final String KEY_LAT12= "lat12";
    private static final String KEY_LONG1= "long1";
    private static final String KEY_LONG2= "long2";
    private static final String KEY_LONG3= "long3";
    private static final String KEY_LONG4= "long4";
    private static final String   KEY_LONG5= "long5";
    private static final String KEY_LONG6= "long6";
    private static final String   KEY_LONG7= "long7";
    private static final String KEY_LONG8= "long8";
    private static final String   KEY_LONG9= "long9";
    private static final String   KEY_LONG10= "long10";
    private static final String KEY_LONG11= "long11";
    private static final String  KEY_LONG12= "long12";
    private static final String KEY_TYPE= "type";
    private static final String KEY_COLLECTIONS= "collections";
    private static final String  KEY_ADDRESS= "address";
    private static final String KEY_PHONE= "phone";
    private static final String KEY_OPENING= "opening";
    private static final String KEY_CLOSED= "closed";
    private static final String KEY_PRICE= "price";
    private static final String KEY_GETHERE= "getHere";
    private static final String KEY_DESCRIPTION= "description";
    private static final String KEY_HISTORY= "history";
    private static final String KEY_VISITED= "visited";


    public GeneralDbAdapter(Context context)
    {
        this.context = context;
    }

    public GeneralDbAdapter open() throws SQLException
    {
        dbHelper = new GeneralDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(double latitude, double longitude, double distanceNs, double distanceEw, double lat1, double long1,
                                              double lat2, double long2, double lat3, double long3, double lat4, double long4, double lat5,
                                              double long5, double lat6, double long6, double lat7, double long7, double lat8, double long8,
                                              double lat9, double long9, double lat10, double long10, double lat11, double long11, double lat12,
                                              double long12, String name, String type,String  collections, String address, String phone,
                                              String opening, String closed, String price, String getHere, String description,String  history, String visited)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_LATITUDE, latitude);
        values.put(KEY_LONGITUDE, longitude);
        values.put(KEY_DISTANCESNS, distanceNs);
        values.put(KEY_DISTANCESEW, distanceEw);
        values.put(KEY_LAT1, lat1);
        values.put(KEY_LAT2, lat2);
        values.put(KEY_LAT3, lat3);
        values.put(KEY_LAT4, lat4);
        values.put(KEY_LAT5, lat5);
        values.put(KEY_LAT6, lat6);
        values.put(KEY_LAT7, lat7);
        values.put(KEY_LAT8, lat8);
        values.put(KEY_LAT9, lat9);
        values.put(KEY_LAT10, lat10);
        values.put(KEY_LAT11, lat11);
        values.put(KEY_LAT12, lat12);
        values.put(KEY_LONG1, long1);
        values.put(KEY_LONG2, long2);
        values.put(KEY_LONG3, long3);
        values.put(KEY_LONG4, long4);
        values.put(KEY_LONG5, long5);
        values.put(KEY_LONG6, long6);
        values.put(KEY_LONG7, long7);
        values.put(KEY_LONG8, long8);
        values.put(KEY_LONG9, long9);
        values.put(KEY_LONG10, long10);
        values.put(KEY_LONG11, long11);
        values.put(KEY_LONG12, long12);
        values.put(KEY_NAME, name);
        values.put(KEY_TYPE, type);
        values.put(KEY_COLLECTIONS, collections);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_PHONE, phone);
        values.put(KEY_OPENING, opening);
        values.put(KEY_CLOSED, closed);
        values.put(KEY_PRICE, price);
        values.put(KEY_GETHERE, getHere);
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_HISTORY, history);
        values.put(KEY_VISITED, visited);
        return values;
    }

    //create an attraction
    public long createContact( double latitude, double longitude, double distanceNs, double distanceEw, double lat1, double long1,
                               double lat2, double long2, double lat3, double long3, double lat4, double long4, double lat5,
                               double long5, double lat6, double long6, double lat7, double long7, double lat8, double long8,
                               double lat9, double long9, double lat10, double long10, double lat11, double long11, double lat12,
                               double long12, String name, String type,String  collections, String address, String phone,
                               String opening, String closed, String price, String getHere, String description,String  history, String visited)
    {
        Vector<String> existingNames = fetchAllContactsNames();
        if( !inVector(name,existingNames) )
        {
            ContentValues initialValues = createContentValues(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
        }
        else
        {
            return 0;
        }
    }

    //modify an attraction
    public long updateContact( double latitude, double longitude, double distanceNs, double distanceEw, double lat1, double long1,
                               double lat2, double long2, double lat3, double long3, double lat4, double long4, double lat5,
                               double long5, double lat6, double long6, double lat7, double long7, double lat8, double long8,
                               double lat9, double long9, double lat10, double long10, double lat11, double long11, double lat12,
                               double long12, String name, String type,String  collections, String address, String phone,
                               String opening, String closed, String price, String getHere, String description,String  history, String visited)
    {
        ContentValues initialValues = createContentValues(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
        //Log.wtf("name: " , "name: " + KEY_NAME + ";  VALUE = " + name);
        return database.update(DATABASE_TABLE, initialValues, "name = '" + name + "'", null);
    }

    //fetch all contacts
    private Cursor fetchAllContacts() {
        return database.query(DATABASE_TABLE, new String[]{KEY_LATITUDE, KEY_LONGITUDE,KEY_DISTANCESNS,KEY_DISTANCESEW,KEY_LAT1,KEY_LAT2, KEY_LAT3,KEY_LAT4,KEY_LAT5, KEY_LAT6, KEY_LAT7, KEY_LAT8,KEY_LAT9, KEY_LAT10, KEY_LAT11,KEY_LAT12, KEY_LONG1,KEY_LONG2, KEY_LONG3, KEY_LONG4,KEY_LONG5, KEY_LONG6, KEY_LONG7, KEY_LONG8,KEY_LONG9, KEY_LONG10, KEY_LONG11, KEY_LONG12, KEY_NAME, KEY_TYPE, KEY_COLLECTIONS,KEY_ADDRESS, KEY_PHONE, KEY_OPENING, KEY_CLOSED, KEY_PRICE, KEY_GETHERE, KEY_DESCRIPTION,KEY_HISTORY, KEY_VISITED}, null, null, null, null, null);
    }

    public Vector<String> fetchAllContactsNames()
    {
        Vector<String> risp = new Vector<String>();
        Vector<Attrazioni> toScan = fetchAllContactsByObjects();
        for(int i=0; i<toScan.size(); i++)
        {
            String name = toScan.get(i).getName();
            if( !inVector(name,risp) )
            {
                risp.add(name);
            }
        }
        return risp;
    }

    public Attrazioni getOneAttractionByName(String name)
    {
        Vector<Attrazioni> found = fetchAllContactsByObjects();
        Attrazioni risp = null;
        for(int i=0; i<found.size(); i++)
        {
            if( found.get(i).getName().toLowerCase().contains( name.toLowerCase() ) )
            {
                risp = found.get(i);
            }
        }
        return risp;
    }

    public Attrazioni getOneAttractionByType(String type)
    {
        Vector<Attrazioni> found = fetchAllContactsByObjects();
        Attrazioni risp = null;
        for(int i=0; i<found.size(); i++)
        {
            if( found.get(i).getType().toLowerCase().contains(type.toLowerCase()) )
            {
                risp = found.get(i);
            }
        }
        return risp;
    }

    public Vector<Attrazioni> fetchAllContactsByObjects()
    {
        cursor = fetchAllContacts();
        Integer num = cursor.getCount();
        //Log.wtf("count cursor", "count cursor" + String.valueOf(num));
        cursor.moveToLast();
        Vector<Attrazioni> risp = new Vector<Attrazioni>();
        for (int i=1; i<num-1; i++)
        {
            Attrazioni atr = new Attrazioni( cursor.getDouble(0), cursor.getDouble(1), cursor.getDouble(2),
                    cursor.getDouble(3), cursor.getDouble(4), cursor.getDouble(5), cursor.getDouble(6), cursor.getDouble(7),
                    cursor.getDouble(8), cursor.getDouble(9), cursor.getDouble(10), cursor.getDouble(11), cursor.getDouble(12),
                    cursor.getDouble(13), cursor.getDouble(14), cursor.getDouble(15), cursor.getDouble(16), cursor.getDouble(17),
                    cursor.getDouble(18), cursor.getDouble(19), cursor.getDouble(20), cursor.getDouble(21), cursor.getDouble(22),
                    cursor.getDouble(23), cursor.getDouble(24), cursor.getDouble(25), cursor.getDouble(26), cursor.getDouble(27),
                    cursor.getString(28), cursor.getString(29), cursor.getString(30), cursor.getString(31), cursor.getString(32),
                    cursor.getString(33), cursor.getString(34), cursor.getString(35), cursor.getString(36), cursor.getString(37),
                    cursor.getString(38), cursor.getString(39) );
            cursor.moveToPosition(num - 1 - i);
            if( !inVector(atr,risp) )
            {
                risp.add(atr);
            }
        }
        return risp;
    }
}
