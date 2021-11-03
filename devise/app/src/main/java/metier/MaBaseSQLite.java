package metier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MaBaseSQLite extends SQLiteOpenHelper {
    public static final String TAG ="MaBaseSQLite";
    public static final String TABLE_DEVISE = "table_devise";
    public static final String ID = "id";
    public static final String COL_DEVISE = "devise";
    public static final String COL_TAUX = "taux_convertion";

    public static final String CREATE_BDD = "CREATE TABLE " + TABLE_DEVISE +

            "("+ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_DEVISE + " TEXT  NOT NULL,"
            +COL_TAUX + " REAL);";

    // Requêtes de peuplement
    public static final String INSERT_LIVRE =
            "INSERT INTO " + TABLE_DEVISE + " (" + COL_DEVISE + "," + COL_TAUX + ") VALUES ('Livre',0.6405);";
    public static final String INSERT_EURO =
            "INSERT INTO " + TABLE_DEVISE + " (" + COL_DEVISE + "," + COL_TAUX + ") VALUES ('Euro',0.7697);";
    public static final String INSERT_DIRHAM =
            "INSERT INTO " + TABLE_DEVISE + " (" + COL_DEVISE + "," + COL_TAUX + ") VALUES ('Dirham',8.5656);";
    public static final String INSERT_YEN =
            "INSERT INTO " + TABLE_DEVISE + " (" + COL_DEVISE + "," + COL_TAUX + ") VALUES ('Yen',76.6908);";
    public static final String INSERT_FRANC_CFA =
            "INSERT INTO " + TABLE_DEVISE + " (" + COL_DEVISE + "," + COL_TAUX + ") VALUES ('Franc CFA',503.17);";
    public static final String INSERT_DOLLARS_US =
            "INSERT INTO " + TABLE_DEVISE + " (" + COL_DEVISE + "," + COL_TAUX + ") VALUES ('Dollars US',1);";
    public static final String INSERT_FRANC =
            "INSERT INTO " + TABLE_DEVISE + " (" + COL_DEVISE + "," + COL_TAUX + ") VALUES ('Franc',5.049);";

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
        // Peuplement de la base
        Log.i(TAG, "Peuplement de la base");
        db.execSQL(INSERT_LIVRE);
        db.execSQL(INSERT_EURO);
        db.execSQL(INSERT_DIRHAM);
        db.execSQL(INSERT_YEN);
        db.execSQL(INSERT_FRANC_CFA);
        db.execSQL(INSERT_DOLLARS_US);
        db.execSQL(INSERT_FRANC);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_DEVISE+";");

    }








}
