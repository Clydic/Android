package fr.afpa.devise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner sPinnerDepart;
    private Spinner sPinnerArrived;
    private EditText tMoney;
    private Button bConvert;
    private  Button bQuitt;
    private final static String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sPinnerDepart=findViewById(R.id.SpinnerDepart);
        sPinnerArrived=findViewById(R.id.SpinnerArrivee);
        tMoney = findViewById(R.id.iMontant);
        bConvert = findViewById(R.id.bConvert);
        bQuitt = findViewById(R.id.bQuitt);


    }
    public void onConvert(View v){
        // Log.i(TAG , "onConvert");
        Toast.makeText(getBaseContext(),"onConvert", Toast.LENGTH_SHORT).show();
        EditText eMontant = (EditText) findViewById(R.id.iMontant);
        String strMontant = eMontant.getText().toString();
        Double dbMontant = Double.valueOf(strMontant);
        Spinner nbDepart = (Spinner) findViewById(R.id.SpinnerDepart);
        Spinner nbArrivee = (Spinner) findViewById(R.id.SpinnerArrivee);
        String dNbDepart = (String) nbDepart.getSelectedItem().toString();
        String dNbArrivee = (String) nbArrivee.getSelectedItem().toString();
        if (dNbDepart == dNbArrivee)
        {
            Toast.makeText(getBaseContext(), "Les devises sont identique",
                    Toast.LENGTH_LONG).show();

        }else if(eMontant.equals(null)==false || dNbArrivee.equals(null)==false || dNbDepart.equals(null)==false)
        {
            Toast.makeText(getBaseContext(), "Les champs sont vides",
                    Toast.LENGTH_LONG).show();
        }else
        {
            Log.i(TAG, "Tout va bien");
        }





    }
    public void onQuitt(View v){
        Log.i(TAG , "onQuitt");
        Toast.makeText(getBaseContext(),"onQuitt", Toast.LENGTH_SHORT).show();
    }
}