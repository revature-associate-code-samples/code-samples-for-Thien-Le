package com.revature.caliber.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * The type Batch.
 */
@Entity
@Table(name = "CALIBER_BATCH")
public class Batch implements Serializable {

	private static final long serialVersionUID = 2916173221333913998L;
	
	@Id
	@Column(name="BATCH_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer batchId;
	
	@NotNull
	@Column(name="TRAINER")
	private String trainer;
	
	@NotNull
	@Column(name="START_DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate;
	
}
