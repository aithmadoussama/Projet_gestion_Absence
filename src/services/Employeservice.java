package services;

import entities.Employe;
import dao.Employedao;
import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

public class Employeservice {

    private final Employedao empdao = new Employedao();

    public Employeservice() {
    }

    public Employe createEmplye(Employe e) {
        return empdao.insert(e);
    }

    public boolean updateEmplye(Employe e) {
        return empdao.update(e);
    }

    public boolean deleteEmplye(Employe e) {
        return empdao.delete(e);
    }

    public Employe getEmplyeByid(int id) {
        return empdao.findById(id);
    }

    public Employe getEmployeBynom(String nom) {
        return empdao.findBynom(nom);
    }

    public List<Employe> getAllemplye() {
        return empdao.findAll();
    }

    public DefaultPieDataset getPieDataset() {
        return empdao.dataset();
    }

}
