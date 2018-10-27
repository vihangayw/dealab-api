package com.zinios.dealab.models.parse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.entity.Images;
import com.zinios.dealab.models.entity.Reviews;

import java.io.Serializable;
import java.util.List;

public class BranchWrapper implements Serializable {

	private List<Reviews> reviews;
	private List<Images> images;
	@JsonIgnoreProperties({"company"})
	private Branch branch;
	@JsonIgnoreProperties({"category"})
	private Company company;

	public BranchWrapper() {
	}

	public BranchWrapper(List<Reviews> reviews, List<Images> images, Branch branch, Company company) {
		this.reviews = reviews;
		this.images = images;
		this.branch = branch;
		this.company = company;
	}

	public List<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
