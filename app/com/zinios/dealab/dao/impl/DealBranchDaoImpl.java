package com.zinios.dealab.dao.impl;

import com.zinios.dealab.dao.DealBranchDao;
import com.zinios.dealab.models.DealBranch;

public class DealBranchDaoImpl implements DealBranchDao {
	@Override
	public DealBranch add(DealBranch entityToAdd) {
		entityToAdd.save();
		return entityToAdd;
	}

	@Override
	public DealBranch update(DealBranch entityToUpdate) {
		return null;
	}

	@Override
	public DealBranch delete(DealBranch entityToDelete) {
		return null;
	}

	@Override
	public DealBranch find(Long idToFind) {
		return null;
	}
}
