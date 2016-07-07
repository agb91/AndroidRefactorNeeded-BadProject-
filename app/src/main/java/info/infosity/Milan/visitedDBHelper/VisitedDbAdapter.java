package info.infosity.Milan.visitedDBHelper;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class VisitedDbAdapter {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = VisitedDbAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private VisitedDatabaseHelper dbHelper;

    // Database fields

    /*
    "_id integer primary key autoincrement," +
            " name text not null, " +
            " gen text not null, " +
            " desc text not null, " +
            " tec text not null, " +
            " latitude real not null) "+
            " longitude real not null) "+
            " distance real not null);";
     */
    private static final String DATABASE_TABLE = "visited";


    public static final String KEY_NAME = "name";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_DISTANCESNS = "distanceNs";
    public static final String KEY_DISTANCESEW= "distanceEw";
    public static final String KEY_LAT1= "lat1";
    public static final String KEY_LAT2= "lat2";
    public static final String KEY_LAT3= "lat3";
    public static final String KEY_LAT4= "lat4";
    public static final String KEY_LAT5= "lat5";
    public static final String KEY_LAT6= "lat6";
    public static final String KEY_LAT7= "lat7";
    public static final String KEY_LAT8= "lat8";
    public static final String KEY_LAT9= "lat9";
    public static final String KEY_LAT10= "lat10";
    public static final String KEY_LAT11= "lat11";
    public static final String KEY_LAT12= "lat12";
    public static final String KEY_LONG1= "long1";
    public static final String KEY_LONG2= "long2";
    public static final String KEY_LONG3= "long3";
    public static final String KEY_LONG4= "long4";
    public static final String   KEY_LONG5= "long5";
    public static final String KEY_LONG6= "long6";
    public static final String   KEY_LONG7= "long7";
    public static final String KEY_LONG8= "long8";
    public static final String   KEY_LONG9= "long9";
    public static final String   KEY_LONG10= "long10";
    public static final String KEY_LONG11= "long11";
    public static final String  KEY_LONG12= "long12";
    public static final String KEY_TYPE= "type";
    public static final String KEY_COLLECTIONS= "collections";
    public static final String  KEY_ADDRESS= "address";
    public static final String KEY_PHONE= "phone";
    public static final String KEY_OPENING= "opening";
    public static final String KEY_CLOSED= "closed";
    public static final String KEY_PRICE= "price";
    public static final String KEY_GETHERE= "getHere";
    public static final String KEY_DESCRIPTION= "description";
    public static final String KEY_HISTORY= "history";

    public VisitedDbAdapter(Context context) {
        this.context = context;
    }

    public VisitedDbAdapter open() throws SQLException {
        dbHelper = new VisitedDatabaseHelper(context);
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
                                              String opening, String closed, String price, String getHere, String description,String  history)
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
        return values;
    }


    //create a contact
    public long createContact( double latitude, double longitude, double distanceNs, double distanceEw, double lat1, double long1,
                               double lat2, double long2, double lat3, double long3, double lat4, double long4, double lat5,
                               double long5, double lat6, double long6, double lat7, double long7, double lat8, double long8,
                               double lat9, double long9, double lat10, double long10, double lat11, double long11, double lat12,
                               double long12, String name, String type,String  collections, String address, String phone,
                               String opening, String closed, String price, String getHere, String description,String  history)
    {
        ContentValues initialValues = createContentValues(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //fetch all contacts
    public Cursor fetchAllContacts() {
        return database.query(DATABASE_TABLE, new String[]{KEY_LATITUDE, KEY_LONGITUDE,KEY_DISTANCESNS,KEY_DISTANCESEW,KEY_LAT1,KEY_LAT2, KEY_LAT3,KEY_LAT4,KEY_LAT5, KEY_LAT6, KEY_LAT7, KEY_LAT8,KEY_LAT9, KEY_LAT10, KEY_LAT11,KEY_LAT12, KEY_LONG1,KEY_LONG2, KEY_LONG3, KEY_LONG4,KEY_LONG5, KEY_LONG6, KEY_LONG7, KEY_LONG8,KEY_LONG9, KEY_LONG10, KEY_LONG11, KEY_LONG12, KEY_NAME, KEY_TYPE, KEY_COLLECTIONS,KEY_ADDRESS, KEY_PHONE, KEY_OPENING, KEY_CLOSED, KEY_PRICE, KEY_GETHERE, KEY_DESCRIPTION,KEY_HISTORY}, null, null, null, null, null);
    }


    //fetch contacts filter by a string
/*    public Cursor fetchContactsByFilter(String id) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[]
                {
                        KEY_CONTACTID, KEY_NAME, KEY_GEN,
                        KEY_DESC, KEY_TEC, KEY_TIME
                },
                KEY_CONTACTID + " like '%" + id + "%'", null, null, null, null, null);

        return mCursor;
    }*/
}
