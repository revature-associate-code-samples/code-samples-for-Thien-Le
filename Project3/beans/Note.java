package com.revature.caliber.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 *
 */
@Entity
@Table(name = "CALIBER_NOTE")
public class Note implements Serializable{

	private static final long serialVersionUID = 4960654794116385953L;

	@Id
	@Column(name = "NOTE_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTE_ID_SEQUENCE")
	@SequenceGenerator(name = "NOTE_ID_SEQUENCE", sequenceName = "NOTE_ID_SEQUENCE")
	private int noteId;

	@Length(min=0, max=4000)
	@Column(name = "NOTE_CONTENT")
	private String content;
	
	@Column(name="MAX_VISIBILITY")
	private int maxVisibility;
	
	@Column(name="IS_QC_FEEDBACK")
	private int qcFeedback;
	
	@Column(name="QC_STATUS")
	private String qcStatus;
	
	@Column(name="NOTE_TYPE")
	private String noteType;

	@Min(value=1)
	@Column(name = "WEEK_NUMBER")
	private short week;
	
	@Column(name="BATCH_ID")
	private int batchId;
	
	@Column(name="TRAINEE_ID")
	private int traineeId;

	

	public Note() {
		super();
		
	}



	public Note(int noteId, String content, int maxVisibility, int qcFeedback, String qcStatus, String noteType,
			short week, int batchId, int traineeId) {
		super();
		this.noteId = noteId;
		this.content = content;
		this.maxVisibility = maxVisibility;
		this.qcFeedback = qcFeedback;
		this.qcStatus = qcStatus;
		this.noteType = noteType;
		this.week = week;
		this.batchId = batchId;
		this.traineeId = traineeId;
	}



	public int getNoteId() {
		return noteId;
	}



	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public int getMaxVisibility() {
		return maxVisibility;
	}



	public void setMaxVisibility(int maxVisibility) {
		this.maxVisibility = maxVisibility;
	}



	public int getQcFeedback() {
		return qcFeedback;
	}



	public void setQcFeedback(int qcFeedback) {
		this.qcFeedback = qcFeedback;
	}



	public String getQcStatus() {
		return qcStatus;
	}



	public void setQcStatus(String qcStatus) {
		this.qcStatus = qcStatus;
	}



	public String getNoteType() {
		return noteType;
	}



	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}



	public short getWeek() {
		return week;
	}



	public void setWeek(short week) {
		this.week = week;
	}



	public int getBatchId() {
		return batchId;
	}



	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}



	public int getTraineeId() {
		return traineeId;
	}



	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", content=" + content + ", maxVisibility=" + maxVisibility + ", qcFeedback="
				+ qcFeedback + ", qcStatus=" + qcStatus + ", noteType=" + noteType + ", week=" + week + ", batchId="
				+ batchId + ", traineeId=" + traineeId + "]";
	}

	
	
}

