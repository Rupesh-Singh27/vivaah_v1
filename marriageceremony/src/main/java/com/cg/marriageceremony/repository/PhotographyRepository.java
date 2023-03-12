package com.cg.marriageceremony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.marriageceremony.model.Photography;

@Repository

public interface PhotographyRepository extends JpaRepository<Photography, Integer>{

	List<Photography> findByPhotographyName(String photographyName);
}
