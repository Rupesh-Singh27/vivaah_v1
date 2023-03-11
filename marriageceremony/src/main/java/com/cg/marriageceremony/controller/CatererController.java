package com.cg.marriageceremony.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.cg.marriageceremony.model.Caterer;
import com.cg.marriageceremony.service.CatererService;
import com.cg.marriageceremony.service.MapValidationErrorService;

@RestController
@RequestMapping("/marriageceremony")
@CrossOrigin(origins = "http://localhost:3000",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
public class CatererController {
		
	Logger logger = LoggerFactory.getLogger(CatererController.class);
	
	@Autowired
	private CatererService catererService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("caterer/addCaterer")
	public ResponseEntity<?> addCaterer(@Valid @RequestBody Caterer catarer, BindingResult bindingResult) {
		
		logger.info("Inside Controller class: Request for adding caterer");
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(bindingResult);
		if (errorMap != null) {
			logger.error("Fields consisted of error and error has been sent for same");
			return errorMap;
		}
		
		Caterer caterer = catererService.addCaterer(catarer);
		return new ResponseEntity<Caterer>(caterer, HttpStatus.CREATED);
	}
	
	@GetMapping("caterer/getAllCaterers")
	public ResponseEntity<?> getAllCaterers(){
		
		logger.info("Inside Controller class: Request for getting all the caterers");
		
		List<Caterer> gotAllCaterersList = catererService.getAllCaterers();
		return new ResponseEntity<List<Caterer>>(gotAllCaterersList, HttpStatus.FOUND);
	}
	
    @GetMapping("caterer/searchByCatererName/{catererName}")
    public ResponseEntity<?> getByName(@PathVariable("catererName") String catererName){
    	
		logger.info("Inside Controller class: Request for getting all the caterers by name");
    	
    	List<Caterer> gotAllCatererWithSameName = catererService.getByName(catererName);
    	return new ResponseEntity<List<Caterer>>(gotAllCatererWithSameName, HttpStatus.FOUND);
    }
	
    @DeleteMapping("caterer/deleteCaterer/{vendorId}")
    public ResponseEntity<?> deleteVendor(@PathVariable("vendorId") int vendorId) {
    	
		logger.info("Inside Controller class: Request for deleting the caterer by Id");
    	
    	String message = catererService.deleteVendorById(vendorId);
    	return new ResponseEntity<String>(message, HttpStatus.GONE);
    }
}