package com.timelec.timelec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.exception.ResourceNotFoundException;
import com.timelec.timelec.models.CentreCharge;
import com.timelec.timelec.models.UAP;
import com.timelec.timelec.repository.LoadChargeRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/cc")
public class LoadChargeController {

	@Autowired
	LoadChargeRepository loadChargeRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CentreCharge>getAllCCs(){
		return loadChargeRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CentreCharge getCCById(@PathVariable int id) {
		CentreCharge loadCharge = loadChargeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Load charge not exist with id:" + id));
		return loadCharge;
	}
	
	@PostMapping(path = "/create")
	public CentreCharge add(@RequestBody CentreCharge cc) {
	    CentreCharge cc1 = loadChargeRepository.save(cc);
	  	return cc1;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Boolean>> deleteCC(@PathVariable int id){
		CentreCharge cc = loadChargeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("CC not exit with id:" + id));
		loadChargeRepository.delete(cc);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CentreCharge> updateCC(@PathVariable int id, @RequestBody CentreCharge loadChargeDetails){
		CentreCharge cc = loadChargeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("CC not exit with id:" + id));
		cc.setCCDescription(loadChargeDetails.getCCDescription());
		cc.setCCName(loadChargeDetails.getCCName());
		cc.setUap(loadChargeDetails.getUap());		
		CentreCharge updateCC = loadChargeRepository.save(cc);
		return ResponseEntity.ok(updateCC);	
	}		
	
	@RequestMapping(value="/sonByMother/{IdUAP}",  method = RequestMethod.GET)
	public List<CentreCharge> listCCByUAP(@PathVariable UAP IdUAP) {
		return loadChargeRepository.listCCByUAP(IdUAP);
	}
	
	
}