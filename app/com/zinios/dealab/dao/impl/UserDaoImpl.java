package com.zinios.dealab.dao.impl;

import com.avaje.ebean.Model;
import com.zinios.dealab.dao.UserDao;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
	private static Model.Find<Long, User> find = new Model.Finder<>(User.class);

	@Override
	public User login(String userName, String password, Company company) {
		return find.where()
				.eq("company", company)
				.eq("email", userName)
				.eq("password", password)
				.findUnique();
	}

	@Override
	public User login(String userName, String password) {
		return find.where()
				.eq("email", userName)
				.eq("password", password)
				.findUnique();
	}

	@Override
	public User findByUserName(String userName, Company company) {
		return find.where()
				.eq("company", company)
				.eq("email", userName)
				.findUnique();
	}

	@Override
	public User findByEmail(String email) {
		return find.where()
				.eq("email", email)
				.findUnique();
	}

	@Override
	public List<User> getSortedList(int offset, int limit) {
		return null;
	}

	@Override
	public User add(User entityToAdd) {
		entityToAdd.save();
		return entityToAdd;
	}

	@Override
	public User update(User entityToUpdate) {
		entityToUpdate.update();
		return entityToUpdate;
	}

	@Override
	public User delete(User entityToDelete) {
		return null;
	}

	@Override
	public User find(Long idToFind) {
		return find.byId(idToFind);
	}
}
