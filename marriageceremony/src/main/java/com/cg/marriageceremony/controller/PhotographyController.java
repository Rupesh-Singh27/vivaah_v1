package com.cg.marriageceremony.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.marriageceremony.model.Photography;
import com.cg.marriageceremony.service.MapValidationErrorService;
import com.cg.marriageceremony.service.PhotographyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/marriageceremony")
@SecurityRequirement(name = "Vivaah")
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.POST })

public class PhotographyController {

	Logger logger = LoggerFactory.getLogger(PhotographyController.class);

	
	@Autowired
	private PhotographyService photographyService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PreAuthorize("hasAuthority('user')")
	@PostMapping("photography/addPhotography")
	public ResponseEntity<?> addPhotography(@Valid @RequestBody Photography photography, BindingResult bindingResult) {
		
		logger.info("Inside Controller class: Request for adding photographer into the database");
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(bindingResult);
		if (errorMap != null) {
			logger.error("Fields consisted of error and error has been sent for same");
			return errorMap;
		}
		
		Photography savedPhotography = photographyService.addPhotography(photography);
		return new ResponseEntity<Photography>(savedPhotography, HttpStatus.CREATED);
	}

	@GetMapping("photography/getAllPhotography")
	public ResponseEntity<?> getAllPhotography() {
		
		logger.info("Inside Controller class: Request for getting all the photographers");
		
		List<Photography> photographersList = photographyService.getAllPhotography();
		return new ResponseEntity<List<Photography>>(photographersList, HttpStatus.FOUND);

	}

	@GetMapping("photography/findByPhotographyName/{photographerName}")
	public ResponseEntity<?> findByName(@PathVariable("photographerName") String photographyName) {
		
		logger.info("Inside Controller class: Request for getting all the phototgraphers by name");

		List<Photography> photographersListWithSameName = photographyService.findByName(photographyName);
		return new ResponseEntity<List<Photography>>(photographersListWithSameName, HttpStatus.FOUND);
	}

	@DeleteMapping("photography/deletePhotography/{vendorId}")
	public ResponseEntity<?> deleteVendorById(@PathVariable("vendorId") int vendorId) {
		
		logger.info("Inside Controller class: Request for deleting the phototgrapher by Id");

		
		String message = photographyService.deleteVendorById(vendorId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
