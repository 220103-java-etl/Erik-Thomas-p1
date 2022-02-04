package dev.thomas.repositories;

//import dev.thomas.repositories.ConnectionFactory;
//import dev.thomas.repositories.ReimbursementDao;
import dev.thomas.models.Reimbursement;
import dev.thomas.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl extends ReimbursementDAO {

    public List<Reimbursement> getAllReimbursements() {
        List<Reimbursement> reimbursements = new ArrayList<>();
        Connection conn = null;
        Statement prst = null;

        try {
            conn = ConnectionFactory.getConnection();

            prst = conn.createStatement();

            String sql = "SELECT * FROM reimbursement.requests";

            ResultSet rs = prst.executeQuery(sql);

            while (rs.next()) {
                Reimbursement a = new Reimbursement();
                a.setId(rs.getInt("request_id"));
                a.setAmount(rs.getInt("amount"));
                a.setSubmitted(rs.getString("submitted"));
                a.setResolved(rs.getString("resolved"));
                a.setDescription(rs.getString("description"));
                a.setAuthor_id(rs.getInt("author_id"));
                a.setResolver_id(rs.getInt("resolver_id"));
                a.setReimbursementType(rs.getString("reimbursementType"));
                a.setReimbursementStatus(rs.getString("reimbursementStatus"));

                reimbursements.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prst != null) {
                    prst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reimbursements;
    }

    public Reimbursement getReimbursement(int requestid) {
        Reimbursement reimbursement = new Reimbursement();
        Connection conn = null;
        PreparedStatement prst = null;

        try {
            conn = ConnectionFactory.getConnection();

            String sql = "SELECT * FROM reimbursementee WHERE request_id = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, requestid);


            ResultSet rs = prst.executeQuery();
            if (rs.next()){
                reimbursement.setId(rs.getInt("request_id"));
                reimbursement.setAmount(rs.getInt("amount"));
                reimbursement.setSubmitted(rs.getString("submitted"));
                reimbursement.setResolved(rs.getString("resolved"));
                reimbursement.setDescription(rs.getString("description"));
                reimbursement.setAuthor_id(rs.getInt("author_id"));
                reimbursement.setResolver_id(rs.getInt("resolver_id"));
                reimbursement.setReimbursementType(rs.getString("reimbursementType"));
                reimbursement.setReimbursementStatus(rs.getString("reimbursementStatus"));
            }else {
                reimbursement.setId(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prst != null) {
                    prst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reimbursement;
    }

    public List<Reimbursement> getReimbursementByUserid(int author_id) {
        List<Reimbursement>  reimbursement = new ArrayList<>();
        Connection conn = null;
        PreparedStatement prst = null;

        try {
            conn = ConnectionFactory.getConnection();

            String sql = "SELECT * FROM Reimbursement WHERE author_id = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, author_id);


            ResultSet rs = prst.executeQuery();
            while (rs.next()){
                Reimbursement a = new Reimbursement();
                a.setId(rs.getInt("request_id"));
                a.setAmount(rs.getInt("amount"));
                a.setSubmitted(rs.getString("submitted"));
                a.setResolved(rs.getString("resolved"));
                a.setDescription(rs.getString("description"));
                a.setAuthor_id(rs.getInt("author_id"));
                a.setResolver_id(rs.getInt("resolver_id"));
                a.setReimbursementType(rs.getString("reimbursementType"));
                a.setReimbursementStatus(rs.getString("reimbursementStatus"));
                reimbursement.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prst != null) {
                    prst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reimbursement;
    }

    public Reimbursement saveReimbursement(Reimbursement reimbursementToSave) throws Exception {
        Connection conn = null;
        PreparedStatement prst = null;
        int success = 0;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO reimbursementsee VALUES (?,?,?,?,?,?,?,?,?)";

            // Setup PreparedStatement
            prst = conn.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            prst.setInt(1, reimbursementToSave.getId());
            prst.setDouble(2, reimbursementToSave.getAmount());
            prst.setString(3, reimbursementToSave.getSubmitted());
            prst.setString(4, reimbursementToSave.getResolved());
            prst.setString(5, reimbursementToSave.getDescription());
            prst.setInt(6, reimbursementToSave.getAuthor_id());
            prst.setInt(7, reimbursementToSave.getResolver_id());
            prst.setString(8, reimbursementToSave.getReimbursementEnum());
            prst.setString(9, reimbursementToSave.getReimbursementStatus());



            success = prst.executeUpdate();
            return reimbursementToSave;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prst != null)
                    prst.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            // then update didn't occur, throw an exception
            throw new Exception("Insert Reimbursement failed: " + reimbursementToSave);
        }

        return reimbursementToSave;
    }


    public void updateReimbursementStatus(Reimbursement reimbursementStatusToUpdate) throws Exception {

        Connection conn = null;
        PreparedStatement prst = null;
        int success = 0;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "update reimbursementsee set reimbursementStatus = ?,resolved = ? ,resolver_id = ? where request_id = ?";


            // Setup PreparedStatement
            prst = conn.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            prst.setString(1, reimbursementStatusToUpdate.getReimbursementStatus());
            prst.setString(2, reimbursementStatusToUpdate.getResolved());
            prst.setInt(3, reimbursementStatusToUpdate.getResolver_id());
            prst.setInt(4, reimbursementStatusToUpdate.getId());


            success = prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prst != null)
                    prst.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            // then update didn't occur, throw an exception
            throw new Exception("Failed to Update Reimbursement Status > : " + reimbursementStatusToUpdate);
        }
    }
}
