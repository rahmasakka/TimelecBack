package com.timelec.timelec.gabarie.repository;

import org.springframework.data.domain.Pageable;
import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.gabarie.model.Demarrage;

@Repository
public interface DemarrageRepository extends JpaRepository<Demarrage, Integer>{
	
	@Query(value="SELECT * FROM démarrage where Date = ?1", nativeQuery = true)
	Page<Demarrage> demarrageParDate(Date jour, Pageable page);

	@Query(value="SELECT * FROM démarrage where of = ?1", nativeQuery = true)
	Page<Demarrage> demarrageByOF(int of, Pageable page); 
	
	@Query(value="SELECT * FROM démarrage where Date >= ?1 and Date <= ?2", nativeQuery = true)
	Page<Demarrage> demarrageBetweenTwoDates(Date dateDeb, Date dateFin,Pageable page);

	@Query(value="SELECT * FROM démarrage where Date = ?1 and Of = ?2;", nativeQuery = true)
	Page<Demarrage> demarrageDateOF(Date jour, int of, Pageable page);
	
	@Query(value="SELECT * FROM démarrage where Date >= ?1 and  Date <= ?2 and Of = ?3;", nativeQuery = true)
	Page<Demarrage> demarrageBetweenTwoDatesByOF(Date dateDeb, Date dateFin, Integer of, Pageable page);

}