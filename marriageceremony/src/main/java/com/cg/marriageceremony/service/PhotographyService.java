package com.cg.marriageceremony.service;

import java.util.List;

import com.cg.marriageceremony.model.Photography;



public interface PhotographyService {
	Photography addPhotography(Photography photography);
	List<Photography> allPhotography();
	List<Photography> searchingByName(String photographyName);
	int deleteVendor(int vendorId);

}
