package com.timelec.timelec.gabarie.repository;

import java.sql.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.gabarie.model.Suivi;

@Repository
public interface SuiviRepository extends JpaRepository<Suivi, Integer>{	
	
	@Query(value="SELECT * FROM suivi where Date = ?1", nativeQuery = true)
	Page<Suivi> suivieParDate(Date jour, Pageable page);
	
	@Query(value="SELECT * FROM gabarie.suivi where Date >= ?1 and Date <= ?2", nativeQuery = true)
	Page<Suivi> suivieBetweenTwoDates(Date dateDeb, Date dateFin, Pageable page);
	
	@Query(value="SELECT * FROM gabarie.suivi where nom = ?1", nativeQuery = true)
	Page<Suivi> suivieParMachines(String machine, Pageable page); 
	
	@Query(value="Select * from suivi where Date >= ?1 and Date <= ?2 and nom = ?3", nativeQuery = true)
	Page<Suivi> suiviBetweenTwoDatesByMachine(Date dateDeb, Date dateFin, String machine, Pageable page);
	
	@Query(value="SELECT * FROM suivi where Date = ?1 and nom = ?2", nativeQuery = true)
	Page<Suivi> suivieDateNom(Date jour, String machine, Pageable page);	
}