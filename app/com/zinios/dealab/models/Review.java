package com.zinios.dealab.models;
// Generated Oct 25, 2018 2:43:44 PM by Hibernate Tools 3.6.0

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Review generated by hbm2java
 */
@Entity
@Table(name = "review", catalog = "dealab_db")
public class Review implements java.io.Serializable {

	private Long id;
	private User user;
	private Branch branch;
	private String comment;
	private Integer rating;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z")
	private Date addedDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z")
	private Date updatededDate;


	public Review() {
	}

	public Review(User user, Branch branch, String comment, Integer rating) {
		this.user = user;
		this.branch = branch;
		this.comment = comment;
		this.rating = rating;
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

	@Column(name = "comment", length = 1000)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "rating")
	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
