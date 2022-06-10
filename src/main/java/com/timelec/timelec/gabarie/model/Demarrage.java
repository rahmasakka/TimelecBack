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
@Table(name = "démarrage")
public class Demarrage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id; 
	
	@Column(name="OF")
	private int of; 
	
	@Column(name="Qte")
	private int quantity;
	
	@Column(name="Date")
	private Date date ;
	
	@Column(name="Temps")
	private Time temps;

	public int getId() {
		return id;
	}

	public int getOf() {
		return of;
	}

	public int getQuantity() {
		return quantity;
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTemps(Time temps) {
		this.temps = temps;
	}

	public Demarrage(int id, int of, int quantity, Date date, Time temps) {
		super();
		this.id = id;
		this.of = of;
		this.quantity = quantity;
		this.date = date;
		this.temps = temps;
	}
	
	public Demarrage() {
		super();
	}

}