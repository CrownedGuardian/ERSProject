package com.revature.dao;

import java.util.List;

import com.revature.model.ErsStatus;


public interface ErsReimbursementStatusDao {
	//CREATE
	public boolean insertStatus(ErsStatus s);
	//READ
	public List<ErsStatus> selectAllStatuses();
	public ErsStatus selectStatusById(int id);
	//UPDATE
	public boolean updateStatus(ErsStatus s);
	//DELETE
	public boolean deleteStatus(ErsStatus s);
}
