package com.timelec.timelec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.timelec.testResultVm.models.Summary;
import com.timelec.timelec.models.LoadCharge;


@Repository
public interface LoadChargeRepository extends JpaRepository <LoadCharge, Integer> {
	
	@Query(value="SELECT * FROM machine, centre_charge WHERE machine.laod_charge_id = centre_charge.ID_CC", nativeQuery = true)
	List<Summary> listMachineByCC();
	
}
