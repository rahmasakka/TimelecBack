package com.timelec.timelec.gabarie.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.timelec.timelec.gabarie.model.Suivi;
import com.timelec.timelec.gabarie.repository.SuiviRepository;

@Service
public class SuiviService {

	@Autowired
	SuiviRepository suiviRepository;
	
	public Page<Suivi>getAll( int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return suiviRepository.findAll(page);
	}	
	
	public Page<Suivi> getSuiviParDate(Date jour, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return suiviRepository.suivieParDate(jour, page);
	}
	
	public Page<Suivi> getSuiviParDateMachine(Date jour, String machine, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return suiviRepository.suivieDateNom(jour, machine, page);
	}
	
	public Page<Suivi> suivieBetweenTwoDates(Date dateDeb, Date dateFin, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return suiviRepository.suivieBetweenTwoDates(dateDeb, dateFin, page);
	}
	
	public Page<Suivi> suivieParMachines(String machine, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return suiviRepository.suivieParMachines(machine, page);
	}
	
	public Page<Suivi> suiviBetweenTwoDatesByMachine(Date dateDeb, Date dateFin,String machine, int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return suiviRepository.suiviBetweenTwoDatesByMachine(dateDeb, dateFin, machine, page);
	}
}