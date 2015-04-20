package dao;


import model.Prispevek;
import dbconnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;


public class PrispevekDAO {
    private Connection connection;

    public PrispevekDAO() {
        this.connection = new DBConnection().getConnection();
    }
    
    public List<Prispevek> getAll() {
        try {
            Statement st = connection.createStatement();
            String query = "SELECT p.id, p.text, u.id, u.login FROM prispevek p, uzivatel u WHERE p.id_uzivatele=u.id ORDER BY p.id desc";
            ResultSet rs = st.executeQuery(query);
            List<Prispevek> prispevky = new ArrayList<>();
            while (rs.next()) {
                User u = new User(rs.getInt(3), rs.getString(4), "");
                Prispevek p = new Prispevek(rs.getInt(1), u, rs.getString(2));
                prispevky.add(p);
            }
            rs.close();
            st.close();
            return prispevky;
        } catch (SQLException ex) {
            Logger.getLogger(PrispevekDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public List<Prispevek> getAllByUser(User u) {
        try {
            String query = "SELECT p.id, p.text, u.id, u.login FROM prispevek p, uzivatel u WHERE p.id_uzivatele=u.id AND p.id_uzivatele=? ORDER BY p.id desc";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, u.getId());
            ResultSet rs = st.executeQuery();
            List<Prispevek> prispevky = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt(3), rs.getString(4), "");
                Prispevek p = new Prispevek(rs.getInt(1), user, rs.getString(2));
                prispevky.add(p);
            }
            rs.close();
            st.close();
            return prispevky;
        } catch (SQLException ex) {
            Logger.getLogger(PrispevekDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void insert(Prispevek p){
        try {
            PreparedStatement st = connection.prepareStatement("insert into prispevek values(?,?,?)");
            st.setInt(1, 0);
            st.setInt(2, p.getUzivatel().getId());
            st.setString(3, p.getText());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrispevekDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
