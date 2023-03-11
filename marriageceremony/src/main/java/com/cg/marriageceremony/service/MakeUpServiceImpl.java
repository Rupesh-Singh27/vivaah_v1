package com.cg.marriageceremony.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForMakeup;
import com.cg.marriageceremony.exceptions.MakeupDoesNotExistException;
import com.cg.marriageceremony.exceptions.MarriageCeremonyApplicationException;
import com.cg.marriageceremony.exceptions.NoMakeupPresentException;
import com.cg.marriageceremony.model.MakeUp;
import com.cg.marriageceremony.repository.MakeUpRepository;

@Component
public class MakeUpServiceImpl implements MakeUpService{

	@Autowired
	private MakeUpRepository makeUpRepository;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public MakeUp addMakeUp(MakeUp makeUp) {
		
		try {
			if(makeUp.getmDiscription() == null || makeUp.getmName() == null || makeUp.getImagepath() == null) {
				throw new FieldCannotBeEmptyExceptionForMakeup("Please fill every field appropriately");
			}
			return makeUpRepository.save(makeUp);
		}catch(FieldCannotBeEmptyExceptionForMakeup exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
		
//		if(makeUp.getmDiscription().equals(null) || makeUp.getmName().equals(null) || makeUp.getImagepath().equals(null)) 
//		{
//			throw new FieldCannotBeEmptyExceptionForMakeup("Please fill every field appropriately");
//		}
//		
//		return makeUpRepository.save(makeUp);
	}

	@Override
	public List<MakeUp> allMakeUp() {
		
		try {
			List<MakeUp> makeupList=makeUpRepository.findAll();
			if(makeupList.isEmpty()) {
				throw new NoMakeupPresentException("There in no makeup present in database");
			}
			return makeupList;
		}catch(NoMakeupPresentException exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
//		List<MakeUp> makeupList=makeUpRepository.findAll();		
//		
//		if(makeupList.isEmpty()) {
//			throw new NoMakeupPresentException("There in no makeup present in database");
//		}
//		
//		return makeupList;
	}

	@Override
	@Transactional
	public List<MakeUp> searchingByName(String makeupName) {
		if(makeupName != null) {
		Query query=entityManager.createQuery("select m from MakeUp m where m.mName like :MU");
		query.setParameter("MU","%"+makeupName+"%");
		List<MakeUp> makeupNameList=query.getResultList();
		if(makeupNameList.size()==0) {
			String message="Sorry !! The MakeUp "+ makeupName +" is not available";
			throw new MarriageCeremonyApplicationException(message);
		}
		return makeupNameList;
	}
			String message="Sorry !! The MakeUp "+ makeupName +" is not available";
			throw new MarriageCeremonyApplicationException(message);

}
	@Override
	@Transactional
	public int deleteVendor(int vendorId) {
		
		try {
			return makeUpRepository.deleteVendor(vendorId);
		}catch (RuntimeException exception) {
			throw new MakeupDoesNotExistException("Caterer does not exist with the id " + vendorId);
		}
		
		
//		Optional<MakeUp> makeup = makeUpRepository.findById(vendorId);
//		
//		if(makeup.isEmpty()) {
//			throw new MakeupDoesNotExistException("Caterer does not exist with the id " + vendorId);
//		
//		}
//		
//		return makeUpRepository.deleteVendor(vendorId);
	}
}