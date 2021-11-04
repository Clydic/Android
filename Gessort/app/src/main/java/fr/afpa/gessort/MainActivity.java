package fr.afpa.gessort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import metier.ListOfSpellMgr;
import metier.Spell;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="Main Activity" ;
    private TextView title;
    private ListView listViewOfSpell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu((ImageView)findViewById(R.id.imageMenu));
        title= findViewById(R.id.Title);
        listViewOfSpell = findViewById(R.id.list_of_sort);
        loadListOfSpell(ListOfSpellMgr.getListOfSpell(),listViewOfSpell);
        title.setVisibility(View.VISIBLE);
        String CREATE_BDD= String.format("CREATE TABLE %s (%s2 integer primary key " +
                "autoincrement, %s text not null, %s text not null",TAG,"ID","NAME","SHORT_DESCRIPTION");
        Log.i(TAG , CREATE_BDD);
    }

    public void loadListOfSpell(Spell[] list_to_load, ListView list_view){
        ArrayAdapter<Spell> arrayAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list_to_load);

        list_view.setAdapter(arrayAdapter);

    }

    public void onQuitt(View v){

        Log.i(TAG , "onQuitt");
        Toast.makeText(getBaseContext(),"onQuitt", Toast.LENGTH_SHORT).show();
        System.exit(0);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        // Instanciation du menu XML spécifier en un objet  Menu
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.addspell:

                return true;

            case R.id.setting:
                return true;

            case R.id.quit:
                View vItemQ = (View) item.getActionView();
                onQuitt(vItemQ);
                return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v , ContextMenu.ContextMenuInfo menuInfo)

    {
        super.onCreateContextMenu(menu,v , menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        return onOptionsItemSelected(item);
    }
}