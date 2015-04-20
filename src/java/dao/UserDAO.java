package dao;


import dbconnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;


public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = new DBConnection().getConnection();
    }
    
    public User insert(User entity) {
        try {
            PreparedStatement st = connection.prepareStatement("insert into uzivatel values(?,?,?)");
            st.setInt(1, 0);
            st.setString(2, entity.getLogin());
            st.setString(3, entity.getPassword());
            st.executeUpdate();
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getByUsername(String username) {
        try {
            String query = "select id, login, password from uzivatel where login=?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                return user;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
//    public User getByUsername(String username) {
//        try {
//            Statement st = connection.createStatement();
//            String query = "select id, login, password from uzivatel where login = '" + username + "'";
//            ResultSet rs = st.executeQuery(query);
//            if (rs.next()) {
//                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
//                return user;
//            }
//            
//            rs.close();
//            st.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
