package com.timelec.timelec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timelec.timelec.exception.ResourceNotFoundException;
import com.timelec.timelec.models.Machine;
import com.timelec.timelec.repository.MachineRepository;


@CrossOrigin
@RestController
@RequestMapping("/api/machine")
public class MachineController {
	
	@Autowired
	MachineRepository machineRepository;
	
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Machine>getAllMachines(){
		return machineRepository.findAll();
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Machine getMachineById(@PathVariable int id) {
		Machine machine = machineRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Machine not exist with id:" + id));
		return machine;
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(path = "/create")
	public Machine addMachine(@RequestBody Machine machine) {
	    Machine machine1 = machineRepository.save(machine);
	  	return machine1;
	}
	
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Machine> updateMachine(@PathVariable int id, @RequestBody Machine machine){
		Machine machine1 = machineRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Machine not exit with id:" + id));
		//machine1.setMachineCategory(machine.getMachineCategory());
		machine1.setMachineDescription(machine.getMachineDescription());
		machine1.setMachineName(machine.getMachineName());
		machine1.setReference(machine.getReference());
		machine.setMachineCategory(machine.getMachineCategory());
		Machine machineUpdate = machineRepository.save(machine1);
		return ResponseEntity.ok(machineUpdate);
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/updateReferenceMachineFalse/{idCC}", method = RequestMethod.PUT)
	public ResponseEntity<Machine> updateReferenceMachineFalse(@PathVariable int idCC){
		List<Machine> machines = machineRepository.listMachineByCC(idCC);
		Machine machineUpdate = null;
		
		for (int i = 0; i<machines.size(); i++) {
			machines.get(i).setReference(false);
			machineUpdate = machineRepository.save(machines.get(i));
		}
		return ResponseEntity.ok(machineUpdate);
	}
	
	//modifier l'etat de la machine (par reference)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/updateReferenceMachine/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Machine> updateReferenceMachine(@PathVariable int id){
		Machine machine1 = machineRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Machine not exit with id:" + id));
		machine1.setReference(!machine1.getReference());
		Machine machineUpdate = machineRepository.save(machine1);
		return ResponseEntity.ok(machineUpdate);
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

	//selectionner la machine réferencée par centre de charge 
	@RequestMapping(value="/referenced/{centreChargeId}", method = RequestMethod.GET)
	public Machine machineReferencedToCentreCharge(@PathVariable int centreChargeId){
		Machine machineReference= machineRepository.machineReferencedToCentreCharge(centreChargeId) ;
		return machineReference;
	}

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

	//selectionner la liste des machines par centre de charge 
	@RequestMapping(value = "/sonByMother/{id}", method = RequestMethod.GET)
	public List<Machine>listMachineByCC(@PathVariable int id){
		return machineRepository.listMachineByCC(id);
	}
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Boolean>> deleteMachine(@PathVariable int id){
		Machine machine = machineRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Machine not exit with id:" + id));
		machineRepository.delete(machine);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value = "/listTesteurReferenced", method = RequestMethod.GET)
	public List<Machine>listTesteurReferenced(){
		return machineRepository.listTesteurReferenced();
	}
}