//package com.timelec.services;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class ServiceProduction implements com.timelec.repository.RepositoryProduction{
//
////	ProductionRepository productionRepository;
////	TesteurEnReposRepository testeurEnRepos;	
//
//	
//	public int ETL(){
////		List<Time> result = productionRepository.calculeSecond(jour, testerID);
////		List<Summary> summaries= productionRepository.findByDateTesterID(jour, testerID);
////		int somme = 0;
////		for (int i = 1; i<result.size(); i++) {
////			int difference = (int) (result.get(i).getTime() - result.get(i-1).getTime());
////			difference /= 1000;
////			if (difference > (nbMinute * 60)) {
////            	System.out.println ("ligne " + i +" ==> " + difference );
////            	if (testeurEnRepos.exist(summaries.get(i).getIdSummary())== 0) {
////            		System.out.print("hellooooo");
////            		testeurEnRepos.insert(
////						summaries.get(i).getIdSummary(),
////						summaries.get(i).getTestStartTime(),
////						summaries.get(i).getTesterID(),
////						summaries.get(i).getMechanicalAssembly());
////            	}
////            }	
////            somme += difference;
////		}
////		System.out.println ("nb se seconde du " + jour + " pour le testeur " + testerID + " est: " +  somme);
////		System.out.println ("il y a  " + result.size() + " lignes ");	
////		return somme;
//		return 0;
//	}
//}