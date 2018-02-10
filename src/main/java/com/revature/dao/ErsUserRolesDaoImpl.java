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

import com.revature.model.UserRole;

public class ErsUserRolesDaoImpl implements ErsUserRolesDao{

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
	public boolean insertRole(UserRole r) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "INSERT INTO ers_user_roles(ers_user_role_id, user_role) VALUES(?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getId());
			ps.setString(2, r.getRole());
			ps.executeUpdate();
			isSuccessful = true;
		} catch(SQLException e) {
			e.printStackTrace();
			isSuccessful = false;
		}
		return isSuccessful;
	}

	@Override
	public List<UserRole> selectAllRoles() {
		// TODO Auto-generated method stub
		List<UserRole> r = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_user_roles";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				r.add(new UserRole(rs.getInt(1), rs.getString(2)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public UserRole selectUserRoleById(int id) {
		// TODO Auto-generated method stub
		UserRole r = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_user_roles WHERE ers_user_role_id = '" + id + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r = new UserRole(rs.getInt(1), rs.getString(2));
			}
		} catch(SQLException e) {
			System.out.println("No statuses in the table");
		}
		return r;
	}

	@Override
	public boolean updateRole(UserRole r) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call update_role(?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, r.getId());
			cs.setString(2, r.getRole());
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
	public boolean deleteRole(UserRole r) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call delete_role(?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, r.getId());
			cs.setString(2, r.getRole());
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
