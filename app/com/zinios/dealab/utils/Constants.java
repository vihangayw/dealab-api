package com.zinios.dealab.utils;

/**
 * Created by vihanga on 10/30/17.
 */
public class Constants {

	// JWT Claim ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public static final String CLAIM_TYPE_COMPANY = "auth-company";
	public static final String CLAIM_TYPE_BRANCH = "auth-branch";
	public static final String CLAIM_TYPE_USER = "auth-user";
	public static final String CLAIM_USER_PASSWORD = "auth-branch";

	// Response Message ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//common
	public static final String UNAUTHORIZED_ACCESS = "Access denied";
	public static final String INVALID_PAYLOAD = "Access is denied due to invalid credentials";

	public static final String SERVER_ERROR = "Unexpected error occurred\nPlease contact administration";
	public static final String ADDED = "Successfully added";
	public static final String UPDATED = "Successfully updated";
	public static final String DELETED = "Successfully deleted";
	public static final String SUCCESS = "Success";
	public static final String FAIL = "Fail";
	public static final String FORBIDDEN_ = "You are not allowed here";
	public static final String INVALID_PARAM = "Can't find what you requested";
	public static final String INVALID_URL = "This function is no longer available";
	public static final String INVALID_CRREDENTIAL = "Invalid Credential";
	public static final String IMAGE_URL_ERROR = "URL length is too long";

	//company
	public static final String NULL_COMPANY = "Company data is not valid";
	public static final String ALREADY_REGISTERED = "A client with this email address is already registered to Dealab";
	public static final String INVALID_COMPANY_DATA = "Company data is not valid\nPlease check your details";
	public static final String COMPANY_NOT_FOUND = "We couldn't find a company with the provided details";
	public static final String PASSWORD_NOT_VALID = "Password does not meet our criteria";
	public static final String PASSWORD_NOT_SET = "Your invitiation is not accepted yet";
	public static final String COMPANY_OBJECT = "Company_";

}
