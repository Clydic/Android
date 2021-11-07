package metier;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import modele.MaBaseSQLite;

public class ListOfSpellMgr {
    private static String TAG = "ListOfSpellMgr";
    private static ArrayList<Spell> listOfSpell=new ArrayList<Spell>();
    //static {
    //list_of_spell .add(new Spell("Lumière Brulante","Envoie un rayon brulant sur un cible"));

    //new Spell("Soin Modéré","Soigne de 3d8 par niveau de lanceur de sort"),
    //new Spell("Façonnage de la pierre","Permet de façonner la pierre"),
    //new Spell("Intuition divine","donne un +10 dans une connaissance"),
    //new Spell("soin léger","soigne de 1d8"),
    //new Spell("Force du Colosse","donne +8 en force +4 en constitution")));
    //}


    public static ArrayList<Spell> getListOfSpell(Context context) {
            Log.i(TAG,"initilisation");
            ListOfSpellDb maListeOfSpellDb = new ListOfSpellDb(context);
            maListeOfSpellDb.open();
        listOfSpell = maListeOfSpellDb.getAllSpell(context);

        Log.i(TAG,"ma liste : "+ listOfSpell+"");
        maListeOfSpellDb.close();
        return listOfSpell;
    }
}

