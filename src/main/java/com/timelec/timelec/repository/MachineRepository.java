package com.timelec.timelec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timelec.timelec.models.Machine;

public interface MachineRepository extends JpaRepository<Machine, Integer> {

}
