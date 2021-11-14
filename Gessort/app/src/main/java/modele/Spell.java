package modele;

import android.util.Log;


import java.util.HashMap;
import java.util.List;
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
    private Integer NBOFATTR=11;



    /////////////////Constructors///////////////

   public Spell(List<String> list_to_load){
       //if(list_to_load.size()==NBOFATTR)
       //{
           this.setName(list_to_load.get(0));
           this.setShortDescription(list_to_load.get(1));
           this.setSpellBranch(list_to_load.get(2));
           this.setSpellLevel(list_to_load.get(3));
           this.setSpellInvocationTime(list_to_load.get(4));
           this.setSpellRange(list_to_load.get(5));
           this.setSpellDuration(list_to_load.get(6));
           this.setSpellBackup(list_to_load.get(7));
           this.setSpellTarget(list_to_load.get(8));
           this.setSpellMagicResistance(list_to_load.get(9));
           this.setSpellCompleteDescription(list_to_load.get(10));
           Log.i("Message : ","Le Sort "+this.getName()+" est bien créé");
       //}
   }
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
     * Get the Name
     * @return String
     */

    public String getName(){
       return this.spellName;
    }

    /**
     * Get the Short description
     * @return String
     */
    public String getShortDescription(){
        return  this.shortDescription;
    }

    /**
     * Set the Name of Spell
     */

    public void setName(String spellNameToSet){
         this.spellName=spellNameToSet;
    }

    /**
     * Set the short description
     *
     */
    public void setShortDescription(String shortDescriptionToSet){
        this.shortDescription= shortDescriptionToSet;
    }


    /**
     * Get the branch of Spell
     * @return String
     */
    public String getSpellBranch() {return this.spellBranch;}
    /**
     * Get the level of Spell
     * @return String
     */
    public String getSpellLevel() {return this.spellLevel;}

    /**
     * Get the invoation Time
     * @return String
     */
    public String getSpellInvocationTime() {return this.spellInvocationTime;}

    /**
     * Get the duration of Spell
     * @return String
     */
    public String getSpellDuration() {return this.spellDuration;}


    /**
     * Get the Target of Spell
     * @return String
     */
    public String getSpellTarget() {return this.spellTarget;}


    /**
     * Get the Range of the Spell
     * @return String
     */
    public String getSpellRange() {return this.spellRange;}


    /**
     * get the Backup of the Spell
     * @return String
     */
    public String getSpellBackup() {return this.spellBackup;}


    /**
     * Get the Magic Resistance
     * @return String
     */
    public String getSpellMagicResistance() {return this.spellMagicResistance;}



    /**
     * Get the complete Description
     * @return String
     */
    public String getSpellCompleteDescription() {return this.spellCompleteDescription;}


    /**
     * Set the Branch of the Spell
     */
    public void  setSpellBranch(String str) { this.spellBranch=str;}

    /**
     * Set the Level of the Spell
     */
    public void  setSpellLevel(String str) { this.spellLevel=str;}
    /**
     * Set The InvocationTime of the Spell
     */
    public void setSpellInvocationTime(String str) { this.spellInvocationTime = str;}

    /**
     * Set the Duration of the Spell
     */
    public void setSpellDuration(String str) { this.spellDuration = str;}
    /**
     * Set the target of the Spell
     */
    public void setSpellTarget(String str) { this.spellTarget = str;}
    /**
     * set the Range of the Spell
     */
    public void setSpellRange(String str) { this.spellRange = str;}



    /**
     * Set the Backup of the Spell
     */
    public void setSpellBackup(String str) { this.spellBackup = str;}



    /**
     * Set The magic Resistance of the Spell
     */
    public void setSpellMagicResistance(String str) { this.spellMagicResistance = str;}



    /**
     * Set the Complete Description of the Spell
     */
    public void setSpellCompleteDescription(String str) { this.spellCompleteDescription = str;}

    /**
     * Get all attribut of this class
     * @return Map<String,String>
     */
    public Map<String,String> getAllAttr(){

        Map<String,String> mapSpell = new HashMap<String, String>();
        mapSpell.put("Nom",this.getName());
        mapSpell.put("Courte description",this.getShortDescription());
        mapSpell.put("Branche",this.getSpellBranch());
        mapSpell.put("Niveau",getSpellLevel());
        mapSpell.put("Temps d'invocation",getSpellInvocationTime());
        mapSpell.put("Durée",getSpellDuration());
        mapSpell.put("Cible",getSpellTarget());
        mapSpell.put("Portée",getSpellRange());
        mapSpell.put("Jet de sauvegarde",getSpellBackup());
        mapSpell.put("Résistance à la magie",getSpellMagicResistance());
        mapSpell.put("Description Complète",getSpellCompleteDescription());

        return mapSpell;
    }
    @Override
    public String toString(){

        String msg= "Nom : " + this.spellName+
                " \nCourte description " + this.shortDescription+
                "\nBranche : " + this.getSpellBranch() +
                "\nNiveau : " + this.getSpellLevel() +
                "\nTemps d'invocation" + this.getSpellInvocationTime() +
                "\nDurée : " + this.getSpellDuration() +
                "\nCible : " + this.getSpellTarget() +
                "\nPortée : " + this.getSpellRange() +
                "\nSauvegarde"+ this.getSpellBackup()+
                "\nRésistance magique" + this.getSpellMagicResistance()+
                "\nDescription Complète" + this.getSpellCompleteDescription();
        return msg;
    }
}
