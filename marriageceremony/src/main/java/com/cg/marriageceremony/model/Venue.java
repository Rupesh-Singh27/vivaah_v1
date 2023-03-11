package com.cg.marriageceremony.model;

import javax.persistence.Entity;

@Entity
public class Venue extends Vendor{
	private String vName;
	private String vDiscription;
	private String imagepath;
	
	
	public Venue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Venue(String vName, String vDiscription, String imagepath) {
		super();
		this.vName = vName;
		this.vDiscription = vDiscription;
		this.imagepath = imagepath;
	}



	public String getvName() {
		return vName;
	}
	public void setvName(String vName) {
		this.vName = vName;
	}
	public String getvDiscription() {
		return vDiscription;
	}
	public void setvDiscription(String vDiscription) {
		this.vDiscription = vDiscription;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	
	


}
