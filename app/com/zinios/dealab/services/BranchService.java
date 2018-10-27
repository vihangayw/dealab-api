package com.zinios.dealab.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.zinios.dealab.dao.BranchDao;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.utils.DateTimeUtils;
import com.zinios.dealab.utils.JWTUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vihanga on 08/10/2018.
 */
public class BranchService {

	@Inject
	JWTUtil jwtUtil;
	@Inject
	private DateTimeUtils dateTimeUtils;
	@Inject
	@Named("branch")
	private BranchDao branchDao;

	public Branch addBranch(Branch company) {


		Transaction transaction = Ebean.beginTransaction();
		try {
			Branch addedBranch = branchDao.add(company);

			if (addedBranch == null) {
				transaction.end();
				return null;
			} else {
				transaction.commit();
				transaction.end();
				return addedBranch;
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.end();
			return null;
		}
	}

	public Branch find(String id) {
		return branchDao.findById(Long.valueOf(id));
	}

	public List<Branch> getSortedBranchList(Company company, int offset, int limit) {
		return branchDao.getSortedList(company, offset, limit);
	}

	public List<Branch> getSortedInactiveBranchList(Company company, int offset, int limit) {
		return branchDao.getSortedInactiveBranchList(company, offset, limit);
	}

	public Branch updateBranch(Branch companyOld, Branch companyNew) {

		if (companyNew.getName() != null)
			companyOld.setName(companyNew.getName());
		if (companyNew.getAddress() != null)
			companyOld.setAddress(companyNew.getAddress());
		if (companyNew.getContact() != null)
			companyOld.setContact(companyNew.getContact());
		if (companyNew.getNotes() != null)
			companyOld.setNotes(companyNew.getNotes());
		if (companyNew.getLandmark() != null)
			companyOld.setLandmark(companyNew.getLandmark());

		try {
			Branch updatedBranch = branchDao.update(companyOld);

			Transaction transaction = Ebean.beginTransaction();
			if (updatedBranch != null) {
				transaction.commit();
				transaction.end();
				return updatedBranch;
			} else {
				transaction.end();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Branch isExisits(Company company, String name, String placeId) {
		return branchDao.isExists(company, name, placeId);
	}

	public Branch update(Branch company) {

		try {
			Branch updatedBranch = branchDao.update(company);

			Transaction transaction = Ebean.beginTransaction();
			if (updatedBranch != null) {
				transaction.commit();
				transaction.end();
				return updatedBranch;
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
