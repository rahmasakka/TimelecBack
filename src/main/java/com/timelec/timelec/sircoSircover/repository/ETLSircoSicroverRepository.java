package com.timelec.timelec.sircoSircover.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.sircoSircover.model.Summary;


@Repository
public interface ETLSircoSicroverRepository extends CrudRepository<Summary, Long>{

	@Query(value="select *"
			   + "from table_summary where Convert(Test_start_time, date) = ?1 and Tester_ID = ?2", nativeQuery = true)
	public List<Summary> listSummarydByDateTester(LocalDate jour, int testerID);
	
	@Query(value="select count(*)"
			   + "from table_summary where Convert(Test_start_time, date) = ?1 and Tester_ID = ?2", nativeQuery = true)
	public int nbLigneByDateTester(LocalDate jour, long tester);	
}