package com.timelec.timelec.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.timelec.timelec.models.Summary;

public interface ProductionRepository extends CrudRepository<Summary, Long>{

	List<Summary> findAll();
	List<Summary> findByTesterID(Long testerID);
	List<Summary> findByTestStartTime(Timestamp jour);
	

	
	@Query(value = "select * from table_summary where Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Summary> findByDate(Date jour);
	
	
	@Query(value = "select * from test_results_vm.table_summary where Tester_ID = ?2 and Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Summary> findByDateTesterID(Date jour, Long testerID);
	
	
}