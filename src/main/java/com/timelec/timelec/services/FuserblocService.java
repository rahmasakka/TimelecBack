package com.timelec.timelec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.timelec.testResultsFuserbloc.models.Summary;
import com.timelec.testResultsFuserbloc.repository.FuserblocRepository;

@Service
public class FuserblocService {

	@Autowired 
	FuserblocRepository productionRepository;

	public Page<Summary> getSummary(int pageNumber,int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.findAll(page);
	}
	
	public Page<Summary> findByTesterIDPageable(Long testerID ,int pageNumber,int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.findByTesterIDPageable(testerID, page);
	}	
}