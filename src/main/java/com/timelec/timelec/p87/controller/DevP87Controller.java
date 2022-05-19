package com.timelec.timelec.p87.controller;

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

import com.timelec.timelec.p87.model.Summary;
import com.timelec.timelec.p87.repository.DevP87Respository;
import com.timelec.timelec.p87.service.DevP87Service;
@CrossOrigin
@RestController
@RequestMapping("/api/devP87")
public class DevP87Controller {

	
	 @Autowired
	    private DevP87Respository productionRepository;
	    
	    @Autowired 
	    private DevP87Service productionService;
	    
	    
	    @GetMapping("/all")
		Page<Summary> listSummary(@RequestParam int pageNumber,@RequestParam int pageSize){
			return productionService.getSummary(pageNumber, pageSize);
		}
	    
	   /* 
		@GetMapping("/{id}")
		public Summary getProductionById(@PathVariable Long id) {
			Summary prod = productionRepository.findById(id).
					orElseThrow(() -> new ResourceNotFoundException("summary not exist with id:" + id));
			return prod;
		}
		*/

		@RequestMapping(value = "/testerID/{testerID}", method = RequestMethod.GET)
		public Page<Summary>getSummaryWithTesterID1(@PathVariable Long testerID ,@RequestParam int pageNumber,@RequestParam int pageSize){
			Page<Summary> listSummaryByTester = productionService.findByTesterIDPageable(testerID, pageNumber, pageSize);
			return listSummaryByTester;
		}
		
		@RequestMapping(value="/testStartTime/{jour}", method = RequestMethod.GET)
		public Page<Summary>getSummaryByStartTime(@PathVariable Date jour, @RequestParam int pageNumber, @RequestParam int pageSize){
			//System.out.println("nb de ligne: " + productionRepository.findByDate(jour).size());
			return productionService.findByDate(jour, pageNumber, pageSize);
		}
		
		@RequestMapping(value="/testStartTime/{jour}/testerID/{testerID}", method = RequestMethod.GET)
		public Page<Summary>getSummaryByTesterStartTime(@PathVariable Date jour, @PathVariable Long testerID, @RequestParam int pageNumber, @RequestParam int pageSize){
			//System.out.println("nb de ligne: "+ productionRepository.findByDateTesterID(jour, testerID).size());
			return productionService.findByDateTesterID(jour, testerID, pageNumber, pageSize);
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
			int nb_heure = difference / (60*60);
			difference = difference - (nb_heure * 60 *60) ;
			int nb_minute = difference / (60);
			difference = difference - (nb_minute * 60);		
			System.out.println(nb_heure + ":" + nb_minute + ":" + difference );
			return fin - debut;
		}
		
		
		@RequestMapping(value="/{jour1}/{jour2}", method = RequestMethod.GET)
		Page<Summary>listSummaryBetweenTwoDays(@RequestParam int pageNumber, @RequestParam int pageSize, @PathVariable Date jour1, @PathVariable Date jour2){
			return productionService.listSummaryBetweenTwoDays(jour1, jour2, pageNumber, pageSize);
		}
		
		@RequestMapping(value="/{jour1}/{jour2}/{testerID}", method = RequestMethod.GET)
		Page<Summary>listSummaryBetweenTwoDaysByTesterID(@RequestParam int pageNumber, @RequestParam int pageSize, @PathVariable Date jour1, @PathVariable Date jour2, @PathVariable Long testerID){
			return productionService.listSummaryBetweenTwoDaysByTesterID(jour1, jour2, testerID, pageNumber, pageSize);
		}
		
		@RequestMapping(value="/year/{year}", method = RequestMethod.GET)
		Page<Summary> findByYear(@RequestParam int pageNumber, @RequestParam int pageSize, @PathVariable int year) {
			return productionService.findByYear(year, pageNumber, pageSize);
		}
		

		@RequestMapping(value="/month/{month}", method = RequestMethod.GET)
		Page<Summary> findByMonth(@RequestParam int pageNumber,@RequestParam int pageSize, @PathVariable int month) {
			return productionService.findByMonth(month, pageNumber, pageSize);
		}
		
		@RequestMapping(value="/month/{month}/year/{year}", method = RequestMethod.GET)
		Page<Summary> findByMonthByYear(@RequestParam int pageNumber,@RequestParam int pageSize, @PathVariable int month, @PathVariable int year) {
			return productionService.findByMonthByYear(month, year, pageNumber, pageSize);
		}
		
		@RequestMapping(value="/listeTesterIdByDatabase", method = RequestMethod.GET)
		public List<Object>listetesterId(){
			return productionRepository.listTesterIdByDatabase();
		}
	}