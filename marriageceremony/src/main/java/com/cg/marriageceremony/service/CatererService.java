package com.cg.marriageceremony.service;

import java.util.List;

import com.cg.marriageceremony.model.Caterer;



public interface CatererService {
	
	Caterer addCaterer(Caterer caterer);
	
	List<Caterer> getAllCaterers();
	
	List<Caterer> getByName(String catarerName);
	
	String deleteVendorById(int vendorId);

}
