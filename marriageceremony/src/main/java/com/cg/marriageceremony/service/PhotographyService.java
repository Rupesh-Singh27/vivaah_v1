package com.cg.marriageceremony.service;

import java.util.List;

import com.cg.marriageceremony.model.Photography;



public interface PhotographyService {
	Photography addPhotography(Photography photography);
	List<Photography> getAllPhotography();
	List<Photography> findByName(String photographerName);
	String deleteVendorById(int vendorId);

}
