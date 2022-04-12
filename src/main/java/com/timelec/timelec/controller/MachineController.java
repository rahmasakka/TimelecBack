package com.timelec.timelec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.exception.ResourceNotFoundException;
import com.timelec.timelec.models.LoadCharge;
import com.timelec.timelec.models.Machine;
import com.timelec.timelec.repository.MachineRepository;


@CrossOrigin
@RestController
@RequestMapping("/api/machine")
public class MachineController {
	
	@Autowired
	MachineRepository machineRepository;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Machine>getAllMachines(){
		return machineRepository.findAll();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Machine getMachineById(@PathVariable int id) {
		Machine machine = machineRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Machine not exist with id:" + id));
		return machine;
	}
	
	
	@PostMapping(path = "/createMachine")
	public Machine addMachine(@RequestBody Machine machine) {
	    Machine machine1 = machineRepository.save(machine);
	  	return machine1;
	}
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Machine> updateMachine(@PathVariable int id, @RequestBody Machine machine){
		Machine machine1 = machineRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Machine not exit with id:" + id));
		machine1.setMachineCategory(machine.getMachineCategory());
		machine1.setMachineDescription(machine.getMachineDescription());
		machine1.setMachineName(machine.getMachineName());
		
		Machine machineUpdate = machineRepository.save(machine1);
		return ResponseEntity.ok(machineUpdate);
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Boolean>> deleteMachine(@PathVariable int id){
		Machine machine = machineRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Machine not exit with id:" + id));
		machineRepository.delete(machine);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	@RequestMapping(value = "/listMachineByCC/{id}", method = RequestMethod.GET)
	public List<Machine>listMachineByCC(@PathVariable LoadCharge id){
		return machineRepository.listMachineByCC(id);
	}
}