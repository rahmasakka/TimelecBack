package com.timelec.timelec.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.testResultsDevP87.models.Summary;
import com.timelec.testResultsDevP87.repository.DevP87Respository;
import com.timelec.timelec.exception.ResourceNotFoundException;
import com.timelec.timelec.services.DevP87Service;

@CrossOrigin
@RestController
@RequestMapping("/api/devP87")
public class DevP87Controller {

	
	@Autowired
    private DevP87Respository productionRepository;
	
    @Autowired 
    private DevP87Service productionService;
    
    /*
    @GetMapping("/all")
	public Page<Summary> listSummary(){
		return productionRepository.findAll();
	}
     */
    
    
    @GetMapping("/all")
	Page<Summary> listSummary(@RequestParam (required = false, defaultValue = "0") int pageNumber,@RequestParam int pageSize){
		return productionService.getSummary(pageNumber, pageSize);
	}
    
    
	@GetMapping("/{id}")
	public Summary getProductionById(@PathVariable Long id) {
		Summary prod = productionRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("summary not exist with id:" + id));
		return prod;
	}
	
	
	@RequestMapping(value = "/testerID/{testerID}", method = RequestMethod.GET)
	public List<Summary>getSummaryWithTesterID(@PathVariable Long testerID){
		List<Summary> listSummaryByTester = productionRepository.findByTesterID(testerID);
		//System.out.println("count(*) du testerID "+testerID+" = " + listSummaryByTester.size());
		return listSummaryByTester;
	}
	
	
	
	@RequestMapping(value = "/testerIDPageable/{testerID}", method = RequestMethod.GET)
	public Page<Summary>getSummaryWithTesterID1(@PathVariable Long testerID ,@RequestParam int pageNumber,@RequestParam int pageSize){
		Page<Summary> listSummaryByTester = productionService.findByTesterIDPageable(testerID, pageNumber, pageSize);
		//System.out.println("count(*) du testerID "+testerID+" = " + listSummaryByTester.size());
		return listSummaryByTester;
	}
	
	@RequestMapping(value="/testStartTime/{jour}", method = RequestMethod.GET)
	public List<Summary>getSummaryByStartTime(@PathVariable Date jour){
		System.out.println("nb de ligne: " + productionRepository.findByDate(jour).size());
		return productionRepository.findByDate(jour);
	}
	
	@RequestMapping(value="/testStartTime/{jour}/testerID/{testerID}", method = RequestMethod.GET)
	public List<Summary>getSummaryByTesterStartTime(@PathVariable Date jour, @PathVariable Long testerID){
		System.out.println("nb de ligne: "+ productionRepository.findByDateTesterID(jour, testerID).size());
		return productionRepository.findByDateTesterID(jour, testerID);
	}
	
	@RequestMapping(value="/heure/{jour}/testerID/{testerID}")
	public List<Time> calculeSecond(@PathVariable Date jour, @PathVariable Long testerID){
		return productionRepository.calculeSecond(jour, testerID);
	}
	
	@RequestMapping(value="/calcul/{jour}/testerID/{testerID}")
	public Integer calculNbSecondFinDebut(@PathVariable Date jour, @PathVariable Long testerID) {
		List<Time> Result = calculeSecond(jour, testerID );
		System.out.println("nb de ligne (count(*)) "+ Result.size());
		int debut = (int) (Result.get(0).getTime() / 1000); //nb seconde
		int fin =  (int) (Result.get(Result.size()-1).getTime() / 1000); //nb seconde
		int difference = fin - debut;
/*
		System.out.println("debut en seconde: "+ debut );
		System.out.println("fin en seconde: "+ fin );
		System.out.println("difference en seconde: "+ difference );
*/
		int nb_heure = difference / (60*60);
		difference = difference - (nb_heure * 60 *60) ;
		int nb_minute = difference / (60);
		difference = difference - (nb_minute * 60);		
		System.out.println(nb_heure + ":" + nb_minute + ":" + difference );
		return fin - debut;
	}
	
	@RequestMapping(value="/{jour1}/{jour2}", method = RequestMethod.GET)
	public List<Summary>listSummaryBetweenTwoDays(@PathVariable Date jour1, @PathVariable Date jour2){
		return productionRepository.listSummaryBetweenTwoDays(jour1, jour2);
	}
	
	
	@RequestMapping(value="/{jour1}/{jour2}/{testerID}", method = RequestMethod.GET)
	public List<Summary>listSummaryBetweenTwoDaysByTesterID(@PathVariable Date jour1, @PathVariable Date jour2, @PathVariable Long testerID){
		return productionRepository.listSummaryBetweenTwoDaysByTesterID(jour1, jour2, testerID);
	}
	
}
