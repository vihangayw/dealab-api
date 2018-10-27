package com.zinios.dealab.controllers;

import com.zinios.dealab.authentication.UserSecuredAction;
import com.zinios.dealab.controllers.util.ResponseWrapper;
import com.zinios.dealab.controllers.util.StatusCode;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.User;
import com.zinios.dealab.parsers.BranchBodyParser;
import com.zinios.dealab.services.BranchService;
import com.zinios.dealab.services.util.ValidatorUtil;
import com.zinios.dealab.utils.Constants;
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
public class BranchController extends Controller {

	@Inject
	private BranchService branchService;

	@With(UserSecuredAction.class)
	@BodyParser.Of(BranchBodyParser.class)
	public Result add() {

		User user = (User) ctx().args.get(Constants.USER_OBJECT);

		if (user.getCompany() == null || user.getCompany().getId() == null || user.getCompany().getId() == 0L) {
			return unauthorized(new ResponseWrapper(UNAUTHORIZED_ACCESS,
					StatusCode.UNAUTHORIZED, null).jsonSerialize());
		}
		//read data from body
		Branch branch = request().body().as(Branch.class);
		if (branch == null) {
			return badRequest(new ResponseWrapper(NULL_BRANCH,
					StatusCode.DATA_NULL, null).jsonSerialize());
		}

		if (!ValidatorUtil.validateBranch(branch)) {
			return badRequest(new ResponseWrapper(INVALID_BRANCH_DATA,
					StatusCode.DATA_INVALID, null).jsonSerialize());
		}

		if (user.getCompany().getId().longValue() != branch.getCompany().getId().longValue()) {
			return forbidden(new ResponseWrapper(FORBIDDEN_,
					StatusCode.FORBIDDEN, null).jsonSerialize());
		}

		if (branchService.isExisits(user.getCompany(), branch.getName(), branch.getPlaceId()) != null) {
			return badRequest(new ResponseWrapper(BRANCH_EXISTS,
					StatusCode.DATA_DUPLICATED, null).jsonSerialize());
		}

		branch.setStatus(STATUS_INACTIVE);

		Branch addBranch = branchService.addBranch(branch);
		if (addBranch != null) {
			return ok(new ResponseWrapper(ADDED, StatusCode.ADDED, user).jsonSerialize());
		}

		return internalServerError(new ResponseWrapper(SERVER_ERROR,
				StatusCode.DATA_ADD_FAIL, null).jsonSerialize());

	}

	@With(UserSecuredAction.class)
	public Result approveBranch(String id) {
		//User user = (User) ctx().args.get(Constants.USER_OBJECT);

		if (id == null) {
			return badRequest(new ResponseWrapper(BRANCH_NOT_FOUND,
					StatusCode.NOT_FOUND, null).jsonSerialize());
		}

		Branch branch = branchService.find(id);

		if (branch == null) {
			return badRequest(new ResponseWrapper(BRANCH_NOT_FOUND,
					StatusCode.NOT_FOUND, null).jsonSerialize());
		}
		if (branch.getStatus() == Constants.STATUS_ACTIVE) {
			return badRequest(new ResponseWrapper(BRANCH_APPROVED,
					StatusCode.DATA_UPDATE_FAIL, null).jsonSerialize());
		}

		branch.setStatus(Constants.STATUS_ACTIVE);
		branch = branchService.update(branch);

		if (branch != null) {
			return ok(new ResponseWrapper(UPDATED,
					StatusCode.UPDATED, branch).jsonSerialize());
		}

		return internalServerError(new ResponseWrapper(SERVER_ERROR,
				StatusCode.DATA_UPDATE_FAIL, null).jsonSerialize());
	}

	@With(UserSecuredAction.class)
	public Result list(Option<Integer> offset, Option<Integer> limit, Option<String> companyId) {
		if (companyId.isDefined() && !companyId.isEmpty() && offset.isDefined()
				&& limit.isDefined() && !offset.isEmpty() && !limit.isEmpty()) {
			List<Branch> branchList = branchService.getSortedBranchList(
					new Company(Long.parseLong(companyId.get())), offset.get(), limit.get());
			if (branchList == null) {
				return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
			}
			return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, branchList).jsonSerialize());
		} else {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.REQUEST_ERROR, null).jsonSerialize());
		}
	}

	@With(UserSecuredAction.class)
	public Result listInactive(Option<Integer> offset, Option<Integer> limit, Option<String> companyId) {
		if (companyId.isDefined() && !companyId.isEmpty() && offset.isDefined() &&
				limit.isDefined() && !offset.isEmpty() && !limit.isEmpty()) {
			List<Branch> inactiveBranchList = branchService.getSortedInactiveBranchList(
					new Company(Long.parseLong(companyId.get())), offset.get(), limit.get());
			if (inactiveBranchList == null) {
				return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
			}
			return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, inactiveBranchList).jsonSerialize());
		} else {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.REQUEST_ERROR, null).jsonSerialize());
		}
	}

}
