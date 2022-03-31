package com.timelec.timelec.models;

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
	@Column(name="ID_summary")
	private long IdSummary;
		
	@Column(name="Tester_ID") 
	private Long testerID;
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="Test_start_time")
	private Timestamp testStartTime;
	
	@Column(name="id_mechanical_assembly")
    private long IdMechanicalAssembly;

}