package com.timelec.timelec.p77.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.models.Dashboard;
import com.timelec.timelec.models.TesteurEnProduction;
import com.timelec.timelec.models.TesteurEnRepos;
import com.timelec.timelec.p77.model.Summary;
import com.timelec.timelec.p77.repository.ETLP77Repository;
import com.timelec.timelec.repository.DashboardRepository;
import com.timelec.timelec.repository.TesteurEnProductionRepository;
import com.timelec.timelec.repository.TesteurEnReposRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/p77/etl")
public class ETLP77Controller {
	@Autowired
    private ETLP77Repository productionRepository;
	
	@Autowired
	private DashboardRepository dashboardRepository;
	
	@Autowired
	private TesteurEnReposRepository testeurEnReposRepository;
	
	@Autowired
	private TesteurEnProductionRepository testeurEnProductionRepository;
	
	
	Time getTime(long secondes) {
        long heures = (secondes / 3600) % 60;
        long minutes = (secondes / 60) % 60;
        long seconds = secondes % 60;
        Time timing = Time.valueOf(heures + ":" + minutes + ":" + seconds);
        return timing;
	}
	
    @GetMapping("/{jour}/{testerID}")
	private List<Summary> ETL(@PathVariable Date jour, @PathVariable int testerID) {    	
    	long quantiteNonConforme = 0;
    	long quantiteConforme = 0;	
    	long dureeFonctionnementSeconde = 0;	
    	long dureeDisfonctionnementSeconde = 0;	

    	List<Summary> summaries = productionRepository.listSummarydByDateTester(jour, testerID);
    	if((dashboardRepository.listLigneByDateTester(jour, testerID)==0) ) {
    		
    		if(productionRepository.nbLignedByDateTester(jour, testerID)!=0) {
	    		if(summaries.get(0).getTestStatus() == true) 
	    			quantiteConforme++;
	    		else 
	    			quantiteNonConforme++;
	        	
	        	for(int i = 1; i< summaries.size(); i++) {
	        		//Time timing = getTime(result.get(i).getTestStartTime().getTime()/1000);	
	                //System.out.println(timing);
	        		if(summaries.get(i).getTestStatus() == true) 
	        			quantiteConforme++;
	        		else 
	        			quantiteNonConforme++;
	    			int difference = (int) (summaries.get(i).getTestStartTime().getTime() - summaries.get(i-1).getTestStartTime().getTime());
	    			difference/=1000;
	                if(difference < 180) {
	                	dureeFonctionnementSeconde += difference;
	                	TesteurEnProduction testeurEnProd = new TesteurEnProduction();
	                	testeurEnProd.setIdSummary(summaries.get(i).getIdSummary());
	                	testeurEnProd.setTesterID(summaries.get(i).getTesterID());
	                	testeurEnProd.setIdMechanicalAssembly(summaries.get(i).getMechanicalAssembly().getIdMechanicalAssembly());
	                	testeurEnProd.setTestStartTime(summaries.get(i).getTestStartTime());
	                	testeurEnProd.setDureeSeconde(difference);
	    	        	testeurEnProd.setTestStatus(summaries.get(i).getTestStatus());
	    	        	testeurEnProd.setDuree(getTime(summaries.get(i).getTestStartTime().getTime()/1000));
	    	        	testeurEnProductionRepository.save(testeurEnProd);
	                }
	                else {
	                	dureeDisfonctionnementSeconde += difference;      
	                	TesteurEnRepos testeurEnRepos = new TesteurEnRepos();
	                	testeurEnRepos.setIdSummary(summaries.get(i).getIdSummary());
	                	testeurEnRepos.setTesterID(summaries.get(i).getTesterID());
	                	testeurEnRepos.setIdMechanicalAssembly(summaries.get(i).getMechanicalAssembly().getIdMechanicalAssembly());
	                	testeurEnRepos.setTestStartTime(summaries.get(i).getTestStartTime());
	                	testeurEnRepos.setDureeSeconde(difference);
	                	testeurEnRepos.setDuree(getTime(summaries.get(i).getTestStartTime().getTime()/1000));
	                	testeurEnRepos.setTestStatus(summaries.get(i).getTestStatus());
	                	testeurEnReposRepository.save(testeurEnRepos);

	                }
	            }
	    	    	Dashboard newLigne = new Dashboard();
	    	    	newLigne.setDate(jour);
	    	    	newLigne.setDureeDisfonctionnementSeconde(dureeDisfonctionnementSeconde);
	    	    	newLigne.setDureeFonctionnementSeconde(dureeFonctionnementSeconde);
	    	    	newLigne.setDatabase("p77");
	    	    	newLigne.setFinishTime(summaries.get(summaries.size() - 1).getTestStartTime());
	    	    	newLigne.setStartTime(summaries.get(0).getTestStartTime());
	    	    	newLigne.setQuantiteConforme(quantiteConforme);
	    	    	newLigne.setQuantiteNonConforme(quantiteNonConforme);
	    	    	newLigne.setTesteurId(testerID);
	    	    	newLigne.setDureeDisfonctionnement(getTime(dureeDisfonctionnementSeconde));
	    	    	newLigne.setDureeFonctionnement(getTime(dureeFonctionnementSeconde));
	    	    	dashboardRepository.save(newLigne);
	    	       // System.out.println(newLigne);	
	    		}
    	}
		return summaries;
	}
}