package com.timelec.timelec.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Centre_Charge")
public class CentreCharge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id_centre_de_charge")
	private int IdCC;
	
	
	@Column(name="Nom_centre_de_charge")
	private String CCName;
	
	@Column(name="Description_centre_charge")
	private String CCDescription;
	
	@ManyToOne
    @JoinColumn(name="ID_UAP")
    private UAP uap;

	public int getIdCC() {
		return IdCC;
	}

	public String getCCName() {
		return CCName;
	}

	public String getCCDescription() {
		return CCDescription;
	}

	public UAP getUap() {
		return uap;
	}

	public void setIdCC(int idCC) {
		IdCC = idCC;
	}

	public void setCCName(String CCName) {
		this.CCName = CCName;
	}

	public void setCCDescription(String CCDescription) {
		this.CCDescription = CCDescription;
	}

	public void setUap(UAP uap) {
		this.uap = uap;
	}

	public CentreCharge(int idCC, String CCName, String CCDescription, UAP uap) {
		super();
		IdCC = idCC;
		this.CCName = CCName;
		this.CCDescription = CCDescription;
		this.uap = uap;
	}
    
    
	public CentreCharge() {
		super();
	}

	@Override
	public String toString() {
		return "CentreCharge [IdCC=" + IdCC + ", CCName=" + CCName + ", CCDescription=" + CCDescription + ", uap=" + uap + "]";
	}
	
}