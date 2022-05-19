package com.timelec.timelec.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Machine")
public class Machine {
	
	@Id
	@Column(name="Id_machine", unique=true)
	private int idMachine;
	
	@Column(name="Nom_machine")
	private String machineName;
	
	@Column(name="Description_machine")
	private String machineDescription;	
	
	@Column(name="Taux_fonctionnement")
	private int tauxFonctionnement;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="Category_machine", length = 20)
	private MachineCategory machineCategory;
	
	
	@Column(name="Reference")
	private Boolean reference;
	
    @ManyToOne
    @JoinColumn(name="Centre_de_charge")
    private CentreCharge centreCharge;

	public int getIdMachine() {
		return idMachine;
	}

	public String getMachineName() {
		return machineName;
	}

	public String getMachineDescription() {
		return machineDescription;
	}

	public int getTauxFonctionnement() {
		return tauxFonctionnement;
	}

	public MachineCategory getMachineCategory() {
		return machineCategory;
	}

	public Boolean getReference() {
		return reference;
	}

	public CentreCharge getCentreCharge() {
		return centreCharge;
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

	public void setTauxFonctionnement(int tauxFonctionnement) {
		this.tauxFonctionnement = tauxFonctionnement;
	}

	public void setMachineCategory(MachineCategory machineCategory) {
		this.machineCategory = machineCategory;
	}

	public void setReference(Boolean reference) {
		this.reference = reference;
	}

	public void setCentreCharge(CentreCharge centreCharge) {
		this.centreCharge = centreCharge;
	}

	public Machine(int idMachine, String machineName, String machineDescription, int tauxFonctionnement,
			MachineCategory machineCategory, Boolean reference, CentreCharge centreCharge) {
		super();
		this.idMachine = idMachine;
		this.machineName = machineName;
		this.machineDescription = machineDescription;
		this.tauxFonctionnement = tauxFonctionnement;
		this.machineCategory = machineCategory;
		this.reference = reference;
		this.centreCharge = centreCharge;
	}

	public Machine() {
		super();
	}      
}