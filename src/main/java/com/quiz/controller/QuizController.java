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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/*@GetMapping(value="/attempt",params = {"id","answer"})
	public ResponseEntity<?> attemptQuestion(@RequestParam(value = "id") String id, @RequestParam(value="answer") String answer){
		
		List<Question> questionsEasy = questionService.getQuestionsByDifficulty("EASY");
		List<Question> questionsMedium = questionService.getQuestionsByDifficulty("MEDIUM");
		List<Question> questionsHard = questionService.getQuestionsByDifficulty("HARD");
		
		Random rand = new Random();
	    Question randomEasy = questionsEasy.get(rand.nextInt(questionsEasy.size()));
	    Question randomMedium = questionsMedium.get(rand.nextInt(questionsMedium.size()));
	    Question randomHard = questionsHard.get(rand.nextInt(questionsHard.size()));
		
		Question question = questionService.getQuestionById(id);
		
		String correctAnswer = question.getCorrect();
		
		
		if(correctAnswer.equalsIgnoreCase(answer) && question.getDifficulty().equals("EASY")) {
			question.setResponded(true);
			questionService.updateQuestion(question);
			question = randomMedium;
			
			System.out.println("first if condition"+randomMedium);
			return ResponseEntity.ok(question);
		}else if (correctAnswer.equalsIgnoreCase(answer) && question.getDifficulty().equals("MEDIUM")) {
			question.setResponded(true);
			questionService.updateQuestion(question);
			question = randomHard;
			System.out.println("second if condition"+randomHard);
			return ResponseEntity.ok(question);
		}else if (correctAnswer.equalsIgnoreCase(answer) && question.getDifficulty().equals("HARD")) {
			question.setResponded(true);
			questionService.updateQuestion(question);
			question = randomHard;
			
			System.out.println("third else condidtion"+randomHard);
			return ResponseEntity.ok(question);
		}else {
			question.setResponded(false);
			questionService.updateQuestion(question);
			System.out.println("final else condidtion"+randomEasy);
			return ResponseEntity.ok(question);
		}
		
		//dhdsf
		
		//awrtywy;
	}*/
	
	
	@GetMapping(value="/attempt",params = {"id","answer"})
	public ResponseEntity<?> attemptQuestion(@RequestParam(value = "id") String id, @RequestParam(value="answer") String answer){
		
		Question question = questionService.getQuestionById(id);
		String level = question.getDifficulty();
		
		int size = level.length();
		
		if(size<=3) {
			if(!answer.equals(question.getCorrect())) {
				level = level+".2";
				question = questionService.getQuestionByDifficulty(level);
			}else {
				level = level+".1";
				question = questionService.getQuestionByDifficulty(level);
			}
		}else {
			String[] levelArray = level.split(".");
			level = Integer.toString((Integer.parseInt(levelArray[0])+1));
			question = questionService.getQuestionByDifficulty(level);
		}
		
		return ResponseEntity.ok(question);
	}


}
