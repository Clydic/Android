package metier;

import android.util.Log;


import java.util.HashMap;
import java.util.Map;

public class Spell {
    private String spellName;
    private String shortDescription;
    public  String spellBranch;
    public  String spellLevel;
    public  String spellInvocationTime;
    public  String spellDuration;
    public  String spellTarget;
    public  String spellRange;
    public  String spellBackup;
    public  String spellMagicResistance;
    public  String spellCompleteDescription;



    public Spell(String spellName_to_define,
                 String short_description_to_define,
                 String branch_to_define,
                 String level_to_define,
                 String time_to_define,
                 String duration_do_define,
                 String target_to_define,
                 String range_to_define,
                 String backup_to_define,
                 String magic_resistance_to_define,
                 String complete_description_to_define
                 ){
        this.setName(spellName_to_define);
        this.setShortDescription(short_description_to_define);
        this.setSpellBranch(branch_to_define);
        this.setSpellLevel(level_to_define);
        this.setSpellInvocationTime(time_to_define);
        this.setSpellDuration(duration_do_define);
        this.setSpellTarget(target_to_define);
        this.setSpellRange(range_to_define);
        this.setSpellBackup(backup_to_define);
        this.setSpellMagicResistance(magic_resistance_to_define);
        this.setSpellCompleteDescription(complete_description_to_define);
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


    /**
     * @return String
     */
    public String getSpellBranch() {return this.spellBranch;}
    /**
     * @return String
     */
    public String getSpellLevel() {return this.spellLevel;}
    /**
     * @return String
     */
    public String getSpellInvocationTime() {return this.spellInvocationTime;}
    /**
     * @return String
     */
    public String getSpellDuration() {return this.spellDuration;}
    /**
     * @return String
     */
    public String getSpellTarget() {return this.spellTarget;}
    /**
     * @return String
     */
    public String getSpellRange() {return this.spellRange;}
    /**
     * @return String
     */
    public String getSpellBackup() {return this.spellBackup;}
    /**
     * @return String
     */
    public String getSpellMagicResistance() {return this.spellMagicResistance;}
    /**
     * @return String
     */
    public String getSpellCompleteDescription() {return this.spellCompleteDescription;}


    /**
     * @return String
     */
    public void  setSpellBranch(String str) { this.spellBranch=str;}

    /**
     * @return String
     */
    public void  setSpellLevel(String str) { this.spellLevel=str;}
    /**
     * @return String
     */
    public void setSpellInvocationTime(String str) { this.spellInvocationTime = str;}

    /**
     * @return String
     */
    public void setSpellDuration(String str) { this.spellDuration = str;}
    /**
     * @return String
     */
    public void setSpellTarget(String str) { this.spellTarget = str;}
    /**
     * @return String
     */
    public void setSpellRange(String str) { this.spellRange = str;}
    /**
     * @return String
     */
    public void setSpellBackup(String str) { this.spellBackup = str;}
    /**
     * @return String
     */
    public void setSpellMagicResistance(String str) { this.spellMagicResistance = str;}
    /**
     * @return String
     */
    public void setSpellCompleteDescription(String str) { this.spellCompleteDescription = str;}


    public Map<String,String> getAllAttr(){
        Map<String,String> mapSpell = new HashMap<String, String>();
        mapSpell.put("Branche  ",this.getSpellBranch());
        mapSpell.put("Niveau  ",getSpellLevel());
        mapSpell.put("Temps d'invocation  ",getSpellInvocationTime());
        mapSpell.put("Durée : ",getSpellDuration());
        mapSpell.put("Cible  ",getSpellTarget());
        mapSpell.put("Portée  ",getSpellRange());
        mapSpell.put("Jet de sauvegarde  ",getSpellBackup());
        mapSpell.put("Résistance à la magie  ",getSpellMagicResistance());
        mapSpell.put("Description Complète  ",getSpellCompleteDescription());

        return mapSpell;
    }
    @Override
    public String toString(){

        String msg= this.spellName+ " :\n " + this.shortDescription;
        return msg;
    }
}
