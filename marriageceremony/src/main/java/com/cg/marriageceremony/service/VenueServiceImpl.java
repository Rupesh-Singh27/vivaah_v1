package com.cg.marriageceremony.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForMakeup;
//import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForPhotography;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForVenue;
//import com.cg.marriageceremony.exceptions.NoMakeupPresentException;
import com.cg.marriageceremony.exceptions.NoVenuePresentException;
import com.cg.marriageceremony.model.MakeUp;
import com.cg.marriageceremony.model.Venue;
import com.cg.marriageceremony.repository.VenueRepository;

@Component

public class VenueServiceImpl implements VenueService {
	
		@Autowired
		private VenueRepository venueRepository;
		@PersistenceContext
		private EntityManager em;

	   
		@Override
		@Transactional
		public Venue addVenue(Venue venue) {
			
			
			try {
				if(venue.getvDiscription() == null || venue.getvName() == null || venue.getImagepath() == null) {
					throw new FieldCannotBeEmptyExceptionForVenue("Please fill every field appropriately");
				}
				return venueRepository.save(venue);
			}catch(FieldCannotBeEmptyExceptionForVenue exception) {
				throw exception;
			}catch(RuntimeException exception) {
				throw new RuntimeException(exception.getMessage());
			}
			
//			if(venue.getvDiscription().equals(null) || venue.getvName().equals(null) || venue.getImagepath().equals(null)) 
//			{
//				throw new FieldCannotBeEmptyExceptionForVenue("Please fill every field appropriately");
//			}
//			
//			return venueRepository.save(venue);
		}

		@Override
		public List<Venue> allVenue() {
			try {
				List<Venue> venuelist= venueRepository.findAll();		
				
				if(venuelist.isEmpty()) {
					throw new NoVenuePresentException("There in no venue present in database");
				}
				
				return venuelist;
			}catch(NoVenuePresentException exception) {
				throw exception;
			}catch(RuntimeException exception) {
				throw new RuntimeException(exception.getMessage());
			}
		}

		@Override
		@Transactional
		public List<Venue> searchByVenuename(String vName) {
			Query q = em.createQuery("select n from Venue n where n.veName like :nv");
			q.setParameter("nv", "%" + vName + "%");
			List<Venue> vName1 = q.getResultList();
			return vName1;
		}
	
		
}

