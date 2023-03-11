package com.cg.marriageceremony.service;

import java.util.List;

import com.cg.marriageceremony.model.MakeUp;


public interface MakeUpService {
	MakeUp addMakeUp(MakeUp mu);
	List<MakeUp> allMakeUp();
	List<MakeUp> searchingByName(String makeupName);
	int deleteVendor(int vid);

}
