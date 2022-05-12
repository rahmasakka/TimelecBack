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
		
	@RequestMapping(value = "/database/{db}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDatabase(@PathVariable String db){
		return repos.getDashboardByDatabase(db);
	}
	
	@RequestMapping(value = "/date/{jour}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDate(@PathVariable Date jour){
		return repos.getDashboardByDate(jour);
	}

	@RequestMapping(value = "/dateDeb/{jour1}/dateFin/{jour2}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDate(@PathVariable Date jour1, @PathVariable Date jour2){
		return repos.getDashboardBetweenTwoDays(jour1, jour2);
	}
	
	@RequestMapping(value = "/database/{database}/dateDeb/{jour1}/dateFin/{jour2}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDateByDatabase(@PathVariable String database, @PathVariable Date jour1, @PathVariable Date jour2){
		return repos.getDashboardBetween2DaysByDatabase(jour1, jour2, database);
	}
	
	
	@RequestMapping(value = "/database/{database}/dateDeb/{jour}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDateByDatabase(@PathVariable String database, @PathVariable Date jour1){
		return repos.getDashboardByDateByDatabase(jour1, database);
	}
		
	@RequestMapping(value = "/database/{database}/dateDeb/{jour1}/dateFin/{jour2}/tester/{tester}", method = RequestMethod.GET)
	public List<Dashboard> getDashboardByDateByDatabase(@PathVariable String database, @PathVariable Date jour1, @PathVariable Date jour2, @PathVariable int tester){
		return repos.getDashboardBetween2DaysByDatabaseByTester(jour1, jour2, database, tester);
	}
	
	
	@RequestMapping(value = "/DashboardByTesters", method = RequestMethod.GET)
	public List<Object> dashboardByTesterID(){
		return repos.dashboardByTesterID();
	}
	
}