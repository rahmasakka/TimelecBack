package com.timelec.timelec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.testParametersVM.models.Benche;
import com.timelec.testParametersVM.repository.BencheRepository;


@CrossOrigin
@RestController
@RequestMapping("/api/benche")
public class BencheController {

	@Autowired
	private BencheRepository bencheRepository;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Benche>getAllMachines(){
		return bencheRepository.findAll();
	}
}
