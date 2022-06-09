package com.timelec.timelec.models;

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

public class DashboardExportExcel {
	  private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    private List<Dashboard> listDashboard;
	     
	    public DashboardExportExcel(List<Dashboard> listDashboard){
	        this.listDashboard = listDashboard;
	        workbook = new XSSFWorkbook();
	    }
	 
	    private void writeHeaderLine1() {
	        sheet = workbook.createSheet("Users");
	        Row row = sheet.createRow(0);
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        createCell1(row, 0, "Date", style);       
	        createCell1(row, 1, "Machine", style);
	        createCell1(row, 2, "Quantité conforme", style);
	        createCell1(row, 3, "Quantité non conforme", style);
	        createCell1(row, 4, "Durée fonctionnement", style);
	        createCell1(row, 5, "Durée disfonctionnement", style);
	        createCell1(row, 6, "Database", style);
	        createCell1(row, 7, "Start time", style);
	        createCell1(row, 8, "Finish time", style);
	    }
	     
	    private void createCell1(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
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
	     
	    private void writeDataLines() {
	        int rowCount = 1;
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);            
	        for (Dashboard dashboard : listDashboard) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	            createCell1(row, columnCount++, dashboard.getDate().toString(), style);
	            createCell1(row, columnCount++, dashboard.getTesteurId().getMachineName(), style); 
	            createCell1(row, columnCount++, (int)dashboard.getQuantiteConforme(), style); 
	            createCell1(row, columnCount++, (int)dashboard.getQuantiteNonConforme(), style); 
	            createCell1(row, columnCount++, dashboard.getDureeFonctionnement(), style); 
	            createCell1(row, columnCount++, dashboard.getDureeDisfonctionnement(), style); 
	            createCell1(row, columnCount++, dashboard.getDatabase(), style); 
	            createCell1(row, columnCount++, dashboard.getStartTime(), style); 
	            createCell1(row, columnCount++, dashboard.getFinishTime(), style);        
	        }
	    }
	     
	    public void export(HttpServletResponse response) throws IOException {
	        writeHeaderLine1();
	        writeDataLines();
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }
	}