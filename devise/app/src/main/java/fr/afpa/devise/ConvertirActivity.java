package fr.afpa.devise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConvertirActivity extends AppCompatActivity {
    private TextView tMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertir);
        Intent thisIntent = getIntent();
        String msg = thisIntent.getExtras().getString("msg");
        tMsg = findViewById(R.id.textRes);
        tMsg.setText(msg);
    }

    public void cLeave(View view){
        finish();
    }

}