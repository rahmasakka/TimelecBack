package com.timelec.timelec.models;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Dashboard")
public class Dashboard {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
    @JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="Test_start_time")
	private Date date;	
    
    @Column(name="tester_id")
    private int testeurId;

    @Column(name="quantite_conforme")
    private long quantiteConforme;
    
    @Column(name="quantite_non_conforme")
    private long quantiteNonConforme;
    
    @Column(name="duree_fonctionnement_en_seconde")
    private long dureeFonctionnementSeconde;
    
    @Column(name="duree_disfonctionnement_en_seconde")
    private long dureeDisfonctionnementSeconde;
    
    @Column(name="duree_fonctionnement")
    private Time dureeFonctionnement;
    
    @Column(name="duree_disfonctionnement")
    private Time dureeDisfonctionnement;
    
    @Column(name="db")
    private String database;
    
    @JsonFormat(pattern="HH:mm:ss")
    @Column(name="heure_depart")
    private Time startTime;
   
    @JsonFormat(pattern="HH:mm:ss")
    @Column(name="heure_fin")
    private Time finishTime;

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public int getTesteurId() {
		return testeurId;
	}

	public long getQuantiteConforme() {
		return quantiteConforme;
	}

	public long getQuantiteNonConforme() {
		return quantiteNonConforme;
	}

	public long getDureeFonctionnementSeconde() {
		return dureeFonctionnementSeconde;
	}

	public long getDureeDisfonctionnementSeconde() {
		return dureeDisfonctionnementSeconde;
	}

	public Time getDureeFonctionnement() {
		return dureeFonctionnement;
	}

	public Time getDureeDisfonctionnement() {
		return dureeDisfonctionnement;
	}

	public String getDatabase() {
		return database;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getFinishTime() {
		return finishTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTesteurId(int testeurId) {
		this.testeurId = testeurId;
	}

	public void setQuantiteConforme(long quantiteConforme) {
		this.quantiteConforme = quantiteConforme;
	}

	public void setQuantiteNonConforme(long quantiteNonConforme) {
		this.quantiteNonConforme = quantiteNonConforme;
	}

	public void setDureeFonctionnementSeconde(long dureeFonctionnementSeconde) {
		this.dureeFonctionnementSeconde = dureeFonctionnementSeconde;
	}

	public void setDureeDisfonctionnementSeconde(long dureeDisfonctionnementSeconde) {
		this.dureeDisfonctionnementSeconde = dureeDisfonctionnementSeconde;
	}

	public void setDureeFonctionnement(Time dureeFonctionnement) {
		this.dureeFonctionnement = dureeFonctionnement;
	}

	public void setDureeDisfonctionnement(Time dureeDisfonctionnement) {
		this.dureeDisfonctionnement = dureeDisfonctionnement;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setFinishTime(Time finishTime) {
		this.finishTime = finishTime;
	}

	public Dashboard(Long id, Date date, int testeurId, long quantiteConforme, long quantiteNonConforme,
			long dureeFonctionnementSeconde, long dureeDisfonctionnementSeconde, Time dureeFonctionnement,
			Time dureeDisfonctionnement, String database, Time startTime, Time finishTime) {
		super();
		this.id = id;
		this.date = date;
		this.testeurId = testeurId;
		this.quantiteConforme = quantiteConforme;
		this.quantiteNonConforme = quantiteNonConforme;
		this.dureeFonctionnementSeconde = dureeFonctionnementSeconde;
		this.dureeDisfonctionnementSeconde = dureeDisfonctionnementSeconde;
		this.dureeFonctionnement = dureeFonctionnement;
		this.dureeDisfonctionnement = dureeDisfonctionnement;
		this.database = database;
		this.startTime = startTime;
		this.finishTime = finishTime;
	}

	public Dashboard() {
		super();
	}
}