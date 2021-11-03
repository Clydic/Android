package metier;

public class Devise {
    private String devise;
    private int id;
    private Double taux_convertion;

   public Devise(){}

   public Devise(String devise, Double taux_convertion ){
       setDevise(devise);
       setTauxConvertion(taux_convertion);
   }

   // ---------------------Getter and Setter------------------------

    public String getDevise(){
       return this.devise;
    }
    public Double getTauxConvertion(){
        return this.taux_convertion;
    }
    public Integer getID(){
        return this.id;
    }
    public void setDevise(String devise){
        this.devise=devise;
    }
    public void setTauxConvertion(Double taux_convertion){
        this.taux_convertion=taux_convertion;
    }
    public void setId(int id){
        this.id=id;
    }

    public String toString(){
       String msg="ID : "+this.id+ " Devise : " + this.devise +
               " Taux de convertion : " +this.taux_convertion;
       return msg;
    }

}
