package com.cg.marriageceremony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cg.marriageceremony.model.MakeUp;
import com.cg.marriageceremony.service.MakeUpService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/marriageceremony")
@CrossOrigin(origins = "http://localhost:3000",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})

public class MakeUpController {
	@Autowired
	private MakeUpService makeupService;
	
	@PostMapping("makeup/addMakeup")
	public  MakeUp addMakeUp(@RequestBody  MakeUp mu) {
		MakeUp makeup=makeupService.addMakeUp(mu);
				return makeup;
	}
	@GetMapping("makeup/getAllMakeup")
	public List<MakeUp> allMakeUp(){
		return makeupService.allMakeUp();
	}
    @GetMapping("makeup/searchByMakeupName")
    public List<MakeUp> searchingByName(@RequestParam("mname") String makeupName){
    	return makeupService.searchingByName(makeupName);
    }
    @DeleteMapping("makeup/deleteMakeup/{vid}")
    public int deleteVendor(@PathVariable("vid") int vendoeId) {
    	return makeupService.deleteVendor(vendoeId);
    }
}
