package com.timelec.timelec.gabarie.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.timelec.timelec.gabarie.model.Production;


@Repository
public interface ProductionRepository extends JpaRepository<Production, Integer>{
	
	@Query(value="SELECT * FROM production where convert(Date, date) = ?1", nativeQuery = true)
	Page<Production> productionParDate(Date jour, Pageable page);
	
	@Query(value="SELECT * FROM production where OF = ?1", nativeQuery = true)
	Page<Production> productionParOF(Integer of, Pageable page);
	
	@Query(value="SELECT * FROM production where convert(Date, date) >= ?1 and convert(Date, date) <= ?2", nativeQuery = true)
	Page<Production> productionbetweenTwoDates(Date dateDeb, Date dateFin, Pageable page);
	
	@Query(value="SELECT * FROM production where convert(Date, date) = ?1 and OF = ?2 ", nativeQuery = true)
	Page<Production> productionDateOF(Date jour, Integer of, Pageable page);
	
	@Query(value="SELECT * FROM production "
			+ "where convert(Date, date) >= ?1 "
			+ "and convert(Date, date) <= ?2 "
			+ "and OF = ?3", nativeQuery = true)
	Page<Production> productionbetweenTwoDatesByOF(Date dateDeb, Date dateFin, Integer of, Pageable page);
	
}