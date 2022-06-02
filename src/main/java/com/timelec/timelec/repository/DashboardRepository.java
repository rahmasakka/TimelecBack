package com.timelec.timelec.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.timelec.timelec.models.Dashboard;

public interface DashboardRepository extends JpaRepository<Dashboard, Long>{
	
	//1 [database testerID datedeb datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2 and db=?3 and ID_Machine = ?4", nativeQuery = true)
	List<Dashboard> getDashboardBetween2DaysByDatabaseByTester(Date jour1,Date jour2, String database, int tester);
	
	//2 [database testerID datedeb !datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time = ?1 and db=?2 and ID_Machine = ?3", nativeQuery = true)
	List<Dashboard> getDashboardByDateByDatabaseByTester(Date jour, String database, int tester);
	
	//3 [database testerID !datedeb !datefin]
	@Query(value= "SELECT * FROM dashboard where db=?1 and ID_Machine = ?2", nativeQuery = true)
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
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2 and ID_Machine = ?3", nativeQuery = true)
	List<Dashboard> getDashboardBetween2DaysByTester(Date jour1,Date jour2, int tester);	
	
	//8 [!database testerID datedeb !datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time = ?1 and ID_Machine = ?2", nativeQuery = true)
	List<Dashboard> getDashboardByDateBytesterId(Date jour, int tester);
	
	//9 [!database testerID !datedeb !datefin]
	@Query(value="SELECT * FROM dashboard where ID_Machine = ?1", nativeQuery = true)
	List<Dashboard> getDashboardByTesterID(int tester);
	
	//10 [!database !testerID datedeb datefin]
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2", nativeQuery = true)
	List<Dashboard> getDashboardBetween2Days(Date jour1, Date jour2);
	
