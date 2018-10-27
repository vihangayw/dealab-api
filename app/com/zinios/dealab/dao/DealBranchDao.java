package com.zinios.dealab.dao;


import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.DealBranch;
import com.zinios.dealab.models.entity.Location;
import com.zinios.dealab.models.entity.Promotions;

import java.util.List;

/**
 * Created by vihanga on 10/29/18.
 */
public interface DealBranchDao extends BaseDao<DealBranch> {

	List<Location> getDealBranchList();

	List<Location> getFutureDealBranchList();

	List<Location> getTodayDealBranchList();

	List<Location> getDealBranchListRadius(double lat, double lng);

	List<Location> getFutureDealBranchListRadius(double lat, double lng);

	List<Location> getTodayDealBranchListRadius(double lat, double lng);

	List<Promotions> getTodayDeals(Branch branch);

	List<Promotions> getFutureDeals(Branch branch);

//	List<Promotions> getTodayDealsRadius(Branch branch);

//	List<Promotions> getFutureDealsRadius(Branch branch);

}
