package com.cg.marriageceremony.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.marriageceremony.model.Caterer;



@Repository
public interface CatererRepository  extends JpaRepository<Caterer, Integer>{


	List<Caterer> findByCatererName(String catererName);

}
