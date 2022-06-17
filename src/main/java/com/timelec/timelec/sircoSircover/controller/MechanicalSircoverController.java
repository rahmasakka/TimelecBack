package com.timelec.timelec.sircoSircover.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.sircoSircover.repository.MechanicalSircoverRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/sircoSircover/mechanical")

public class MechanicalSircoverController {
	@Autowired
	MechanicalSircoverRepository mechanical;
    
    @GetMapping("/{of}")
    List<Object> assemblyByOf(@PathVariable int of ){
    	return mechanical.assemblyByOf(of);		
    }  
    
    @GetMapping("/packing/{of}")
    int sumPacking(@PathVariable int of) {
    	return mechanical.sumPacking(of);
    }  
}