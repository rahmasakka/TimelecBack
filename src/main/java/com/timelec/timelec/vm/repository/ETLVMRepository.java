package com.timelec.timelec.vm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.timelec.timelec.vm.model.Summary;

@Repository
public interface ETLVMRepository extends CrudRepository<Summary, Long>{	
	@Query(value="select * "
			   + "from table_summary where Convert(Test_start_time, date) = ?1 and Tester_ID = ?2", nativeQuery = true)
	public List<Summary> listSummarydByDateTester(String jour, long tester);
	
	@Query(value="select count(*)"
			   + "from table_summary where Convert(Test_start_time, date) = ?1 and Tester_ID = ?2", nativeQuery = true)
	public int nbLigneByDateTester(String jour, long tester);
}