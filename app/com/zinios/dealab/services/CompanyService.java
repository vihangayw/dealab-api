package com.zinios.dealab.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.zinios.dealab.dao.CompanyDao;
import com.zinios.dealab.models.Company;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vihanga on 08/10/2018.
 */
public class CompanyService {

	@Inject
	@Named("company")
	private CompanyDao companyDao;

	public Company addCompany(Company company) {
		try {
			Transaction transaction = Ebean.beginTransaction();
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
			return null;
		}
	}

	public Company findByEmail(String email) {
		return companyDao.findByEmail(email);
	}

	public Company find(String id) {
		return companyDao.findById(Long.valueOf(id));
	}

	public Company find(Long id) {
		return companyDao.findById(id);
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
			Transaction transaction = Ebean.beginTransaction();
			Company updatedCompany = companyDao.update(companyOld);

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
			Transaction transaction = Ebean.beginTransaction();
			Company updatedCompany = companyDao.update(company);

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

}
