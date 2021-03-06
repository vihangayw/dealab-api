package com.zinios.dealab.models.entity;
// Generated Oct 25, 2018 2:43:44 PM by Hibernate Tools 3.6.0

import com.avaje.ebean.annotation.Sql;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Reviews generated by hbm2java
 */
@Entity
@Sql
public class Reviews {

	private Long id;
	private Long userId;
	private String userName;
	private String email;
	private String imageUrl;
	private String comment;
	private Integer rating;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z")
	private Date updatededDate;


	public Reviews() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Date getUpdatededDate() {
		return updatededDate;
	}

	public void setUpdatededDate(Date updatededDate) {
		this.updatededDate = updatededDate;
	}
}
