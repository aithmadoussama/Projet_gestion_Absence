/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aithmad
 */
public class User {

    private int id;
    private String prenom;
    private String nom;
    private String email;
    private String mot_passe;

    public User() {
    }

    public User(String nom, String email, String mot_passe, String prenom) {
        this.nom = nom;
        this.email = email;
        this.mot_passe = mot_passe;
        this.prenom = prenom;
    }

    public User(int id, String nom, String email, String mot_passe) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.mot_passe = mot_passe;
    }

    public static boolean update_pass_word(String new_passord, String email) {
        try {
            String sql = "UPDATE users SET mot_passe = ? WHERE email = ?";
            Connection conn = Connexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, new_passord);
            ps.setString(2, email);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur dans la mise du mot de passe utilisateur!");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<String> getAllemails() {
        String sql = "SELECT email FROM users";
        List<String> list = new ArrayList<>();
        try {
            Connection conn = Connexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString("email"));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null ;
        }
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMot_passe() {
        return mot_passe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMot_passe(String mot_passe) {
        this.mot_passe = mot_passe;
    }
}
