package com.cg.marriageceremony.model;

import javax.persistence.Entity;

@Entity
public class MakeUp extends Vendor{
	private String mName;
	private String mDiscription;
	private String imagepath;
	
	
	public MakeUp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MakeUp(String mName, String mDiscription, String imagepath) {
		super();
		this.mName = mName;
		this.mDiscription = mDiscription;
		this.imagepath = imagepath;
	}


	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmDiscription() {
		return mDiscription;
	}
	public void setmDiscription(String mDiscription) {
		this.mDiscription = mDiscription;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}


}
