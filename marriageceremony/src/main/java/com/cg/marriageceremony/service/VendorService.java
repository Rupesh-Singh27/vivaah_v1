package com.cg.marriageceremony.service;

import java.util.List;

import com.cg.marriageceremony.model.Vendor;



public interface VendorService {
	Vendor addVendor(Vendor v);
	List<Vendor> allVendor();
	 List<Vendor> searchByCategory(String category);
	 int deleteVendor(int vid);
	 public Vendor updateVendor(Vendor vendor,int vid);
}
