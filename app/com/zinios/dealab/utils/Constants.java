package com.zinios.dealab.utils;

/**
 * Created by vihanga on 10/30/17.
 */
public class Constants {

	// JWT Claim ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public static final String CLAIM_TYPE_COMPANY = "auth-company";
	public static final String CLAIM_TYPE_USER = "auth-user";

	// Response Message ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//common
	public static final String UNAUTHORIZED_ACCESS = "Access denied";
	public static final String INVALID_PAYLOAD = "Access is denied due to invalid credentials";
	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_ACTIVE = 1;
	public static final double RADIUS_DISTANCE = 3.0; //Km

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
	public static final String COMPANY_APPROVED = "This company is already approved";
	public static final String PASSWORD_NOT_VALID = "Password does not meet our criteria";
	public static final String PASSWORD_NOT_SET = "Your invitiation is not accepted yet";

	//user
	public static final String USER_OBJECT = "User_";
	public static final String NULL_USER = "User data is not valid";
	public static final String INVALID_USER_DATA = "User data is not valid\nPlease check your details";
	public static final String USER_NOT_FOUND = "We couldn't find a user with the provided details";

	//branch
	public static final String NULL_BRANCH = "Branch data is not valid";
	public static final String INVALID_BRANCH_DATA = "Branch data is not valid\nPlease check your details";
	public static final String BRANCH_NOT_FOUND = "We couldn't find a branch with the provided details";
	public static final String BRANCH_APPROVED = "This branch is already approved";
	public static final String BRANCH_EXISTS = "Branch Data already exists";

	//review
	public static final String NULL_REVIEW = "Reviews data is not valid";
	public static final String INVALID_REVIEW_DATA = "Reviews data is not valid\nPlease check your details";
	public static final String REVIEW_NOT_FOUND = "We couldn't find a review with the provided details";
	public static final String REVIEW_APPROVED = "This review is already approved";
	public static final String REVIEW_EXISTS = "Reviews Data already exists";

	//deal
	public static final String NULL_DEAL = "Deal data is not valid";
	public static final String INVALID_DEAL_DATA = "Deal data is not valid\nPlease check your details";
	public static final String DEAL_NOT_FOUND = "We couldn't find a deal with the provided details";
	public static final String DEAL_EXISTS = "Deal Data already exists";
}
