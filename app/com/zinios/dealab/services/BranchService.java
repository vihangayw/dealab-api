package com.zinios.dealab.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.zinios.dealab.dao.BranchDao;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.entity.Images;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vihanga on 08/10/2018.
 */
public class BranchService {

	@Inject
	@Named("branch")
	private BranchDao branchDao;

	public Branch addBranch(Branch company) {
		try {
			Transaction transaction = Ebean.beginTransaction();
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
			return null;
		}
	}

	public Branch find(String id) {
		return branchDao.findById(Long.valueOf(id));
	}

	public Branch find(Long id) {
		return branchDao.findById(id);
	}

	public List<Images> branchImages(Long id) {
		return branchDao.branchImages(id);
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
			Transaction transaction = Ebean.beginTransaction();
			Branch updatedBranch = branchDao.update(companyOld);

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

	public Branch update(Branch b) {

		try {
			Transaction transaction = Ebean.beginTransaction();
			Branch updatedBranch = branchDao.update(b);

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
