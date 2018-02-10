package com.revature.service;

import java.util.List;

import com.revature.dao.ErsReimbursementDao;
import com.revature.dao.ErsReimbursementDaoImpl;
import com.revature.dao.ErsUsersDao;
import com.revature.dao.ErsUsersDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ErsReimbursementServiceImpl implements ErsReimbursementService{

	private ErsReimbursementDao dao = new ErsReimbursementDaoImpl();
	
	@Override
	public boolean addReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return dao.insertReimbursement(r);
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		// TODO Auto-generated method stub
		return dao.selectAllReimbursements();
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		// TODO Auto-generated method stub
		return dao.selectReimbursementById(id);
	}

	@Override
	public List<Reimbursement> getReimbursementsByAuthorUsername(String username) {
		// TODO Auto-generated method stub
		ErsUsersDao userDao = new ErsUsersDaoImpl();
		User u = userDao.selectUserByUsername(username);
		return dao.selectReimbursementsByAuthorName(u.getFirstName(), u.getLastName());
	}

	@Override
	public List<Reimbursement> getReimbursementsByResolverUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByStatus(String status) {
		// TODO Auto-generated method stub
		return dao.selectReimbursementsByStatus(status);
	}

	@Override
	public List<Reimbursement> getReimbursementsByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return dao.updateReimbursement(r);
	}
}
