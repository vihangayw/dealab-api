package com.zinios.dealab.models;
// Generated Oct 25, 2018 2:43:44 PM by Hibernate Tools 3.6.0

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Company generated by hbm2java
 */
@Entity
@Table(name = "company", catalog = "dealab_db"
)
public class Company implements java.io.Serializable {

	private Long id;
	private Category category;
	private String name;
	private String logo;
	private String coverImage;
	private String description;
	private String contact1;
	private String cantact2;
	private Integer status;
	private String website;
	private String hqAddress;

	public Company() {
	}

	public Company(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "logo", length = 2000)
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "cover_image", length = 2000)
	public String getCoverImage() {
		return this.coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "contact_1", length = 25)
	public String getContact1() {
		return this.contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	@Column(name = "cantact_2", length = 25)
	public String getCantact2() {
		return this.cantact2;
	}

	public void setCantact2(String cantact2) {
		this.cantact2 = cantact2;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "website", length = 500)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "hq_address")
	public String getHqAddress() {
		return this.hqAddress;
	}

	public void setHqAddress(String hqAddress) {
		this.hqAddress = hqAddress;
	}

}
