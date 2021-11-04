package metier;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import modele.MaBaseSQLite;

public class ListOfSpellMgr {
    private static String TAG = "ListOfSpellMgr";
    private static ArrayList<Spell> list_of_spell;
    private SQLiteDatabase bdd;
    private MaBaseSQLite maBaseSQLite;
    static {
         list_of_spell .add(new Spell("Lumière Brulante","Envoie un rayon brulant sur un cible"));

        //new Spell("Soin Modéré","Soigne de 3d8 par niveau de lanceur de sort"),
        //new Spell("Façonnage de la pierre","Permet de façonner la pierre"),
        //new Spell("Intuition divine","donne un +10 dans une connaissance"),
        //new Spell("soin léger","soigne de 1d8"),
        //new Spell("Force du Colosse","donne +8 en force +4 en constitution")));
    }

    public void open(){
        if(bdd==null){
            bdd = maBaseSQLite.getWritableDatabase();
        }
    }

    public void close(){
        bdd.close();
    }



    public List<Spell> getListOfSpell(){
        Log.i(TAG,"getListOfSpel");
        String sql = String.format("select %s, %s from %s",MaBaseSQLite.NAME,MaBaseSQLite.SHORT_DESCRIPTION,MaBaseSQLite.TABLE_SPELL);
        list_of_spell=new ArrayList<Spell>();
        this.open();
        Cursor cursor = bdd.rawQuery(sql,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                // On doit récupérer les données inscrit dans la base puis les mettre dans une classe elle même
                //mise dans une liste
            }
        }
        return ListOfSpellMgr.list_of_spell;
    }
}
