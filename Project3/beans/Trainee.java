package com.revature.caliber.beans;

import java.util.Set;



public class Trainee {

	
	private int traineeId;
	private String resourceId;
	private String name;
	private String email;
	private TrainingStatus trainingStatus;
	private Batch batch;
	private TraineeFlag flagStatus;
	private String flagNotes;
	
	private Set<Note> notes;
	
	public Trainee() {
		super();
		this.flagStatus = TraineeFlag.NONE;
	}

	public Trainee(int traineeId, String resourceId, String name, TrainingStatus trainingStatus, Batch batch,
			TraineeFlag flagStatus, String flagNotes, Set<Note> notes) {
		super();
		this.traineeId = traineeId;
		this.resourceId = resourceId;
		this.name = name;
		this.trainingStatus = trainingStatus;
		this.batch = batch;
		this.flagStatus = flagStatus;
		this.flagNotes = flagNotes;
		this.notes = notes;
	}

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrainingStatus getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(TrainingStatus trainingStatus) {
		this.trainingStatus = trainingStatus;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public TraineeFlag getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(TraineeFlag flagStatus) {
		this.flagStatus = flagStatus;
	}

	public String getFlagNotes() {
		return flagNotes;
	}

	public void setFlagNotes(String flagNotes) {
		this.flagNotes = flagNotes;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}
	
	


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Trainee [traineeId=" + traineeId + ", resourceId=" + resourceId + ", name=" + name + ", trainingStatus="
				+ trainingStatus + ", batch=" + batch + ", flagStatus=" + flagStatus + ", flagNotes=" + flagNotes
				+ ", notes=" + notes + "]";
	}
	

	
	

}
