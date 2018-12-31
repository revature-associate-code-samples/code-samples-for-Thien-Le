package com.revature.caliber.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;


/**
 * 
 *
 */
@Entity
@Table(name = "CALIBER_NOTE")
@DynamicUpdate
public class Note implements Serializable{

	private static final long serialVersionUID = 4960654794116385953L;

	@Id
	@Column(name = "NOTE_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTE_ID_SEQUENCE")
	@SequenceGenerator(name = "NOTE_ID_SEQUENCE", sequenceName = "NOTE_ID_SEQUENCE")
	private int noteId;

	@NotNull
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

	@NotNull
	@Min(value=1)
	@Column(name = "WEEK_NUMBER")
	private short week;
	
	@NotNull
	@Column(name="BATCH_ID")
	private int batchId;
	
	@Column(name="TRAINEE_ID")
	private int traineeId;
	
	@Column(name="MODIFY_DATE")
	private Timestamp modifyDate;

//	@Transient
//	private String modifyTimestamp;
//	
//	public String getModifyTimestamp() {
//        if(modifyTimestamp == null) {
//            modifyTimestamp = DateTimeFormatter
//            .ISO_DATE_TIME.format(
//                modifyDate.toLocalDateTime()
//            );
//        }
//        return modifyTimestamp;
//    }

	public Note() {
		super();
		this.modifyDate = new Timestamp(System.currentTimeMillis());
	}



	public Note(int noteId, String content, int maxVisibility, int qcFeedback, String qcStatus, String noteType,
			short week, int batchId, int traineeId, Timestamp modifyDate) {
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
		this.modifyDate = new Timestamp(System.currentTimeMillis());
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



	public Timestamp getModifyDate() {
		return modifyDate;
	}



	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + noteId;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + maxVisibility;
		result = prime * result + qcFeedback;
		result = prime * result + ((qcStatus == null) ? 0 : qcStatus.hashCode());
		result = prime * result + ((noteType == null) ? 0 : noteType.hashCode());
		result = prime * result + week;
		result = prime * result + batchId;
		result = prime * result + traineeId;
		result = prime * result + ((modifyDate == null) ? 0 : modifyDate.hashCode());
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", content=" + content + ", maxVisibility=" + maxVisibility + ", qcFeedback="
				+ qcFeedback + ", qcStatus=" + qcStatus + ", noteType=" + noteType + ", week=" + week + ", batchId="
				+ batchId + ", traineeId=" + traineeId + ", modifyDate=" + modifyDate + "]";
	}

	
	
}

