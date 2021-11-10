package modele;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaBaseSQLite extends SQLiteOpenHelper {
    public static final String TAG = "MaBaseSQLite";
    public static final String TABLE_SPELL = "table_spell";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SHORT_DESCRIPTION = "short_description";
    public static final String BRANCHE = "branch";
    public static final String LEVEL = "level";
    public static final String INVOCATION_TIME = "invocation_time";
    public static final String RANGE = "range";
    public static final String DURATION = "duration";
    public static final String BACKUP = "backup";
    public static final String TARGET ="target";
    public static final String MAGIC_RESISTANCE = "magic_resistance";
    public static final String COMPLETE_DESCRIPTION = "complete_description";
    public static final List<String> LISTOFCOLUMNS = (List<String>) Arrays.asList(
    MaBaseSQLite.NAME,
    MaBaseSQLite.SHORT_DESCRIPTION,
    MaBaseSQLite.BRANCHE,
    MaBaseSQLite.LEVEL,
    MaBaseSQLite.INVOCATION_TIME,
    MaBaseSQLite.RANGE,
    MaBaseSQLite.DURATION,
    MaBaseSQLite.BACKUP,
    MaBaseSQLite.TARGET,
    MaBaseSQLite.MAGIC_RESISTANCE,
    MaBaseSQLite.COMPLETE_DESCRIPTION);


    public static final String CREATE_BDD= String.format("CREATE TABLE %s (%s integer primary key " +
                    "autoincrement, %s text not null, %s text, %s text, %s text,%s text, %s text, %s text, %s text, %s text, %s text, %s text  );",
            TABLE_SPELL, ID, NAME, SHORT_DESCRIPTION, BRANCHE, LEVEL, INVOCATION_TIME,
            RANGE, DURATION, BACKUP, TARGET, MAGIC_RESISTANCE, COMPLETE_DESCRIPTION);
    // -------------------------------
    // Put a Spell into the table for test
    // -------------------------------
    public static final String INSERT_LUMIERE_BRULANTE = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) "+
                    "VALUES('Lumière Brulante','Envoie un rayon brulant sur un cible ','Soleil','4','action simple','moyenne','instantanée','aucune','rayon','oui'," +
                    "'Ce sort permet au prêtre de canaliser le pouvoir de son dieu" +
                    " et de le projeter sous forme d’un rayon de lumière brûlante jaillissant " +
                    "de la paume de sa main. Il doit réussir une attaque de contact à distance " +
                    "pour atteindre sa cible, qui subit alors 1d8 points de dégâts tous les deux niveaux " +
                    "de lanceur de sorts (jusqu’à un maximum de 5d8). Pour leur part, les morts-vivants " +
                    "se voient infliger 1d6 points de dégâts par niveau de lanceur de sorts (maximum 10d6). " +
                    "C’est encore pire pour ceux qui sont vulnérables à la lumière du jour, " +
                    "comme les vampires (1d8 points de dégâts par niveau de lanceur de sorts, " +
                    "maximum 10d8). À l’inverse, créatures artificielles et objets inanimés sont " +
                    "moins affectés (1d6 points de dégâts tous les deux niveaux de lanceur de sorts, " +
                    "maximum 5d6).');",
            TABLE_SPELL, NAME, SHORT_DESCRIPTION, BRANCHE, LEVEL, INVOCATION_TIME,
            RANGE, DURATION, BACKUP, TARGET, MAGIC_RESISTANCE, COMPLETE_DESCRIPTION);
    /////////////////////Constructor/////////////////////
    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version){
        super(context, name, factory, version);
    }

    /////////////////////Callback Method/////////////////////
    @Override
    public void onCreate(SQLiteDatabase db){

        Log.i (TAG, "Création de la base de donnée");
        db.execSQL(CREATE_BDD);

        //db.execSQL(INSERT_FACONNAGE_PIERRE);
        //db.execSQL(INSERT_FORCE_COLOSSE);
        //db.execSQL(INSERT_INTUITION_DIVINE);
        db.execSQL(INSERT_LUMIERE_BRULANTE);
        //db.execSQL(INSERT_SOIN_LEGER);
        //db.execSQL(INSERT_SOIN_MODEREE);
    }

    /**
     * Drop the table if the Data base version change
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_SPELL+";");

    }

}
