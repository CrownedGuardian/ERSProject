package com.revature.dao;

import java.util.List;

import com.revature.model.UserRole;

public interface ErsUserRolesDao {
	// CREATE
	public boolean insertRole(UserRole r);
	// READ
	public List<UserRole> selectAllRoles();
	public UserRole selectUserRoleById(int id);
	// UPDATE
	public boolean updateRole(UserRole r);
	// DELETE
	public boolean deleteRole(UserRole r);
}
