package metier;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public class DeviseBDD {
    private static final Integer VERSION_BDD = 1;
    private static final String NOM_BDD = "devises_convertion";
    private static final String TABLE_DEVISE = "table_devise";
    private static final String COL_ID = "ID";
    private static final Integer NUM_COL_ID = 0;
    private static final String COL_DEVISE = "devise";
    private static final Integer NUM_COL_DEVISE = 1;
    private static final String COL_TAUX = "taux_convertion";
    private static final Integer NUM_COL_TAUX = 1;
    private  static final String TAG = "DeviseBDD";
    private SQLiteDatabase bdd ;

    private MaBaseSQLite maBaseSQLite;

    public DeviseBDD (Context context){
        // We create the data base and its table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public SQLiteDatabase getBdd(){
        return bdd;
    }

    public void open(){
        if(bdd==null){
            bdd = maBaseSQLite.getWritableDatabase();
        }
    }

    public void close() {
        bdd.close();
    }
    public long insertDevise(Devise newDevise){
        this.open();
        // WE create a ContentValue in order to insert it into the table
        ContentValues values = new ContentValues();
        // We put the values into the ContentValues
        values.put(COL_DEVISE,newDevise.getDevise());
        values.put(COL_TAUX,newDevise.getTauxConvertion());
        long id =  bdd.insert(TABLE_DEVISE,null,values);
        bdd.close();
        return id;

    }

    public long delDeviseWithId(int id){
        this.open();
        return bdd.delete(TABLE_DEVISE,COL_ID+"="+id,null);
    }

    public long delDeviseWithName(String devise){
        this.open();
        return bdd.delete(TABLE_DEVISE,COL_DEVISE+"="+devise,null);
    }

    public  long updateDevise(int id, Devise newDevise){
        this.open();
        ContentValues values = new ContentValues();
        values.put(COL_DEVISE,newDevise.getDevise());
        values.put(COL_TAUX,newDevise.getTauxConvertion());
        return bdd.update(TABLE_DEVISE,values,COL_ID+" = "+id,null);
    }

    public Devise getDeviseWithName(String namOfDevise){
        this.open();
        Cursor cursor = bdd.query(TABLE_DEVISE,new String[]{COL_ID,COL_DEVISE,COL_TAUX},COL_DEVISE +
                " LIKE '"+ namOfDevise+ "'",null,null,null,null);
        return (Devise) cursor;

    }

   public  Map<String, Double> getAll(Context context){
        Log.i(TAG, "getAll");
        Map<String, Double> aDevises = new HashMap<String,Double>();
        // Open a DB connection
       this.open();
       // Récupérer la liste des monnaies
       String sql = "SELECT " + MaBaseSQLite.COL_DEVISE + " as _id,"
               + MaBaseSQLite.COL_TAUX + " FROM " + MaBaseSQLite.TABLE_DEVISE;

    Cursor cursor= bdd.rawQuery(sql, null);
    if (cursor != null){
        if(cursor.moveToFirst()){
            do{
                String monnaie = cursor.getString(0);
                double taux =cursor.getDouble(1);
                aDevises.put(monnaie,taux);
            }while (cursor.moveToNext());
        }
    }
    bdd.close();
    return aDevises;
   }
}

