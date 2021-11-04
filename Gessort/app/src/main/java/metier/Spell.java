package metier;

import android.util.Log;

public class Spell {
    private String name;
    private String shortDesctiption;

    public Spell(String name_to_define, String short_description_to_define){
        this.setName(name_to_define);
        this.setShortDesctiption(short_description_to_define);
        Log.i("Message : ","Le Sort "+this.getName()+" est bien créé");
    }

    // ---------------------Setters an getters---------------------

    /**
     * @return String
     */

    public String getName(){
       return this.name;
    }

    public String getShortDesctiption(){
        return  this.shortDesctiption;
    }

    /**
     * @return String
     */

    public void setName(String nameToSet){
         this.name=nameToSet;
    }

    public void setShortDesctiption(String shortDesctiptionToSet){
        this.shortDesctiption= shortDesctiptionToSet;
    }

    public String toString(){
        String msg= this.name+ " :\n " + this.shortDesctiption;
        return msg;
    }
}
