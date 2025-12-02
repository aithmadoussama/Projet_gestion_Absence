package entities;

public class Employe {

    private int id;
    private String nom;
    private String departement;
    private String poste;

    public Employe() {
    }
    

    public Employe(int id, String nom, String departement, String poste) {
        this.id = id;
        this.nom = nom;
        this.departement = departement;
        this.poste = poste;
    }

    public Employe(String nom, String departement, String poste) {
        this.nom = nom;
        this.departement = departement;
        this.poste = poste;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDepartement() {
        return departement;
    }

    public String getPoste() {
        return poste;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    @Override
    public String toString() {
        return this.getNom();
    }

}
