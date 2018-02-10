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

import com.revature.model.User;

public class ErsUsersDaoImpl implements ErsUsersDao{
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
	
	/*public static void main(String[] args) {
		ErsUsersDaoImpl.insertUser(new User(null, "negstephen", "p4ssw0rd", "Stephen", "Negron", "snegron@csumb.edu", 1));
	}*/
	
	@Override
	public boolean insertUser(User u) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			System.out.println("Entered try block");
			String sql = "INSERT INTO ers_users(ers_username, ers_password, user_first_name, " +
						 "user_last_name, user_email, user_role_id) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getRoleId());
			ps.executeUpdate();
			isSuccessful = true;
		} catch(SQLException e) {
			e.printStackTrace();
			isSuccessful = false;
		}
		return isSuccessful;
	}
	@Override
	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		List<User> u = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_users";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				u.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	@Override
	public User selectUserById(int id) {
		// TODO Auto-generated method stub
		User u = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_users WHERE ers_users_id = '" + id + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("No users in the table");
		}
		return u;
	}
	@Override
	public User selectUserByUsername(String user) {
		// TODO Auto-generated method stub
		User u = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_users WHERE ers_username = '" + user + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("No users in the table with specified attribute");
		}
		return u;
	}
	@Override
	public List<User> selectUsersByFirstName(String name) {
		// TODO Auto-generated method stub
		List<User> u = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_users WHERE user_first_name = '" + name + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	@Override
	public List<User> selectUsersByLastName(String name) {
		// TODO Auto-generated method stub
		List<User> u = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_users WHERE user_last_name = '" + name + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	@Override
	public User selectUserByEmail(String email) {
		// TODO Auto-generated method stub
		User u = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_users WHERE user_email = '" + email + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7));
			}
		} catch(SQLException e) {
			System.out.println("No users in the table with specified attribute");
		}
		return u;
	}
	@Override
	public List<User> selectUsersByRole(String role) {
		// TODO Auto-generated method stub
		List<User> u = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_users u INNER JOIN ers_user_roles r ON u.user_role_id = r.ers_user_role_id " + 
					 "WHERE r.user_role = '" + role + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call update_user(?, ?, ?, ?, ?, ?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, u.getUserId());
			cs.setString(2, u.getUsername());
			cs.setString(3, u.getPassword());
			cs.setString(4, u.getFirstName());
			cs.setString(5, u.getLastName());
			cs.setString(6, u.getEmail());
			cs.setInt(7, u.getRoleId());
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
	public boolean deleteUser(User u) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call delete_user(?, ?, ?, ?, ?, ?, ?) }";
			//String errorMessage = null;
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, u.getUserId());
			cs.setString(2, u.getUsername());
			cs.setString(3, u.getPassword());
			cs.setString(4, u.getFirstName());
			cs.setString(5, u.getLastName());
			cs.setString(6, u.getEmail());
			cs.setInt(7, u.getRoleId());
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
