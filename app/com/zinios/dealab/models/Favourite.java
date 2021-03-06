package com.zinios.dealab.models;
// Generated Oct 25, 2018 2:43:44 PM by Hibernate Tools 3.6.0

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Favourite generated by hbm2java
 */
@Entity
@Table(name = "favourite", catalog = "dealab_db")
public class Favourite extends com.avaje.ebean.Model implements java.io.Serializable {

	private Long id;
	private User user;
	private Branch branch;

	public Favourite() {
	}

	public Favourite(User user, Branch branch) {
		this.user = user;
		this.branch = branch;
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
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id")
	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}
