package com.timelec.EtlScheduled;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.timelec.timelec.models.Dashboard;
import com.timelec.timelec.models.Machine;
import com.timelec.timelec.models.TesteurEnProduction;
import com.timelec.timelec.models.TesteurEnRepos;
import com.timelec.timelec.repository.DashboardRepository;
import com.timelec.timelec.repository.MachineRepository;
import com.timelec.timelec.repository.TesteurEnProductionRepository;
import com.timelec.timelec.repository.TesteurEnReposRepository;
import com.timelec.timelec.service.EmailSenderService;
import com.timelec.timelec.p87.model.Summary;
import com.timelec.timelec.p87.repository.ETLDevP87Repository;

@Service
public class EtlP87Scheduled {

	@Autowired
    private ETLDevP87Repository productionRepository;
	
	@Autowired
	private DashboardRepository dashboardRepository;
	
	@Autowired
	private TesteurEnReposRepository testeurEnReposRepository;
	
	@Autowired
	private TesteurEnProductionRepository testeurEnProductionRepository;
	
	@Autowired
	private MachineRepository machineRepository;
	
	@Autowired
	private EmailSenderService senderService;
	
	public String getTime(long totalSecs) {
		long heures = (totalSecs / 3600) %24;
		long minutes = (totalSecs % 3600) / 60;
		long seconds = totalSecs % 60;
		return(heures + ":" + minutes + ":" + seconds);
		
	}
	
	
	@Scheduled(cron ="0 58 11 * * *")
	public void someJob() throws InterruptedException{
		LocalDate dateSystem = LocalDate.now();
		String dateString = dateSystem.toString();
		ETL(dateString);
	} 

	public void ETL(String jour) { 
		//String jour2 = jour.toString(); 
    	List<Machine> listMachine = machineRepository.findAll();
    	for (int tester = 0; tester< listMachine.size(); tester++) {
        	long quantiteNonConforme = 0;
        	long quantiteConforme = 0;	
        	long dureeFonctionnementSeconde = 0;	
        	long dureeDisfonctionnementSeconde = 0;
        	List<Summary> summaries = productionRepository.listSummarydByDateTester(jour, listMachine.get(tester).getIdMachine());
        	if((dashboardRepository.listLigneByDateTester(jour , listMachine.get(tester).getIdMachine())==0) ) {
        		if(productionRepository.nbLigneByDateTester(jour, listMachine.get(tester).getIdMachine())!=0) {
    	    		if(summaries.get(0).getTestStatus() == true) 
    	    			quantiteConforme++;
    	    		else 
    	    			quantiteNonConforme++;
    	        	
    	        	for(int i = 1; i< summaries.size(); i++) {
    	        		if(summaries.get(i).getTestStatus() == true) 
    	        			quantiteConforme++;
    	        		else 
    	        			quantiteNonConforme++;
    	    			int difference = (int) Math.abs(summaries.get(i).getTestStartTime().getTime()- summaries.get(i-1).getTestStartTime().getTime())/ 1000;
    	                if (difference < listMachine.get(tester).getTauxFonctionnement() * 60) {
    	                	dureeFonctionnementSeconde += difference;
    	                	TesteurEnProduction testeurEnProd = new TesteurEnProduction();
    	                	testeurEnProd.setIdSummary(summaries.get(i).getIdSummary());
    	                	testeurEnProd.setTesterID(listMachine.get(tester));
    	                	testeurEnProd.setIdMechanicalAssembly(summaries.get(i).getMechanicalAssembly().getIdMechanicalAssembly());
    	                	testeurEnProd.setTestStartTime(summaries.get(i).getTestStartTime());
    	                	testeurEnProd.setDureeSeconde(difference);
    	    	        	testeurEnProd.setTestStatus(summaries.get(i).getTestStatus());
    	    	        	testeurEnProd.setDuree(getTime(summaries.get(i).getTestStartTime().getTime()));
    	    	        	testeurEnProductionRepository.save(testeurEnProd);
    	                }
    	                else {
    	                	dureeDisfonctionnementSeconde += difference;  
    	                	TesteurEnRepos testeurEnRepos = new TesteurEnRepos();
    	                	testeurEnRepos.setIdSummary(summaries.get(i).getIdSummary());
    	                	testeurEnRepos.setTesterID(listMachine.get(tester));
    	                	testeurEnRepos.setIdMechanicalAssembly(summaries.get(i).getMechanicalAssembly().getIdMechanicalAssembly());
    	                	testeurEnRepos.setTestStartTime(summaries.get(i).getTestStartTime());
    	                	testeurEnRepos.setDureeSeconde(difference);
    	    	        	testeurEnRepos.setDuree(getTime(summaries.get(i).getTestStartTime().getTime()));
    	                	testeurEnRepos.setTestStatus(summaries.get(i).getTestStatus());
    	                	testeurEnReposRepository.save(testeurEnRepos);
    	                }
    	            }
	    	    	Dashboard newLigne = new Dashboard();
	    	    	newLigne.setDate(jour);
	    	    	newLigne.setDureeDisfonctionnementSeconde(dureeDisfonctionnementSeconde);
	    	    	newLigne.setDureeFonctionnementSeconde(dureeFonctionnementSeconde);
	    	    	newLigne.setDatabase("devP87");
	    	    	newLigne.setFinishTime(getTime(summaries.get(summaries.size() - 1).getTestStartTime().getTime()/1000));
	    	    	newLigne.setStartTime(getTime(summaries.get(0).getTestStartTime().getTime()/1000));
	    	    	newLigne.setQuantiteConforme(quantiteConforme);
	    	    	newLigne.setQuantiteNonConforme(quantiteNonConforme);
	    	    	newLigne.setTesteurId(listMachine.get(tester));
	    	    	newLigne.setDureeDisfonctionnement(getTime(dureeDisfonctionnementSeconde));
	    	    	newLigne.setDureeFonctionnement(getTime(dureeFonctionnementSeconde));
	    	    	dashboardRepository.save(newLigne);
	    		}
        	}
    	}
		senderService.sendEmail("rahmasakka3@gmail.com", "iData", "P87 de la date " + jour+ " charg?? avec succ??s");
	}
}