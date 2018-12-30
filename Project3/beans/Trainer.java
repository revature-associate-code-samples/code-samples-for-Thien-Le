package com.revature.caliber.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Trainer.
 */
@Entity
@Table(name = "CALIBER_TRAINER")
public class Trainer implements Serializable{

	private static final long serialVersionUID = 2886418048122511755L;
	
	@Id
	@Column(name = "TRAINER_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAINER_ID_SEQUENCE")
	@SequenceGenerator(name = "TRAINER_ID_SEQUENCE", sequenceName = "TRAINER_ID_SEQUENCE")
	@JsonProperty
	private int trainerId;
	
	@NotEmpty
	@Column(name = "NAME", nullable = false)
	@JsonProperty
	private String name;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "TIER")
	private TrainerRole tier;
	
	@OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Batch> batches;
	
	
	

	public Trainer() {
		super();
	}




	public Trainer(int trainerId, String name, TrainerRole tier, Set<Batch> batches) {
		super();
		this.trainerId = trainerId;
		this.name = name;
		this.tier = tier;
		this.batches = batches;
	}


	

	public int getTrainerId() {
		return trainerId;
	}




	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public TrainerRole getTier() {
		return tier;
	}




	public void setTier(TrainerRole tier) {
		this.tier = tier;
	}




	public Set<Batch> getBatches() {
		return batches;
	}




	public void setBatches(Set<Batch> batches) {
		this.batches = batches;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "Trainer [trainerId=" + trainerId + ", name=" + name + ", tier=" + tier + ", batches=" + batches + "]";
	}
	
	

}
