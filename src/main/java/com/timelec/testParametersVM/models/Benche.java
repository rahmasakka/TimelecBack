package com.timelec.testParametersVM.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "benches")
public class Benche {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Tester_ID")
	private int testerId;
	
	
	@Column(name="Tester_Name", length = 45)
	private String testerName;
	

	@Column(name="Site_Code", length = 45)
	private String siteCode;
	
	
	@Column(name="Next_Maintenance_Date")
	private Date nextMaintenanceDate;
	
	
	@Column(name="Test_Soft_Version", length = 8)
	private String testSoftVersion;


	public int getTesterId() {
		return testerId;
	}


	public String getTesterName() {
		return testerName;
	}


	public String getSiteCode() {
		return siteCode;
	}


	public Date getNextMaintenanceDate() {
		return nextMaintenanceDate;
	}


	public String getTestSoftVersion() {
		return testSoftVersion;
	}


	public void setTesterId(int testerId) {
		this.testerId = testerId;
	}


	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}


	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}


	public void setNextMaintenanceDate(Date nextMaintenanceDate) {
		this.nextMaintenanceDate = nextMaintenanceDate;
	}


	public void setTestSoftVersion(String testSoftVersion) {
		this.testSoftVersion = testSoftVersion;
	}


	public Benche(int testerId, String testerName, String siteCode, Date nextMaintenanceDate, String testSoftVersion) {
		super();
		this.testerId = testerId;
		this.testerName = testerName;
		this.siteCode = siteCode;
		this.nextMaintenanceDate = nextMaintenanceDate;
		this.testSoftVersion = testSoftVersion;
	}
	
	public Benche() {
		super();
	}
}