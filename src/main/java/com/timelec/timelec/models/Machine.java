package com.timelec.timelec.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Machine")
public class Machine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Machine")
	private int IdMachine;
	
	@Column(name="machine_name")
	private String MachineName;
	
	@Column(name="Machine_description")
	private String MachineDescription;	
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="Machine_category", length = 20)
	private MachineCategory MachineCategory;


	public int getIdMachine() {
		return IdMachine;
	}


	public String getMachineName() {
		return MachineName;
	}


	public String getMachineDescription() {
		return MachineDescription;
	}


	public MachineCategory getMachineCategory() {
		return MachineCategory;
	}


	public void setIdMachine(int idMachine) {
		IdMachine = idMachine;
	}


	public void setMachineName(String machineName) {
		MachineName = machineName;
	}


	public void setMachineDescription(String machineDescription) {
		MachineDescription = machineDescription;
	}


	public void setMachineCategory(MachineCategory machineCategory) {
		MachineCategory = machineCategory;
	}


	public Machine(int idMachine, String machineName, String machineDescription,
			com.timelec.timelec.models.MachineCategory machineCategory) {
		super();
		IdMachine = idMachine;
		MachineName = machineName;
		MachineDescription = machineDescription;
		MachineCategory = machineCategory;
	}
	
	
	public Machine() {
		super();
	}
	
}