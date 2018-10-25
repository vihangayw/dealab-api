package com.zinios.dealab.controllers;

import com.mysql.jdbc.StringUtils;
import com.zinios.dealab.authentication.SecuredAction;
import com.zinios.dealab.controllers.util.ResponseWrapper;
import com.zinios.dealab.controllers.util.StatusCode;
import com.zinios.dealab.models.User;
import com.zinios.dealab.models.parse.Login;
import com.zinios.dealab.models.parse.LoginBranch;
import com.zinios.dealab.models.parse.UserAuth;
import com.zinios.dealab.parsers.BranchLoginBodyParser;
import com.zinios.dealab.parsers.LoginBodyParser;
import com.zinios.dealab.services.CompanyService;
import com.zinios.dealab.services.UserService;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;

import static com.zinios.dealab.utils.Constants.*;


/**
 * Created by vihanga on 08/10/2018.
 */
public class UserController extends Controller {

	@Inject
	private UserService userService;
	@Inject
	private CompanyService companyService;

//	@With(SecuredAction.class)
//	@BodyParser.Of(CompanyBodyParser.class)
//	public Result add() {
//
//		//read data from body
//		Company asCompany = request().body().as(Company.class);
//		if (asCompany == null) {
//			return badRequest(new ResponseWrapper(NULL_COMPANY,
//					StatusCode.DATA_NULL, null).jsonSerialize());
//		}
//
//		if (!ValidatorUtil.validateCompany(asCompany)) {
//			return badRequest(new ResponseWrapper(INVALID_COMPANY_DATA,
//					StatusCode.DATA_INVALID, null).jsonSerialize());
//		}
//
//		if (userService.findByEmail(asCompany.getEmail()) != null) {
//			return badRequest(new ResponseWrapper(ALREADY_REGISTERED,
//					StatusCode.DATA_DUPLICATED, null).jsonSerialize());
//		}
//		Company company = userService.addCompany(asCompany);
//
//		if (company != null) {
//			return ok(new ResponseWrapper(ADDED,
//					StatusCode.ADDED, company).jsonSerialize());
//		}
//
//		return internalServerError(new ResponseWrapper(SERVER_ERROR,
//				StatusCode.DATA_ADD_FAIL, null).jsonSerialize());
//
//	}

	@With(SecuredAction.class)
	@BodyParser.Of(BranchLoginBodyParser.class)
	public Result companyLogin() {

		//read data from body
		LoginBranch asLogin = request().body().as(LoginBranch.class);
		if (asLogin == null) {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.DATA_NULL, null).jsonSerialize());
		}
		if (StringUtils.isNullOrEmpty(asLogin.getEmail())
				|| StringUtils.isNullOrEmpty(asLogin.getPassword())) {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.NOT_FOUND, null).jsonSerialize());
		}

		User user = userService.login(asLogin.getEmail(), asLogin.getPassword(), asLogin.getCompany());

		if (user == null) {
			return badRequest(new ResponseWrapper(USER_NOT_FOUND,
					StatusCode.NOT_FOUND, null).jsonSerialize());
		}
		return ok(new ResponseWrapper(SUCCESS,
				StatusCode.FOUND, new UserAuth(user, user.getToken())).jsonSerialize());

	}

	@With(SecuredAction.class)
	@BodyParser.Of(LoginBodyParser.class)
	public Result userLogin() {

		//read data from body
		Login asLogin = request().body().as(Login.class);
		if (asLogin == null) {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.DATA_NULL, null).jsonSerialize());
		}
		if (StringUtils.isNullOrEmpty(asLogin.getEmail())
				|| StringUtils.isNullOrEmpty(asLogin.getPassword())) {
			return badRequest(new ResponseWrapper(INVALID_PARAM,
					StatusCode.NOT_FOUND, null).jsonSerialize());
		}

		User user = userService.login(asLogin.getEmail(), asLogin.getPassword());

		if (user == null) {
			return badRequest(new ResponseWrapper(USER_NOT_FOUND,
					StatusCode.NOT_FOUND, null).jsonSerialize());
		}
		return ok(new ResponseWrapper(SUCCESS,
				StatusCode.FOUND, new UserAuth(user, user.getToken())).jsonSerialize());

	}

//
//	@With(SecuredAction.class)
//	public Result list(Option<Integer> offset, Option<Integer> limit) {
//		if (offset.isDefined() && limit.isDefined() && !offset.isEmpty() && !limit.isEmpty()) {
//			List<Company> sortedCompanyList = userService.getSortedCompanyList(offset.get(), limit.get());
//			if (sortedCompanyList == null) {
//				return ok(new ResponseWrapper(SUCCESS, StatusCode.NOT_FOUND, new ArrayList<>()).jsonSerialize());
//			}
//			return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, sortedCompanyList).jsonSerialize());
//		} else {
//			return badRequest(new ResponseWrapper(INVALID_PARAM,
//					StatusCode.REQUEST_ERROR, null).jsonSerialize());
//		}
//	}
//
//	@With(CompanySecuredAction.class)
//	@BodyParser.Of(CompanyBodyParser.class)
//	public Result update() {
//
//		//read data from body
//		Company asCompany = request().body().as(Company.class);
//		if (asCompany == null) {
//			return badRequest(new ResponseWrapper(NULL_COMPANY,
//					StatusCode.DATA_NULL, null).jsonSerialize());
//		}
//
//		if (asCompany.getId() == null) {
//			return badRequest(new ResponseWrapper(COMPANY_NOT_FOUND,
//					StatusCode.NOT_FOUND, null).jsonSerialize());
//		}
//
//		Company company = (Company) ctx().args.get(Constants.COMPANY_OBJECT);
//
//		if (!company.getId().equals(asCompany.getId())) {
//			return forbidden(new ResponseWrapper(FORBIDDEN_,
//					StatusCode.FORBIDDEN, null).jsonSerialize());
//		}
//
//		Company updateCompany = userService.updateCompany(company, asCompany);
//		if (updateCompany != null) {
//			return ok(new ResponseWrapper(SUCCESS,
//					StatusCode.UPDATED, updateCompany).jsonSerialize());
//		}
//
//		return internalServerError(new ResponseWrapper(SERVER_ERROR,
//				StatusCode.DATA_UPDATE_FAIL, null).jsonSerialize());
//	}
//
//	@With(CompanySecuredAction.class)
//	public Result getCompany(String id) {
//		Company company = (Company) ctx().args.get(Constants.COMPANY_OBJECT);
//
//		if (!company.getId().equals(id)) {
//			return forbidden(new ResponseWrapper(FORBIDDEN_,
//					StatusCode.FORBIDDEN, null).jsonSerialize());
//		}
//		return ok(new ResponseWrapper(SUCCESS, StatusCode.FOUND, company).jsonSerialize());
//	}

}
