package com.cg.marriageceremony.service;

import java.util.List;

import com.cg.marriageceremony.model.Venue;

public interface VenueService {
	Venue addVenue(Venue ve);
    List<Venue> allVenue();
    List<Venue> searchByVenuename(String vName);
    
    }