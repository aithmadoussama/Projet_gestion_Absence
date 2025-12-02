package services;

import dao.Absencedao;
import entities.Absence;
import entities.Employe;
import entities.TypeAbsence;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Absenceservice {

    Absencedao absencedao = new Absencedao();

    public Absence createAbsence(Absence a) {
        return absencedao.insert(a);
    }

    public boolean deleteAbsence(Absence a) {
        return absencedao.delete(a);
    }

    public boolean updateAbsence(Absence a) {
        return absencedao.update(a);
    }

    public Absence getAbsenceByid(int id) {
        return absencedao.findById(id);
    }

    public List<Absence> getAllAbsence() {
        return absencedao.findAll();
    }
    
    public DefaultCategoryDataset getBardataset(){
        return absencedao.dataset();
    }
    
    public DefaultCategoryDataset getDatasetAbsenceParMois(){
        return absencedao.getDatasetAbsencesParMois();
    }
    
    public int getNombreAbsenceEmploye(int id){
        return absencedao.getNombreAbsenceEmploye(id);
    }
    
}
