package fr.afpa.devise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Map;

import metier.Convert;

public class MainActivity extends AppCompatActivity {
    // -------------------------------------
    //                Attribut
    // -------------------------------------
    private Spinner sPinnerDepart;
    private Spinner sPinnerArrived;
    private EditText tMoney;
    private TextView tMsg;
    private Button bConvert;
    private  Button bQuitt;
    private final static String TAG ="MainActivity";
    private ArrayList<String> arrayOfKey;
    public final static String DEVISE_DEPART="deviseDepart";
    public final static String DEVISE_ARRIVE="deviseArrive";
    public final static  String PREFRENCE_FILE="preference_files";
    public final static  String MONTANT="montant";
    public String strDepart = null;
    public String strArrivee = null;
    public Double dbMontant = null;
    // -------------------------------------
    //         Méhodes Applicatives
    // -------------------------------------
    @Override
    // Initialise the class
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu((ImageView)findViewById(R.id.conf));
        tMoney = findViewById(R.id.iMontant);
        bConvert = findViewById(R.id.bConvert);
        bQuitt = findViewById(R.id.bQuitt);
        SharedPreferences mesPrefs = getSharedPreferences(PREFRENCE_FILE,MODE_PRIVATE);

        this.strDepart = mesPrefs.getString(DEVISE_DEPART,"");
        this.strArrivee = mesPrefs.getString(DEVISE_ARRIVE,"");
        chargeDevises();
        charcheSpinner(R.id.SpinnerArrivee,strArrivee);
        charcheSpinner(R.id.SpinnerDepart,strDepart);
    }
   //Méthod which make the convertion and transfert the result in an other page
    public void Convert(View v) {
        // Log.i(TAG , "onConvert");
        //Toast.makeText(getBaseContext(),"onConvert", Toast.LENGTH_SHORT).show();

        // We initiate variables
        this.tMoney = (EditText) findViewById(R.id.iMontant);
        String strMontant = this.tMoney.getText().toString();

        this.sPinnerDepart = (Spinner) findViewById(R.id.SpinnerDepart);
        this.sPinnerArrived = (Spinner) findViewById(R.id.SpinnerArrivee);
        this.strDepart = (String) this.sPinnerDepart.getSelectedItem().toString();
        this.strArrivee = (String)this.sPinnerArrived.getSelectedItem().toString();
        // We check if fields are empty or has a  the wrong format
        if (doConvertir(this.strDepart, this.strArrivee,strMontant)){

            // We test if dbMontant is a double
            try {

                this.dbMontant = Double.valueOf(strMontant);
                Double res = Convert.convertir(strDepart, strArrivee, dbMontant);
                Intent intent = new Intent(this, ConvertirActivity.class);
                // We make the message wich will be displayed in the other Activity
                String msg = strMontant + " " + strDepart + " fait " + res + " " + strArrivee;
                Log.i (TAG,msg);
                // We put the msg in the intent now it is ready to be sent
                intent.putExtra("msg",msg);
                // We start the activity where the message will be displayed
               startActivity(intent);
               // If dbMontant is not a double catch the exception and display an error message
            } catch (NumberFormatException e) {
                Toast.makeText(getBaseContext(), "Vous devez enter un Nombre ",
                        Toast.LENGTH_LONG).show();
            }
            // Display a message if doConvertir return false
        }else{
            Toast.makeText(getBaseContext(), "Des champs sont vides, vous devez les remplir",
                    Toast.LENGTH_LONG).show();

        }
    }

    // Get a code and compare it with an  excepted code of an intent
    protected  void onActivityResult(int requestCode, int resultCode,Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i(TAG, "onActivityResult");
        switch (requestCode){
            case 1 :
                // Get the message from an activity.
                String retourMsg = intent.getStringExtra("msg");
                tMsg = findViewById(R.id.textRes);
                // Write the message in a TextView
                tMsg.setText(retourMsg);
            break;
            default:
                // Write a messege if the code given  and the excepted one don't match
                Toast.makeText(getBaseContext(), "Il semblerait qu'il y ai un soucis",
                        Toast.LENGTH_SHORT).show();
        }
    }


    // Convert a number from a currency to an other but the result display in the same view
    public void ConvertAR(View v){
        // Initialisation
        EditText eMontant = (EditText) findViewById(R.id.iMontant);
        String strMontant = eMontant.getText().toString();

        Spinner nbDepart = (Spinner) findViewById(R.id.SpinnerDepart);
        Spinner nbArrivee = (Spinner) findViewById(R.id.SpinnerArrivee);
        this.strDepart = (String) nbDepart.getSelectedItem().toString();
        this.strArrivee = (String) nbArrivee.getSelectedItem().toString();
        // We check if fields are empty or in the wrong format
        if (doConvertir(this.strDepart, strArrivee,strMontant)){


            try {

                this.dbMontant = Double.valueOf(strMontant);// Change the type of strMontant
                Intent intent = new Intent(this, ConvertirAR.class);

                //Put the values which will be used in the convertion into the intent
                intent.putExtra("dbMontant",this.dbMontant);
                intent.putExtra("strDepart",this.strDepart);
                intent.putExtra("strArrivee",this.strArrivee);


            //Prepare the file of user's preferences
                SharedPreferences MesPrefs = getSharedPreferences(PREFRENCE_FILE, MODE_PRIVATE);
                //SharedPreferences activityPrefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor edtPrefsApp=MesPrefs.edit();
                //SharedPreferences.Editor edtPrefsActivity=activityPrefs.edit();
            //Put keys and values into the preferences file.
                edtPrefsApp.putString(MainActivity.DEVISE_DEPART,strDepart);
                edtPrefsApp.putString(MainActivity.DEVISE_ARRIVE,strArrivee);
                //edtPrefsApp.putFloat(MainActivity.MONTANT,new Float(this.dbMontant));
                //edtPrefsActivity.putFloat(MainActivity.MONTANT,new Float(this.dbMontant));
                //edtPrefsActivity.apply();
            // Aplly changes
                edtPrefsApp.apply();

                startActivityForResult(intent,1);
                // Calculate the number given in the target currency
            } catch (NumberFormatException e) {
                Toast.makeText(getBaseContext(), "Vous devez enter un Nombre ",
                        Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getBaseContext(), "Des champs sont vides, vous devez les remplir",
                    Toast.LENGTH_LONG).show();

        }

    }




    // Load currencies in an array
    protected void  chargeDevises(){
        Map<String,Double>  tableau = Convert.getConversionTable();
        this.arrayOfKey = new ArrayList<String>(tableau.keySet());

    }




    // Load the array of currencies into a Spinner given in parameter
    /**
     *
     * @param idSpinner
     * @param devise
     * */
    protected void charcheSpinner(Integer idSpinner, String devise){
        Spinner spinner = findViewById(idSpinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item, this.arrayOfKey );
        int pos = adapter.getPosition(devise);
        spinner.setAdapter(adapter);
        if(pos<0 || pos>spinner.getCount()) pos=0;
        spinner.setSelection(pos);

    }

    protected boolean doConvertir(String strCurencySart, String strCurrencyTarget, String montant){

        if(montant.equals("") || montant.matches("[^0-9]")|| strCurencySart.equals("")
                ||strCurrencyTarget.equals(""))
        {
            return false;
        }else if (strCurencySart.equals(strCurrencyTarget))
        {
            Toast.makeText(getBaseContext(), "Il doit y avoir deux devises différentes",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
        }

    public void onQuitt(View v){
        Log.i(TAG , "onQuitt");
        Toast.makeText(getBaseContext(),"onQuitt", Toast.LENGTH_SHORT).show();
        System.exit(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        // Instanciation du menu XML spécifier en un objet  Menu
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.convertir:
                View vItem = (View) item.getActionView();

                ConvertAR(vItem);
                return true;
            case R.id.parametre:
                return true;

            case R.id.quitter:
                View vItemQ = (View) item.getActionView();
                onQuitt(vItemQ);
                return true;

            case R.id.langue:
                Intent changerLangue = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(changerLangue);
                return true;

            case R.id.date:
                Intent changerDate = new Intent(Settings.ACTION_DATE_SETTINGS);
                startActivity(changerDate);
                return true;
            case R.id.affichage:
                Intent changerAffichage = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
                startActivity(changerAffichage);
                return true;

        }
        return false;
    }



    public void onCreateContextMenu(ContextMenu menu, View v , ContextMenu.ContextMenuInfo menuInfo)

    {
        super.onCreateContextMenu(menu,v , menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        return onOptionsItemSelected(item);
    }
}

