package metier;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import fr.afpa.gessort.R;

public class Form extends AppCompatActivity {
   private TextView titleForm;
   private EditText spellCardDescription;
   private String TAG = "Form";
   private Map<String,String> listOfLabel;
   private TableLayout formTable;

   @RequiresApi(api = Build.VERSION_CODES.KITKAT)


   @Override
   protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.form);

      Intent thisIntent = getIntent();
      //int id = Objects.requireNonNull(thisIntent.getExtras()).getInt("id");

      ArrayList<Spell> listOfSpell;
      listOfSpell = ListOfSpellMgr.getListOfSpell(this);

      titleForm=findViewById(R.id.titleForm);
      formTable = findViewById(R.id.form);
      titleForm.setText("Ajouter un sort");
      loadForm();
   }


   protected void loadForm(){
      List<String> list_to_load = (List<String>) Arrays.asList("Nom",
              "Branche",
              "Registre",
              "Niveau",
              "Temps d'invocation",
              "Cible",
              "Portée",
              "Jet de sauvegarde",
              "Résistance à la magie",
              "Courte des description",
              "Description Complète");
      Map<String,String> arrayList ;
      for (int key=0; key<list_to_load.size();key++){

         TableRow row = new TableRow(this);
         TextView tvKey = new TextView(this);
         EditText etValues = new EditText(this);
         TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
         tvKey.setLayoutParams(params);
         etValues.setLayoutParams(params);
         etValues.setSingleLine(false);
         Log.i(TAG,"key : -" + list_to_load.get(key)+"-");
         row.addView(tvKey);
         if(!list_to_load.get(key).equals("Description Complète")){
            Log.i(TAG,"key2 : -" + key+"-");
            tvKey.setText( list_to_load.get(key).toString());
            //etValues.setText(arrayList.get(key));
            row.addView(etValues);
         }else{

            EditText etDescription = new EditText(this);
            //etDescription.setText(arrayList.get(key));
            Log.i(TAG,"else ! ");
            FrameLayout frameDescription = findViewById(R.id.frameDescription);
            frameDescription.addView(etDescription);

         }
         formTable.addView(row);

      }

   }

   public void cLeave(View view){
      finish();
   }

    /*public void generateTableLayout(List listLabel, List<TextView> textViews) {

    }*/
}
