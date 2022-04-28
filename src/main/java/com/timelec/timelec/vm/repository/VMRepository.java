package com.timelec.timelec.vm.repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.vm.model.Summary;

@Repository
public interface VMRepository extends JpaRepository<Summary, Long>{

	//Page<Summary> findAll();
	
	List<Summary> findByTestStartTime(Timestamp jour);
	
	@Query(value="select * FROM test_results_vm.table_summary where Tester_ID = ?1", nativeQuery = true)
	List<Summary> findByTesterID(Long testerID);	
	
	@Query(value="select * FROM test_results_vm.table_summary where Tester_ID = ?1", nativeQuery = true)
	Page<Summary> findByTesterIDPageable(Long testerID, Pageable page);	

	@Query(value = "select * from test_results_vm.table_summary where Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Summary> findByDate(Date jour);
	
	@Query(value = "select * from test_results_vm.table_summary where Tester_ID = ?2 and Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Summary> findByDateTesterID(Date jour, Long testerID);
	
	
	@Query(value="select Convert(Test_start_time, time) from test_results_vm.table_summary "
			   + "where Tester_ID = ?2 and Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Time> calculeSecond(Date jour, Long testerID);
	

	@Query(value="select * from test_results_vm.table_summary" + 
			"	where Convert(Test_start_time, date) >= ?1 and Convert(Test_start_time, date) <= ?2", nativeQuery = true)
	public List<Summary> listSummaryBetweenTwoDays(Date jour1, Date jour2);
	
	
	@Query(value="select * from test_results_vm.table_summary" + 
			"	where Convert(Test_start_time, date) >= ?1 and Convert(Test_start_time, date) <= ?2 and Tester_ID = ?3 ", nativeQuery = true)
	public List<Summary> listSummaryBetweenTwoDaysByTesterID(Date jour1, Date jour2, Long testerID);
}