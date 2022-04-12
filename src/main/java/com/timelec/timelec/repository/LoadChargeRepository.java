package com.timelec.timelec.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.timelec.timelec.models.LoadCharge;
import com.timelec.timelec.models.UAP;


@Repository
public interface LoadChargeRepository extends JpaRepository <LoadCharge, Integer> {
	

	@Query(value = "SELECT * FROM centre_charge where UAP_ID = ?1", nativeQuery = true)
	List<LoadCharge> listCCByUAP(UAP IdUAP);
}