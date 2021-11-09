package metier;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        Intent thisIntent = getIntent();
        int id = Objects.requireNonNull(thisIntent.getExtras()).getInt("id");

        ArrayList<Spell> listOfSpell;
        listOfSpell = ListOfSpellMgr.getListOfSpell(this);
        /*listOfLabel =  (
                "Branche : ",
                "Niveau : ",
                "Temps d'invocation : ",
                "Durée : ",
                "Cible : ",
                "Portée : ",
                "Jet de sauvegarde : ",
                "Résistance à la magie : ",
                "Description Complète : ");*/
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
           tvValues.setText(arrayList.get(key));
           tvValues.setLayoutParams(params);
           tvValues.setSingleLine(false);
           row.addView(tvKey);
           row.addView(tvValues);
           tableCarac.addView(row);
           tvKey.setText( key.toString());

       }

    }

    public void cLeave(View view){
        finish();
    }

    /*public void generateTableLayout(List listLabel, List<TextView> textViews) {

    }*/
}
