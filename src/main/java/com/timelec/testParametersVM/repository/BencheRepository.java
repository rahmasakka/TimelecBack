package com.timelec.testParametersVM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timelec.testParametersVM.models.Benche;

public interface BencheRepository extends JpaRepository<Benche, Integer>{
	
	List<Benche> findAll();
}