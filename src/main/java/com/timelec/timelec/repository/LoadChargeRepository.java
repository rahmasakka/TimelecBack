package com.timelec.timelec.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.timelec.timelec.models.CentreCharge;
import com.timelec.timelec.models.UAP;


@Repository
public interface LoadChargeRepository extends JpaRepository <CentreCharge, Integer> {
	
	@Query(value = "SELECT * FROM centre_charge where ID_UAP = ?1", nativeQuery = true)
	List<CentreCharge> listCCByUAP(UAP IdUAP);
}