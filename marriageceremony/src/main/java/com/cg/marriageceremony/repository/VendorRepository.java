package com.cg.marriageceremony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.marriageceremony.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer>  {
	@Query(value="select v from Vendor v where v.vendorId in(select c.vendor.vendorId from CartItems c where c.customer.custId= :cid)")
	List<Vendor> findAllVendorInCart(@Param("cid") int custId);

       @Query(value= "delete from Vendor v where v.vendorId=:vid")
       @Modifying
       int deleteVendor(@Param("vid") int vid);


}