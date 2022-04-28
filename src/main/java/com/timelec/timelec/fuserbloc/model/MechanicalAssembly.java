package com.timelec.timelec.fuserbloc.model;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "table_mechanical_assembly")
public class MechanicalAssembly {

	//int(10)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_mechanical_assembly")
	private long idMechanicalAssembly;
	
	@NotBlank
	@Column(name="Serial_No", length = 20)
	private String serialNo;
	
	
	@Column(name="Part_No", length = 20)
	private String partNo;
	
	
	@Column(name="Revision", length = 3)
	private String revision;
	
	
	@Column(name="Product_name", length = 45)
	private String productName;
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "MA_date")
	private Timestamp maDate;
	
	
	@Column(name="MA_start_time")
	private Time maStartTime;
	
	
	@Column(name="Manufacturing_Order", length = 10)
	private String manufactoringOrder;
	
	
	@Column(name="Packing_status")
	private Boolean packingStatus;
	
	
	@Column(name="Nb_Reworks")
	private int nbReworks;
	
	
	@Column(name="Packing_operator", length = 20)
	private String packingOperator;
	
	
	@Column(name="Nb_Repacks")
	private int nbRepacks;


	public long getIdMechanicalAssembly() {
		return idMechanicalAssembly;
	}


	public String getSerialNo() {
		return serialNo;
	}


	public String getPartNo() {
		return partNo;
	}


	public String getRevision() {
		return revision;
	}


	public String getProductName() {
		return productName;
	}


	public Timestamp getMaDate() {
		return maDate;
	}


	public Time getMaStartTime() {
		return maStartTime;
	}


	public String getManufactoringOrder() {
		return manufactoringOrder;
	}


	public Boolean getPackingStatus() {
		return packingStatus;
	}


	public int getNbReworks() {
		return nbReworks;
	}


	public String getPackingOperator() {
		return packingOperator;
	}


	public int getNbRepacks() {
		return nbRepacks;
	}


	public void setIdMechanicalAssembly(long idMechanicalAssembly) {
		this.idMechanicalAssembly = idMechanicalAssembly;
	}


	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}


	public void setRevision(String revision) {
		this.revision = revision;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public void setMaDate(Timestamp maDate) {
		this.maDate = maDate;
	}


	public void setMaStartTime(Time maStartTime) {
		this.maStartTime = maStartTime;
	}


	public void setManufactoringOrder(String manufactoringOrder) {
		this.manufactoringOrder = manufactoringOrder;
	}


	public void setPackingStatus(Boolean packingStatus) {
		this.packingStatus = packingStatus;
	}


	public void setNbReworks(int nbReworks) {
		this.nbReworks = nbReworks;
	}


	public void setPackingOperator(String packingOperator) {
		this.packingOperator = packingOperator;
	}


	public void setNbRepacks(int nbRepacks) {
		this.nbRepacks = nbRepacks;
	}


	public MechanicalAssembly(long idMechanicalAssembly, @NotBlank String serialNo, String partNo, String revision,
			String productName, Timestamp maDate, Time maStartTime, String manufactoringOrder, Boolean packingStatus,
			int nbReworks, String packingOperator, int nbRepacks) {
		super();
		this.idMechanicalAssembly = idMechanicalAssembly;
		this.serialNo = serialNo;
		this.partNo = partNo;
		this.revision = revision;
		this.productName = productName;
		this.maDate = maDate;
		this.maStartTime = maStartTime;
		this.manufactoringOrder = manufactoringOrder;
		this.packingStatus = packingStatus;
		this.nbReworks = nbReworks;
		this.packingOperator = packingOperator;
		this.nbRepacks = nbRepacks;
	}
	
	public MechanicalAssembly() {
		super();
		
	}	
}