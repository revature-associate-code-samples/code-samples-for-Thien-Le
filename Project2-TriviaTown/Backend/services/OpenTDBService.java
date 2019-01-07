package com.ex.services;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.ex.api.opentdb.OpenTDBAPIJson;
import com.ex.api.opentdb.QuestionJson;
import com.ex.game.QuestionBean;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenTDBService {
	

	public static  ArrayList<QuestionBean> getQuestions(int numOfQuestions, String category) {	
		// Will populate list with questions fetched from the API
		ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
		
		try {
			// Generate URL to fetch questions from API
			String urlString;
			int categoryNumber = convertCategoryToInt(category);		
			if(categoryNumber == 0) {	// Corresponds to "any" category
				urlString = "https://opentdb.com/api.php?amount=" + numOfQuestions;
			} else {
				urlString = "https://opentdb.com/api.php?amount=" + numOfQuestions 
						+ "&category=" + categoryNumber;
			}
			
			// Retrieve JSON string from API and map it to a "data transfer object"
			// This APIJson includes the response code
			APIJson objJson = readJsonWithObjectMapper(urlString);
			// Get the JSON associated with only the array of questions (without the response code)
			QuestionJson[] question = objJson.getResults();
			
			// Convert each question to a QuestionBean and add to questionList			
			for(QuestionJson q : question ) {
				QuestionBean qb = createQuestionBean(q);
				questionList.add(qb);
			}		
			//System.out.println(questionList);		
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		return questionList;
		
	}

	// Read JSON from Trivia API and convert it into a "data transfer" object
	public static APIJson readJsonWithObjectMapper(String urlString) throws IOException {
		

		// Create a URL object from the String
		URL url = new URL(urlString);
		ObjectMapper mapper = new ObjectMapper();
		APIJson obj = mapper.readValue(url, APIJson.class);
//		APIJson obj = mapper.readValue(hardcode, APIJson.class);
		return obj;
	}

	// Convert category string name to its corresponding category number according to the API
	public static int convertCategoryToInt(String category) {
		
		switch(category) {
		case "geography": return 22;
		case "history": return 23;
		case "sports": return 21;
		case "politics": return 24;
		case "art": return 25;
		case "math": return 19;
		case "computer": return 18;
		case "movies": return 11;
		case "all": return 0;	
		default: 
			System.out.println("wrong category");	// Should throw an exception here
			return 1;
		}
	}

}
