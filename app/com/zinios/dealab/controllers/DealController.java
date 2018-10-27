package com.zinios.dealab.controllers;

import com.zinios.dealab.authentication.SecuredAction;
import com.zinios.dealab.authentication.UserSecuredAction;
import com.zinios.dealab.controllers.util.ResponseWrapper;
import com.zinios.dealab.controllers.util.StatusCode;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Deal;
import com.zinios.dealab.models.DealBranch;
import com.zinios.dealab.models.User;
import com.zinios.dealab.models.entity.Location;
import com.zinios.dealab.models.entity.Promotions;
import com.zinios.dealab.models.parse.PromoWrapper;
import com.zinios.dealab.parsers.DealBodyParser;
import com.zinios.dealab.services.BranchService;
import com.zinios.dealab.services.DealService;
import com.zinios.dealab.services.util.ValidatorUtil;
import com.zinios.dealab.utils.Constants;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import scala.Option;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.zinios.dealab.utils.Constants.*;


/**
 * Created by vihanga on 08/10/2018.
 */
public class DealController extends Controller {

	@Inject
	private DealService dealService;
	@Inject
	private BranchService branchService;

	@With(UserSecuredAction.class)
	@BodyParser.Of(DealBodyParser.class)
	public Result add() {

		User user = (User) ctx().args.get(Constants.USER_OBJECT);

		if (user.getCompany() == null || user.getCompany().getId() == null || user.getCompany().getId() == 0L) {
			return unauthorized(new ResponseWrapper(UNAUTHORIZED_ACCESS,
					StatusCode.UNAUTHORIZED, null).jsonSerialize());
		}
		//read data from body
		Deal deal = request().body().as(Deal.class);
		if (deal == null) {
			return badRequest(new ResponseWrapper(NULL_DEAL,
					StatusCode.DATA_NULL, null).jsonSerialize());
		}

		if (!ValidatorUtil.validateDeal(deal)) {
			return badRequest(new ResponseWrapper(INVALID_DEAL_DATA,
					StatusCode.DATA_INVALID, null).jsonSerialize());
		}

		deal.setCompany(user.getCompany());
		Deal addDeal = dealService.addDeal(deal);
		if (addDeal != null) {
			if (deal.getBranches() != null && !deal.getBranches().isEmpty()) {
				List<Branch> branches = assignBrnches(addDeal, deal.getBranches());
				addDeal.setBranches(branches);
			}
			return ok(new ResponseWrapper(SUCCESS, StatusCode.ADDED, addDeal).jsonSerialize());
		}

		return internalServerError(new ResponseWrapper(SERVER_ERROR,
				StatusCode.DATA_ADD_FAIL, null).jsonSerialize());

	}

	private List<Branch> assignBrnches(Deal deal, List<Branch> branches) {
		List<Branch> validBranches = new ArrayList<>();
		for (Branch branch : branches) {
			if (branch != null && branch.getId() != null && branch.getId() != 0) {
				dealService.assignBranchDeal(new DealBranch(deal, branch));
				validBranches.add(branch);
			}
		}
		deal.setBranches(validBranches);
		return validBranches;
	}

