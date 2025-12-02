package dao;

import entities.Employe;
import java.util.List;
import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import org.jfree.data.general.DefaultPieDataset;

public class Employedao implements Idao<Employe> {

    @Override
    public Employe insert(Employe obj) {
        String sql = "INSERT INTO `employe` (`id`, `nom`, `departement`, `poste`) VALUES (NULL, ?, ?, ?);";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getDepartement());
            ps.setString(3, obj.getPoste());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    obj.setId(rs.getInt(1));
                }
            }

            return obj;

        } catch (Exception e) {
            System.out.println("Erreur dans l'insertion de l'employé !");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Employe obj) {
        String sql = "UPDATE `employe` SET `nom` = ?, `departement` = ?, `poste` = ? WHERE `employe`.`id` = ?";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getDepartement());
            ps.setString(3, obj.getPoste());
            ps.setInt(4, obj.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur dans la mise à jour de l'employé !");
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Employe obj) {
        String sql = "DELETE FROM employe WHERE `id` = ?";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur dans la suppression de l'objet");
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Employe findById(int id) {
        String sql = "SELECT * FROM employe WHERE id = ?";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("departement"), rs.getString("poste"));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Employe findBynom(String nom) {
        String sql = "SELECT * FROM employe WHERE nom = ?";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nom);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("departement"), rs.getString("poste"));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Employe> findAll() {
        List<Employe> list = new ArrayList<>();
        String sql = "SELECT * FROM employe";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("departement"), rs.getString("poste")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Erreur dans la sélection des employés");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public DefaultPieDataset dataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String sql = "SELECT departement, COUNT(*) AS nombre FROM employe GROUP BY departement";
        try (
                Connection conn = Connexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                dataset.setValue(rs.getString("departement"), rs.getInt("nombre"));
            }
            return dataset;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
