package com.quiz.service;


import java.util.List;

import com.quiz.model.Question;

public interface QuestionService {
	
	public List<Question> createQuestions(List<Question> questions);
	
	public List<Question> getQuestions();
	
	public List<Question> getQuestionsByDifficulty(String difficulty);
	
	public Question getQuestionById(String id);
	
	public Question updateQuestion(Question question);
	
	public Question getQuestionByDifficulty(String difficulty);

}
