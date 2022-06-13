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

import com.timelec.timelec.gabarie.model.Demarrage;
import com.timelec.timelec.gabarie.service.DemarrageService;

@CrossOrigin
@RestController
@RequestMapping("/api/gabarie/demarrage/")
public class DemarrageController {
		
	@Autowired
	DemarrageService demarrageService;
	
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public Page<Demarrage>getAllDemarrage(@RequestParam int pageNumber, @RequestParam int pageSize){
		return demarrageService.getAll(pageNumber, pageSize);
	}
		
	@RequestMapping(value = "date/{jour}", method = RequestMethod.GET)
	public Page<Demarrage>getDemarrageParDate(@PathVariable Date jour, @RequestParam int pageNumber, @RequestParam int pageSize){
		return demarrageService.getDemarrageParDate(jour, pageNumber, pageSize);
	}
	
	@RequestMapping(value = "of/{of}", method = RequestMethod.GET)
	public Page<Demarrage>getDemarrageof(@PathVariable int of, @RequestParam int pageNumber, @RequestParam int pageSize){
		return demarrageService.getDemarrageOF(of, pageNumber, pageSize);
	}
	
	@RequestMapping(value = "dateDeb/{dateDeb}/dateFin/{dateFin}", method = RequestMethod.GET)
	public Page<Demarrage>getDemarragebetweenTwoDates(@PathVariable Date dateDeb, @PathVariable Date dateFin,  @RequestParam int pageNumber, @RequestParam int pageSize){
		return demarrageService.getDemarrageBetweenTwoDates(dateDeb, dateFin, pageNumber, pageSize);
	}
	
	@RequestMapping(value = "date/{jour}/of/{of}", method = RequestMethod.GET)
	public Page<Demarrage>getDemarrageParDateOF(@PathVariable Date jour,@PathVariable int of, @RequestParam int pageNumber, @RequestParam int pageSize){
		return demarrageService.getDemarrageDateOF(jour, of, pageNumber, pageSize);
	}
	
	@RequestMapping(value = "dateDeb/{dateDeb}/dateFin/{dateFin}/of/{of}", method = RequestMethod.GET)
	public Page<Demarrage>getDemarragebetweenTwoDatesOF(@PathVariable Date dateDeb, @PathVariable Date dateFin, @PathVariable int of, @RequestParam int pageNumber, @RequestParam int pageSize){
		return demarrageService.getDemarrageBetweenTwoDatesOF(dateDeb, dateFin, of, pageNumber, pageSize);
	}
}