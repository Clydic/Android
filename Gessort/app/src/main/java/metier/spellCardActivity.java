package metier;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;


import fr.afpa.gessort.R;
import modele.ListOfSpellMgr;
import modele.Spell;

public class spellCardActivity extends AppCompatActivity {
    private TextView spellCardName;
    private TextView spellCardDescription;
    private String TAG = "spellCard";
    private Map<String,String> listOfLabel;
    private TableLayout tableCarac;
    int id;
    ArrayList<Spell> listOfSpell;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)



    @Override
    /**
     * Create the spellCardActivity
     */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spell_card);
        /*ListOfLabel = (ArrayList<String>) Arrays.asList("Nom",
                "Branche",
                "Registre",
                "Niveau",
                "Temps d'invocation",
               "Cible",
                "Portée",
                "Jet de sauvegarde",
                "Résistance à la magie",
                "Courte des description",
                "Description Complète");*/


        // Get the intent
        Intent thisIntent = getIntent();
        id = Objects.requireNonNull(thisIntent.getExtras()).getInt("id");

        listOfSpell = ListOfSpellMgr.getListOfSpell(this);
   
        spellCardName=findViewById(R.id.name_card_spell);
        tableCarac = findViewById(R.id.listCaract);
        loadCardSpell((Spell) listOfSpell.get(id));
    }


  /**
     * Load all labels and attributs of the Spell into a table Layout
     * @param spell
     */
    protected void loadCardSpell(Spell spell ){
        Map<String,String> arrayList = spell.getAllAttr();
       for (Object key : arrayList.keySet()){
            // Initialsiation
           TableRow row = new TableRow(this);
           TextView tvKey = new TextView(this);
           TextView tvValues = new TextView(this);
           TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);

           // Set parameters
           tvKey.setLayoutParams(params);
           tvValues.setLayoutParams(params);
           tvValues.setSingleLine(false);
           row.addView(tvKey);
           if(key.equals("Description Complète")) {
               TextView tvDescription = new TextView(this);
               // Write the complete description into the EditText
               tvDescription.setText(arrayList.get(key));
               // Put the EditText into a Frame Layout
               FrameLayout frameDescription = findViewById(R.id.frameDescription);
               frameDescription.addView(tvDescription);
           }else if(key.equals("Nom")) {
               // Set the title
               spellCardName.setText(spell.getName());
           }else if(key.equals("Courte description")){
               // Do nothing
               int nothing = 0;
           }else{
               // Set the row with label and the attribut of Spell
               tvKey.setText(key.toString());
               tvValues.setText(arrayList.get(key));
               row.addView(tvValues);


           }
           tableCarac.addView(row);

       }

    }

    /**
     * Method called by the image button btDelete which come from form.xml
     * @param view
     */
    public void delSpell(View view){
        Spell spell = listOfSpell.get(id);
        try{
            Log.i(TAG,spell.getName());
            // Call the method which will delete spell
            ListOfSpellMgr.deleteSpell(this,spell);
            // Display a message when the spell is delete
            Toast.makeText(getBaseContext(),"Le sort " + spell.getName() + " a été supprimé.",Toast.LENGTH_LONG).show();
            // Go back to MainActivity
            this.finish();



        }catch (Exception e){
            Toast.makeText(getBaseContext(),"Le sort " + spell.getName() + " n'existe pas.",Toast.LENGTH_LONG)
            .show();

        }

    }


    /**
     * Leave the activity
     * @param view
     */
    public void cLeave(View view){
        finish();
    }

}
