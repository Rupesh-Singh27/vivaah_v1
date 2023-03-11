package com.cg.marriageceremony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.marriageceremony.model.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer>  {
	 @Query(value= "delete from Vendor v where v.vendorId=:vid")
     @Modifying
     int deleteVendor(@Param("vid") int vid);


}