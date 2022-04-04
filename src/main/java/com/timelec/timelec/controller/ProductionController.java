package com.timelec.timelec.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.timelec.timelec.exception.ResourceNotFoundException;
import com.timelec.timelec.models.Summary;
import com.timelec.timelec.repository.ProductionRepository;


@CrossOrigin
@RestController
@RequestMapping("/api/production")

public class ProductionController {
	
    @Autowired
    private ProductionRepository productionRepository;

	@GetMapping("/all")
	public List<Summary> listSummary(){
		return this.productionRepository.findAll();
	}

   // @Transactional
	@Qualifier("productEntityManagerFactory") 
	@GetMapping("/{id}")
	public Summary getProductionById(@PathVariable Long id) {
		Summary prod = productionRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("summary not exist with id:" + id));
		return prod;
	}
	
	
	@RequestMapping(value = "/testerID/{testerID}", method = RequestMethod.GET)
	public List<Summary>getSummaryWithTesterID(@PathVariable Long testerID){
		List<Summary> listSummaryByTester = productionRepository.findByTesterID(testerID);
		System.out.println("count(*) du testerID "+testerID+" = " + listSummaryByTester.size());
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
		int difference = fin - debut; //nb seconde

		System.out.println("debut en seconde: "+ debut );
		System.out.println("fin en seconde: "+ fin );
		System.out.println("difference en seconde: "+ difference );

		int nb_heure = difference / (60*60);
		difference = difference - (nb_heure * 60 *60) ;
		int nb_minute = difference / (60);
		difference = difference - (nb_minute * 60);		
		System.out.println(nb_heure + ":" + nb_minute + ":" + difference );
		return fin - debut;
	}
	
		
	@RequestMapping(value="/nbSecondParJour/{jour}/testerID/{testerID}/nbMinute/{nbMinute}")
	public int calculNbSecond(@PathVariable Date jour, @PathVariable Long testerID, @PathVariable int nbMinute) {
		List<Time> result = calculeSecond(jour, testerID );
		List<Summary> summaries= productionRepository.findByDateTesterID(jour, testerID);
		int somme = 0;
		for (int i = 1; i<result.size(); i++) {
			int difference = (int) (result.get(i).getTime() - result.get(i-1).getTime()); // difference en milliseconde
			difference /= 1000;  // difference en seconde  
			if (difference > (nbMinute * 60)) { // nbMinutes en seconde 
            	System.out.println ("ligne " + i +" ==> " + difference );
            	if (productionRepository.exist(summaries.get(i).getIdSummary())== 0) {
            	productionRepository.insert(summaries.get(i).getIdSummary(),
            								summaries.get(i).getTestStartTime(),
            								summaries.get(i).getTesterID(),
            								summaries.get(i).getMechanicalAssembly());
            	}
            }	
		// 	System.out.println (" ==> " +difference);
            somme += difference;
		}
		System.out.println ("nb se seconde du "+jour+ " pour le testeur "+ testerID + " est: "+  somme);
		System.out.println ("il y a  " + result.size() + " lignes ");
		return somme;
	}
}