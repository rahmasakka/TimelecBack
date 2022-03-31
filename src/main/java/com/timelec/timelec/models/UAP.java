package com.timelec.timelec.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "UAP")
public class UAP {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_UAP")
	private int IdUAP;
	
    @NotEmpty(message = "Name may not be empty")
	@Column(name="UAP_Name")
	private String UapName;
	
    
    @NotEmpty(message = "description may not be empty")
	@Column(name="UAP_description")
	private String UapDescription;


	public int getIdUAP() {
		return IdUAP;
	}


	public String getUapName() {
		return UapName;
	}


	public String getUapDescription() {
		return UapDescription;
	}


	public void setIdUAP(int idUAP) {
		IdUAP = idUAP;
	}


	public void setUapName(String uapName) {
		UapName = uapName;
	}


	public void setUapDescription(String uapDescription) {
		UapDescription = uapDescription;
	}


	public UAP(int idUAP, String uapName, String uapDescription) {
		super();
		IdUAP = idUAP;
		UapName = uapName;
		UapDescription = uapDescription;
	}	
    
    
	public UAP() {
		super();
	}	
    
}