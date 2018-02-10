package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ErsReimbursementDao {
	//CREATE
	public boolean insertReimbursement(Reimbursement r);
	//READ
	public List<Reimbursement> selectAllReimbursements();
	public Reimbursement selectReimbursementById(int id);
	public List<Reimbursement> selectReimbursementsByAuthorName(String firstName, String lastName);
	public List<Reimbursement> selectReimbursementsByResolverName(String firstName, String lastName);
	public List<Reimbursement> selectReimbursementsByStatus(String status);
	public List<Reimbursement> selectReimbursementsByType(String type);
	//UPDATE
	public boolean updateReimbursement(Reimbursement r);
	//DELETE
	public boolean deleteReimbursement(Reimbursement r);
}
