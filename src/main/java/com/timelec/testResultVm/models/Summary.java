package com.timelec.testResultVm.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "table_summary")
@Component
public class Summary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_summary")
	private long IdSummary;
		
	@Column(name="Tester_ID") 
	private Long testerID;
	
	
	@Column(name="Test_status")
	private boolean testStatus;
	

	@Column(name="site_code", length = 3)
	private String siteCode;
	
	
	@Column(name="Operator_Name", length = 20)
	private String operatorName;
	
	
	/*@Column(name="fixture_ID")
	private long fixtureId;	*/
	
	
	@Column(name="VLO", length = 45)
	private String vlo;
	
	
	@Column(name="test_soft_version", length = 45)
	private String testSoftVersion;
	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="Test_start_time")
	private Timestamp testStartTime;
	
    /*
	@Column(name="Test_elapsed_time")
	private double testElapsedTime;
	*/
	
	@Column(name="Client_serial_No", length = 45)
	private String clientSerialNo;
	
	@Column(name="Test_Index")
	private long testIndex;
	
	@Column(name="Rework_Index")
	private Integer reworkIndex;	
	
	
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name="Table_mechanical_assembly_ID_mechanical_assembly", nullable=false)
    private MechanicalAssembly mechanicalAssembly;


	public long getIdSummary() {
		return IdSummary;
	}


	public Long getTesterID() {
		return testerID;
	}


	public boolean isTestStatus() {
		return testStatus;
	}


	public String getSiteCode() {
		return siteCode;
	}


	public String getOperatorName() {
		return operatorName;
	}


	public String getVlo() {
		return vlo;
	}


	public String getTestSoftVersion() {
		return testSoftVersion;
	}


	public Timestamp getTestStartTime() {
		return testStartTime;
	}


	public String getClientSerialNo() {
		return clientSerialNo;
	}


	public long getTestIndex() {
		return testIndex;
	}


	public Integer getReworkIndex() {
		return reworkIndex;
	}


	public MechanicalAssembly getMechanicalAssembly() {
		return mechanicalAssembly;
	}


	public void setIdSummary(long idSummary) {
		IdSummary = idSummary;
	}


	public void setTesterID(Long testerID) {
		this.testerID = testerID;
	}


	public void setTestStatus(boolean testStatus) {
		this.testStatus = testStatus;
	}


	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}


	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}


	public void setVlo(String vlo) {
		this.vlo = vlo;
	}


	public void setTestSoftVersion(String testSoftVersion) {
		this.testSoftVersion = testSoftVersion;
	}


	public void setTestStartTime(Timestamp testStartTime) {
		this.testStartTime = testStartTime;
	}


	public void setClientSerialNo(String clientSerialNo) {
		this.clientSerialNo = clientSerialNo;
	}


	public void setTestIndex(long testIndex) {
		this.testIndex = testIndex;
	}


	public void setReworkIndex(Integer reworkIndex) {
		this.reworkIndex = reworkIndex;
	}


	public void setMechanicalAssembly(MechanicalAssembly mechanicalAssembly) {
		this.mechanicalAssembly = mechanicalAssembly;
	}


	public Summary(long idSummary, Long testerID, boolean testStatus, String siteCode, String operatorName, String vlo,
			String testSoftVersion, Timestamp testStartTime, String clientSerialNo, long testIndex, Integer reworkIndex,
			MechanicalAssembly mechanicalAssembly) {
		super();
		IdSummary = idSummary;
		this.testerID = testerID;
		this.testStatus = testStatus;
		this.siteCode = siteCode;
		this.operatorName = operatorName;
		this.vlo = vlo;
		this.testSoftVersion = testSoftVersion;
		this.testStartTime = testStartTime;
		this.clientSerialNo = clientSerialNo;
		this.testIndex = testIndex;
		this.reworkIndex = reworkIndex;
		this.mechanicalAssembly = mechanicalAssembly;
	}


	
	public Summary() {
		super();
	}
    
}