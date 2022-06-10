package com.timelec.timelec.gabarie.service;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.timelec.timelec.gabarie.model.Production;
import com.timelec.timelec.gabarie.repository.ProductionRepository;

@Service
public class ProductionService {
	
	@Autowired
	ProductionRepository productionRepository;
	
	public Page<Production>getProductionParDate(Date jour, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.productionParDate(jour, page);
	}
	
	public Page<Production>getProductionBetweenTwoDates(Date dateDeb, Date dateFin, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.productionbetweenTwoDates(dateDeb, dateFin, page);
	}
	
	public Page<Production>getProductionByOF(Integer of, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.productionParOF(of, page);
	}

	public Page<Production>getProductionBetweenTwoDatesByOF(Date dateDeb, Date dateFin, Integer of, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.productionbetweenTwoDatesByOF(dateDeb, dateFin, of, page);
	}
	
	public Page<Production>getProductionParDateOF(Date jour, Integer of,  int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productionRepository.productionDateOF(jour, of, page);
	}
}