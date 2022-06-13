package com.timelec.timelec.gabarie.service;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.timelec.timelec.gabarie.model.Demarrage;
import com.timelec.timelec.gabarie.repository.DemarrageRepository;

@Service
public class DemarrageService {
	
	@Autowired
	DemarrageRepository demarrageRepository;
	
	public Page<Demarrage>getAll( int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return demarrageRepository.findAll(page);
	}	
	
	public Page<Demarrage>getDemarrageParDate(Date jour, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return demarrageRepository.demarrageParDate(jour, page);
	}
	
	public Page<Demarrage>getDemarrageOF(int of, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return demarrageRepository.demarrageByOF(of, page);
	}
	
	public Page<Demarrage>getDemarrageBetweenTwoDates(Date dateDeb, Date dateFin, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return demarrageRepository.demarrageBetweenTwoDates(dateDeb, dateFin, page);
	}
	
	public Page<Demarrage>getDemarrageBetweenTwoDatesOF(Date dateDeb, Date dateFin,int of,  int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return demarrageRepository.demarrageBetweenTwoDatesByOF(dateDeb, dateFin, of, page);
	}
	
	public Page<Demarrage>getDemarrageDateOF(Date jour, int of, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return demarrageRepository.demarrageDateOF(jour, of, page); 
	}

}