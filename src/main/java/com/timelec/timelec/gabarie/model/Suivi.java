package com.timelec.timelec.gabarie.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suivi")
public class Suivi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="TEMPF")
	private String tempf;
	
	@Column(name="date")
	private Date date;

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getTempf() {
		return tempf;
	}

	public Date getDate() {
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setTempf(String tempf) {
		this.tempf = tempf;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Suivi(int id, String nom, String tempf, Date date) {
		super();
		this.id = id;
		this.nom = nom;
		this.tempf = tempf;
		this.date = date;
	}
	
	public Suivi() {
		super();
	}
}