	@With(SecuredAction.class)
	public Result getDealBranchList() {
		List<Location> list = dealService.getDealBranchList();
		if (list == null) {
			return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
		}
		return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, list).jsonSerialize());
	}

	@With(SecuredAction.class)
	public Result getFutureDealBranchList() {
		List<Location> list = dealService.getFutureDealBranchList();
		if (list == null) {
			return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
		}
		return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, list).jsonSerialize());
	}

	@With(SecuredAction.class)
	public Result getTodayDealBranchList() {
		List<Location> list = dealService.getTodayDealBranchList();
		if (list == null) {
			return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
		}
		return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, list).jsonSerialize());
	}

	@With(SecuredAction.class)
	public Result getDealBranchListRadius(Option<String> lat, Option<String> lng) {
		if (lat.isDefined() && lng.isDefined() && !lat.isEmpty() && !lng.isEmpty()) {
			try {
				double latitude = Double.parseDouble(lat.get());
				double longitude = Double.parseDouble(lng.get());
				Logger.info("Coordinates - " + latitude + "," + longitude);
				List<Location> list = dealService.getDealBranchListRadius(latitude, longitude);
				if (list == null) {
					return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
				}
				return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, list).jsonSerialize());
			} catch (NumberFormatException e) {
				Logger.error("Error Coordinates", e);
				return badRequest(new ResponseWrapper(INVALID_PARAM,
						StatusCode.REQUEST_ERROR, null).jsonSerialize());
			}
		} else {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.REQUEST_ERROR, null).jsonSerialize());
		}
	}

	@With(SecuredAction.class)
	public Result getFutureDealBranchListRadius(Option<String> lat, Option<String> lng) {
		if (lat.isDefined() && lng.isDefined() && !lat.isEmpty() && !lng.isEmpty()) {
			try {
				double latitude = Double.parseDouble(lat.get());
				double longitude = Double.parseDouble(lng.get());
				Logger.info("Coordinates - " + latitude + "," + longitude);
				List<Location> list = dealService.getFutureDealBranchListRadius(latitude, longitude);
				if (list == null) {
					return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
				}
				return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, list).jsonSerialize());
			} catch (NumberFormatException e) {
				Logger.error("Error Coordinates", e);
				return badRequest(new ResponseWrapper(INVALID_PARAM,
						StatusCode.REQUEST_ERROR, null).jsonSerialize());
			}
		} else {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.REQUEST_ERROR, null).jsonSerialize());
		}
	}

	@With(SecuredAction.class)
	public Result getTodayDealBranchListRadius(Option<String> lat, Option<String> lng) {
		if (lat.isDefined() && lng.isDefined() && !lat.isEmpty() && !lng.isEmpty()) {
			try {
				double latitude = Double.parseDouble(lat.get());
				double longitude = Double.parseDouble(lng.get());
				Logger.info("Coordinates - " + latitude + "," + longitude);
				List<Location> list = dealService.getTodayDealBranchListRadius(latitude, longitude);
				if (list == null) {
					return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
				}
				return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, list).jsonSerialize());
			} catch (NumberFormatException e) {
				Logger.error("Error Coordinates", e);
				return badRequest(new ResponseWrapper(INVALID_PARAM,
						StatusCode.REQUEST_ERROR, null).jsonSerialize());
			}
		} else {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.REQUEST_ERROR, null).jsonSerialize());
		}
	}

	@With(SecuredAction.class)
	public Result getTodayDeals(Option<String> branchId) {
		if (branchId.isDefined() && !branchId.isEmpty()) {
			Branch branch = branchService.find(branchId.get());
			if (branch == null) {
				return badRequest(new ResponseWrapper(NULL_BRANCH,
						StatusCode.DATA_NULL, null).jsonSerialize());
			}
			List<Promotions> list = dealService.getTodayDeals(branch);
			if (list == null) {
				return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
			}
			return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, new PromoWrapper(list, branch, branch.getCompany())).jsonSerialize());
		} else {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.REQUEST_ERROR, null).jsonSerialize());
		}
	}

	@With(SecuredAction.class)
	public Result getFutureDeals(Option<String> branchId) {
		if (branchId.isDefined() && !branchId.isEmpty()) {
			Branch branch = branchService.find(branchId.get());
			if (branch == null) {
				return badRequest(new ResponseWrapper(NULL_BRANCH,
						StatusCode.DATA_NULL, null).jsonSerialize());
			}
			List<Promotions> list = dealService.getFutureDeals(branch);
			if (list == null) {
				return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
			}
			return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, new PromoWrapper(list, branch, branch.getCompany())).jsonSerialize());
		} else {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.REQUEST_ERROR, null).jsonSerialize());
		}
	}

}
