package com.zinios.dealab.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.zinios.dealab.dao.CompanyDao;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.utils.DateTimeUtils;
import com.zinios.dealab.utils.JWTUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vihanga on 08/10/2018.
 */
public class CompanyService {

	@Inject
	JWTUtil jwtUtil;
	@Inject
	private DateTimeUtils dateTimeUtils;
	@Inject
	@Named("company")
	private CompanyDao companyDao;

	public Company addCompany(Company company) {


		Transaction transaction = Ebean.beginTransaction();
		try {
			Company addedCompany = companyDao.add(company);

			if (addedCompany == null) {
				transaction.end();
				return null;
			} else {
				transaction.commit();
				transaction.end();
				return addedCompany;
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.end();
			return null;
		}
	}

	public Company findByEmail(String email) {
		return companyDao.findByEmail(email);
	}

	public Company find(String id) {
		return companyDao.findById(Long.valueOf(id));
	}

	public List<Company> getSortedCompanyList(int offset, int limit) {
		return companyDao.getSortedList(offset, limit);
	}

	public List<Company> getSortedInactiveCompanyList(int offset, int limit) {
		return companyDao.getSortedInactiveCompanyList(offset, limit);
	}

	public Company updateCompany(Company companyOld, Company companyNew) {

		if (companyNew.getName() != null)
			companyOld.setName(companyNew.getName());
		if (companyNew.getLogo() != null)
			companyOld.setLogo(companyNew.getLogo());

		try {
			Company updatedCompany = companyDao.update(companyOld);

			Transaction transaction = Ebean.beginTransaction();
			if (updatedCompany != null) {
				transaction.commit();
				transaction.end();
				return updatedCompany;
			} else {
				transaction.end();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Company update(Company company) {

		try {
			Company updatedCompany = companyDao.update(company);

			Transaction transaction = Ebean.beginTransaction();
			if (updatedCompany != null) {
				transaction.commit();
				transaction.end();
				return updatedCompany;
			} else {
				transaction.end();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Company login(Company companyOld) {
//
//		Transaction transaction = Ebean.beginTransaction();
//		try {
//			companyOld.setAuth(jwtUtil.createAuthTokenCompany(companyOld.getEmail(), companyOld.getId()));
//			Company updatedCompany = companyDao.update(companyOld);
//
//			if (updatedCompany != null) {
//				transaction.commit();
//				transaction.end();
//				return updatedCompany;
//			} else {
//				transaction.end();
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			transaction.end();
		return null;
//		}
	}

	public Company setPassword(Company companyOld, String password) {

//		Transaction transaction = Ebean.beginTransaction();
//		try {
//			companyOld.setPassword(PasswordUtils.encrypt(password));
//			companyOld.setAuth(jwtUtil.createAuthTokenCompany(companyOld.getEmail(), companyOld.getId()));
//
//			Company updatedCompany = companyDao.update(companyOld);
//
//			if (updatedCompany != null) {
//				transaction.commit();
//				transaction.end();
//				return updatedCompany;
//			} else {
//				transaction.end();
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			transaction.end();
		return null;
//		}
	}

	public Company findByEmailID(String email, String id) {
		return companyDao.findByEmailId(email, id);
	}
}
