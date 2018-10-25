package com.zinios.dealab.authentication;

import com.auth0.jwt.interfaces.Claim;
import com.zinios.dealab.controllers.util.ResponseWrapper;
import com.zinios.dealab.controllers.util.StatusCode;
import com.zinios.dealab.utils.JWTUtil;
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
public class SecuredAction extends Action.Simple {

	@Inject
	JWTUtil jwtUtil;

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
		if (type.equals("auth")) {
			return delegate.call(ctx);
		} else {
			return CompletableFuture.completedFuture(unauthorized
					(new ResponseWrapper(INVALID_PAYLOAD, StatusCode.INVALID_PAYLOAD, null).jsonSerialize()));
		}
	}

	private String getTokenFromHeader(Http.Context ctx) {
		String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
		if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
			return authTokenHeaderValues[0];
		}
		return null;
	}
}
