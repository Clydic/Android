package fr.afpa.devise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import metier.Convert;

public class ConvertirAR extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =  getIntent();
        String strDepart = intent.getExtras().getString("strDepart");
        String strArrivee = intent.getExtras().getString("strArrivee");
        Double dbMontant = intent.getExtras().getDouble("dbMontant");
        Double res = Convert.convertir(strDepart, strArrivee, dbMontant,this);
        String strRes = String.format("%.2f",res);
        String strMontant = String.format("%.2f",dbMontant);
        String msg = strMontant + " " + strDepart + " fait " + strRes + " " + strArrivee;
        Intent intentRes = new Intent();
        intentRes.putExtra("msg",msg);
        setResult(1,intentRes);
        finish();
    }
}