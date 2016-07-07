package info.infosity.Milan.generalDBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class GeneralDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabaseGeberal.db";
    private static final int DATABASE_VERSION = 1;

    // Lo statement SQL di creazione del database

    //(45.942907, 10.27775713,"Teatro della scala", gen12,desc12,tec12, 0.009999);
    private static final String DATABASE_CREATE = "create table contact (" +
            "latitude real not null," +
            "longitude real not null," +
            "distanceNs real not null, " +
            "distanceEw real not null," +
            "lat1 real not null, " +
            "long1 real not null, " +
            "lat2 real not null, " +
            "long2 real not null, " +
            "lat3 real not null, " +
            "long3 real not null, " +
            "lat4 real not null, " +
            "long4 real not null, " +
            "lat5 real not null, " +
            "long5 real not null, " +
            "lat6 real not null, " +
            "long6 real not null, " +
            "lat7 real not null, " +
            "long7 real not null, " +
            "lat8 real not null, " +
            "long8 real not null, " +
            "lat9 real not null, " +
            "long9 real not null, " +
            "lat10 real not null, " +
            "long10 real not null, " +
            "lat11 real not null, " +
            "long11 real not null," +
            "lat12 real not null, " +
            "long12 real not null, " +
            "name text not null, " +
            "type text not null, " +
            "collections text not null," +
            "address text not null," +
            "phone text not null," +
            "opening text not null, " +
            "closed text not null, " +
            "price text not null, " +
            "getHere text not null, " +
            "description text not null, " +
            "history text not null);";

    // Costruttore
    public GeneralDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Questo metodo viene chiamato durante la creazione del database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Questo metodo viene chiamato durante l'upgrade del database, ad esempio quando viene incrementato il numero di versione
    @Override
    public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {

        database.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(database);

    }
}
