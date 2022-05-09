package com.timelec.timelec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.timelec.timelec.models.TesteurEnProduction;


public interface TesteurEnProductionRepository extends JpaRepository<TesteurEnProduction, Long>{

	@Query(value="SELECT count(*) FROM timelec.testeur_en_arret where ID_summary = ?1", nativeQuery = true)
	int existBySummary(Long summaryID);
	
}
