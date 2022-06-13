package com.timelec.timelec.models.excel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.timelec.timelec.gabarie.model.Production;
import com.timelec.timelec.gabarie.model.Suivi;
import com.timelec.timelec.gabarie.model.Demarrage;

public class GabarieExportExcel {
	private XSSFWorkbook workbook;
    private XSSFSheet sheetprod;
    private XSSFSheet sheetsuivi;
    private XSSFSheet sheetdemarrage;
    private List<Production> listProduction;
    private List<Demarrage> listDemarrage;
    private List<Suivi> listSuivie;
    
     
    public GabarieExportExcel(List<Production> listProduction, List<Demarrage> listDemarrage, List<Suivi> listSuivie){
        this.listProduction = listProduction;
        this.listDemarrage = listDemarrage;
        this.listSuivie = listSuivie;
        workbook = new XSSFWorkbook();
    }
 
    private void writeHeaderLineProduction() {
    	sheetprod = workbook.createSheet("Production");
		Row row = sheetprod.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCellProduction(row, 0, "Identificateur", style);       
		createCellProduction(row, 1, "Ordre de fabrication", style);
		createCellProduction(row, 2, "Quantité produite", style);
		createCellProduction(row, 3, "Quantité rebut", style);
		createCellProduction(row, 4, "Date", style);
		createCellProduction(row, 5, "Temps", style);
	}
    
    private void writeHeaderLineDemarrage() {
	    sheetdemarrage = workbook.createSheet("Démarrage");
		Row row = sheetdemarrage.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCellDemarrage(row, 0, "Identificateur", style);       
		createCellDemarrage(row, 1, "Ordre de fabrication", style);
		createCellDemarrage(row, 2, "Quantité", style);
		createCellDemarrage(row, 3, "Date", style);
		createCellDemarrage(row, 4, "Temps", style);
	}
    
    private void writeHeaderLineSuivi() {
	    sheetsuivi = workbook.createSheet("Suivie");
		Row row = sheetsuivi.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCellSuivi(row, 0, "Identificateur", style);       
		createCellSuivi(row, 1, "Nom machine", style);
		createCellSuivi(row, 2, "Temps de fonctionnement", style);
		createCellSuivi(row, 3, "Date", style);
	}
	 
	private void createCellProduction(Row row, int columnCount, Object value, CellStyle style) {
	    sheetprod.autoSizeColumn(columnCount);
	    Cell cell = row.createCell(columnCount);
	    if (value instanceof Integer) {
	        cell.setCellValue((Integer) value);
	    } else if (value instanceof Boolean) {
	        cell.setCellValue((Boolean) value);
	    }else {
	        cell.setCellValue((String) value);
	    }
	    cell.setCellStyle(style);
	}
	
	private void createCellDemarrage(Row row, int columnCount, Object value, CellStyle style) {
	    sheetdemarrage.autoSizeColumn(columnCount);
	    Cell cell = row.createCell(columnCount);
	    if (value instanceof Integer) {
	        cell.setCellValue((Integer) value);
	    } else if (value instanceof Boolean) {
	        cell.setCellValue((Boolean) value);
	    }else {
	        cell.setCellValue((String) value);
	    }
	    cell.setCellStyle(style);
	}
	
	private void createCellSuivi(Row row, int columnCount, Object value, CellStyle style) {
	    sheetsuivi.autoSizeColumn(columnCount);
	    Cell cell = row.createCell(columnCount);
	    if (value instanceof Integer) {
	        cell.setCellValue((Integer) value);
	    } else if (value instanceof Boolean) {
	        cell.setCellValue((Boolean) value);
	    }else {
	        cell.setCellValue((String) value);
	    }
	    cell.setCellStyle(style);
	}
	 
	private void writeDataLinesProduction() {
	    int rowCount = 1;
	    CellStyle style = workbook.createCellStyle();
	    XSSFFont font = workbook.createFont();
	    font.setFontHeight(14);
	    style.setFont(font);            
	    for (Production production : listProduction) {
	        Row row = sheetprod.createRow(rowCount++);
	        int columnCount = 0;
	        createCellProduction(row, columnCount++, production.getId(), style);
	        createCellProduction(row, columnCount++, (int)production.getOf(), style); 
	        createCellProduction(row, columnCount++, (int)production.getQuantiteProduite(), style); 
	        createCellProduction(row, columnCount++, (int)production.getQuantiteRebut(), style); 
	        createCellProduction(row, columnCount++, production.getDate().toString(), style); 
	        createCellProduction(row, columnCount++, production.getTemps().toString(), style); 
	    }
	}
	
	private void writeDataLinesDemarrage() {
	    int rowCount = 1;
	    CellStyle style = workbook.createCellStyle();
	    XSSFFont font = workbook.createFont();
	    font.setFontHeight(14);
	    style.setFont(font);            
	    for (Demarrage demarrage : listDemarrage) {
	        Row row = sheetdemarrage.createRow(rowCount++);
	        int columnCount = 0;
	        createCellDemarrage(row, columnCount++, demarrage.getId(), style);
	        createCellDemarrage(row, columnCount++, (int)demarrage.getOf(), style); 
	        createCellDemarrage(row, columnCount++, (int)demarrage.getQuantity(), style); 
	        createCellDemarrage(row, columnCount++, demarrage.getDate().toString(), style); 
	        createCellDemarrage(row, columnCount++, demarrage.getTemps().toString(), style); 
	    }
	}
	
	private void writeDataLinesSuivie() {
	    int rowCount = 1;
	    CellStyle style = workbook.createCellStyle();
	    XSSFFont font = workbook.createFont();
	    font.setFontHeight(14);
	    style.setFont(font);            
	    for (Suivi suivi : listSuivie) {
	        Row row = sheetsuivi.createRow(rowCount++);
	        int columnCount = 0;
	        createCellProduction(row, columnCount++, suivi.getId(), style);
	        createCellProduction(row, columnCount++, suivi.getNom(), style); 
	        createCellProduction(row, columnCount++, suivi.getTempf(),  style); 
	        createCellProduction(row, columnCount++, suivi.getDate().toString(), style); 
	    }
	}
	 
	public void export(HttpServletResponse response) throws IOException {
	    writeHeaderLineProduction();
	    writeHeaderLineDemarrage();
	    writeHeaderLineSuivi();
	    
	    writeDataLinesProduction();
	    writeDataLinesDemarrage();
	    writeDataLinesSuivie();
	    
	    ServletOutputStream outputStream = response.getOutputStream();
	    workbook.write(outputStream);
	    workbook.close();
	    outputStream.close();
	}
}