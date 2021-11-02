package fr.afpa.devise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    private ArrayList<String> arrayOfKey
    public String strDepart = null;
    public String strArrivee = null;
    public Double dbMontant = null;
    // -------------------------------------
    //         Méhodes Applicatives
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu((ImageView)findViewById(R.id.conf));
        tMoney = findViewById(R.id.iMontant);
        bConvert = findViewById(R.id.bConvert);
        bQuitt = findViewById(R.id.bQuitt);
        Integer pos = adapter.getPostion(value)
        chargeDevises();
        charcheSpinner(R.id.SpinnerArrivee);
        charcheSpinner(R.id.SpinnerDepart);
    }
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


            try {

                this.dbMontant = Double.valueOf(strMontant);
                Double res = Convert.convertir(strDepart, strArrivee, dbMontant);
                Intent intent = new Intent(this, ConvertirActivity.class);
                String msg = strMontant + " " + strDepart + " fait " + res + " " + strArrivee;
                Log.i (TAG,msg);
                intent.putExtra("msg",msg);
               startActivity(intent);
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

    protected  void onActivityResult(int requestCode, int resultCode,Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i(TAG, "onActivityResult");
        switch (requestCode){
            case 1 :
                String retourMsg = intent.getStringExtra("msg");
                tMsg = findViewById(R.id.textRes);
                tMsg.setText(retourMsg);
            break;
            default:
                Toast.makeText(getBaseContext(), "Il semblerait qu'il y ai un soucis",
                        Toast.LENGTH_SHORT).show();
        }
    }
    public void ConvertAR(View v){
        EditText eMontant = (EditText) findViewById(R.id.iMontant);
        String strMontant = eMontant.getText().toString();

        Spinner nbDepart = (Spinner) findViewById(R.id.SpinnerDepart);
        Spinner nbArrivee = (Spinner) findViewById(R.id.SpinnerArrivee);
        this.strDepart = (String) nbDepart.getSelectedItem().toString();
        this.strArrivee = (String) nbArrivee.getSelectedItem().toString();
        // We check if fields are empty or in the wrong format
        if (doConvertir(this.strDepart, strArrivee,strMontant)){


            try {

                this.dbMontant = Double.valueOf(strMontant);
                Intent intent = new Intent(this, ConvertirAR.class);
                Log.i(TAG,"TEST" + this.dbMontant);
                intent.putExtra("dbMontant",this.dbMontant);
                intent.putExtra("strDepart",this.strDepart);
                intent.putExtra("strArrivee",this.strArrivee);
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

    protected void  chargeDevises(){
        Map<String,Double>  tableau = Convert.getConversionTable();
        this.arrayOfKey = new ArrayList<String>(tableau.keySet());

    }




    protected void charcheSpinner(Integer idSpinner){
        Spinner spinner = findViewById(idSpinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item, this.arrayOfKey );
        spinner.setAdapter(adapter);

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
            case R.id.date:
                Intent changerDate = new Intent(Settings.ACTION_DATE_SETTINGS);
                startActivity(changerDate);
            case R.id.affichage:
                Intent changerAffichage = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
                startActivity(changerAffichage);

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

