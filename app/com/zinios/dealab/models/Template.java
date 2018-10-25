package com.zinios.dealab.models;
// Generated Oct 25, 2018 2:43:44 PM by Hibernate Tools 3.6.0

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Template generated by hbm2java
 */
@Entity
@Table(name = "template", catalog = "dealab_db")
public class Template extends com.avaje.ebean.Model implements java.io.Serializable {

	private Integer id;
	private String bgImage;
	private String overlayUrl;

	public Template() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "bg_image", length = 2000)
	public String getBgImage() {
		return this.bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	@Column(name = "overlay_url", length = 2000)
	public String getOverlayUrl() {
		return this.overlayUrl;
	}

	public void setOverlayUrl(String overlayUrl) {
		this.overlayUrl = overlayUrl;
	}

}
