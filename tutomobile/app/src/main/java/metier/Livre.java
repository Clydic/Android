package metier;

/**
 * Création d'un livre tout simple pour un exemple d'utilisation de SQlite sous Android
 * @author Cedric
 *
 */
public class Livre {
    private int id;
    private String isbn;
    private String titre;


    public Livre(){}
    public Livre(String isbn, String titre){
        this.isbn = isbn;
        this.titre = titre;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getIsbn(){
        return isbn;
    }
    public void setIsbn(String isbn){
        this.isbn=isbn;
    }
    public String getTitre(){
        return titre;
    }
    public void setTitre(String titre){
        this.titre=titre;
    }

    public String toString(){
        return "ID : " + id + "\nISBN : " + "\nTitre : "+titre;
    }
}
