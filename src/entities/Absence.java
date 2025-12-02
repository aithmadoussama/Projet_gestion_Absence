package entities;

import java.util.Date;

public class Absence {

    private int id;
    private Date date_debut;
    private Date date_fin;
    Employe employe;
    TypeAbsence typeabence;

    public Absence() {
    }

    public Absence(int id, Date date_debut, Date date_fin, Employe employe, TypeAbsence typeabence) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.employe = employe;
        this.typeabence = typeabence;
    }

    public Absence(Date date_debut, Date date_fin, Employe employe, TypeAbsence typeabence) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.employe = employe;
        this.typeabence = typeabence;
    }

    public int getId() {
        return id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public Employe getEmploye() {
        return employe;
    }

    public TypeAbsence getTypeabence() {
        return typeabence;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setTypeabence(TypeAbsence typeabence) {
        this.typeabence = typeabence;
    }

    @Override
    public String toString() {
        return "Absence{" + "id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", employe=" + employe + ", typeabence=" + typeabence + '}';
    }

}
