package dev.thomas.repositories;

import dev.thomas.models.User;
import dev.thomas.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static void main(String[] args){
        new UserDAO();
        UserDAO.getByUsername("username");
    }

    static ConnectionFactory cu = ConnectionFactory.getInstance();
    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public static User getByUsername(String username) {
        String sql = "select * from project1.users where username = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public static User create(User userToBeRegistered) {
        String sql = "insert into project1.users (id, first_name, last_name, username, password, role) values (default, ?, ?, ?, ?, ?)";
        try(Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userToBeRegistered.getFirstName());
            ps.setString(2, userToBeRegistered.getLastName());
            ps.setString(3, userToBeRegistered.getUsername());
            ps.setString(4, userToBeRegistered.getPassword());
            ps.setString(5, userToBeRegistered.getRole());
            ps.executeUpdate();
            return userToBeRegistered;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userToBeRegistered;
    }
}
