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
        Log.i(TAG , "onConvert");
        Toast.makeText(getBaseContext(),"onConvert", Toast.LENGTH_SHORT).show();
        EditText count = (EditText) findViewById(R.id.iMontant);
        Spinner nbDepart = (Spinner) findViewById(R.id.SpinnerDepart);
        Spinner nbArrivee = (Spinner) findViewById(R.id.SpinnerArrivee);
        nbDepart.getSelectedItem();
    }
    public void onQuitt(View v){
        Log.i(TAG , "onQuitt");
        Toast.makeText(getBaseContext(),"onQuitt", Toast.LENGTH_SHORT).show();
    }
}