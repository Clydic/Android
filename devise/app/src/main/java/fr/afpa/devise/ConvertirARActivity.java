package fr.afpa.devise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ConvertirARActivity extends AppCompatActivity {
    private TextView tMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertir_a_r);
        tMsg = findViewById(R.id.textMsg);
        // On veut récupérer le message à afficher
        tMsg.setText();
    }
}