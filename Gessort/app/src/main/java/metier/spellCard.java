package metier;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import metier.ListOfSpellMgr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import fr.afpa.gessort.R;

public class spellCard extends AppCompatActivity {
    private TextView spellCardName;
    private TextView spellCardDescription;
    private String TAG = "spellCard";
    private Collection collection  = new Collection() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public Object[] toArray(@NonNull Object[] a) {
            return new Object[0];
        }

        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };
    private ArrayList<String> listOfLabel;
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

    /*public void generateTableLayout(List listLabel, List<TextView> textViews) {

    }*/
}
