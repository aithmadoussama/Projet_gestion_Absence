package services;

import dao.Typeabsencedao;
import entities.TypeAbsence;
import java.util.List;

public class Typeabsenceservice {

    private final Typeabsencedao typeabsencedao = new Typeabsencedao();

    public Typeabsenceservice() {
    }

    public TypeAbsence createTypeabsence(TypeAbsence t) {
        return typeabsencedao.insert(t);
    }

    public boolean updateTypeabsence(TypeAbsence t) {
        return typeabsencedao.update(t);
    }

    public boolean deleteTypeabsence(TypeAbsence t) {
        return typeabsencedao.delete(t);
    }

    public TypeAbsence getTypeabsenceByid(int id) {
        return typeabsencedao.findById(id);
    }

    public List<TypeAbsence> getAllTypeabsence() {
        return typeabsencedao.findAll();
    }

    public List<TypeAbsence> getTypeBylibelle(String libelle) {
        return typeabsencedao.finBylibelle(libelle);
    }

}
