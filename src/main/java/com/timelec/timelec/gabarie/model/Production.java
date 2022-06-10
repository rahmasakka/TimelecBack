package com.timelec.timelec.gabarie.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "production")
public class Production {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id; 
	
	@Column(name="OF")
	private int of;
	
	@Column(name="Qte_Produite")
	private int quantiteProduite;
	
	@Column(name="Qte_Rebut")
	private int quantiteRebut;
	
	@Column(name="Date")
	private Date date;
	
	@Column(name="Temps")
	private Time temps;

	public int getId() {
		return id;
	}

	public int getOf() {
		return of;
	}

	public int getQuantiteProduite() {
		return quantiteProduite;
	}

	public int getQuantiteRebut() {
		return quantiteRebut;
	}

	public Date getDate() {
		return date;
	}

	public Time getTemps() {
		return temps;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOf(int of) {
		this.of = of;
	}

	public void setQuantiteProduite(int quantiteProduite) {
		this.quantiteProduite = quantiteProduite;
	}

	public void setQuantiteRebut(int quantiteRebut) {
		this.quantiteRebut = quantiteRebut;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTemps(Time temps) {
		this.temps = temps;
	}

	public Production(int id, int of, int quantiteProduite, int quantiteRebut, Date date, Time temps) {
		super();
		this.id = id;
		this.of = of;
		this.quantiteProduite = quantiteProduite;
		this.quantiteRebut = quantiteRebut;
		this.date = date;
		this.temps = temps;
	}
	
	public Production() {
		super();
	}
}