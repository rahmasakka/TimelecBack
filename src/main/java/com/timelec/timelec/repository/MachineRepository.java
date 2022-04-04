package com.timelec.timelec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timelec.timelec.models.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {

}