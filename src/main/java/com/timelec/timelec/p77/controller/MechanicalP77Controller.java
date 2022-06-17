package com.timelec.timelec.p77.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.timelec.timelec.p77.repository.MechanicalP77Repository;

@CrossOrigin
@RestController
@RequestMapping("/api/p77/mechanical")
public class MechanicalP77Controller {
	
	@Autowired
	MechanicalP77Repository mechanical;
    
    @GetMapping("/{of}")
    List<Object> assemblyByOf(@PathVariable int of ){
    	return mechanical.assemblyByOf(of);		
    }  
    
    @GetMapping("/packing/{of}")
    int sumPacking(@PathVariable int of) {
    	return mechanical.sumPacking(of);
    }  
}