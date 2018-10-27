package com.zinios.dealab.dao.impl;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSqlBuilder;
import com.zinios.dealab.dao.DealBranchDao;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.DealBranch;
import com.zinios.dealab.models.entity.Location;
import com.zinios.dealab.models.entity.Promotions;
import com.zinios.dealab.utils.Constants;

import java.util.List;

import static com.zinios.dealab.utils.Constants.RADIUS_DISTANCE;

public class DealBranchDaoImpl implements DealBranchDao {

	private static Model.Find<Long, DealBranch> find = new Model.Finder<>(DealBranch.class);

	@Override
	public DealBranch add(DealBranch entityToAdd) {
		entityToAdd.save();
		return entityToAdd;
	}

	@Override
	public DealBranch update(DealBranch entityToUpdate) {
		return null;
	}

	@Override
	public DealBranch delete(DealBranch entityToDelete) {
		return null;
	}

	@Override
	public DealBranch find(Long idToFind) {
		return null;
	}

	@Override
	public List<Location> getDealBranchList() {
		String sql = "select b.id as branchId, count(db.id) as dealCount, c.name as company, c.id as companyId," +
				" b.name as branch, b.place_id as placeId, b.lat, b.lng " +
				" from deal d, branch b, company c,  deal_branch db " +
				" where d.status = " + Constants.STATUS_ACTIVE + "  && b.status = " + Constants.STATUS_ACTIVE +
				" && c.id = " + Constants.STATUS_ACTIVE + "  && d.company_id=c.id && db.branch_id = b.id " +
				" && b.company_id=c.id && d.id = db.deal_id && d.end_date >= curdate() " +
				" group by b.id";

		Query<Location> query = Ebean.find(Location.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}

	@Override
	public List<Location> getFutureDealBranchList() {
		String sql = "select b.id as branchId, count(db.id) as dealCount, c.name as company, c.id as companyId, " +
				"b.name as branch, b.place_id as placeId, b.lat, b.lng " +
				" from deal d, branch b, company c,  deal_branch db " +
				" where d.status = " + Constants.STATUS_ACTIVE + "  && b.status = " + Constants.STATUS_ACTIVE +
				" && c.id = " + Constants.STATUS_ACTIVE + "  && d.company_id=c.id && db.branch_id = b.id " +
				" && b.company_id=c.id && d.id = db.deal_id && d.start_date > curdate() " +
				" group by b.id";

		Query<Location> query = Ebean.find(Location.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}

	@Override
	public List<Location> getTodayDealBranchList() {
		String sql = "select b.id as branchId, count(db.id) as dealCount, c.name as company, c.id as companyId," +
				" b.name as branch, b.place_id, b.lat, b.lng " +
				" from deal d, branch b, company c,  deal_branch db " +
				" where d.status = " + Constants.STATUS_ACTIVE + "  && b.status = " + Constants.STATUS_ACTIVE +
				" && c.id = " + Constants.STATUS_ACTIVE + " && db.branch_id = b.id && b.company_id=c.id " +
				" && d.company_id=c.id && d.id = db.deal_id " +
				" && curdate() between d.start_date and d.end_date " +
				" group by b.id";

		Query<Location> query = Ebean.find(Location.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}

	////////////////////////////

	@Override
	public List<Location> getDealBranchListRadius(double lat, double lng) {
		String sql = "select b.id as branchId, count(db.id) as dealCount, c.name as company, c.id as companyId," +
				" b.name as branch, b.place_id as placeId, b.lat, b.lng " +
				" from deal d, branch b, company c,  deal_branch db " +
				" where d.status = " + Constants.STATUS_ACTIVE + "  && b.status = " + Constants.STATUS_ACTIVE +
				" && c.id = " + Constants.STATUS_ACTIVE + "  && d.company_id=c.id && db.branch_id = b.id " +
				" && b.company_id=c.id && d.id = db.deal_id && d.end_date >= curdate() " +
				" && 111.111 " +
				"         * DEGREES(ACOS(COS(RADIANS(" + lat + ")) " +
				"         * COS(RADIANS(b.lat)) " +
				"         * COS(RADIANS(" + lng + " - b.lng)) + SIN(RADIANS(" + lat + ")) " +
				"         * SIN(RADIANS(b.lat)))) < " + RADIUS_DISTANCE +
				" group by b.id";

		Query<Location> query = Ebean.find(Location.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}

	@Override
	public List<Location> getFutureDealBranchListRadius(double lat, double lng) {
		String sql = "select b.id as branchId, count(db.id) as dealCount, c.name as company, c.id as companyId, " +
				"b.name as branch, b.place_id as placeId, b.lat, b.lng " +
				" from deal d, branch b, company c,  deal_branch db " +
				" where d.status = " + Constants.STATUS_ACTIVE + "  && b.status = " + Constants.STATUS_ACTIVE +
				" && c.id = " + Constants.STATUS_ACTIVE + "  && d.company_id=c.id && db.branch_id = b.id " +
				" && b.company_id=c.id && d.id = db.deal_id && d.start_date > curdate() " +
				" && 111.111 " +
				"         * DEGREES(ACOS(COS(RADIANS(" + lat + ")) " +
				"         * COS(RADIANS(b.lat)) " +
				"         * COS(RADIANS(" + lng + " - b.lng)) + SIN(RADIANS(" + lat + ")) " +
				"         * SIN(RADIANS(b.lat)))) < " + RADIUS_DISTANCE +
				" group by b.id";

		Query<Location> query = Ebean.find(Location.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}

	@Override
	public List<Location> getTodayDealBranchListRadius(double lat, double lng) {
		String sql = "select b.id as branchId, count(db.id) as dealCount, c.name as company, c.id as companyId," +
				" b.name as branch, b.place_id, b.lat, b.lng " +
				" from deal d, branch b, company c,  deal_branch db " +
				" where d.status = " + Constants.STATUS_ACTIVE + "  && b.status = " + Constants.STATUS_ACTIVE +
				" && c.id = " + Constants.STATUS_ACTIVE + " && db.branch_id = b.id && b.company_id=c.id " +
				" && d.company_id=c.id && d.id = db.deal_id " +
				" && curdate() between d.start_date and d.end_date " +
				" && 111.111 " +
				"         * DEGREES(ACOS(COS(RADIANS(" + lat + ")) " +
				"         * COS(RADIANS(b.lat)) " +
				"         * COS(RADIANS(" + lng + " - b.lng)) + SIN(RADIANS(" + lat + ")) " +
				"         * SIN(RADIANS(b.lat)))) < " + RADIUS_DISTANCE +
				" group by b.id";

		Query<Location> query = Ebean.find(Location.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}

	//////////
	@Override
	public List<Promotions> getTodayDeals(Branch branch) {
		String sql = "select d.id as id, d.description as description, d.note as note, d.start_date as startDate, d.end_date as endDate," +
				" d.image_url as imageUrl, d.web_url as webUrl" +
				" from deal d, branch b, company c, deal_branch db " +
				" where d.status = " + Constants.STATUS_ACTIVE + " && b.status = " + Constants.STATUS_ACTIVE + "" +
				" && c.id = " + Constants.STATUS_ACTIVE + " && db.branch_id = b.id && b.company_id=c.id" +
				" && curdate() between d.start_date and d.end_date && d.company_id=c.id && d.id = db.deal_id " +
				" && b.id=" + branch.getId();

		Query<Promotions> query = Ebean.find(Promotions.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}


	@Override
	public List<Promotions> getFutureDeals(Branch branch) {
		String sql = "select d.id as id, d.description as description, d.note as note, d.start_date as startDate, d.end_date as endDate," +
				" d.image_url as imageUrl, d.web_url as webUrl" +
				" from deal d, branch b, company c, deal_branch db " +
				" where d.status = " + Constants.STATUS_ACTIVE + " && b.status = " + Constants.STATUS_ACTIVE + "" +
				" && c.id = " + Constants.STATUS_ACTIVE + " && db.branch_id = b.id && b.company_id=c.id" +
				" && d.start_date > curdate() && d.company_id=c.id && d.id = db.deal_id && b.id=" + branch.getId();

		Query<Promotions> query = Ebean.find(Promotions.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}

}
