package com.timelec.timelec.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.timelec.timelec.models.Dashboard;

public interface DashboardRepository extends JpaRepository<Dashboard, Long>{

	@Query(value="SELECT count(*) FROM dashboard where Test_start_time = ?1 and ID_Machine = ?2", nativeQuery = true)
	int listLigneByDateTester(String jour, long object);
	
	@Query(value="SELECT * FROM dashboard where Test_start_time >= ?1 and Test_start_time <= ?2", nativeQuery = true)
	List<Dashboard> getDashboardBetween2Days(Date jour1, Date jour2);
	
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