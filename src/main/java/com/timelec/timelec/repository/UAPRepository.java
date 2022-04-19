package com.timelec.timelec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.models.Machine;
import com.timelec.timelec.models.UAP;


@Repository
public interface UAPRepository extends JpaRepository<UAP, Integer>{
	
	@Query(value = "SELECT * FROM machine, centre_charge, uap "
				 + "WHERE machine.laod_charge_id = centre_charge.ID_CC and uap.ID_UAP = centre_charge.UAP_ID and uap.ID_UAP=?1", nativeQuery = true)
	List<Machine> listMachineByUAP(int IdUAP);
		
}