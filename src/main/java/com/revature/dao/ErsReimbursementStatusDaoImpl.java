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

import com.revature.model.ErsStatus;

public class ErsReimbursementStatusDaoImpl implements ErsReimbursementStatusDao{

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
	
	@Override
	public boolean insertStatus(ErsStatus s) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "INSERT INTO ers_reimbursement_status(reimb_status_id, reimb_status) VALUES(?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, s.getId());
			ps.setString(2, s.getStatus());
			ps.executeUpdate();
			isSuccessful = true;
		} catch(SQLException e) {
			e.printStackTrace();
			isSuccessful = false;
		}
		return isSuccessful;
	}

	@Override
	public List<ErsStatus> selectAllStatuses() {
		// TODO Auto-generated method stub
		List<ErsStatus> s = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement_status";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				s.add(new ErsStatus(rs.getInt(1), rs.getString(2)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public ErsStatus selectStatusById(int id) {
		// TODO Auto-generated method stub
		ErsStatus s = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement_status WHERE reimb_status_id = '" + id + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				s = new ErsStatus(rs.getInt(1), rs.getString(2));
			}
		} catch(SQLException e) {
			System.out.println("No statuses in the table");
		}
		return s;
	}

	@Override
	public boolean updateStatus(ErsStatus s) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call update_status(?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, s.getId());
			cs.setString(2, s.getStatus());
			int status = cs.executeUpdate();
			isSuccessful = true;
			System.out.println("CallableStatement returns: " + status);
		} catch(SQLException e) {
			e.printStackTrace();
			isSuccessful = false;
		}
		return isSuccessful;
	}

	@Override
	public boolean deleteStatus(ErsStatus s) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call delete_status(?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, s.getId());
			cs.setString(2, s.getStatus());
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
