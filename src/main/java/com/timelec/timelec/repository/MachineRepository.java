package com.timelec.timelec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.models.LoadCharge;
import com.timelec.timelec.models.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {


	@Query(value="SELECT * FROM machine where ID_CC = ?1", nativeQuery = true)
	List<Machine> listMachineByCC(LoadCharge id);
}