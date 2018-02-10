package com.revature.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class Receipt implements Blob {
	private Employee employee;
	private Reimbursement reimbursement;
	
	public Receipt() {
		
	}
	
	public Receipt(Employee employee, Reimbursement reimbursement) {
		super();
		this.employee = employee;
		this.reimbursement = reimbursement;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Reimbursement getReimbursement() {
		return reimbursement;
	}
	public void setReimbursement(Reimbursement reimbursement) {
		this.reimbursement = reimbursement;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((reimbursement == null) ? 0 : reimbursement.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receipt other = (Receipt) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (reimbursement == null) {
			if (other.reimbursement != null)
				return false;
		} else if (!reimbursement.equals(other.reimbursement))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Receipt: [Name: " + getEmployee().getFirstName() + getEmployee().getLastName() + 
					   "\nUsername: " + getEmployee().getUsername() + 
					   "\nEmail Address: " + getEmployee().getEmail() + 
					   "\nReimbursement ID: " + getReimbursement().getReimbursementId() +
					   "\nAmount: " + getReimbursement().getAmount() + 
					   "\nDescription: " + getReimbursement().getDescription() + 
					   "\nTime Submitted: " + getReimbursement().getTimeSubmitted() + "]";
	}

	@Override
	public void free() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputStream getBinaryStream() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getBinaryStream(long arg0, long arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes(long arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long length() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long position(byte[] arg0, long arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long position(Blob arg0, long arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OutputStream setBinaryStream(long arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setBytes(long arg0, byte[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setBytes(long arg0, byte[] arg1, int arg2, int arg3) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void truncate(long arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
