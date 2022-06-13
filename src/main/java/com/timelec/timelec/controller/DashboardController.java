package com.timelec.timelec.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.models.Dashboard;
import com.timelec.timelec.models.excel.DashboardExportExcel;
import com.timelec.timelec.repository.DashboardRepository;
import com.timelec.timelec.service.EmailSenderService;


@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	@Autowired
	DashboardRepository repos;
	
	@Autowired
	private EmailSenderService senderService;
	
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
	
	@GetMapping("/export/excelDashboard/{dateDeb}/{dateFin}")
	public void exportToExcel(HttpServletResponse response, @PathVariable Date dateDeb, @PathVariable Date dateFin) throws IOException, ParseException {
	    response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new java.util.Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=dashboard_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Dashboard> listDashboard = repos.getDashboardBetween2Days(dateDeb, dateFin);
		DashboardExportExcel excelExporter = new DashboardExportExcel(listDashboard);
		excelExporter.export(response);    
	}
	
	@GetMapping("/mail")
	public void sendMail() {
		senderService.sendEmail("rahmasakka3@gmail.com", "iData", "this is body of mail");
	}
}