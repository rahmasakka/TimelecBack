/*package com.timelec.timelec.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;


@Embeddable
public class TesteurPrimaryKey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="Tester_ID") 
	private Long testerID;
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="Test_start_time")
	private Timestamp testStartTime;
	

	public Long getTesterID() {
		return testerID;
	}

	public Timestamp getTestStartTime() {
		return testStartTime;
	}

	public void setTesterID(Long testerID) {
		this.testerID = testerID;
	}

	public void setTestStartTime(Timestamp testStartTime) {
		this.testStartTime = testStartTime;
	}
}*/