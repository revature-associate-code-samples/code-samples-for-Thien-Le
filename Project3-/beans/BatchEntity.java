package com.revature.caliber.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * BatchEntity to represent a batch. It holds the training name,
 * training type, skill type, trainer, co-trainer, location, start date,
 * end date, good grade, and passing grade.
 * 
 * @author Bita, Justin
 *
 */
@Entity
@Table(name="BATCH")
public class BatchEntity {
	@Id
	@Column(name="BATCH_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer batchId;
	
	@NotNull
	@Column(name="TRAINING_NAME")
	private String trainingName;
	
	@NotNull
	@Column(name="TRAINING_TYPE")
	private String trainingType;
	
	@NotNull
	@Column(name="SKILL_TYPE")
	private String skillType;
	
	@NotNull
	@Column(name="TRAINER")
	private String trainer;
	
	@Column(name="CO_TRAINER")
	private String coTrainer;
	
	@NotNull
	@Column(name="LOCATION_ID")
	private Integer locationId;
	
	
	private String location;
	
	@NotNull
	@Column(name="START_DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate;
	
	@NotNull
	@Column(name="END_DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate;
	
	@NotNull
	@Column(name="GOOD_GRADE")
	private Integer goodGrade;
	
	@NotNull
	@Column(name="PASSING_GRADE")
	private Integer passingGrade;
	
	/**
	 * Constructor for BatchEntity. It holds the training name,
	 * training type, skill type, trainer, co-trainer, location, start date,
	 * end date, good grade, and passing grade.
	 * 
	 * @param trainingName A String to represent the training name
	 * @param trainingType A String to represent the training type
	 * @param skillType A String to represent the skill type.
	 * @param trainer A String to represent the trainer
	 * @param coTrainer A String to represent the co-trainer
	 * @param location A String to represent the location
	 * @param startDate A Date to represent the start date
	 * @param endDate A Date to represent the end date
	 * @param goodGrade A Integer to represent the good grade
	 * @param passingGrade A Integer to represent the passing grade
	 */
	
	public BatchEntity(String trainingName, String trainingType, String skillType, String trainer,
			String coTrainer, Integer locationId, Date startDate, Date endDate, Integer goodGrade,
			Integer passingGrade) {
		super();
		this.trainingName = trainingName;
		this.trainingType = trainingType;
		this.skillType = skillType;
		this.trainer = trainer;
		this.coTrainer = coTrainer;
		this.locationId = locationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.goodGrade = goodGrade;
		this.passingGrade = passingGrade;
	}
	
	/**
	 * Default constructor of BatchEntity.
	 */
	public BatchEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getter for training name.
	 * @return A String that holds the batch's training name.
	 */
	public String getTrainingName() {
		return trainingName;
	}
	/**
	 * Setter for training name.
	 * @param trainingName A String that holds the batch's training name.
	 */
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	/**
	 * Getter for training type.
	 * @return A String that holds the batch's training type.
	 */
	public String getTrainingType() {
		return trainingType;
	}
	/**
	 * Setter for training type.
	 * @param trainingType A String that holds the batch's training type.
	 */
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	/**
	 * Getter for skill type.
	 * @return A String that holds the batch's skill type.
	 */
	public String getSkillType() {
		return skillType;
	}
	/**
	 * Setter for skill type.
	 * @param skillType A String that holds the batch's skill type.
	 */
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}
	/**
	 * Getter for trainer.
	 * @return A String that holds the batch's trainer.
	 */
	public String getTrainer() {
		return trainer;
	}
	/**
	 * Setter for trainer.
	 * @param trainer A String that holds the batch's trainer.
	 */
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	/**
	 * Getter for co-trainer.
	 * @return A String that holds the batch's co-trainer.
	 */
	public String getCoTrainer() {
		return coTrainer;
	}
	/**
	 * Setter for co-trainer.
	 * @param coTrainer A String that holds the batch's co-trainer.
	 */
	public void setCoTrainer(String coTrainer) {
		this.coTrainer = coTrainer;
	}

	/**
	 * Getter for start date.
	 * @return A Date that holds the batch's start date.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Setter for start date.
	 * @param startDate A Date that holds the batch's start date.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Getter for end date.
	 * @return A Date that holds the batch's end date.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * Setter for end date.
	 * @param endDate A Date that holds the batch's end date.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * Getter for good grade.
	 * @return A Date that holds the batch's good grade.
	 */
	public Integer getGoodGrade() {
		return goodGrade;
	}
	/**
	 * Setter for good date.
	 * @param goodGrade A Integer that holds the batch's good grade.
	 */
	public void setGoodGrade(Integer goodGrade) {
		this.goodGrade = goodGrade;
	}
	/**
	 * Getter for passing grade.
	 * @return A Integer that holds the batch's passing grade.
	 */
	public Integer getPassingGrade() {
		return passingGrade;
	}
	/**
	 * Setter for passing grade.
	 * @param passingGrade A Integer that holds the batch's passing grade.
	 */
	public void setPassingGrade(Integer passingGrade) {
		this.passingGrade = passingGrade;
	}
	/**
	 * Getter for batch id.
	 * @return A Integer that holds the batch's id.
	 */
	public Integer getBatchId() {
		return batchId;
	}
	/**
	 * Setter for batch id.
	 * @param batchId A Integer that holds the batch's id.
	 */
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != BatchEntity.class) {
			return false;
		}
		BatchEntity other = (BatchEntity) obj;
		if(!this.coTrainer.equals(other.getCoTrainer()) || !this.locationId.equals(other.getLocationId()) || !this.goodGrade.equals(other.getGoodGrade())
				|| !this.passingGrade.equals(other.getPassingGrade()) || !this.skillType.equals(other.getSkillType()) 
				|| !this.trainer.equals(other.getTrainer()) || !this.trainingName.equals(other.getTrainingName()) 
				|| !this.trainingType.equals(other.getTrainingType()))
		{
			return false;
		}
		
		if(this.startDate.getTime() != other.getStartDate().getTime() || this.endDate.getTime() != other.getEndDate().getTime()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Return a String to represent the BatchEntity.
	 * @return A String to represent the BatchEntity.
	 */
	@Override
	public String toString() {
		return "BatchEntity [batchId=" + batchId + ", trainingName=" + trainingName + ", trainingType=" + trainingType
				+ ", skillType=" + skillType + ", trainer=" + trainer + ", coTrainer=" + coTrainer + ", locationId=" + locationId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", goodGrade=" + goodGrade + ", passingGrade=" + passingGrade + "]";
	}



}
