package info.infosity.Milan.generalDBHelper;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import info.infosity.Milan.generalDBHelper.*;

public class GeneralDbAdapter {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = GeneralDbAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private GeneralDatabaseHelper dbHelper;

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
    private static final String DATABASE_TABLE = "contact";

    public static final String KEY_CONTACTID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_GEN = "gen";
    public static final String KEY_DESC = "desc";
    public static final String KEY_TEC = "tec";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_DISTANCE = "distance";


    public GeneralDbAdapter(Context context) {
        this.context = context;
    }

    public GeneralDbAdapter open() throws SQLException {
        dbHelper = new GeneralDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }


   /* public static final String KEY_CONTACTID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_GEN = "gen";
    public static final String KEY_DESC = "desc";
    public static final String KEY_TEC = "tec";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_DISTANCE = "distance";*/
    private ContentValues createContentValues(String name , String gen, String tec
            , String desc, double latitude, double longitude, double distance)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_GEN, gen);
        values.put(KEY_DESC, desc);
        values.put(KEY_TEC, tec);
        values.put(KEY_LATITUDE, latitude);
        values.put(KEY_LONGITUDE, longitude);
        values.put(KEY_DISTANCE, distance);
        return values;
    }

    //create a contact
    public long createContact(String name , String gen, String tec
            , String desc, double latitude, double longitude, double distance)
    {
        ContentValues initialValues = createContentValues(name, gen, tec, desc, latitude,
                longitude, distance);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

   /* //update a contact
    public boolean updateContact(long contactID, String name, String name2) {
        ContentValues updateValues = createContentValues(name,name2);
        return database.update(DATABASE_TABLE, updateValues, KEY_CONTACTID + "=" + contactID, null) > 0;
    }

    //delete a contact
    public boolean deleteContact(long contactID) {
        return database.delete(DATABASE_TABLE, KEY_CONTACTID + "=" + contactID, null) > 0;
    }*/

    //fetch all contacts
    public Cursor fetchAllContacts() {
        return database.query(DATABASE_TABLE, new String[]{KEY_CONTACTID, KEY_NAME, KEY_GEN,
            KEY_DESC, KEY_TEC, KEY_LATITUDE, KEY_LONGITUDE, KEY_DISTANCE}, null, null, null, null, null);
    }

    //fetch contacts filter by a string
    public Cursor fetchContactsByFilter(String id) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[]
                {
                        KEY_CONTACTID, KEY_NAME, KEY_GEN,
                        KEY_DESC, KEY_TEC, KEY_LATITUDE, KEY_LONGITUDE, KEY_DISTANCE
                },
                KEY_CONTACTID + " like '%" + id + "%'", null, null, null, null, null);

        return mCursor;
    }
}
