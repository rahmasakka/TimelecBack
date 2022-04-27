package com.timelec.timelec.ETL;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.testResultVm.models.Summary;
import com.timelec.testResultVm.repository.ETLRepository;
import com.timelec.timelec.models.TesteurEnRepos;
import com.timelec.timelec.repository.TesteurEnReposRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/ResultsVM")
public class ETLResultsVMController {
	
	@Autowired
    private ETLRepository productionRepository;
    
    @Autowired
    private TesteurEnReposRepository testeurEnReposRepository;

	@RequestMapping(value="/nbSecondParJour/{jour}/testerID/{testerID}/nbMinute/{nbMinute}")
	public int calculNbSecond(@PathVariable Date jour, @PathVariable Long testerID, @PathVariable int nbMinute) {
		List<Time> result = productionRepository.calculeSecond(jour, testerID);
		List<Summary> summaries= productionRepository.findByDateTesterID(jour, testerID);
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
            		testeurEnReposRepository.save(testeurEnRepos);
            	}
            }	
            somme += difference;
		}
		return somme;
	}
	
	@RequestMapping(value="/year/{year}")
	public List<Summary> findByYear(@PathVariable int year) {
		return productionRepository.findByYear(year);
	}

	@RequestMapping(value="/month/{month}")
	public List<Summary> findByMonth(@PathVariable int month) {
		return productionRepository.findByMonth(month);
	}
	
	@RequestMapping(value="/month/{month}/year/{year}")
	public List<Summary> findByMonthByYear(@PathVariable int month, @PathVariable int year) {
		return productionRepository.findByMonthByYear(month, year);
	}
	
}