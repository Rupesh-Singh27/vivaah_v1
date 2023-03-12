package com.cg.marriageceremony.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "photography_table")
public class Photography extends Vendor {
	
	private String photographyName;
	private String photographyDescription;
	private String imagepath;
	
	public Photography() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Photography(String photographyName, String photographyDescription, String imagepath) {
		super();
		this.photographyName = photographyName;
		this.photographyDescription = photographyDescription;
		this.imagepath = imagepath;
	}

	public String getPhotographyName() {
		return photographyName;
	}

	public void setPhotographyName(String photographyName) {
		this.photographyName = photographyName;
	}

	public String getPhotographyDescription() {
		return photographyDescription;
	}

	public void setPhotographyDescription(String photographyDescription) {
		this.photographyDescription = photographyDescription;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	
}
