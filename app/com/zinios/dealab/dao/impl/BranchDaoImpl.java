package com.zinios.dealab.dao.impl;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;
import com.zinios.dealab.dao.BranchDao;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.utils.Constants;

import java.util.List;

public class BranchDaoImpl implements BranchDao {
	private static Model.Find<Long, Branch> find = new Model.Finder<>(Branch.class);

	@Override
	public Branch findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Branch> getSortedInactiveBranchList(Company company, int offset, int limit) {
		return find.where()
				.eq("company", company)
				.eq("status", Constants.STATUS_INACTIVE)
				.orderBy("id desc").findPagedList(offset, limit).getList();
	}

	@Override
	public List<Branch> getSortedList(Company company, int offset, int limit) {
		return find.where()
				.eq("company", company)
				.eq("status", Constants.STATUS_ACTIVE)
				.orderBy("name").findPagedList(offset, limit).getList();
	}

	@Override
	public Branch isExists(Company company, String name, String placeId) {
		return find.where()
				.eq("company", company)
				.or(
						Expr.eq("name", name),
						Expr.eq("place_id", placeId)
				)
				.findUnique();
	}

	@Override
	public Branch add(Branch entityToAdd) {
		entityToAdd.save();
		return entityToAdd;
	}

	@Override
	public Branch update(Branch entityToUpdate) {
		entityToUpdate.update();
		return entityToUpdate;
	}

	@Override
	public Branch delete(Branch entityToDelete) {
		return null;
	}

	@Override
	public Branch find(Long idToFind) {
		return find.byId(idToFind);
	}
}
