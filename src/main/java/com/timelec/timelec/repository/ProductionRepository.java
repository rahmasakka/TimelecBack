package com.timelec.timelec.repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.models.MechanicalAssembly;
import com.timelec.timelec.models.Summary;

@Repository

public interface ProductionRepository extends CrudRepository<Summary, Long>{
	

	List<Summary> findAll();
	
	@Query(value="select * FROM test_results_vm.table_summary where Tester_ID = ?1", nativeQuery = true)
	List<Summary> findByTesterID(Long testerID);

	List<Summary> findByTestStartTime(Timestamp jour);
	

	@Query(value = "select * from test_results_vm.table_summary where Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Summary> findByDate(Date jour);
	
	@Query(value = "select * from test_results_vm.table_summary where Tester_ID = ?2 and Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Summary> findByDateTesterID(Date jour, Long testerID);
	
	
	@Query(value="select Convert(Test_start_time, time) "
			   + "from test_results_vm.table_summary where Tester_ID = ?2 and Convert(Test_start_time, date) = ?1", nativeQuery = true)
	public List<Time> calculeSecond(Date jour, Long testerID);
	
	@Modifying
	@Query (value="INSERT INTO timelec.testeur_en_arret (id_summary, test_start_time, tester_id, id_mechanical_assembly)"
			+" values (?1, ?2, ?3, ?4)" , nativeQuery = true)
	@Transactional
	void insert(long IdSummary, Timestamp timestamp, Long testerID, MechanicalAssembly mechanicalAssembly);
	
	@Query(value="SELECT count(*) FROM test_results_vm.testeur_en_arret where id_summary = ?1", nativeQuery = true)
	int exist(long IdSummary);
}