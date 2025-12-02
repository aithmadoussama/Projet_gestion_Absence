package entities;

public class TypeAbsence {

    private int id;
    private String libelle;
    private String justification;

    public TypeAbsence() {
    }

    public TypeAbsence(int id, String libelle, String justification) {
        this.id = id;
        this.libelle = libelle;
        this.justification = justification;
    }

    public TypeAbsence(String libelle, String justification) {
        this.libelle = libelle;
        this.justification = justification;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getJustification() {
        return justification;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public String toString() {
        return this.getLibelle();
    }
    
}
