package dev.thomas.repositories;

import dev.thomas.models.Reimbursement;
import dev.thomas.models.Status;
import dev.thomas.models.User;
import dev.thomas.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ReimbursementDAO {

    static ConnectionFactory cu = ConnectionFactory.getInstance();

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public static Reimbursement getById(int id) {
        String sql = "select * from reimbursementsee where id = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("id"),
                        Status.valueOf(rs.getString("status")),
                        (rs.getString("author") == null) ? null : UserDAO.getByUsername(rs.getString("author")),
                        (rs.getString("resolver") == null) ? null : UserDAO.getByUsername(rs.getString("resolver")),
                        rs.getInt("cost"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("justification")
                );
                return r;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public static Reimbursement update(Reimbursement unprocessedReimbursement) {
        String sql = "insert into reimbursementsee (id, status, author, resolver, cost, date, time, location, description, justification) values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, unprocessedReimbursement.getStatus().toString());
            ps.setString(2, unprocessedReimbursement.getAuthor().getUsername());
            ps.setString(3, unprocessedReimbursement.getResolver().getUsername());
            ps.setDouble(4, unprocessedReimbursement.getAmount());
            /*ps.setString(5, unprocessedReimbursement.getDate());
            ps.setString(6, unprocessedReimbursement.getTime());
            ps.setString(7, unprocessedReimbursement.getLocation());
            ps.setString(8, unprocessedReimbursement.getDescription());
            ps.setString(9, unprocessedReimbursement.getJustification());*/


            ps.executeUpdate();
            return unprocessedReimbursement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unprocessedReimbursement;
    }

    public static void updateAllowance(Reimbursement newAllowance) {

        String sql = "update users set project1.reimbursement_allowed = ? where id = ?";

        try(Connection conn = ConnectionFactory.getConnection()) {

            User author = UserDAO.getByUsername(newAllowance.getAuthor().getUsername());

            int newAllowanceInt = getAllowance(author) - newAllowance.getAmount();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, newAllowanceInt);
            ps.setInt(2, author.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getAllowance(User author) {
        String sql = "select * from users where id = ?";
        try(Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, author.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("reimbursement_allowed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
