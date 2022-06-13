package com.timelec.timelec.gabarie.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.gabarie.model.Demarrage;
import com.timelec.timelec.gabarie.model.Production;
import com.timelec.timelec.gabarie.model.Suivi;
import com.timelec.timelec.gabarie.repository.DemarrageRepository;
import com.timelec.timelec.gabarie.repository.ProductionRepository;
import com.timelec.timelec.gabarie.repository.SuiviRepository;
import com.timelec.timelec.models.excel.GabarieExportExcel;

@CrossOrigin
@RestController
@RequestMapping("/api/gabarie/excel")
public class GabarieExcelController {
	
	
	@Autowired
	SuiviRepository suivie;
	
	@Autowired
	ProductionRepository production;
	
	@Autowired
	DemarrageRepository demarrage;
	
	@GetMapping("/{dateDeb}/{dateFin}")
	public void exportToExcel(HttpServletResponse response, @PathVariable Date dateDeb, @PathVariable Date dateFin) throws IOException, ParseException {
	    response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new java.util.Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=dashboard_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Suivi> listSuivi = suivie.suivieBetweenTwoDate(dateDeb, dateFin);
		List<Production> listProduction = production.productionbetweenTwoDate(dateDeb, dateFin);
		List<Demarrage> listDemarrage = demarrage.demarrageBetweenTwoDate(dateDeb, dateFin);
		GabarieExportExcel excelExporter = new GabarieExportExcel(listProduction, listDemarrage, listSuivi);
		excelExporter.export(response);    
	}
}