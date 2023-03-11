package com.cg.marriageceremony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.marriageceremony.model.Photography;

@Repository

public interface PhotographyRepository extends JpaRepository<Photography, Integer>{
	@Query(value= "delete from Vendor v where v.vendorId=:vendorId")
	@Modifying
	int deleteVendor(@Param("vendorId") int vendorId);
}
