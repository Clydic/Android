package metier;

import android.content.Context;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import fr.afpa.gessort.R;

import static android.widget.Toast.LENGTH_SHORT;

public class FormActivity extends AppCompatActivity {
   private TextView titleForm;
   private EditText spellCardDescription;
   private String TAG = "Form";
   private Map<String,String> listOfLabel;
   private TableLayout formTable;
   private List<String> list_of_label;
   private ArrayList<EditText> listOfEditText ;

   @RequiresApi(api = Build.VERSION_CODES.KITKAT)


   @Override
   protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.form);

      Intent thisIntent = getIntent();
      //int id = Objects.requireNonNull(thisIntent.getExtras()).getInt("id");

      ArrayList<Spell> listOfSpell;
      listOfSpell = ListOfSpellMgr.getListOfSpell(this);
      listOfEditText=new ArrayList<EditText>();
      titleForm=findViewById(R.id.titleForm);
      formTable = findViewById(R.id.form);
      titleForm.setText("Ajouter un sort");
      list_of_label= (List<String>) Arrays.asList("Nom",
              "Courte des description",
              "Branche",
              "Niveau",
              "Temps d'invocation",
              "Portée",
              "Durée",
              "Jet de sauvegarde",
              "Cible",
              "Résistance à la magie",
              "Description Complète");
      List<EditText> listOfEditText = new ArrayList<EditText>();
      loadForm(list_of_label);
      Log.i(TAG, "listofedittext : " + listOfEditText.toString());
   }


   /**
    * Load TextView and Edit in a TableRow which load into formTable
    * that is a TableLayout
    * @param list_to_load list_to_load
    */
   protected void loadForm(List<String> list_to_load){
      Map<String,String> arrayList ;
      // We make a loop on list_to_load
      for (int i=0; i<list_to_load.size();i++){
         // Initialisation
         TableRow row = new TableRow(this);
         TextView tvKey = new TextView(this);
         EditText etValues = new EditText(this);
         EditText etDescription = new EditText(this);
         TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
         // We set parameters
         tvKey.setLayoutParams(params);
         etValues.setLayoutParams(params);
         etValues.setSingleLine(false);
         Log.i(TAG,"i : -" + list_to_load.get(i)+"-");
         //Add the TextView of label into the table row
         row.addView(tvKey);

         if(!list_to_load.get(i).equals("Description Complète")){
            Log.i(TAG,"i2 : -" + i+"-");
            // We set the label's text
            tvKey.setText( list_to_load.get(i).toString());
            // Add the EditText into a List in order to get later all values
           listOfEditText.add(etValues);
           // Add the EditText into the TableRow
            row.addView(etValues);
         }else{

            //etDescription.setText(arrayList.get(key));
            Log.i(TAG,"else ! ");
            FrameLayout frameDescription = findViewById(R.id.frameDescription);
            // Add the EditText into a List in order to get later all values
            listOfEditText.add(etDescription);
            // Add the EditText into a FrameLayout
            frameDescription.addView(etDescription);

         }
         // Add TableRow into the TableLayout
         formTable.addView(row);

      }

   }

   /**
    * Method which finish the activity
    * @param view
    */
   public void cLeave(View view){
      finish();
   }


   /**
    * Insert a spell into the DataBase
    * @param v
    */
   public void addSpell(View v){
      Context context = this;
     Spell newSpell;
     ArrayList<String> listOfValue = new ArrayList<String>();
     Log.i(TAG, "taille : "+listOfEditText.size());

     for (int i = 0; i<listOfEditText.size(); i++){
         // Add values get from EditTexts and put them into a List
        listOfValue.add(String.valueOf(listOfEditText.get(i).getText()));
     }
     newSpell = new Spell(listOfValue);
     try {
       // Launch the insert method of LisOfMgr
        ListOfSpellMgr.insertSpell(context, listOfValue );
        // Display a message to inform the user
        Toast.makeText(getBaseContext(),newSpell.getName() + " a bien été enregistré dans la base", LENGTH_SHORT).show();
        finish();

     }catch (Exception e){

         // Display error message into a toast
        Toast.makeText(getBaseContext(),e.getMessage(), LENGTH_SHORT).show();
     }
   }
}
