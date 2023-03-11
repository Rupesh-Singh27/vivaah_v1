package com.cg.marriageceremony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.marriageceremony.exceptions.CatererDoesNotExistException;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyException;
import com.cg.marriageceremony.exceptions.NoCatererPresentException;
import com.cg.marriageceremony.model.Caterer;
import com.cg.marriageceremony.repository.CatererRepository;


@Service
public class CatererServiceImpl implements CatererService{
	
	@Autowired
	private CatererRepository catererRepository;

	@Override
	public Caterer addCaterer(Caterer caterer) {
		try {
			if(caterer.getCatererDescription() == null || caterer.getCatererName() == null || caterer.getImagepath() == null) {
				throw new FieldCannotBeEmptyException("Please fill every field appropriately");
			}
			return catererRepository.save(caterer);
		}catch(FieldCannotBeEmptyException exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public List<Caterer> getAllCaterers() {
		
		try {
			List<Caterer> caterersList=catererRepository.findAll();
			
			if(caterersList.isEmpty()) {
				throw new NoCatererPresentException("There in no caterers present in database");
			}
			return caterersList;
		}catch (NoCatererPresentException exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public List<Caterer> getByName(String catererName) {
		 
		try {
			List<Caterer> listOfCatererWithSameName = catererRepository.findByCatererName(catererName);
			if(listOfCatererWithSameName.isEmpty()) {
				throw new NoCatererPresentException("No Caterer with the name " + catererName + " is the database!!!");
			}
			return listOfCatererWithSameName;
		}catch (NoCatererPresentException exception) {
			throw exception;
		}catch (RuntimeException exception) {
			throw new RuntimeException("Unexpected behaviour!!! Working on it...");
		}
	}

	@Override
	public String deleteVendorById(int vendorId) {
		
		try {
			Caterer caterer = catererRepository.findById(vendorId).get();
			if(caterer == null) {
				throw new CatererDoesNotExistException("Caterer does not exist with the id " + vendorId);
			}
			catererRepository.delete(caterer);
			return "Caterer with the Id: " + vendorId + " has been deleted!!!";
		}catch(CatererDoesNotExistException exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}	
	}

}
