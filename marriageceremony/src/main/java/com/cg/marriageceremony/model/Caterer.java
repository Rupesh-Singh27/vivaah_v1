package com.cg.marriageceremony.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "caterer_table")
public class Caterer extends Vendor {
	
	@NotBlank(message="Name cannot be left blank")
	@Pattern(regexp = "^[a-zA-Z0-9\s]*$", message="Name should only contain alphabets")
	@Length(min = 3, max = 20)
	@Column(name = "caterer_name")
	private String catererName;
	
	@NotBlank(message="Description cannot be left blank")
	@Pattern(regexp = "^[a-zA-Z0-9\s]*$", message="Name should only contain alphabets space and numbers")
	@Length(min = 3, max = 50)
	@Column(name = "caterer_description")
	private String catererDescription;
	
	@NotBlank(message="Image path cannot be left blank")
	@Column(name = "caterer_imagepath")
	@Length(min = 3, max = 500)
	private String imagepath;
	
	public Caterer() {
		super();
	}
	
	public Caterer(String catererName, String catererDescription, String imagepath) {
		super();
		this.catererName = catererName;
		this.catererDescription = catererDescription;
		this.imagepath = imagepath;
	}

	public String getCatererName() {
		return catererName;
	}

	public void setCatererName(String catererName) {
		this.catererName = catererName;
	}

	public String getCatererDescription() {
		return catererDescription;
	}

	public void setCatererDescription(String catererDescription) {
		this.catererDescription = catererDescription;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
}