package dao;

import connexion.Connexion;
import entities.Absence;
import entities.Employe;
import entities.TypeAbsence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;

public class Absencedao implements Idao<Absence> {

    @Override
    public Absence insert(Absence obj) {
        String sql = "INSERT INTO `absence` (`id`, `date_debut`, `date_fin`, `id_typeabsence`, `id_employe`) VALUES (NULL,?, ?,?,?)";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, new java.sql.Date(obj.getDate_debut().getTime()));
            ps.setDate(2, new java.sql.Date(obj.getDate_fin().getTime()));
            ps.setInt(3, obj.getTypeabence().getId());
            ps.setInt(4, obj.getEmploye().getId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    obj.setId(rs.getInt(1));
                }
            }

            return obj;

        } catch (SQLException e) {
            System.out.println("Erreur dans l'insertion de l'absence !");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Absence obj) {
        String sql = "UPDATE absence SET date_debut = ?, date_fin = ?, id_typeabsence = ?, id_employe = ? WHERE id = ?";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(obj.getDate_debut().getTime()));
            ps.setDate(2, new java.sql.Date(obj.getDate_fin().getTime()));
            ps.setInt(3, obj.getTypeabence().getId());
            ps.setInt(4, obj.getEmploye().getId());
            ps.setInt(5, obj.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur dans la mise à jour de l'absence !");
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Absence obj) {
        String sql = "DELETE FROM absence WHERE `id` = ?";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur dans la suppression de l'absence");
            System.out.println("message : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Absence findById(int id) {
        String sql = "SELECT * FROM absence WHERE id = ?";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                Employedao empdao = new Employedao();
                Typeabsencedao typedao = new Typeabsencedao();

                if (rs.next()) {
                    Employe employe = empdao.findById(rs.getInt("id_employe"));
                    TypeAbsence typeabs = typedao.findById(rs.getInt("id_typeabsence"));
                    return new Absence(rs.getInt("id"), rs.getDate("date_debut"), rs.getDate("date_fin"), employe, typeabs);
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur findById : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Absence> findAll() {
        List<Absence> list = new ArrayList<>();
        String sql = "SELECT * FROM absence";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            Employedao empdao = new Employedao();
            Typeabsencedao typedao = new Typeabsencedao();

            while (rs.next()) {
                Employe employe = empdao.findById(rs.getInt("id_employe"));
                TypeAbsence typeabs = typedao.findById(rs.getInt("id_typeabsence"));
                list.add(new Absence(rs.getInt("id"), rs.getDate("date_debut"), rs.getDate("date_fin"), employe, typeabs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Erreur dans la sélection des absences !");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public DefaultCategoryDataset dataset() {
        String sql = "SELECT TRIM(LOWER(departement)) AS departement, COUNT(a.id) AS nombre "
                + "FROM employe e "
                + "LEFT JOIN absence a ON e.id = a.id_employe "
                + "GROUP BY TRIM(LOWER(departement));";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                dataset.addValue(
                        rs.getInt("nombre"),
                        "Nombre d'absences",
                        rs.getString("departement")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataset;
    }

    public int getNombreAbsenceEmploye(int id) {
        String sql = "SELECT COUNT(*) AS nb_absences FROM absence WHERE id_employe = ?";
        int nb = 0;

        try (Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id); 

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nb = rs.getInt("nb_absences"); 
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nb;
    }

    public DefaultCategoryDataset getDatasetAbsencesParMois() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> map = new LinkedHashMap<>();

        for (int m = 1; m <= 12; m++) {
            map.put(String.format("%02d", m), 0);
        }

        List<Absence> absences = this.findAll();

        for (Absence a : absences) {
            LocalDate debut = ((java.sql.Date) a.getDate_debut()).toLocalDate();
            LocalDate fin = ((java.sql.Date) a.getDate_fin()).toLocalDate();

            LocalDate d = debut;
            while (!d.isAfter(fin)) {
                String mois = String.format("%02d", d.getMonthValue());
                map.put(mois, map.get(mois) + 1);
                d = d.plusDays(1);
            }
        }

        for (String mois : map.keySet()) {
            dataset.addValue(map.get(mois), "Absences", mois);
        }

        return dataset;
    }

}
