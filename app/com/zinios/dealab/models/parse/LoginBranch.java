package com.zinios.dealab.models.parse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zinios.dealab.models.Company;

import java.io.Serializable;

public class LoginBranch implements Serializable {

	@JsonIgnoreProperties({"name", "logo", "email", "password", "contact1", "contact2", "website", "hqAddress"})
	private Company company;
	private String email;
	private String password;

	public LoginBranch() {
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
