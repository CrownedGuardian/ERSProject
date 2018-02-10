package com.revature.model;

import java.sql.Timestamp;

public class Reimbursement {
	private int reimbursementId, authorId, resolverId, statusId, typeId;
	private Timestamp timeSubmitted, timeResolved;
	private String description;
	private Receipt receipt;
	private double amount;
	
	public Reimbursement() {
		
	}
	
	public Reimbursement(int reimbursementId, double amount, Timestamp timeSubmitted, Timestamp timeResolved, String description, 
						 Receipt receipt, int authorId, int resolverId, int statusId, int typeId) {
		super();
		this.reimbursementId = reimbursementId;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.receipt = receipt;
		this.amount = amount;
	}
	
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}
	public void setTimeSubmitted(Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}
	public Timestamp getTimeResolved() {
		return timeResolved;
	}
	public void setTimeResolved(Timestamp timeResolved) {
		this.timeResolved = timeResolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + authorId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + reimbursementId;
		result = prime * result + resolverId;
		result = prime * result + statusId;
		result = prime * result + ((timeResolved == null) ? 0 : timeResolved.hashCode());
		result = prime * result + ((timeSubmitted == null) ? 0 : timeSubmitted.hashCode());
		result = prime * result + typeId;
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (authorId != other.authorId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (statusId != other.statusId)
			return false;
		if (timeResolved == null) {
			if (other.timeResolved != null)
				return false;
		} else if (!timeResolved.equals(other.timeResolved))
			return false;
		if (timeSubmitted == null) {
			if (other.timeSubmitted != null)
				return false;
		} else if (!timeSubmitted.equals(other.timeSubmitted))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}
	
	
}
