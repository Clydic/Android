package modele;

import android.annotation.SuppressLint;
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
    public static final String BRANCHE = "branch";
    public static final String LEVEL = "level";
    public static final String INVOCATION_TIME = "invocation_time";
    public static final String RANGE = "range";
    public static final String DURATION = "duration";
    public static final String BACKUP = "backup";
    public static final String TARGET ="target";
    public static final String MAGIC_RESISTANCE = "magic_resistance";
    public static final String COMPLETE_DESCRIPTION = "complete_description";

    public static final String CREATE_BDD= String.format("CREATE TABLE %s (%s integer primary key " +
            "autoincrement, %s text not null, %s text, %s text, %s text,%s text, %s text, %s text, %s text, %s text, %s text, %s text  );",
            TABLE_SPELL, ID, NAME, SHORT_DESCRIPTION, BRANCHE, LEVEL, INVOCATION_TIME,
            RANGE, DURATION, BACKUP, TARGET, MAGIC_RESISTANCE, COMPLETE_DESCRIPTION);
    // -------------------------------
    // We fill the table for test
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
    /*public static final String INSERT_FACONNAGE_PIERRE = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)" +
                    " VALUES('Façonnage de la pierre', 'Façonne la pierre selon ses envies','Transmutation(terre)'," +
                    "'3',' action simple','contact','instantannée','aucun','pierre ou objet touché, " +
                    "dans la limite de 0.3m3 plus  30dm3/niveau','non'," +
                    "'Ce sort permet d’altérer une masse rocheuse existante pour lui donner la forme choisie par le personnage. " +
                    "Il est ainsi possible de créer une arme en pierre, une trappe, ou encore une idole aux contours grossiers. " +
                    "Façonnage de la pierre permet également d’ouvrir une issue là où il n’y en a pas, en taillant une porte à même la pierre, " +
                    "ou encore de bloquer une porte existante en modifiant ses dimensions (si elle est en pierre) ou en déformant son chambranle. " +
                    "Le personnage peut créer des objets (coffres, etc.), mais ceux-ci restent nécessairement grossiers ; il est impossible de les fignoler. " +
                    "Si l’objet est doté de pièces plus ou moins mobiles, il y a 30 % de chances pour qu’il ne fonctionne pas.');",
            TABLE_SPELL, NAME,SHORT_DESCRIPTION,BRANCHE, LEVEL, INVOCATION_TIME,
            RANGE, DURATION, BACKUP, TARGET, MAGIC_RESISTANCE, COMPLETE_DESCRIPTION);*/
    //public static final String INSERT_SOIN_MODEREE = String.format("INSERT INTO %s (%s,%s) " +
    //                "VALUES('Soin moderee','Soigne 3d8 plus niveau du lanceur');",TABLE_SPELL,
    //        NAME,SHORT_DESCRIPTION);
    //public static final String INSERT_FORCE_COLOSSE  = String.format("INSERT INTO %s (%s,%s) VALUES('Force du Colosse','Augmente de la Force de 8 et la Con de 4');",TABLE_SPELL,
           // NAME,SHORT_DESCRIPTION);
    //public static final String INSERT_INTUITION_DIVINE = String.format("INSERT INTO %s (%s,%s) VALUES('Intuition Divine','Ajoute un BONUS de 10 à un test de connaissance');",TABLE_SPELL,
    //        NAME,SHORT_DESCRIPTION);
    //public static final String INSERT_SOIN_LEGER = String.format("INSERT INTO %s (%s,%s) VALUES('Soin léger','Soigne 1d8 plus niveau du lanceur');",TABLE_SPELL,
    //        NAME,SHORT_DESCRIPTION);

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        Log.i (TAG, "Création de la base de donnée");
        db.execSQL(CREATE_BDD);

        Log.i (TAG, "Peuplement de la base");
        //db.execSQL(INSERT_FACONNAGE_PIERRE);
        //db.execSQL(INSERT_FORCE_COLOSSE);
        //db.execSQL(INSERT_INTUITION_DIVINE);
        db.execSQL(INSERT_LUMIERE_BRULANTE);
        //db.execSQL(INSERT_SOIN_LEGER);
        //db.execSQL(INSERT_SOIN_MODEREE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_SPELL+";");

    }
}
