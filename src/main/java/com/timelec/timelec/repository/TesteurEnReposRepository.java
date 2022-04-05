package com.timelec.timelec.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.timelec.testResultVm.models.MechanicalAssembly;
import com.timelec.timelec.models.TesteurEnRepos;

public interface TesteurEnReposRepository extends CrudRepository<TesteurEnRepos, Long>{	
	
	@Query(value="SELECT count(*) FROM test_results_vm.testeur_en_arret where id_summary = ?1", nativeQuery = true)
	int exist(long IdSummary);
	

	@Modifying
	@Query (value="INSERT INTO timelec.testeur_en_arret (id_summary, test_start_time, tester_id, id_mechanical_assembly)"
			+" values (?1, ?2, ?3, ?4)" , nativeQuery = true)
	@Transactional
	void insert(long idSummary, java.sql.Timestamp testStartTime, Long testerID, MechanicalAssembly mechanicalAssembly);
}