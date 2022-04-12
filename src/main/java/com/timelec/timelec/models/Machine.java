package com.timelec.timelec.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Machine")
public class Machine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Machine")
	private int idMachine;
	
	@Column(name="machine_name")
	private String machineName;
	
	@Column(name="Machine_description")
	private String machineDescription;	
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="Machine_category", length = 20)
	private MachineCategory machineCategory;

	
    @ManyToOne
    @JoinColumn(name="ID_CC", nullable=false)
    private LoadCharge loadCharge;

    
	public int getIdMachine() {
		return idMachine;
	}


	public String getMachineName() {
		return machineName;
	}


	public String getMachineDescription() {
		return machineDescription;
	}


	public MachineCategory getMachineCategory() {
		return machineCategory;
	}


	public void setIdMachine(int idMachine) {
		this.idMachine = idMachine;
	}


	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}


	public void setMachineDescription(String machineDescription) {
		this.machineDescription = machineDescription;
	}


	public void setMachineCategory(MachineCategory machineCategory) {
		this.machineCategory = machineCategory;
	}

	
	public LoadCharge getCentreCharge() {
		return loadCharge;
	}


	public void setCentreCharge(LoadCharge loadCharge) {
		this.loadCharge = loadCharge;
	}


	public Machine() {
		super();
	}


	public Machine(int idMachine, String machineName, String machineDescription, MachineCategory machineCategory,
			LoadCharge loadCharge) {
		super();
		this.idMachine = idMachine;
		this.machineName = machineName;
		this.machineDescription = machineDescription;
		this.machineCategory = machineCategory;
		this.loadCharge = loadCharge;
	}
	
}