package com.zinios.dealab.controllers;

import com.zinios.dealab.authentication.SecuredAction;
import com.zinios.dealab.authentication.UserSecuredAction;
import com.zinios.dealab.controllers.util.ResponseWrapper;
import com.zinios.dealab.controllers.util.StatusCode;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Review;
import com.zinios.dealab.models.User;
import com.zinios.dealab.parsers.RreviewBodyParser;
import com.zinios.dealab.services.BranchService;
import com.zinios.dealab.services.ReviewService;
import com.zinios.dealab.utils.Constants;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;

import static com.zinios.dealab.utils.Constants.*;


/**
 * Created by vihanga on 08/10/2018.
 */
public class ReviewController extends Controller {

	@Inject
	private ReviewService reviewService;
	@Inject
	private BranchService branchService;

	@With(UserSecuredAction.class)
	@BodyParser.Of(RreviewBodyParser.class)
	public Result add() {

		User user = (User) ctx().args.get(Constants.USER_OBJECT);

		if (user.getCompany() == null || user.getCompany().getId() == null || user.getCompany().getId() == 0L) {
			return unauthorized(new ResponseWrapper(UNAUTHORIZED_ACCESS,
					StatusCode.UNAUTHORIZED, null).jsonSerialize());
		}
		//read data from body
		Review review = request().body().as(Review.class);
		if (review == null || review.getBranch() == null || review.getBranch().getId() == null) {
			return badRequest(new ResponseWrapper(NULL_REVIEW,
					StatusCode.DATA_NULL, null).jsonSerialize());
		}
		Branch branch = branchService.find(review.getBranch().getId());
		if (branch == null)
			return badRequest(new ResponseWrapper(REVIEW_NOT_FOUND,
					StatusCode.NOT_FOUND, null).jsonSerialize());

		review.setUser(user);
		review.setBranch(branch);

		Review addReview = reviewService.addReview(review);
		if (addReview != null) {
			return ok(new ResponseWrapper(ADDED, StatusCode.ADDED, addReview).jsonSerialize());
		}

		return internalServerError(new ResponseWrapper(SERVER_ERROR,
				StatusCode.DATA_ADD_FAIL, null).jsonSerialize());

	}

	@With(SecuredAction.class)
	public Result list(String branchId) {
		if (branchId == null) {
			return badRequest(new ResponseWrapper(BRANCH_NOT_FOUND,
					StatusCode.NOT_FOUND, null).jsonSerialize());
		}
		try {
			long id = Long.parseLong(branchId);
			Branch branch = branchService.find(id);

			if (branch == null) {
				return badRequest(new ResponseWrapper(BRANCH_NOT_FOUND,
						StatusCode.NOT_FOUND, null).jsonSerialize());
			}

			return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND,
					reviewService.getSortedReviewList(branch)).jsonSerialize());

		} catch (NumberFormatException e) {
			Logger.error("Error", e);
			return badRequest(new ResponseWrapper(BRANCH_NOT_FOUND,
					StatusCode.NOT_FOUND, null).jsonSerialize());
		}
	}
}
