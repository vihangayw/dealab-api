package com.zinios.dealab.models.parse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.entity.Promotions;

import java.io.Serializable;
import java.util.List;

public class PromoWrapper implements Serializable {

	private List<Promotions> promotions;
	@JsonIgnoreProperties({"company"})
	private Branch branch;
	@JsonIgnoreProperties({"category"})
	private Company company;

	public PromoWrapper() {
	}

	public PromoWrapper(List<Promotions> promotions, Branch branch, Company company) {
		this.promotions = promotions;
		this.branch = branch;
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Promotions> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotions> promotions) {
		this.promotions = promotions;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
}
