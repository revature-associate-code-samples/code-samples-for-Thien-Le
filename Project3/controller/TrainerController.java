package com.revature.caliber.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.intercomm.TrainerClient;
import com.revature.caliber.beans.Trainer;

/**
 * Retrieve trainer data from user-service
 * @author thienle
 *
 */
@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins="*")
public class TrainerController {

	private static final Logger log = Logger.getLogger(TrainerController.class);
	
	@Autowired
	private TrainerClient client;
	
	
	@GetMapping("/trainers")
	public ResponseEntity<List<Trainer>> getAllTrainers() {
		return client.getAllTrainers();
	}
	
}
