package com.timelec.testResultsDevP87.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.timelec.testResultsDevP87.models.Summary;


@Repository
public interface ETLDevP87Repository extends CrudRepository<Summary, Long> {
	@Query(value = "select * from test_results_vm.table_summary where Tester_ID = ?2 and Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Summary> findByDateTesterID(Date jour, Long testerID);
	
	@Query(value="select Convert(Test_start_time, time) "
			   + "from test_results_vm.table_summary where Tester_ID = ?2 and Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Time> calculeSecond(Date jour, Long testerID);
	
	@Query(value="SELECT * FROM test_results_vm.table_summary where year(convert(Test_start_time, date))= ?1",nativeQuery = true)
	public List<Summary> findByYear(int year);
	
	@Query(value="SELECT * FROM test_results_vm.table_summary where month(convert(Test_start_time, date))= ?1",nativeQuery = true)
	public List<Summary> findByMonth(int month);
	
	@Query(value="SELECT * FROM test_results_vm.table_summary where year(convert(Test_start_time, date))= ?2 and month(convert(Test_start_time, date))= ?1",nativeQuery = true)
	public List<Summary> findByMonthByYear(int month, int year);	
}