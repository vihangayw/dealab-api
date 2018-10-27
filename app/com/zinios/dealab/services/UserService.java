package com.zinios.dealab.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.zinios.dealab.dao.UserDao;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.User;
import com.zinios.dealab.utils.JWTUtil;
import com.zinios.dealab.utils.PasswordUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vihanga on 08/13/2018.
 */
public class UserService {

	@Inject
	JWTUtil jwtUtil;
	@Inject
	@Named("user")
	private UserDao userDao;

	public User addUser(User user) {
		try {
			Transaction transaction = Ebean.beginTransaction();
			User addedUser = userDao.add(user);

			if (addedUser == null) {
				transaction.end();
				return null;
			} else {
				transaction.commit();
				transaction.end();
				return addedUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public User addUserCompany(User user) {
		try {
			Transaction transaction = Ebean.beginTransaction();
			User update = userDao.update(user);

			if (update == null) {
				transaction.end();
				return null;
			} else {
				transaction.commit();
				transaction.end();
				return update;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User findByName(String username, Company company) {
		return userDao.findByUserName(username, company);
	}

	public User findByCompany(Company company) {
		return userDao.findByCompany(company);
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User find(Long id) {
		return userDao.find(id);
	}

	public List<User> getSortedUserList(int offset, int limit) {
		return userDao.getSortedList(offset, limit);
	}

//	public User updateUser(User companyOld, User companyNew) {
//
//		companyOld.setName(companyNew.getName());
//		companyOld.setLogo(companyNew.getLogo());
//
//		Transaction transaction = Ebean.beginTransaction();
//		try {
//			User updatedUser = userDao.update(companyOld);
//
//			if (updatedUser != null) {
//				transaction.commit();
//				transaction.end();
//				return updatedUser;
//			} else {
//				transaction.end();
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			transaction.end();
//			return null;
//		}
//	}

	public User login(String userName, String password, Company company) {

		try {
			Transaction transaction = Ebean.beginTransaction();
			User login = userDao.login(userName, PasswordUtils.encrypt(password), company);
			if (login == null) {
				transaction.end();
				return null;
			}

			login.setToken(jwtUtil.createAuthTokenCompany(login.getId(), login.getCompany().getId()));
			User updatedUser = userDao.update(login);

			if (updatedUser != null) {
				transaction.commit();
				transaction.end();
				return updatedUser;
			} else {
				transaction.end();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User login(String userName, String password) {

		try {
			Transaction transaction = Ebean.beginTransaction();
			User login = userDao.login(userName, PasswordUtils.encrypt(password));
			if (login == null) {
				transaction.end();
				return null;
			}

			login.setToken(jwtUtil.createAuthToken(login.getEmail(), String.valueOf(login.getId())));
			User updatedUser = userDao.update(login);

			if (updatedUser != null) {
				transaction.commit();
				transaction.end();
				return updatedUser;
			} else {
				transaction.end();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User setPassword(User companyOld, String password) {

		try {
			Transaction transaction = Ebean.beginTransaction();
			companyOld.setPassword(PasswordUtils.encrypt(password));
			companyOld.setToken(jwtUtil.createAuthToken(companyOld.getEmail(), String.valueOf(companyOld.getId())));

			User updatedUser = userDao.update(companyOld);

			if (updatedUser != null) {
				transaction.commit();
				transaction.end();
				return updatedUser;
			} else {
				transaction.end();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	public User findByEmailID(String email, String id) {
//		return userDao.findByEmailId(email, id);
//	}
}
