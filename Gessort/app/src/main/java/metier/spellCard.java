package metier;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import metier.ListOfSpellMgr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;


import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import fr.afpa.gessort.R;

public class spellCard extends AppCompatActivity {
    private TextView spellCardName;
    private TextView spellCardDescription;
    private String TAG = "spellCard";
    private Map<String,String> listOfLabel;
    private TableLayout tableCarac;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

   
    @Override
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

        Intent thisIntent = getIntent();
        int id = Objects.requireNonNull(thisIntent.getExtras()).getInt("id");

        ArrayList<Spell> listOfSpell;
        listOfSpell = ListOfSpellMgr.getListOfSpell(this);
   
        spellCardName=findViewById(R.id.name_card_spell);
        tableCarac = findViewById(R.id.listCaract);
        loadCardSpell((Spell) listOfSpell.get(id));
    }


    protected void loadCardSpell(Spell spell ){
        Map<String,String> arrayList = spell.getAllAttr();
       spellCardName.setText(spell.getName());
       for (Object key : arrayList.keySet()){

           TableRow row = new TableRow(this);
           TextView tvKey = new TextView(this);
           TextView tvValues = new TextView(this);
           TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
           tvKey.setLayoutParams(params);
           tvValues.setLayoutParams(params);
           tvValues.setSingleLine(false);
           Log.i(TAG,"key : -" + key+"-");
           row.addView(tvKey);
           if(!key.equals("Description Complète")){
               Log.i(TAG,"key2 : -" + key+"-");
                tvKey.setText( key.toString());
               tvValues.setText(arrayList.get(key));
               row.addView(tvValues);
           }else{

               TextView tvDescription = new TextView(this);
               tvDescription.setText(arrayList.get(key));
               Log.i(TAG,"else ! ");
               FrameLayout frameDescription = findViewById(R.id.frameDescription);
               frameDescription.addView(tvDescription);

           }
           tableCarac.addView(row);

       }

    }

    public void cLeave(View view){
        finish();
    }

    /*public void generateTableLayout(List listLabel, List<TextView> textViews) {

    }*/
}
