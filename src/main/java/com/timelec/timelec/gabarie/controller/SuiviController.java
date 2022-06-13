package com.timelec.timelec.gabarie.controller;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.gabarie.model.Suivi;
import com.timelec.timelec.gabarie.service.SuiviService;

@CrossOrigin
@RestController
@RequestMapping("/api/gabarie/suivi")
public class SuiviController {
	
	@Autowired
	SuiviService suiviService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Page<Suivi>getAllSuivi(@RequestParam int pageNumber, @RequestParam int pageSize){
		return suiviService.getAll(pageNumber, pageSize);
	}
	
	@RequestMapping(value="date/{jour}", method = RequestMethod.GET)
	Page<Suivi>getSuiviParDate(@PathVariable Date jour, @RequestParam int pageNumber, @RequestParam int pageSize){
		return suiviService.getSuiviParDate(jour, pageNumber, pageSize);
	}
	
	@RequestMapping(value="dateDeb/{jour}/machine/{machine}", method = RequestMethod.GET)
	Page<Suivi>getSuiviParDateMachine(@PathVariable Date jour, @PathVariable String machine, @RequestParam int pageNumber, @RequestParam int pageSize){
		return suiviService.getSuiviParDateMachine(jour, machine, pageNumber, pageSize);
	}
	
	@RequestMapping(value="dateDeb/{dateDeb}/dateFin/{dateFin}", method = RequestMethod.GET)
	Page<Suivi>suivieBetweenTwoDates(@PathVariable Date dateDeb, @PathVariable Date dateFin, @RequestParam int pageNumber, @RequestParam int pageSize){
		return suiviService.suivieBetweenTwoDates(dateDeb, dateFin, pageNumber, pageSize);
	}
	
	@RequestMapping(value="machine/{machine}", method = RequestMethod.GET)
	Page<Suivi>suivieParMachines(@PathVariable String machine, @RequestParam int pageNumber, @RequestParam int pageSize){
		return suiviService.suivieParMachines(machine, pageNumber, pageSize);
	}
	
	@RequestMapping(value="dateDeb/{dateDeb}/dateFin/{dateFin}/machine/{machine}", method = RequestMethod.GET)
	Page<Suivi>suiviBetweenTwoDatesByMachine(@PathVariable Date dateDeb, @PathVariable Date dateFin,@PathVariable String machine, @RequestParam int pageNumber, @RequestParam int pageSize){
		return suiviService.suiviBetweenTwoDatesByMachine(dateDeb, dateFin, machine, pageNumber, pageSize);
	}	
}