package com.zinios.dealab.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

public class JWTUtil {

	/**
	 * Create authentication token user
	 *
	 * @param userId
	 * @param companyId
	 * @return Authentication Token
	 */
	public String createAuthTokenCompany(Long userId, long companyId) throws UnsupportedEncodingException {
		return JWT.create()
				.withClaim("time", Calendar.getInstance().getTime())
				.withClaim("userId", String.valueOf(userId))
				.withClaim("companyId", String.valueOf(companyId))
				.withClaim("type", Constants.CLAIM_TYPE_COMPANY)
				.withClaim("uuid", UUID.randomUUID().toString())
				.withIssuer("auth0")
				.sign(Algorithm.HMAC512("secret"));
	}

	/**
	 * Create authentication token company
	 *
	 * @param email
	 * @param id
	 * @return Authentication Token
	 */
	public String createAuthToken(String email, String id) throws UnsupportedEncodingException {
		return JWT.create()
				.withClaim("time", Calendar.getInstance().getTime())
				.withClaim("email", email)
				.withClaim("userId", id.trim())
				.withClaim("type", Constants.CLAIM_TYPE_USER)
				.withClaim("uuid", UUID.randomUUID().toString())
				.withIssuer("auth0")
				.sign(Algorithm.HMAC512("secret"));
	}

	public String encryptPassword(String password) throws UnsupportedEncodingException {
		return JWT.create()
				.withClaim("password", password)
				.sign(Algorithm.HMAC512("secret"));
	}

	public String decryptPassword(String jwtString) throws UnsupportedEncodingException {
		Map<String, Claim> decode = decode(jwtString);
		if (decode != null) {
			return decode.get("password").asString();
		}
		return "";
	}


	public String getAuthToken() {
		return "eyJhbGciOiJIUzUxMiJ9.eyJwcm9kdWN0IjoiZGVhbGFiIiwiYXV0aG9yIjoiVllXIiwiaXNzIjoiYXV0aDAiLCJ0aW1lIjoiU3VuIE9jdCAyOSAxNzozMjo0NSBJU1QgMjAxOCIsInR5cGUiOiJhdXRoIn0.pmIL4gy-VJpWehD0Dp8Z9dWh0fGU-bMtSQsNfbZ0DC2i7CP5XkHoCwXbe5Bj7oXYuFBk0PeWTAwdqVPegbG6dQ";
	}

	/**
	 * Decode token for authentication
	 *
	 * @param jwt token to be decoded
	 * @return map of payload
	 */
	public Map<String, Claim> decode(String jwt) {
		try {
			return JWT.decode(jwt).getClaims();
		} catch (JWTDecodeException e) {
			play.Logger.debug(e.getMessage());
			return null;
		}
	}

//    public static void main(String[] args) {
//        System.out.println(new JWTUtil().decode("eyJhbGciOiJIUzUxMiJ9.eyJwcm9kdWN0IjoiZ2VuLVEiLCJhdXRob3IiOiJWWVciLCJpc3MiOiJhdXRoMCIsInRpbWUiOiJTdW4gT2N0IDI5IDE3OjMyOjQ1IElTVCAyMDE3IiwidHlwZSI6ImF1dGgifQ.pmIL4gy-VJpWehD0Dp8Z9dWh0fGU-bMtSQsNfbZ0DC2i7CP5XkHoCwXbe5Bj7oXYuFBk0PeWTAwdqVPegbG6dQ").get("type").asString());
//    }

}
