package com.zinios.dealab.dao.impl;

import com.avaje.ebean.Model;
import com.zinios.dealab.dao.CompanyDao;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.utils.Constants;

import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
	private static Model.Find<Long, Company> find = new Model.Finder<>(Company.class);

	@Override
	public Company add(Company company) {
		company.save();
		return company;
	}

	@Override
	public Company findByEmail(String email) {
		return find.where().eq("email", email).findUnique();
	}

	@Override
	public Company findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Company> getSortedInactiveCompanyList(int offset, int limit) {
		return find.where()
				.eq("status", Constants.STATUS_INACTIVE)
				.orderBy("id desc").findPagedList(offset, limit).getList();
	}

	@Override
	public List<Company> getSortedList(int offset, int limit) {
		return find.where()
				.eq("status", Constants.STATUS_ACTIVE)
				.orderBy("name")
				.findPagedList(offset, limit).getList();
	}

	@Override
	public Company findByEmailId(String email, String id) {
		return find.where()
				.eq("email", email)
				.ne("id", email).findUnique();
	}

	@Override
	public Company update(Company entityToUpdate) {
		entityToUpdate.update();
		return entityToUpdate;
	}

	@Override
	public Company delete(Company entityToDelete) {
		return null;
	}

	@Override
	public Company find(Long idToFind) {
		return null;
	}
}
