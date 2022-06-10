package com.timelec.timelec.gabarie.controller;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.timelec.timelec.gabarie.model.Production;
import com.timelec.timelec.gabarie.repository.ProductionRepository;
import com.timelec.timelec.gabarie.service.ProductionService;

@CrossOrigin
@RestController
@RequestMapping("/api/gabarie/production")
public class ProductionController {
	
	@Autowired
	ProductionRepository productionRepository;
	
	@Autowired
	ProductionService productionService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Production>getAllProductions(){
		return productionRepository.findAll();
	}
	
	@RequestMapping(value = "date/{jour}", method = RequestMethod.GET)
	public Page<Production>getProductionParDate(@PathVariable Date jour, @RequestParam int pageNumber, @RequestParam int pageSize){
		return productionService.getProductionParDate(jour, pageNumber, pageSize);
	}
	
	@RequestMapping(value = "dateDeb/{dateDeb}/dateFin/{dateFin}", method = RequestMethod.GET)
	public Page<Production>getProductionBetweenTwoDates(@PathVariable Date dateDeb,@PathVariable Date dateFin, @RequestParam int pageNumber, @RequestParam int pageSize){
		return productionService.getProductionBetweenTwoDates(dateDeb, dateFin, pageNumber, pageSize);
	}

	@RequestMapping(value = "of/{of}", method = RequestMethod.GET)
	public Page<Production>getProductionByOF(@PathVariable Integer of, @RequestParam int pageNumber, @RequestParam int pageSize)
	{
		return productionService.getProductionByOF(of, pageNumber, pageSize);
	}	

	@RequestMapping(value = "dateDeb/{dateDeb}/dateFin/{dateFin}/of/{of}", method = RequestMethod.GET)
	public Page<Production>getProductionBetweenTwoDates(@PathVariable Date dateDeb,@PathVariable Date dateFin, @PathVariable Integer of, @RequestParam int pageNumber, @RequestParam int pageSize){
		return productionService.getProductionBetweenTwoDatesByOF(dateDeb, dateFin, of, pageNumber, pageSize);
	}
	
	@RequestMapping(value = "date/{jour}/of/{of}", method = RequestMethod.GET)
	public Page<Production>getProductionDateOF(@PathVariable Date jour,@PathVariable int of,  @RequestParam int pageNumber, @RequestParam int pageSize){
		return productionService.getProductionParDateOF(jour, of, pageNumber, pageSize);
	}	
}