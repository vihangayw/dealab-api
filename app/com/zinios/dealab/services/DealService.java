package com.zinios.dealab.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.zinios.dealab.dao.DealBranchDao;
import com.zinios.dealab.dao.DealDao;
import com.zinios.dealab.models.Deal;
import com.zinios.dealab.models.DealBranch;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by vihanga on 08/13/2018.
 */
public class DealService {

	@Inject
	@Named("deal")
	private DealDao dealDao;
	@Inject
	@Named("deal_branch")
	private DealBranchDao dealBranchDao;

	public Deal addDeal(Deal deal) {
		Transaction transaction = Ebean.beginTransaction();
		try {
			Deal addedDeal = dealDao.add(deal);

			if (addedDeal == null) {
				transaction.end();
				return null;
			} else {
				transaction.commit();
				transaction.end();
				return addedDeal;
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.end();
			return null;
		}
	}

	public Deal find(Long id) {
		return dealDao.find(id);
	}

	public void assignBRanchDeal(DealBranch dealBranch) {
		dealBranchDao.add(dealBranch);
	}

}
