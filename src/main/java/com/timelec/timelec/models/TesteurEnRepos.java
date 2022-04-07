package com.timelec.timelec.models;

import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Testeur_En_Arret")
public class TesteurEnRepos { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_testeur_Repos")
	private long idTesteurEnRepos;
	
	@Column(name="ID_summary")
	private long idSummary;
		
	@Column(name="Tester_ID") 
	private Long testerID;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="Test_start_time")
	private Timestamp testStartTime;
	
	@Column(name="Id_mechanical_assembly")
    private long idMechanicalAssembly;

	
	@Column(name="Duree_repos_second")
	private int dureeSeconde;
	
	@Column(name="Duree")
	private Time duree;

	public long getIdTesteurEnRepos() {
		return idTesteurEnRepos;
	}

	public void setIdTesteurEnRepos(long idTesteurEnRepos) {
		this.idTesteurEnRepos = idTesteurEnRepos;
	}

	public long getIdSummary() {
		return idSummary;
	}

	public Long getTesterID() {
		return testerID;
	}

	public Timestamp getTestStartTime() {
		return testStartTime;
	}

	public long getIdMechanicalAssembly() {
		return idMechanicalAssembly;
	}

	public void setIdSummary(long idSummary) {
		this.idSummary = idSummary;
	}

	public void setTesterID(Long testerID) {
		this.testerID = testerID;
	}

	public void setTestStartTime(Timestamp testStartTime) {
		this.testStartTime = testStartTime;
	}
	public void setIdMechanicalAssembly(long idMechanicalAssembly) {
		this.idMechanicalAssembly = idMechanicalAssembly;
	}

	

	public TesteurEnRepos(long idTesteurEnRepos, long idSummary, Long testerID, Timestamp testStartTime,
			long idMechanicalAssembly, int dureeSeconde, Time duree) {
		super();
		this.idTesteurEnRepos = idTesteurEnRepos;
		this.idSummary = idSummary;
		this.testerID = testerID;
		this.testStartTime = testStartTime;
		this.idMechanicalAssembly = idMechanicalAssembly;
		this.dureeSeconde = dureeSeconde;
		this.duree = duree;
	}

	public int getDureeSeconde() {
		return dureeSeconde;
	}

	public Time getDuree() {
		return duree;
	}

	public void setDureeSeconde(int dureeSeconde) {
		this.dureeSeconde = dureeSeconde;
	}

	public void setDuree(Time duree) {
		this.duree = duree;
	}

	public TesteurEnRepos() {
		super();
	}
}