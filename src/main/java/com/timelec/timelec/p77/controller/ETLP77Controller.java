package com.timelec.timelec.p77.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.models.TesteurEnProduction;
import com.timelec.timelec.models.TesteurEnRepos;
import com.timelec.timelec.p77.model.Summary;
import com.timelec.timelec.p77.repository.ETLP77Repository;
import com.timelec.timelec.repository.TesteurEnProductionRepository;
import com.timelec.timelec.repository.TesteurEnReposRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/p77")
public class ETLP77Controller {
	@Autowired
    private ETLP77Repository productionRepository;
    
    @Autowired
    private TesteurEnReposRepository testeurEnReposRepository;

    @Autowired
    private TesteurEnProductionRepository testeurEnProductionRepository;
    
	@RequestMapping(value="/nbSecondParJour/{jour}/nbMinute/{nbMinute}")
	public int calculNbSecond(@PathVariable Date jour, @PathVariable int nbMinute) {
		List<Time> result = productionRepository.calculeSecond(jour);
		List<Summary> summaries= productionRepository.findByDateTesterID(jour);
		int somme = 0;
		for (int i = 1; i<result.size(); i++) {
			int difference = (int) (result.get(i).getTime() - result.get(i-1).getTime());
			difference /= 1000;
			if (difference > (nbMinute * 60)) {
	        	if(testeurEnReposRepository.existBySummary(summaries.get(i).getIdSummary())==0) {
                	TesteurEnRepos testeurEnRepos = new TesteurEnRepos();
                	testeurEnRepos.setIdSummary(summaries.get(i).getIdSummary());
                	testeurEnRepos.setTesterID(summaries.get(i).getTesterID());
                	testeurEnRepos.setIdMechanicalAssembly(summaries.get(i).getMechanicalAssembly().getIdMechanicalAssembly());
                	testeurEnRepos.setTestStartTime(summaries.get(i).getTestStartTime());
                	testeurEnRepos.setDureeSeconde(difference);
                	String time;
    				int seconde = difference;
    	        	int minute = seconde / 60;
    	        	seconde =  seconde -(minute*60);
    	        	if( minute <10) {
    	        		time = "00:0"+minute+":"+seconde;
    	        	}else {
    	        		time = "00:"+minute+":"+seconde;
    	        	}	        	
    	        	Time duree = java.sql.Time.valueOf(time);
                	testeurEnRepos.setDuree(duree);
                	testeurEnRepos.setTestStatus(summaries.get(i).getTestStatus());
            		testeurEnReposRepository.save(testeurEnRepos);
            	}
            } else {
	        	if(testeurEnProductionRepository.existBySummary(summaries.get(i).getIdSummary())==0) {                	
                	TesteurEnProduction testeurEnProd = new TesteurEnProduction();
                	testeurEnProd.setIdSummary(summaries.get(i).getIdSummary());
                	testeurEnProd.setTesterID(summaries.get(i).getTesterID());
                	testeurEnProd.setIdMechanicalAssembly(summaries.get(i).getMechanicalAssembly().getIdMechanicalAssembly());
                	testeurEnProd.setTestStartTime(summaries.get(i).getTestStartTime());
                	testeurEnProd.setDureeSeconde(difference);
                	String time;
    				int seconde = difference;
    	        	int minute = seconde / 60;
    	        	seconde =  seconde -(minute*60);
    	        	if( minute <10) {
    	        		time = "00:0"+minute+":"+seconde;
    	        	}else {
    	        		time = "00:"+minute+":"+seconde;
    	        	}	        	
    	        	Time duree = java.sql.Time.valueOf(time);
    	        	testeurEnProd.setTestStatus(summaries.get(i).getTestStatus());
    	        	testeurEnProd.setDuree(duree);
                	testeurEnProductionRepository.save(testeurEnProd);
            	}
            }
            somme += difference;
		}
		return somme;
	}
}