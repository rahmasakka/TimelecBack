package com.timelec.timelec.vm.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.timelec.timelec.vm.model.Summary;
import com.timelec.timelec.vm.repository.VMRepository;

@Service
public class ResultsVMService {
	
	@Autowired 
	VMRepository productionRepository;

	public Page<Summary> getSummary(int pageNumber,int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.findAll(page);
	}
	
	public Page<Summary> findByTesterIDPageable(Long testerID ,int pageNumber,int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.findByTesterIDPageable(testerID, page);
	}	
	
	public Page<Summary> findByMonth(int month, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.findByMonth(month,  page);
	}	
	
	public Page<Summary> findByYear(int year, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.findByYear(year,  page);
	}
	
	public Page<Summary> findByMonthByYear(int month, int year, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.findByMonthByYear(month, year, page);
	}
	
	public Page<Summary>listSummaryBetweenTwoDaysByTesterID(Date jour1, Date jour2, Long testerID, int pageNumber,int  pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.listSummaryBetweenTwoDaysByTesterID(jour1, jour2,testerID, page);
	}
	
	public Page<Summary>listSummaryBetweenTwoDays(Date jour1, Date jour2, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.listSummaryBetweenTwoDaysPageable(jour1, jour2, page);
	}
	
	public Page<Summary>findByDate(Date jour, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.findByDate(jour, page); 
	}
	
	public Page<Summary>findByDateTesterID(Date jour, Long testerID, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.findByDateTesterID(jour, testerID, page);
	}
}