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

import com.revature.model.ErsType;

public class ErsReimbursementTypeDaoImpl implements ErsReimbursementTypeDao{

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
	public boolean insertType(ErsType t) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "INSERT INTO ers_reimbursement_type(reimb_type_id, reimb_type) VALUES(?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getId());
			ps.setString(2, t.getType());
			ps.executeUpdate();
			isSuccessful = true;
		} catch(SQLException e) {
			e.printStackTrace();
			isSuccessful = false;
		}
		return isSuccessful;
	}

	@Override
	public List<ErsType> selectAllTypes() {
		// TODO Auto-generated method stub
		List<ErsType> t = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement_type";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				t.add(new ErsType(rs.getInt(1), rs.getString(2)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public ErsType selectTypeById(int id) {
		// TODO Auto-generated method stub
		ErsType t = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement_type WHERE reimb_type_id = '" + id + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				t = new ErsType(rs.getInt(1), rs.getString(2));
			}
		} catch(SQLException e) {
			System.out.println("No types in the table");
		}
		return t;
	}

	@Override
	public boolean updateType(ErsType t) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call update_type(?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, t.getId());
			cs.setString(2, t.getType());
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
	public boolean deleteType(ErsType t) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call delete_type(?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, t.getId());
			cs.setString(2, t.getType());
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
