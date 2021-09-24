package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Question;
import com.quiz.repository.QuestionRepository;

@Service
public class QuestionServiceImpl  implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepo;
	

	@Override
	public List<Question> createQuestions(List<Question> questions) {
		
		return questionRepo.saveAll(questions);
	}

	@Override
	public List<Question> getQuestions() {
		
		return questionRepo.findAll();
	}

	@Override
	public List<Question> getQuestionsByDifficulty(String difficulty) {
		
		return questionRepo.findByDifficulty(difficulty);
	}

}
