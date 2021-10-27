package fr.afpa.devise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import metier.Convert;

public class MainActivity extends AppCompatActivity {
    private Spinner sPinnerDepart;
    private Spinner sPinnerArrived;
    private EditText tMoney;
    private Button bConvert;
    private  Button bQuitt;
    private final static String TAG ="MainActivity";
    private ArrayList<String> arrayOfKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tMoney = findViewById(R.id.iMontant);
        bConvert = findViewById(R.id.bConvert);
        bQuitt = findViewById(R.id.bQuitt);
        chargeDevises();
        charcheSpinner(R.id.SpinnerArrivee);
        charcheSpinner(R.id.SpinnerDepart);
    }
    public void onConvert(View v){
        // Log.i(TAG , "onConvert");
        //Toast.makeText(getBaseContext(),"onConvert", Toast.LENGTH_SHORT).show();

        // We initiate variables
        EditText eMontant = (EditText) findViewById(R.id.iMontant);
        String strMontant = eMontant.getText().toString();

        Spinner nbDepart = (Spinner) findViewById(R.id.SpinnerDepart);
        Spinner nbArrivee = (Spinner) findViewById(R.id.SpinnerArrivee);
        String dNbDepart = (String) nbDepart.getSelectedItem().toString();
        String dNbArrivee = (String) nbArrivee.getSelectedItem().toString();
        // We check if fields are empty or in the wrong format
        if(strMontant.equals("") || strMontant.matches("[^0-9]")|| dNbDepart.equals("")
        || dNbArrivee.equals(""))
        {
            Toast.makeText(getBaseContext(), "Des champs sont vides et doivent être renseignées",
                    Toast.LENGTH_LONG).show();
        // We check if currency of start and end are not the same
        }else if (dNbDepart.equals(dNbArrivee))
        {
            Toast.makeText(getBaseContext(), "Les devises sont identique",
                    Toast.LENGTH_LONG).show();

        } else
            // We put a try control to check if the field is a Number
        {   try {

            Double dbMontant = Double.valueOf(strMontant);
            Double res = Convert.convertir(dNbDepart,dNbArrivee,dbMontant);
            // Calculate the number given in the tatget currency
            String msg = strMontant + " " + dNbDepart + " fait " + res + " " + dNbArrivee;
            Toast.makeText(getBaseContext(), msg,
                    Toast.LENGTH_LONG).show();
        }catch (NumberFormatException e){
            Toast.makeText(getBaseContext(), "Vous devez enter un Nombre ",
                    Toast.LENGTH_LONG).show();
        }

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

    public void onQuitt(View v){
        Log.i(TAG , "onQuitt");
        Toast.makeText(getBaseContext(),"onQuitt", Toast.LENGTH_SHORT).show();
        System.exit(0);
    }
}