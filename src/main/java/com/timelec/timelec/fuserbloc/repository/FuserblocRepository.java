package com.timelec.timelec.fuserbloc.repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.fuserbloc.model.Summary;


@Repository
public interface FuserblocRepository extends JpaRepository<Summary, Long> {
	
	Page<Summary>findByTestStartTime(Timestamp jour, Pageable page);
	
	@Query(value="select * FROM table_summary where Tester_ID = ?1", nativeQuery = true)
	Page<Summary> findByTesterIDPageable(Long testerID, Pageable page);	

	@Query(value = "select * from table_summary where Convert(Test_start_time, date) = ?1", nativeQuery = true)
	Page<Summary> findByDate(Date jour, Pageable page);
	
	@Query(value = "select * from table_summary where Tester_ID = ?2 and Convert(Test_start_time, date) = ?1", nativeQuery = true)
	Page<Summary> findByDateTesterID(Date jour, Long testerID, Pageable page);
	
	@Query(value="select Convert(Test_start_time, time) from test_results_vm.table_summary "
			   + "where Tester_ID = ?2 and Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Time> calculeSecond(Date jour, Long testerID);

	@Query(value="select * from table_summary" + 
			"	where Convert(Test_start_time, date) >= ?1 and Convert(Test_start_time, date) <= ?2", nativeQuery = true)
	Page<Summary> listSummaryBetweenTwoDays(Date jour1, Date jour2, Pageable page );
	
	@Query(value="select * from table_summary" + 
			"	where Convert(Test_start_time, date) >= ?1 and Convert(Test_start_time, date) <= ?2 and Tester_ID = ?3 ", nativeQuery = true)
	Page<Summary> listSummaryBetweenTwoDaysByTesterID(Date jour1, Date jour2, Long testerID, Pageable page);
	
	@Query(value="SELECT * FROM table_summary where year(convert(Test_start_time, date))= ?1",nativeQuery = true)
	Page<Summary> findByYear(int year, Pageable page);
	
	@Query(value="SELECT * FROM table_summary where month(convert(Test_start_time, date))= ?1",nativeQuery = true)
	Page<Summary> findByMonth(int month, Pageable page);
	
	@Query(value="SELECT * FROM table_summary where year(convert(Test_start_time, date))= ?2 and month(convert(Test_start_time, date))= ?1",nativeQuery = true)
	Page<Summary> findByMonthByYear(int month, int year, Pageable page);	
	
	@Query(value="SELECT distinct(Tester_ID) FROM table_summary;", nativeQuery = true)
	List<Object>listTesterIdByDatabase();
}