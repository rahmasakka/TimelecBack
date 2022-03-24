package com.timelec.timelec.controller;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.timelec.timelec.exception.ResourceNotFoundException;
import com.timelec.timelec.models.Summary;
import com.timelec.timelec.repository.ProductionRepository;

@CrossOrigin()
@RestController
@RequestMapping("/api/production")
public class ProductionController {

	
	@Autowired
	private ProductionRepository productionRepository;
	
	
	@GetMapping("/all")
	public List<Summary> listSummary(){
		return this.productionRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Summary getProductionById(@PathVariable Long id) {
		Summary prod = productionRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("summary not exist with id:" + id));
		return prod;
	}
	
	
	@RequestMapping(value = "/testerID/{testerID}", method = RequestMethod.GET)
	public List<Summary>getSummaryWithTesterID(@PathVariable Long testerID){
		return productionRepository.findByTesterID(testerID);
	}
	
	@RequestMapping(value="/testStartTime/{jour}", method = RequestMethod.GET)
	public List<Summary>getSummaryByStartTime(@PathVariable Date jour){
		return productionRepository.findByDate(jour);
	}
	
	
	@RequestMapping(value="/testStartTime/{jour}/testerID/{testerID}", method = RequestMethod.GET)
	public List<Summary>getSummaryByTesterStartTime(@PathVariable Date jour, @PathVariable Long testerID){
		return productionRepository.findByDateTesterID(jour, testerID);
	}
	
	
}