package fr.afpa.gessort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import metier.CustomListAdapter;
import metier.FormActivity;
import metier.ListOfSpellMgr;
import metier.Spell;
import metier.spellCardActivity;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="Main Activity" ;
    private TextView title;
    private ListView listViewOfSpell;
    private CustomListAdapter clAdapter;
    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu((ImageView)findViewById(R.id.imageMenu));
        title= findViewById(R.id.Title);
        listViewOfSpell = findViewById(R.id.list_of_sort);
        //Log.i(TAG,ListOfSpellMgr.getListOfSpell(this).get(0)+"");

        Log.i(TAG,"count : " + listViewOfSpell.getCount());
        if(listViewOfSpell.getCount()==0){

            ListOfSpellMgr.clearListOfSpell();
            Log.i(TAG,"count : " + listViewOfSpell.getCount());
            loadListOfSpell(ListOfSpellMgr.getListOfSpell(this),listViewOfSpell);
        }




    }

    public void onResume() {
        super.onResume();
        Log.i("TAG","onResumeMainactivity");
        this.clAdapter.notifyDataSetChanged(); // Recharge l'adapter
        ListOfSpellMgr.clearListOfSpell();
        loadListOfSpell(ListOfSpellMgr.getListOfSpell(this),listViewOfSpell);

    }

    public void onQuitt(View v){

        Log.i(TAG , "onQuitt");
        Toast.makeText(getBaseContext(),"onQuitt", LENGTH_SHORT).show();
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

                View vItem = (View) item.getActionView();
                addSpell(vItem);
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


    public void loadListOfSpell(ArrayList<Spell> list_to_load, ListView list_view){
        this.clAdapter = new CustomListAdapter(this,list_to_load);
        final Intent intent = new Intent(this, spellCardActivity.class);
        list_view.setAdapter(this.clAdapter);
        list_view.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, " pos : "+ position+"");
                Object object = listViewOfSpell.getItemAtPosition(position);
                Spell spell =(Spell) object;
                intent.putExtra("id",position);
                Toast.makeText(MainActivity.this,"Selected : " + "" + spell , LENGTH_SHORT).show();
                //intent.putExtra("spellName",spell.getName());
                //intent.putExtra("spellDescription",spell.getShortDescription());
                startActivity(intent);

                //findViewById(R.id.textofshortdescr).setVisibility(View.VISIBLE);
            }
        }));

    }

    public void addSpell(View v){
        final Intent intentAdd = new Intent(this, FormActivity.class);
        startActivity(intentAdd);

    }

}