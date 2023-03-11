package com.cg.marriageceremony.model;

import javax.persistence.Entity;

@Entity
public class Photography extends Vendor {
	private String pName;
	private String pDiscription;
	private String imagepath;
	
	public Photography() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Photography(String pName, String pDiscription, String imagepath) {
		super();
		this.pName = pName;
		this.pDiscription = pDiscription;
		this.imagepath = imagepath;
	}


	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDiscription() {
		return pDiscription;
	}
	public void setpDiscription(String pDiscription) {
		this.pDiscription = pDiscription;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	

}
