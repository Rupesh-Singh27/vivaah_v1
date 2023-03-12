package com.cg.marriageceremony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForPhotography;
import com.cg.marriageceremony.exceptions.NoPhotographyPresentException;
import com.cg.marriageceremony.exceptions.PhotographyDoesNotExistException;
import com.cg.marriageceremony.model.Photography;
import com.cg.marriageceremony.repository.PhotographyRepository;

@Service
public class PhotographyServiceImpl implements PhotographyService{
	
	@Autowired
	private PhotographyRepository photographyRepository;

	@Override
	public Photography addPhotography(Photography photography) {
		try {
			if(photography.getPhotographyDescription() == null || photography.getPhotographyName() == null || photography.getImagepath() == null) {
				throw new FieldCannotBeEmptyExceptionForPhotography("Please fill every field appropriately");
			}
			return photographyRepository.save(photography);
		}catch(FieldCannotBeEmptyExceptionForPhotography exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public List<Photography> getAllPhotography() {
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
	public List<Photography> findByName(String photographyName) {
		try {
			List<Photography> listOfPhotographyWithSameName = photographyRepository.findByCatererName(photographyName);
			if(listOfPhotographyWithSameName.isEmpty()) {
				throw new NoPhotographyPresentException("No Photographer with the name " + photographyName + " is the database!!!");
			}
			
			return listOfPhotographyWithSameName;
			
		}catch (NoPhotographyPresentException exception) {
			throw exception;
		}catch (RuntimeException exception) {
			throw new RuntimeException("Unexpected behaviour!!! Working on it...");
		}
	}
	
	@Override
	public String deleteVendorById(int vendorId) {
		
		try {
			Photography photography = photographyRepository.findById(vendorId).get();
			
			if(photography == null) {
				throw new PhotographyDoesNotExistException("Photography does not exist with the id " + vendorId);
			}
			
			photographyRepository.delete(photography);
			return "Photographer with id " + vendorId + " has been deleted";
		}catch(PhotographyDoesNotExistException exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

}
