package metier;

import android.util.Log;

public class Spell {
    private String spellName;
    private String shortDescription;


    public Spell(String spellName_to_define, String short_description_to_define){
        this.setName(spellName_to_define);
        this.setShortDescription(short_description_to_define);
        Log.i("Message : ","Le Sort "+this.getName()+" est bien créé");
    }

    // ---------------------Setters an getters---------------------

    /**
     * @return String
     */

    public String getName(){
       return this.spellName;
    }

    public String getShortDescription(){
        return  this.shortDescription;
    }

    /**
     * @return String
     */

    public void setName(String spellNameToSet){
         this.spellName=spellNameToSet;
    }

    public void setShortDescription(String shortDescriptionToSet){
        this.shortDescription= shortDescriptionToSet;
    }

    public String toString(){
        String msg= this.spellName+ " :\n " + this.shortDescription;
        return msg;
    }
}
