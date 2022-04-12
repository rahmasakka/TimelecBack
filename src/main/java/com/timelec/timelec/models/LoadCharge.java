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
public class LoadCharge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_CC")
	private int IdCC;
	
	
	@Column(name="CC_Name")
	private String CCName;
	
	@Column(name="CC_description")
	private String CCDescription;
	
	@ManyToOne
    @JoinColumn(name="UAP_ID", nullable=false)
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

	public void setCCName(String cCName) {
		CCName = cCName;
	}

	public void setCCDescription(String cCDescription) {
		CCDescription = cCDescription;
	}

	public void setUap(UAP uap) {
		this.uap = uap;
	}

	public LoadCharge(int idCC, String cCName, String cCDescription, UAP uap) {
		super();
		IdCC = idCC;
		CCName = cCName;
		CCDescription = cCDescription;
		this.uap = uap;
	}
    
    
	public LoadCharge() {
		super();
	}
}