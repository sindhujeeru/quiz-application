package com.quiz.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.Question;
import com.quiz.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuizController {
	
	@Autowired
	private QuestionService questionService;
	
	
	@GetMapping()
	public ResponseEntity<?> getAllQuestions(){
		
		List<Question> questions =  questionService.getQuestions();
		
		return ResponseEntity.ok(questions);
		
	}
	
	@PostMapping()
	public ResponseEntity<?> createQuestions(@RequestBody List<Question> questions){
		
		List<Question> questionDb = questionService.createQuestions(questions);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(questionDb);
	}
	
	@GetMapping("/displayQuestion")
	public ResponseEntity<?> displayQuestion(){
		
		int attempted = 0;
		List<Question> questionsEasy = questionService.getQuestionsByDifficulty("EASY");
		List<Question> questionsMedium = questionService.getQuestionsByDifficulty("MEDIUM");
		List<Question> questionsHard = questionService.getQuestionsByDifficulty("HARD");
		
		Random rand = new Random();
	    Question randomEasy = questionsEasy.get(rand.nextInt(questionsEasy.size()));
	    Question randomMedium = questionsEasy.get(rand.nextInt(questionsMedium.size()));
	    Question randomHard = questionsEasy.get(rand.nextInt(questionsHard.size()));
	    
	    Question quest = randomEasy;
	    if(attempted == 0) {
	    	return ResponseEntity.ok(quest);
	    }else {
	    	
	    	for(int i=0; i<10; i++) {
		    	if(quest.getResponded() && quest.getDifficulty().equalsIgnoreCase("easy")) {
		    		quest = randomMedium; 
		    	}else if (quest.getResponded() && quest.getDifficulty().equalsIgnoreCase("medium")) {
					quest = randomHard;
				}else {
					quest = randomEasy;
				}
		    	
		    	return ResponseEntity.ok(quest);
		    }
	    	
	    	return ResponseEntity.ok("Test finished");
	    }
	    
	    
	}
	
	@PostMapping("/displayQuestion")
	public ResponseEntity<?> getQuestionsByDifficulty(@RequestBody Question question){
		
		Question updateQuestion = new Question();
		
		updateQuestion.setId(question.getId());

		updateQuestion.setResponded(question.getResponded());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updateQuestion);
	}
	
	

}
