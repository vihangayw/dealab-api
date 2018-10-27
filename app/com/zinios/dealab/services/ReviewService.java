package com.zinios.dealab.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.zinios.dealab.dao.ReviewDao;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.Review;
import com.zinios.dealab.models.entity.Reviews;
import com.zinios.dealab.utils.DateTimeUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * Created by vihanga on 08/10/2018.
 */
public class ReviewService {

	@Inject
	@Named("review")
	private ReviewDao reviewDao;
	@Inject
	private DateTimeUtils dateTimeUtils;

	public Review addReview(Review review) {
		Date currentDateTime = dateTimeUtils.getCurrentDateTime();
		review.setAddedDate(currentDateTime);
		review.setUpdatededDate(currentDateTime);
		try {
			Transaction transaction = Ebean.beginTransaction();
			Review addedReview = reviewDao.add(review);

			if (addedReview == null) {
				transaction.end();
				return null;
			} else {
				transaction.commit();
				transaction.end();
				return addedReview;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Reviews> getSortedReviewList(Branch branch) {
		return reviewDao.getSortedList(branch);
	}

	public List<Reviews> getSortedReviewList(Company company) {
		return reviewDao.getSortedList(company);
	}

	public Review update(Review review) {

		try {
			Transaction transaction = Ebean.beginTransaction();
			Review updatedReview = reviewDao.update(review);

			if (updatedReview != null) {
				transaction.commit();
				transaction.end();
				return updatedReview;
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
