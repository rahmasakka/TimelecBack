package com.timelec.timelec.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Dashboard")
public class Dashboard {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
//    @Temporal(TemporalType.DATE)
//    @JsonFormat(pattern="yyyy-MM-dd", timezone="Europe/Paris")
	@Column(name="Test_start_time")
	private String date;	
    
    @ManyToOne
    @JoinColumn(name="ID_Machine")
    private Machine testeurId;
    
    @Column(name="quantite_conforme")
    private long quantiteConforme;
    
    @Column(name="quantite_non_conforme")
    private long quantiteNonConforme;
    
    @Column(name="duree_fonctionnement_en_seconde")
    private long dureeFonctionnementSeconde;
    
    @Column(name="duree_disfonctionnement_en_seconde")
    private long dureeDisfonctionnementSeconde;
    
    @Column(name="duree_fonctionnement")
    private String dureeFonctionnement;
    
    @Column(name="duree_disfonctionnement")
    private String dureeDisfonctionnement;
    
    @Column(name="db")
    private String database;
    
  //  @JsonFormat(pattern="HH:mm:ss")
    @Column(name="heure_depart")
    private String startTime;
   
   // @JsonFormat(pattern="HH:mm:ss")
    @Column(name="heure_fin")
    private String finishTime;
    
    public Long getId() {
		return id;
	}


	public String getDate() {
		return date;
	}


	public Machine getTesteurId() {
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


	public String getDureeFonctionnement() {
		return dureeFonctionnement;
	}


	public String getDureeDisfonctionnement() {
		return dureeDisfonctionnement;
	}


	public String getDatabase() {
		return database;
	}


	public String getStartTime() {
		return startTime;
	}


	public String getFinishTime() {
		return finishTime;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public void setTesteurId(Machine testeurId) {
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


	public void setDureeFonctionnement(String dureeFonctionnement) {
		this.dureeFonctionnement = dureeFonctionnement;
	}


	public void setDureeDisfonctionnement(String dureeDisfonctionnement) {
		this.dureeDisfonctionnement = dureeDisfonctionnement;
	}


	public void setDatabase(String database) {
		this.database = database;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public Dashboard(Long id, String date, Machine testeurId, long quantiteConforme, long quantiteNonConforme,
			long dureeFonctionnementSeconde, long dureeDisfonctionnementSeconde, String dureeFonctionnement,
			String dureeDisfonctionnement, String database, String startTime, String finishTime) {
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