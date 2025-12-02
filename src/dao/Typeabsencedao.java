package dao;

import connexion.Connexion;
import entities.TypeAbsence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Typeabsencedao implements Idao<TypeAbsence> {

    public Typeabsencedao() {}

    @Override
    public TypeAbsence insert(TypeAbsence obj) {
        String sql = "INSERT INTO `typeabsence` (`id`, `libelle`, `justification`) VALUES (NULL, ?, ?);";
        try (
            Connection conn = Connexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, obj.getLibelle());
            ps.setString(2, obj.getJustification());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    obj.setId(rs.getInt(1));
                }
            }

            return obj;

        } catch (SQLException e) {
            System.out.println("Erreur dans l'insertion de type absence !");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(TypeAbsence obj) {
        String sql = "UPDATE `typeabsence` SET `libelle` = ?, `justification` = ? WHERE `id` = ?";
        try (
            Connection conn = Connexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, obj.getLibelle());
            ps.setString(2, obj.getJustification());
            ps.setInt(3, obj.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur dans la mise à jour de type d'absence");
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(TypeAbsence obj) {
        String sql = "DELETE FROM typeabsence WHERE `id` = ?";
        try (
            Connection conn = Connexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur dans la suppression de typeabsence !");
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public TypeAbsence findById(int id) {
        String sql = "SELECT * FROM typeabsence WHERE id = ?";
        try (
            Connection conn = Connexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TypeAbsence(rs.getInt("id"), rs.getString("libelle"), rs.getString("justification"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<TypeAbsence> findAll() {
        List<TypeAbsence> list = new ArrayList<>();
        String sql = "SELECT * FROM typeabsence";
        try (
            Connection conn = Connexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(new TypeAbsence(rs.getInt("id"), rs.getString("libelle"), rs.getString("justification")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Erreur dans la sélection des types d'absence");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<TypeAbsence> finBylibelle(String libelle) {
        List<TypeAbsence> list = new ArrayList<>();
        String sql = "SELECT * FROM typeabsence WHERE libelle = ?";
        try (
            Connection conn = Connexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, libelle);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new TypeAbsence(rs.getInt("id"), rs.getString("libelle"), rs.getString("justification")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur dans la sélection de type d'absence");
            System.out.println(e.getMessage());
        }
        return list;
    }
}
