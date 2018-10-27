package com.zinios.dealab.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.zinios.dealab.dao.DealBranchDao;
import com.zinios.dealab.dao.DealDao;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Deal;
import com.zinios.dealab.models.DealBranch;
import com.zinios.dealab.models.entity.Location;
import com.zinios.dealab.models.entity.Promotions;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
		try {
			Transaction transaction = Ebean.beginTransaction();
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
			return null;
		}
	}

	public Deal find(Long id) {
		return dealDao.find(id);
	}

	public void assignBranchDeal(DealBranch dealBranch) {
		dealBranchDao.add(dealBranch);
	}

	public List<Location> getDealBranchList() {
		return dealBranchDao.getDealBranchList();
	}

	public List<Location> getFutureDealBranchList() {
		return dealBranchDao.getFutureDealBranchList();
	}

	public List<Location> getTodayDealBranchList() {
		return dealBranchDao.getTodayDealBranchList();
	}

	public List<Location> getDealBranchListRadius(double lat, double lng) {
		return dealBranchDao.getDealBranchListRadius(lat, lng);
	}

	public List<Location> getFutureDealBranchListRadius(double lat, double lng) {
		return dealBranchDao.getFutureDealBranchListRadius(lat, lng);
	}

	public List<Location> getTodayDealBranchListRadius(double lat, double lng) {
		return dealBranchDao.getTodayDealBranchListRadius(lat, lng);
	}

	public List<Promotions> getTodayDeals(Branch branch) {
		return dealBranchDao.getTodayDeals(branch);
	}

	public List<Promotions> getFutureDeals(Branch branch) {
		return dealBranchDao.getFutureDeals(branch);
	}

}
