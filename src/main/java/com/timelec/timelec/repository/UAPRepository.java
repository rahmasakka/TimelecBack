package com.timelec.timelec.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.models.UAP;


@Repository
public interface UAPRepository extends JpaRepository<UAP, Integer>{
		
}