package com.zinios.dealab.dao.impl;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSqlBuilder;
import com.zinios.dealab.dao.ReviewDao;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.Review;
import com.zinios.dealab.models.entity.Reviews;

import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

	private static Model.Find<Long, Review> find = new Model.Finder<>(Review.class);

	@Override
	public Review add(Review entityToAdd) {
		entityToAdd.save();
		return entityToAdd;
	}

	@Override
	public Review update(Review entityToUpdate) {
		entityToUpdate.update();
		return entityToUpdate;
	}

	@Override
	public Review delete(Review entityToDelete) {
		entityToDelete.delete();
		return entityToDelete;
	}

	@Override
	public Review find(Long idToFind) {
		return find.byId(idToFind);
	}

	@Override
	public List<Reviews> getSortedList(Branch branch) {
		String sql = "select r.comment as comment, r.id as id, r.rating as rating, r.updateded_date as updatededDate, " +
				" u.id as userId, u.name userName, u.email as email, u.image_url as imageUrl" +
				" from review r, branch b, user u " +
				" where r.branch_id = b.id && r.user_id = u.id && b.id = " + branch.getId() +
				" order by updateded_date desc";

		Query<Reviews> query = Ebean.find(Reviews.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}

	@Override
	public List<Reviews> getSortedList(Company company) {
		String sql = "select r.comment as comment, r.id as id, r.rating as rating, r.updateded_date as updatededDate, " +
				" u.id as userId, u.name userName, u.email as email, u.image_url as imageUrl" +
				" from review r, branch b, user u, company c " +
				" where r.branch_id = b.id && r.user_id = u.id && c.id = b.company_id && c.id =" + company.getId() +
				" order by updateded_date desc";

		Query<Reviews> query = Ebean.find(Reviews.class);
		query.setRawSql(RawSqlBuilder
				.parse(sql)
				.create());

		return query.findList();
	}
}
