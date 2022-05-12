package com.timelec.timelec.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.timelec.timelec.models.Dashboard;

public interface DashboardRepository extends JpaRepository<Dashboard, Long>{
	
	//1 by database
	@Query(value= "SELECT * FROM dashboard where db=?1", nativeQuery = true)
	List<Dashboard> getDashboardByDatabase(String database);
	
	
	//2 by database by date 
	@Query(value="SELECT * FROM dashboard where Test_start_time = ?1 and db = ?2", nativeQuery = true)
	List<Dashboard> getDashboardByDateByDatabase(Date jour, String database);
	
	
	//3 by database by 2 days
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2 and db=?3", nativeQuery = true)
	List<Dashboard> getDashboardBetween2DaysByDatabase(Date jour1, Date jour2, String database);
	
	
	//4 by database by 2 days by testerID
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2 and db=?3 and tester_id = ?4", nativeQuery = true)
	List<Dashboard> getDashboardBetween2DaysByDatabaseByTester(Date jour1,Date jour2, String database, int tester);
	
	
	//5 by date
	@Query(value="select * from dashboard where Test_start_time = ?1", nativeQuery = true)
	List<Dashboard> getDashboardByDate(Date jour);
	
	
	//6bye 2 days
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2 ;", nativeQuery = true)
	List<Dashboard> getDashboardBetweenTwoDays(Date jour1 , Date jour2);
	
	
	//8 
	@Query(value="SELECT tester_id, "+ 
						"sum(duree_disfonctionnement) as duree_disfonctionnement , " + 
						"sum(duree_fonctionnement) as duree_fonctionnement, " + 
						"sum(quantite_conforme) as quantite_conforme, " + 
						"sum(quantite_non_conforme) as quantite_non_conforme, " + 
						"Test_start_time as date " + 
						"FROM dashboard " + 
						"GROUP BY tester_id", nativeQuery = true)
	List<Object> dashboardByTesterID();
	
	
	//9 by date by tester id 
	@Query(value="select count(*) from dashboard where Test_start_time = ?1 and tester_id = ?2", nativeQuery = true)
	int listLigneByDateTester(Date jour, int testerID);
	
}