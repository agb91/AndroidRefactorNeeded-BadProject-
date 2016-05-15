package info.infosity.Milan.visitedDBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class VisitedDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabaseGeberalVisited.db";
    private static final int DATABASE_VERSION = 1;

    // Lo statement SQL di creazione del database

    //(45.942907, 10.27775713,"Teatro della scala", gen12,desc12,tec12, 0.009999);
    private static final String DATABASE_CREATE = "create table visited (" +
            "_id integer primary key autoincrement," +
            " name text not null, " +
            " gen text not null, " +
            " desc text not null, " +
            " tec text not null, " +
            " time text not null); ";

    // Costruttore
    public VisitedDatabaseHelper(Context context) {
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
