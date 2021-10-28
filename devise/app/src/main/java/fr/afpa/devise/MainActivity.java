package fr.afpa.devise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private Button bConvert;
    private  Button bQuitt;
    private final static String TAG ="MainActivity";
    private ArrayList<String> arrayOfKey;
    private String strDepart = null;
    private String strArrivee = null;
    // -------------------------------------
    //         Méhodes Applicatives
    // -------------------------------------
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
    public void Convert(View v) {
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
        if (doConvertir(dNbDepart, dNbArrivee,strMontant)){


            try {

                Double dbMontant = Double.valueOf(strMontant);
                Double res = Convert.convertir(dNbDepart, dNbArrivee, dbMontant);
                Intent intent = new Intent(this, ConvertirActivity.class);
                String msg = strMontant + " " + dNbDepart + " fait " + res + " " + dNbArrivee;
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

    public void ConvertAR(View v){
        EditText eMontant = (EditText) findViewById(R.id.iMontant);
        String strMontant = eMontant.getText().toString();

        Spinner nbDepart = (Spinner) findViewById(R.id.SpinnerDepart);
        Spinner nbArrivee = (Spinner) findViewById(R.id.SpinnerArrivee);
        String dNbDepart = (String) nbDepart.getSelectedItem().toString();
        String dNbArrivee = (String) nbArrivee.getSelectedItem().toString();
        // We check if fields are empty or in the wrong format
        if (doConvertir(dNbDepart, dNbArrivee,strMontant)){


            try {

                Double dbMontant = Double.valueOf(strMontant);
                Double res = Convert.convertir(dNbDepart, dNbArrivee, dbMontant);
                Intent intent = new Intent(this, ConvertirActivity.class);
                String msg = strMontant + " " + dNbDepart + " fait " + res + " " + dNbArrivee;
                intent.putExtra("msg",msg);
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

}