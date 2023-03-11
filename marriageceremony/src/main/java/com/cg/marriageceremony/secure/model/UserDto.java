package com.cg.marriageceremony.secure.model;

public class UserDto {
    private String username;
    private String password;
    private String role;
    private String custName;
    private long mobileNo;
    
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
