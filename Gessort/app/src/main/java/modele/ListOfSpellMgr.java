package modele;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

import modele.ListOfSpellDb;
import modele.Spell;

public class ListOfSpellMgr {
    private static String TAG = "ListOfSpellMgr";
    private static ArrayList<Spell> listOfSpell=new ArrayList<Spell>();

    /**
     * Get all Spell and put them into an Arraylist
     * @param context
     * @return ArrayList<Spell>
     */
    public static ArrayList<Spell> getListOfSpell(Context context) {
            Log.i(TAG,"initilisation");
            ListOfSpellDb maListeOfSpellDb = new ListOfSpellDb(context);
            maListeOfSpellDb.open();
        listOfSpell = maListeOfSpellDb.getAllSpell(context);

        Log.i(TAG,"ma liste : "+ listOfSpell+"");
        maListeOfSpellDb.close();
        return listOfSpell;
    }

    /**
     * Clear the list of Spell
     */
    public static void clearListOfSpell(){
        listOfSpell.clear();
    }


    /**
     * Get values from FormActivity and call insert method from ListOfSpellDB
     * @param context
     * @param list_to_load
     * @return
     */
    public static boolean insertSpell(Context context, ArrayList<String> list_to_load){
        ListOfSpellDb maListeOfSpellDb = new ListOfSpellDb(context);
        Spell spell = new Spell(list_to_load);
        // Open the data base
        maListeOfSpellDb.open();
        if(!maListeOfSpellDb.getAllSpell(context).contains(spell.getName())){
            Log.i(TAG,"On insère le sort " + spell.getName());
            // Call the insert method of maListeOfSpellDb
            maListeOfSpellDb.insertSpell(list_to_load);
            // Close the Data base

            maListeOfSpellDb.close();
            return true;
        }
        Log.i(TAG,"Le sort existe déjà");

        // Close the data base

        maListeOfSpellDb.close();
        return false;
    }

    /**
     * Method wich control if the spell exist and then call deleteSpell from ListOfSpellDb class
     * return true if the spell is deleted, false if it doesn't exists
     * @param context
     * @param spell_to_delete
     * @return boolean
     */
    public static boolean deleteSpell(Context context, Spell spell_to_delete){

        ListOfSpellDb maListeOfSpellDb = new ListOfSpellDb(context);
        //if (!maListeOfSpellDb.getAllSpell(context).contains(spell_to_delete.getName())){
            Log.i(TAG,"Fonction deleteSpell");
            maListeOfSpellDb.deleteSpell(spell_to_delete.getName());
            return true;
        //}
        //return false;

    }
}