	//11 [!database !testerID datedeb !datefin]
	@Query(value="select * from dashboard where Test_start_time = ?1", nativeQuery = true)
	List<Dashboard> getDashboardByDate(Date jour);
	
	
	//12
	@Query(value="SELECT ID_Machine, "+ 
			"sum(duree_disfonctionnement) as duree_disfonctionnement , " + 
			"sum(duree_fonctionnement) as duree_fonctionnement, " + 
			"sum(quantite_conforme) as quantite_conforme, " + 
			"sum(quantite_non_conforme) as quantite_non_conforme, " + 
			"Test_start_time as date " + 
			"FROM dashboard " + 
			"GROUP BY ID_Machine", nativeQuery = true)
	List<Object> dashboardByTesterID();
	
	
	//13 nombre de ligne 
	@Query(value="SELECT count(*) FROM dashboard where Test_start_time = ?1 and ID_Machine = ?2", nativeQuery = true)
	int listLigneByDateTester(Date jour, long object);
	
	
	//14 
	@Query(value="SELECT distinct(ID_Machine) FROM dashboard", nativeQuery = true)
	List<Object> listTesters();
	
	
	@Query(value="SELECT * \r\n" + 
			"FROM timelec.machine, timelec.dashboard, timelec.centre_charge\r\n" + 
			"where dashboard.ID_Machine= machine.Id_machine\r\n" + 
			"and machine.Centre_de_charge = centre_charge.Id_centre_de_charge " + 
			"and centre_charge.ID_UAP = ?1 and Test_start_time = ?2 "+
			"and timelec.machine.Reference=true", nativeQuery = true)
	List<Object> dashboardByUAP(int UAP, Date date);
	
	
	@Query(value="SELECT * , sum(duree_disfonctionnement_en_seconde), sum(duree_fonctionnement_en_seconde), sum(quantite_conforme), sum(quantite_non_conforme)\r\n" + 
			"FROM timelec.machine, timelec.dashboard, timelec.centre_charge\r\n" + 
			"where dashboard.ID_Machine= machine.Id_machine\r\n" + 
			"and machine.Centre_de_charge = centre_charge.Id_centre_de_charge \r\n" + 
			"and timelec.machine.Reference=true\r\n" + 
			"and Test_start_time >= ?1 and Test_start_time <= ?2 " + 
			"group by ID_UAP", nativeQuery = true)
	List<Object> GroupByUAP(Date dateDeb, Date dateFin);
	
	
	@Query(value="SELECT * , sum(duree_disfonctionnement_en_seconde), sum(duree_fonctionnement_en_seconde), sum(quantite_conforme), sum(quantite_non_conforme)\r\n" + 
			"FROM timelec.machine, timelec.dashboard, timelec.centre_charge\r\n" + 
			"where dashboard.ID_Machine= machine.Id_machine\r\n" + 
			"and machine.Centre_de_charge = centre_charge.Id_centre_de_charge \r\n" + 
			"and timelec.machine.Reference=true\r\n" + 
			"and Test_start_time >= ?1 and Test_start_time <= ?2 ", nativeQuery = true)
	List<Object> test(Date dateDeb, Date dateFin);

	
	@Query(value="SELECT * , sum(duree_disfonctionnement_en_seconde), sum(duree_fonctionnement_en_seconde), sum(quantite_conforme), sum(quantite_non_conforme) " + 
			"FROM timelec.machine, timelec.dashboard, timelec.centre_charge " + 
			"where dashboard.ID_Machine= machine.Id_machine " + 
			"and machine.Centre_de_charge = centre_charge.Id_centre_de_charge " + 
			"and timelec.machine.Reference=true " + 
			"and Test_start_time >= ?1 " + 
			"and Test_start_time <= ?2 " + 
			"and machine.Id_machine=?3 " + 
			"group by Test_start_time, Id_centre_de_charge\r\n", nativeQuery = true)
	List<Object> GroupByDateUAPByTester(Date dateDeb, Date dateFin, int tester);

	
	@Query(value="SELECT * , sum(duree_disfonctionnement_en_seconde), sum(duree_fonctionnement_en_seconde), sum(quantite_conforme), sum(quantite_non_conforme) " + 
			"FROM timelec.machine, timelec.dashboard, timelec.centre_charge " + 
			"where dashboard.ID_Machine= machine.Id_machine " + 
			"and machine.Centre_de_charge = centre_charge.Id_centre_de_charge " + 
			"and timelec.machine.Reference=true " + 
			"and Test_start_time <=?2 " + 
			"and Test_start_time >=?1 " + 
			"and ID_UAP=?3", nativeQuery = true)
	List<Object>dashboardByUAP(Date dateDeb, Date dateFin, int idUAP);
	
	@Query(value="SELECT * , sum(duree_disfonctionnement_en_seconde), sum(duree_fonctionnement_en_seconde), sum(quantite_conforme), sum(quantite_non_conforme)" + 
			"FROM timelec.machine, timelec.dashboard, timelec.centre_charge " + 
			"where dashboard.ID_Machine= machine.Id_machine " + 
			"and machine.Centre_de_charge = centre_charge.Id_centre_de_charge " + 
			"and timelec.machine.Reference=true " + 
			"and Test_start_time <=?2 " + 
			"and Test_start_time >= ?1 " + 
			"and ID_UAP=?3 group by Id_centre_de_charge", nativeQuery = true)
	List<Object>dashboardByUAPDetails(Date dateDeb, Date dateFin, int idUAP);
	
	
	@Query(value="SELECT * , sum(duree_disfonctionnement_en_seconde), sum(duree_fonctionnement_en_seconde), sum(quantite_conforme), sum(quantite_non_conforme) " + 
			"FROM timelec.machine, timelec.dashboard, timelec.centre_charge " + 
			"where dashboard.ID_Machine= machine.Id_machine " + 
			"and machine.Centre_de_charge = centre_charge.Id_centre_de_charge " + 
			"and timelec.machine.Reference=true " + 
			"and Test_start_time <= ?2 " + 
			"and Test_start_time >= ?1 " + 
			"and Id_centre_de_charge = ?3", nativeQuery = true)
	List<Object>dashboardByCentreCharge(Date dateDeb, Date dateFin, int idCC);
}