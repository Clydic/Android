package metier;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import modele.MaBaseSQLite;

public class ListOfSpellDb {
    private String TAG = "LisOfSpellDb";
    private static Integer DB_VERSION = 2;
    private static String BDD_NAME = "gessort-db";
    public static final String TABLE_SPELL = "table_spell";
    private static ArrayList<Spell> list_of_spell=new ArrayList<Spell>();
    private SQLiteDatabase bdd;
    private MaBaseSQLite maBaseSQLite;

    public ListOfSpellDb (Context context){
        maBaseSQLite = new MaBaseSQLite(context, BDD_NAME,null, DB_VERSION);
        //Log.i(TAG,this.getAllSpell(context)+"");
    }
    public  void open(){
        if(bdd==null){
            bdd = maBaseSQLite.getWritableDatabase();
        }
    }

    public void close(){
        bdd.close();
    }



    public ArrayList<Spell> getAllSpell(Context context){
        Log.i(TAG,"getListOfSpell");
        String sql = String.format("select %s, %s, %s,%s,%s,%s,%s,%s,%s,%s,%s from %s",
                MaBaseSQLite.NAME,
                MaBaseSQLite.SHORT_DESCRIPTION,
                MaBaseSQLite.BRANCHE,
                MaBaseSQLite.LEVEL,
                MaBaseSQLite.INVOCATION_TIME,
                MaBaseSQLite.DURATION,
                MaBaseSQLite.TARGET,
                MaBaseSQLite.RANGE,
                MaBaseSQLite.BACKUP,
                MaBaseSQLite.MAGIC_RESISTANCE,
                MaBaseSQLite.COMPLETE_DESCRIPTION,
                MaBaseSQLite.TABLE_SPELL
        );
        Log.i(TAG,sql);
        //this.open();
        Cursor cursor = bdd.rawQuery(sql,null);
        ArrayList<Spell> list_of_spell = new ArrayList<Spell>();
        if(cursor != null){
            if(cursor.moveToFirst()){
                // On doit récupérer les données inscrit dans la base puis les mettre dans une classe elle même
                //mise dans une liste
                do{
                    String name = cursor.getString(0);
                    String shortDescription = cursor.getString(1);
                    String branch = cursor.getString(2);
                    String level = cursor.getString(3);
                    String invocationTime = cursor.getString(4);
                    String duration = cursor.getString(5);
                    String target = cursor.getString(6);
                    String range = cursor.getString(7);
                    String backup = cursor.getString(8);
                    String magicResistance= cursor.getString(9);
                    String completeDescription = cursor.getString(10);

                    Spell spell =new Spell(name,shortDescription,branch,level,invocationTime,duration,target,range,backup,magicResistance,completeDescription);
                    this.list_of_spell.add(spell);

                }while (cursor.moveToNext());
            }
        }
        //this.close();
        //Log.i(TAG,list_of_spell+"");
        return this.list_of_spell;
    }
}

