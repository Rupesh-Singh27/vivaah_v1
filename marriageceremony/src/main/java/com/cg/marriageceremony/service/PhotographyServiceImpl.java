package com.cg.marriageceremony.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForMakeup;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForPhotography;
import com.cg.marriageceremony.exceptions.MakeupDoesNotExistException;
import com.cg.marriageceremony.exceptions.MarriageCeremonyApplicationException;
import com.cg.marriageceremony.exceptions.NoMakeupPresentException;
import com.cg.marriageceremony.exceptions.NoPhotographyPresentException;
import com.cg.marriageceremony.exceptions.PhotographyDoesNotExistException;
import com.cg.marriageceremony.model.MakeUp;
import com.cg.marriageceremony.model.Photography;
import com.cg.marriageceremony.repository.PhotographyRepository;

@Component
public class PhotographyServiceImpl implements PhotographyService{
	
	@Autowired
	private PhotographyRepository photographyRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Photography addPhotography(Photography photography) {
		try {
			if(photography.getpDiscription() == null || photography.getpName() == null || photography.getImagepath() == null) {
				throw new FieldCannotBeEmptyExceptionForPhotography("Please fill every field appropriately");
			}
			return photographyRepository.save(photography);
		}catch(FieldCannotBeEmptyExceptionForPhotography exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
		
//		if(photography.getpDiscription().equals(null) || photography.getpName().equals(null) || photography.getImagepath().equals(null)) 
//		{
//			throw new FieldCannotBeEmptyExceptionForPhotography("Please fill every field appropriately");
//		}
//		
//		return photographyRepository.save(photography);
	}

	@Override
	public List<Photography> allPhotography() {
        try {
        	
        	List<Photography> photographyList=photographyRepository.findAll();
            
            if(photographyList.isEmpty()) {
    			throw new NoPhotographyPresentException("There in no photography present in database");
    		}
            
    		return photographyList;
        	
        }catch(NoPhotographyPresentException exception) {
        	throw exception;
        }catch(RuntimeException exception) {
        	throw new RuntimeException(exception.getMessage());
        }
	}

	@Override
	@Transactional
	public List<Photography> searchingByName(String photographyName) {
		if(photographyName != null) {
		Query query = entityManager.createQuery("select p from Photography p where p.pName like :PG");
		query.setParameter("PG","%"+photographyName+"%");
		List<Photography> photographyNameList=query.getResultList();
		if(photographyNameList.size()==0) {
			String message="Sorry !! The Photographers "+ photographyName +" is not available";
			throw new MarriageCeremonyApplicationException(message);
		}

		return photographyNameList;
	}
		String message="Sorry !! The Photographers "+ photographyName +" is not available";
		throw new MarriageCeremonyApplicationException(message);
	}
	
	@Override
	@Transactional
	public int deleteVendor(int vendorId) {
		
		try {
			Optional<Photography> photography = photographyRepository.findById(vendorId);
			
			if(photography.isEmpty()) {
				throw new PhotographyDoesNotExistException("Photography does not exist with the id " + vendorId);
			
			}
			
			return photographyRepository.deleteVendor(vendorId);
		}catch(PhotographyDoesNotExistException exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

}
