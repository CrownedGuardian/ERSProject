package com.revature.service;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ErsReimbursementService {
	public boolean addReimbursement(Reimbursement r);
	public List<Reimbursement> getAllReimbursements();
	public Reimbursement getReimbursementById(int id);
	public List<Reimbursement> getReimbursementsByAuthorUsername(String username);
	public List<Reimbursement> getReimbursementsByResolverUsername(String username);
	public List<Reimbursement> getReimbursementsByStatus(String status);
	public List<Reimbursement> getReimbursementsByType(String type);
	public boolean updateReimbursement(Reimbursement r);	
}
