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
import com.timelec.timelec.models.UAP;
import com.timelec.timelec.repository.UAPRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/uap")
public class UAPController {
	
	@Autowired
	UAPRepository uapRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<UAP>getAllUAPs(){
		return uapRepository.findAll();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UAP getUAPById(@PathVariable int id) {
		UAP uap = uapRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("UAP not exist with id:" + id));
		return uap;
	}
	
	
	@PostMapping(path = "/createUAP")
	public UAP add(@RequestBody UAP uap) {
	    UAP uap1 = uapRepository.save(uap);
	  	return uap1;
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Boolean>> deleteUap(@PathVariable int id){
		UAP uap = uapRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("UAP not exit with id:" + id));
		uapRepository.delete(uap);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UAP> updateUap(@PathVariable int id, @RequestBody UAP uapDetails){
		UAP uap = uapRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("UAP not exit with id:" + id));
		uap.setUapDescription(uapDetails.getUapDescription());
		uap.setUapName(uapDetails.getUapName());
		
		UAP updateUAP = uapRepository.save(uap);
		return ResponseEntity.ok(updateUAP);
	}
	
	
	@RequestMapping(value="/listMachineByUAP/{IdUAP}",  method = RequestMethod.GET)
	public List<UAP> listMachineByUAP(@PathVariable int IdUAP) {
		return uapRepository.listMachineByUAP(IdUAP);
	}	
}