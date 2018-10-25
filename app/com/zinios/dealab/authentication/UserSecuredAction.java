package com.zinios.dealab.authentication;

import com.auth0.jwt.interfaces.Claim;
import com.zinios.dealab.controllers.util.ResponseWrapper;
import com.zinios.dealab.controllers.util.StatusCode;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.User;
import com.zinios.dealab.services.CompanyService;
import com.zinios.dealab.services.UserService;
import com.zinios.dealab.utils.Constants;
import com.zinios.dealab.utils.JWTUtil;
import org.h2.util.StringUtils;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static com.zinios.dealab.utils.Constants.INVALID_PAYLOAD;
import static com.zinios.dealab.utils.Constants.UNAUTHORIZED_ACCESS;


/**
 * Created by vihanga on 08/10/2018.
 */
public class UserSecuredAction extends Action.Simple {

	@Inject
	JWTUtil jwtUtil;
	@Inject
	private UserService userService;
	@Inject
	private CompanyService companyService;

	@Override
	public CompletionStage<Result> call(Http.Context ctx) {

		String token = getTokenFromHeader(ctx);
		if (token == null) {
			return CompletableFuture.completedFuture(unauthorized
					(new ResponseWrapper(UNAUTHORIZED_ACCESS, StatusCode.UNAUTHORIZED, null).jsonSerialize()));
		}

		Map<String, Claim> stringClaimMap = jwtUtil.decode(token);

		if (stringClaimMap == null) {
			return CompletableFuture.completedFuture(unauthorized
					(new ResponseWrapper(INVALID_PAYLOAD, StatusCode.INVALID_PAYLOAD, null).jsonSerialize()));
		}

		Claim typeClaim = stringClaimMap.get("type");

		if (typeClaim == null) {
			return CompletableFuture.completedFuture(unauthorized
					(new ResponseWrapper(INVALID_PAYLOAD, StatusCode.INVALID_PAYLOAD, null).jsonSerialize()));
		}

		String type = typeClaim.asString();
		switch (type) {
			case Constants.CLAIM_TYPE_COMPANY:
				Claim companyIdClaim = stringClaimMap.get("id");
				Company company = companyService.find(companyIdClaim.asString());
				if (company == null) {
					return CompletableFuture.completedFuture(unauthorized
							(new ResponseWrapper(UNAUTHORIZED_ACCESS, StatusCode.UNAUTHORIZED, null).jsonSerialize()));
				}
				ctx.args.put(Constants.COMPANY_OBJECT, company);
				return delegate.call(ctx);
			case Constants.CLAIM_TYPE_USER:
				Claim userId = stringClaimMap.get("userId");
				if (userId != null && !StringUtils.isNullOrEmpty(userId.asString())) {
					User user = userService.find(Long.parseLong(userId.asString()));
					if (user == null) {
						return CompletableFuture.completedFuture(unauthorized
								(new ResponseWrapper(UNAUTHORIZED_ACCESS, StatusCode.UNAUTHORIZED, null).jsonSerialize()));
					}
					ctx.args.put(Constants.USER_OBJECT, user);
					return delegate.call(ctx);
				}
				break;
		}
		return CompletableFuture.completedFuture(unauthorized
				(new ResponseWrapper(INVALID_PAYLOAD, StatusCode.INVALID_PAYLOAD, null).jsonSerialize()));

	}

	private String getTokenFromHeader(Http.Context ctx) {
		String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
		if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
			return authTokenHeaderValues[0];
		}
		return null;
	}
}
