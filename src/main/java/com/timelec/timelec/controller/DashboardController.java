package com.timelec.timelec.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.models.Dashboard;
import com.timelec.timelec.repository.DashboardRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	@Autowired
	DashboardRepository repos;
		
	//1 [database testerID datedeb datefin]
	@RequestMapping(value = "/database/{database}/dateDeb/{dateDeb}/dateFin/{dateFin}/tester/{tester}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardBetween2DaysByDatabaseByTester(@PathVariable String database, @PathVariable Date dateDeb, @PathVariable Date dateFin, @PathVariable int tester){
		return repos.getDashboardBetween2DaysByDatabaseByTester(dateDeb, dateFin, database, tester);
	}
	
	//2 [database testerID datedeb !datefin]
	@RequestMapping(value = "/database/{database}/dateDeb/{dateDeb}/tester/{tester}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDateByDatabaseByTester(@PathVariable String database, @PathVariable Date dateDeb, @PathVariable int tester){
		return repos.getDashboardByDateByDatabaseByTester(dateDeb, database, tester);
	}

	//3 [database testerID !datedeb !datefin]
	@RequestMapping(value="/database/{database}/tester/{tester}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDatabaseByTesterID(@PathVariable String database, @PathVariable int tester){
		return repos.getDashboardByDatabaseByTesterID(database, tester);
	}
	
	//4 [database !testerID datedeb datefin]
	@RequestMapping(value = "/database/{database}/dateDeb/{dateDeb}/dateFin/{dateFin}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardBetween2DaysByDatabase(@PathVariable String database, @PathVariable Date dateDeb, @PathVariable Date dateFin){
		return repos.getDashboardBetween2DaysByDatabase(dateDeb, dateFin, database);
	}
	
	//5 [database !testerID datedeb !datefin]
	@RequestMapping(value = "/database/{database}/dateDeb/{jour}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDateByDatabase(@PathVariable String database, @PathVariable Date jour){
		return repos.getDashboardByDateByDatabase(jour, database);
	}
	
	
	//6 [database !testerID !datedeb !datefin]
	@RequestMapping(value = "/database/{db}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDatabase(@PathVariable String db){
		System.out.println(repos.getDashboardByDatabase(db).get(0).getDate());
		return repos.getDashboardByDatabase(db);
	}
	
	//7 [!database testerID datedeb datefin]
	@RequestMapping(value = "/dateDeb/{dateDeb}/dateFin/{dateFin}/tester/{tester}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardBetween2DaysByTester(@PathVariable Date dateDeb, @PathVariable Date dateFin, @PathVariable int tester){
		return repos.getDashboardBetween2DaysByTester(dateDeb, dateFin, tester);
	}

	//8 [!database testerID datedeb !datefin]
	@RequestMapping(value="/dateDeb/{jour}/tester/{tester}", method = RequestMethod.GET)
	public List<Dashboard>getDashboardByDateBytesterId( @PathVariable int tester, @PathVariable Date jour){
		return repos.getDashboardByDateBytesterId(jour, tester);
	}
	
	//9 [!database testerID !datedeb !datefin]
	@RequestMapping(value="/tester/{tester}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByTesterID(@PathVariable int tester){
		return repos.getDashboardByTesterID(tester);
	}

	//10 [!database !testerID datedeb datefin]
	@RequestMapping(value = "/dateDeb/{dateDeb}/dateFin/{dateFin}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardBetween2Days(@PathVariable Date dateDeb, @PathVariable Date dateFin){
		return repos.getDashboardBetween2Days(dateDeb, dateFin);
	}

	//11 [!database !testerID datedeb !datefin]
	@RequestMapping(value = "/date/{jour}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDate(@PathVariable Date jour){
		return repos.getDashboardByDate(jour);
	}
	
	//12 
	@RequestMapping(value = "/DashboardByTesters", method = RequestMethod.GET)
	public List<Object> dashboardByTesterID(){
		return repos.dashboardByTesterID();
	}
		
	
	//14
	@RequestMapping(value = "/listTesters", method = RequestMethod.GET)
	public List<Object> listTesters(){
		return repos.listTesters();
	}
		
	
	@RequestMapping(value="/dashboardByUAP/{idUAP}/{date}", method = RequestMethod.GET)
	public List<Object> dashboardByUAP(@PathVariable int idUAP, @PathVariable Date date){
		return repos.dashboardByUAP(idUAP, date);
	}
	
	@RequestMapping(value="/GroupByDateUAP/dateDeb/{dateDeb}/dateFin/{dateFin}", method=RequestMethod.GET)
	public List<Object>GroupByDateUAP(@PathVariable Date dateDeb, @PathVariable Date dateFin){
		return repos.GroupByUAP(dateDeb, dateFin);
	}

	
	@RequestMapping(value="/GroupByDateUAPBytester/dateDeb/{dateDeb}/dateFin/{dateFin}/tester/{tester}", method=RequestMethod.GET)
	public List<Object>GroupByDateUAPByTester(@PathVariable Date dateDeb, @PathVariable Date dateFin, @PathVariable int tester){
		return repos.GroupByDateUAPByTester(dateDeb, dateFin, tester);
	}
	
	
	@RequestMapping(value="/sommeGlobale/dateDeb/{dateDeb}/dateFin/{dateFin}", method=RequestMethod.GET)
	public List<Object>test(@PathVariable Date dateDeb, @PathVariable Date dateFin){
		return repos.test(dateDeb, dateFin);
	}
	
	@RequestMapping(value="dashboardByUAP/{idUAP}/dateDeb/{dateDeb}/dateFin/{dateFin}", method = RequestMethod.GET)
	public List<Object>dashboardByUAP(@PathVariable Date dateDeb, @PathVariable Date dateFin, @PathVariable int idUAP){
		return repos.dashboardByUAP(dateDeb, dateFin, idUAP); 
	}
	
	
	@RequestMapping(value="dashboardByUAPDetails/{idUAP}/dateDeb/{dateDeb}/dateFin/{dateFin}", method = RequestMethod.GET)
	public List<Object>dashboardByUAPDetails(@PathVariable Date dateDeb, @PathVariable Date dateFin, @PathVariable int idUAP){
		return repos.dashboardByUAPDetails(dateDeb, dateFin, idUAP); 
	}
	
	
	@RequestMapping(value="dashboardByCentreCharge/{idCC}/dateDeb/{dateDeb}/dateFin/{dateFin}", method = RequestMethod.GET)
	public List<Object>dashboardByCentreCharge(@PathVariable Date dateDeb, @PathVariable Date dateFin, @PathVariable int idCC){
		return repos.dashboardByCentreCharge(dateDeb, dateFin, idCC); 
	}
		
}