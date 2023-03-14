package com.cg.marriageceremony.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForVendor;
import com.cg.marriageceremony.exceptions.NoVendorPresentException;
import com.cg.marriageceremony.exceptions.VendorDoesNotExistException;
import com.cg.marriageceremony.model.Vendor;
import com.cg.marriageceremony.repository.VendorRepository;

@Component
public class VendorServiceImpl implements VendorService {
       @Autowired
		private VendorRepository vendorRepository;

		@PersistenceContext
		private EntityManager em;

		@Override
		@Transactional
		public Vendor addVendor(Vendor vendor) {
			
			try {
				
				if(vendor.getVendorId() < 0 || vendor.getCategory() == null || vendor.getPrice() < 0.0f || vendor.getPrice() == null || vendor.getContactNo().SIZE < 10 || vendor.getContactNo() == null || vendor.getCartItems() == null)
				{
					throw new FieldCannotBeEmptyExceptionForVendor("Please fill every field appropriately");
				}
				
				return vendorRepository.save(vendor);
				
			}catch(FieldCannotBeEmptyExceptionForVendor exception) {
				throw exception;
			}catch(RuntimeException exception) {
				throw new RuntimeException(exception.getMessage());
			}
		}

		@Override
		public List<Vendor> allVendor() {
			try {
				List<Vendor> vendorlist = vendorRepository.findAll();
				
				if(vendorlist.isEmpty()) {
					throw new NoVendorPresentException("There in no vendors present in database");
				}
				
				return vendorlist;
			}catch(NoVendorPresentException exception) {
				throw exception;
			}catch(RuntimeException exception) {
				throw new RuntimeException(exception.getMessage());
			}
		}

		@Override
		@Transactional
		public List searchByCategory(String category) {
			Query q = em.createQuery("select c from Vendor c where c.category like :v");
			q.setParameter("v", "%" + category + "%");
			List<Vendor> category1 = q.getResultList();
			return category1;
		}

		@Override
		@Transactional
		public int deleteVendor(int vendorId) {
			
			Vendor vendor = vendorRepository.findById(vendorId).get();
			
			if(vendor == null) {
				throw new VendorDoesNotExistException("Vendor does not exist with the id " + vendorId);
			
			}
			
			return vendorRepository.deleteVendor(vendorId);
		}

		@Override
		@Transactional
		public Vendor updateVendor(Vendor newVendor, int vendorId) {
			
			if(newVendor.getVendorId() < 0 || newVendor.getCategory().equals(null) || newVendor.getPrice() < 0.0f|| newVendor.getContactNo().SIZE < 10 || newVendor.getCartItems() == null)
			{
				throw new FieldCannotBeEmptyExceptionForVendor("Please fill every field appropriately");
			}
			
			Vendor vendor = em.find(Vendor.class,vendorId);
			
			if(vendor == null) {
				throw new VendorDoesNotExistException("Vendor does not exist with the id " + vendorId);
			}
			
			vendor.setCategory(newVendor.getCategory());
			vendor.setCartItems(newVendor.getCartItems());
			vendor.setPrice(newVendor.getPrice());
			return vendor;
		}
		}