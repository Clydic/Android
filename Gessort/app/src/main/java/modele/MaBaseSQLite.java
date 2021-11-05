package modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MaBaseSQLite extends SQLiteOpenHelper {
    public static final String TAG = "MaBaseSQLite";
    public static final String TABLE_SPELL = "table_spell";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SHORT_DESCRIPTION = "short_description";

    public static final String CREATE_BDD= String.format("CREATE TABLE %s (%s integer primary key " +
            "autoincrement, %s text not null, %s text not null);",TABLE_SPELL,ID,NAME,SHORT_DESCRIPTION);
    // -------------------------------
    // We fill the table for test
    // -------------------------------
    public static final String INSERT_LUMIERE_BRULANTE = String.format("INSERT INTO %s (%s,%s) VALUES('Lumière Brulante','Envoie un rayon brulant sur un cible ');",TABLE_SPELL,
    NAME,SHORT_DESCRIPTION);
    public static final String INSERT_FACONNAGE_PIERRE = String.format("INSERT INTO %s (%s,%s) VALUES('Façonnage de la pierre', 'Façonne la pierre selon ses envies');",TABLE_SPELL,
            NAME,SHORT_DESCRIPTION);
    public static final String INSERT_SOIN_MODEREE = String.format("INSERT INTO %s (%s,%s) VALUES('Soin moderee','Soigne 3d8 plus niveau du lanceur');",TABLE_SPELL,
            NAME,SHORT_DESCRIPTION);
    public static final String INSERT_FORCE_COLOSSE  = String.format("INSERT INTO %s (%s,%s) VALUES('Force du Colosse','Augmente de la Force de 8 et la Con de 4');",TABLE_SPELL,
            NAME,SHORT_DESCRIPTION);
    public static final String INSERT_INTUITION_DIVINE = String.format("INSERT INTO %s (%s,%s) VALUES('Intuition Divine','Ajoute un BONUS de 10 à un test de connaissance');",TABLE_SPELL,
            NAME,SHORT_DESCRIPTION);
    public static final String INSERT_SOIN_LEGER = String.format("INSERT INTO %s (%s,%s) VALUES('Soin léger','Soigne 1d8 plus niveau du lanceur');",TABLE_SPELL,
            NAME,SHORT_DESCRIPTION);

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        Log.i (TAG, "Création de la base de donnée");
        db.execSQL(CREATE_BDD);

        Log.i (TAG, "Peuplement de la base");
        db.execSQL(INSERT_FACONNAGE_PIERRE);
        db.execSQL(INSERT_FORCE_COLOSSE);
        db.execSQL(INSERT_INTUITION_DIVINE);
        db.execSQL(INSERT_LUMIERE_BRULANTE);
        db.execSQL(INSERT_SOIN_LEGER);
        db.execSQL(INSERT_SOIN_MODEREE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_SPELL+";");

    }
}
