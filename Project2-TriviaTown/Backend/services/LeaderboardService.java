package com.ex.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ex.data.HighScorePlayerBean;
import com.ex.repository.UserRepository;

/*
 * This class is used for 
 * */
public class LeaderboardService {
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	UserRepository repo = (UserRepository) context.getBean(UserRepository.class);
	public List<HighScorePlayerBean> getAll(){
		return repo.findAll();
	}
	
	public HighScorePlayerBean save(HighScorePlayerBean persisted) {
		return repo.save(persisted);
	}
}