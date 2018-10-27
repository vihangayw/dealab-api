package com.zinios.dealab.dao.impl;

import com.avaje.ebean.Model;
import com.zinios.dealab.dao.DealDao;
import com.zinios.dealab.models.Deal;

public class DealDaoImpl implements DealDao {

	private static Model.Find<Long, Deal> find = new Model.Finder<>(Deal.class);

	@Override
	public Deal add(Deal entityToAdd) {
		entityToAdd.save();
		return entityToAdd;
	}

	@Override
	public Deal update(Deal entityToUpdate) {
		entityToUpdate.update();
		return entityToUpdate;
	}

	@Override
	public Deal delete(Deal entityToDelete) {
		entityToDelete.delete();
		return entityToDelete;
	}

	@Override
	public Deal find(Long idToFind) {
		return find.byId(idToFind);
	}


}
