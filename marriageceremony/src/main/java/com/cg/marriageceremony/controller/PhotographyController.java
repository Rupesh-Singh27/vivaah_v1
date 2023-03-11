package com.cg.marriageceremony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.marriageceremony.model.Photography;
import com.cg.marriageceremony.service.PhotographyService;

@RestController
@RequestMapping("/marriageceremony")
@CrossOrigin(origins = "http://localhost:3000",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})

public class PhotographyController {

	@Autowired
	private PhotographyService photographyService;
	
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("photography/addPhotography")
	public  Photography addPhotography(@RequestBody  Photography pg) {
	   Photography phg=photographyService.addPhotography(pg);
				return phg;
	}
	@GetMapping("photography/getAllPhotography")
	public List<Photography> allPhotography(){
		return photographyService.allPhotography();
	}
    @GetMapping("photography/searchByPhotographyName")
    public List<Photography> searchingByName(@RequestParam("pname") String photographyName){
    	return photographyService.searchingByName(photographyName);
    }
    @DeleteMapping("photography/deletePhotography/{vid}")
    public int deleteVendor(@PathVariable("vid") int vendoeId) {
    	return photographyService.deleteVendor(vendoeId);
    }
}
