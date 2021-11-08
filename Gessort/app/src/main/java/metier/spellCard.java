package metier;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import metier.ListOfSpellMgr;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fr.afpa.gessort.R;

public class spellCard extends AppCompatActivity {
    private TextView spellCardName;
    private TextView spellCardDescription;
    private String TAG = "spellCard";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spell_card);
        Intent thisIntent = getIntent();
        Integer id = thisIntent.getExtras().getInt("id");
        ArrayList listOfSpell= ListOfSpellMgr.getListOfSpell(this);
        spellCardName=findViewById(R.id.name_card_spell);
        spellCardDescription=findViewById(R.id.description_card_spell);
        loadCardSpell((Spell) listOfSpell.get(id));

    }


    protected void loadCardSpell(Spell spell){
       spellCardName.setText(spell.getName());
       spellCardDescription.setText(spell.getShortDescription());
    }

    public void cLeave(View view){
        finish();
    }

}
