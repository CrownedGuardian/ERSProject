package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Receipt;
import com.revature.model.Reimbursement;

public class ErsReimbursementDaoImpl implements ErsReimbursementDao{
	
	private static String url= "jdbc:oracle:thin:@localhost:1521:xe";
	private static String username = "ers";
	private static String password = "p4ssw0rd";
	
	static{
	       try {
	           Class.forName("oracle.jdbc.driver.OracleDriver");
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }
	   }

	public boolean insertReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "INSERT INTO ers_reimbursement(reimb_amount, reimb_submitted, reimb_description, " +
						 "reimb_author, reimb_status_id, reimb_type_id) " + 
						 "VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, r.getAmount());
			ps.setTimestamp(2, r.getTimeSubmitted());
			ps.setString(3, r.getDescription());
			ps.setInt(4, r.getAuthorId());
			ps.setInt(5, r.getStatusId());
			ps.setInt(6, r.getTypeId());
			ps.executeUpdate();
			isSuccessful = true;
		} catch(SQLException e) {
			e.printStackTrace();
			isSuccessful = false;
		}
		return isSuccessful;
	}

	public List<Reimbursement> selectAllReimbursements() {
		// TODO Auto-generated method stub
		List<Reimbursement> r = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), 
										(Receipt)rs.getBlob(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public Reimbursement selectReimbursementById(int id) {
		// TODO Auto-generated method stub
		Reimbursement r = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = '" + id + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), 
						(Receipt)rs.getBlob(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
			}
		} catch(SQLException e) {
			System.out.println("No reimbursements in the table");
		}
		return r;
	}

	public List<Reimbursement> selectReimbursementsByAuthorName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		List<Reimbursement> r = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement r INNER JOIN ers_users u ON r.reimb_author = u.ers_users_id " + 
						 "WHERE u.user_first_name = '" + firstName + "' AND u.user_last_name = '" + lastName + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), 
										(Receipt)rs.getBlob(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public List<Reimbursement> selectReimbursementsByResolverName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		List<Reimbursement> r = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement r INNER JOIN ers_users u ON r.reimb_resolver = u.ers_users_id " + 
						 "WHERE u.user_first_name = '" + firstName + "' AND u.user_last_name = '" + lastName + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), 
										(Receipt)rs.getBlob(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public List<Reimbursement> selectReimbursementsByStatus(String status) {
		// TODO Auto-generated method stub
		List<Reimbursement> r = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement r INNER JOIN ers_reimbursement_status s ON r.reimb_status_id = s.reimb_status_id " + 
						 "WHERE s.reimb_status = '" + status + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), 
										(Receipt)rs.getBlob(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public List<Reimbursement> selectReimbursementsByType(String type) {
		// TODO Auto-generated method stub
		List<Reimbursement> r = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement r INNER JOIN ers_reimbursement_type t ON r.reimb_type_id = t.reimb_type_id " + 
						 "WHERE s.reimb_type = '" + type + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), 
										(Receipt)rs.getBlob(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public boolean updateReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call update_reimbursement(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, r.getReimbursementId());
			cs.setDouble(2, r.getAmount());
			cs.setTimestamp(3, r.getTimeSubmitted());
			cs.setTimestamp(4, r.getTimeResolved());
			cs.setString(5, r.getDescription());
			cs.setBlob(6, r.getReceipt());
			cs.setInt(7, r.getAuthorId());
			cs.setInt(8, r.getResolverId());
			cs.setInt(9, r.getStatusId());
			cs.setInt(10, r.getTypeId());
			int status = cs.executeUpdate();
			isSuccessful = true;
			System.out.println("CallableStatement returns: " + status);
		} catch(SQLException e) {
			e.printStackTrace();
			isSuccessful = false;
		}
		return isSuccessful;
	}

	public boolean deleteReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call delete_reimbursement(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, r.getReimbursementId());
			cs.setDouble(2, r.getAmount());
			cs.setTimestamp(3, r.getTimeSubmitted());
			cs.setTimestamp(4, r.getTimeResolved());
			cs.setString(5, r.getDescription());
			cs.setBlob(6, r.getReceipt());
			cs.setInt(7, r.getAuthorId());
			cs.setInt(8, r.getResolverId());
			cs.setInt(9, r.getStatusId());
			cs.setInt(10, r.getTypeId());
			int status = cs.executeUpdate();
			isSuccessful = true;
			System.out.println("CallableStatement returns: " + status);
		} catch(SQLException e) {
			e.printStackTrace();
			isSuccessful = false;
		}
		return isSuccessful;
	}

}
