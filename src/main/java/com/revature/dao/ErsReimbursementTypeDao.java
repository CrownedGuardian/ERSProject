package com.revature.dao;

import java.util.List;

import com.revature.model.ErsType;

public interface ErsReimbursementTypeDao {
	//CREATE
		public boolean insertType(ErsType t);
		//READ
		public List<ErsType> selectAllTypes();
		public ErsType selectTypeById(int id);
		//UPDATE
		public boolean updateType(ErsType t);
		//DELETE
		public boolean deleteType(ErsType t);
}
