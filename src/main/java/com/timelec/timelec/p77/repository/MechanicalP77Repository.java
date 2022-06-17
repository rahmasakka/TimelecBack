package com.timelec.timelec.p77.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.timelec.timelec.p77.model.MechanicalAssembly;

public interface MechanicalP77Repository extends JpaRepository<MechanicalAssembly, Long>{
	
	@Query(value="SELECT Manufacturing_Order, MA_date, MA_start_time, Packing_status "
			+ " from table_mechanical_assembly "
			+ "where Manufacturing_Order = ?1 ", nativeQuery = true)
	List<Object> assemblyByOf(int of);	
	
	@Query(value="SELECT sum(Packing_status) FROM table_mechanical_assembly where Manufacturing_Order = ?1 ", nativeQuery = true)
	int sumPacking(int of);	
}