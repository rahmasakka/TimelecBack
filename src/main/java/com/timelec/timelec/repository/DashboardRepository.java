package com.timelec.timelec.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.timelec.timelec.models.Dashboard;

public interface DashboardRepository extends JpaRepository<Dashboard, Long>{
	
	//1 [database testerID datedeb datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2 and db=?3 and tester_id = ?4", nativeQuery = true)
	List<Dashboard> getDashboardBetween2DaysByDatabaseByTester(Date jour1,Date jour2, String database, int tester);
	
	//2 [database testerID datedeb !datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time = ?1 and db=?2 and tester_id = ?3", nativeQuery = true)
	List<Dashboard> getDashboardByDateByDatabaseByTester(Date jour, String database, int tester);
	
	//3 [database testerID !datedeb !datefin]
	@Query(value= "SELECT * FROM dashboard where db=?1 and tester_id = ?2", nativeQuery = true)
	List<Dashboard> getDashboardByDatabaseByTesterID(String database, int tester);
	
	//4 [database !testerID datedeb datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2 and db=?3", nativeQuery = true)
	List<Dashboard> getDashboardBetween2DaysByDatabase(Date jour1, Date jour2, String database);
	
	//5 [database !testerID datedeb !datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time = ?1 and db = ?2", nativeQuery = true)
	List<Dashboard> getDashboardByDateByDatabase(Date jour, String database);
	
	//6 [database !testerID !datedeb !datefin]
	@Query(value= "SELECT * FROM dashboard where db=?1", nativeQuery = true)
	List<Dashboard> getDashboardByDatabase(String database);

	//7 [!database testerID datedeb datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2 and tester_id = ?3", nativeQuery = true)
	List<Dashboard> getDashboardBetween2DaysByTester(Date jour1,Date jour2, int tester);	
	
	//8 [!database testerID datedeb !datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time = ?1 and tester_id = ?2", nativeQuery = true)
	List<Dashboard> getDashboardByDateBytesterId(Date jour, int tester);
	
	//9 [!database testerID !datedeb !datefin]
	@Query(value="SELECT * FROM dashboard where tester_id = ?1", nativeQuery = true)
	List<Dashboard> getDashboardByTesterID(int tester);
	
	//10 [!database !testerID datedeb datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2", nativeQuery = true)
	List<Dashboard> getDashboardBetween2Days(Date jour1, Date jour2);
	
	//11 [!database !testerID datedeb !datefin]
	@Query(value="select * from dashboard where Test_start_time = ?1", nativeQuery = true)
	List<Dashboard> getDashboardByDate(Date jour);
	
	
	//12
	@Query(value="SELECT tester_id, "+ 
			"sum(duree_disfonctionnement) as duree_disfonctionnement , " + 
			"sum(duree_fonctionnement) as duree_fonctionnement, " + 
			"sum(quantite_conforme) as quantite_conforme, " + 
			"sum(quantite_non_conforme) as quantite_non_conforme, " + 
			"Test_start_time as date " + 
			"FROM dashboard " + 
			"GROUP BY tester_id", nativeQuery = true)
	List<Object> dashboardByTesterID();
	
	
	//13 nombre de ligne 
	@Query(value="SELECT count(*) FROM dashboard where Test_start_time = ?1 and tester_id = ?2", nativeQuery = true)
	int listLigneByDateTester(Date jour, int tester);
	
	
	//14 
	@Query(value="SELECT distinct(tester_id) FROM dashboard", nativeQuery = true)
	List<Object> listTesters();
	
	
}