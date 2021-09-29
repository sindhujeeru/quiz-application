package com.quiz.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quiz.model.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, Long> {
	public List<Question> findByDifficulty(String difficulty);
	
	public Question findById(String id);
	
	public Question findQuestionByDifficulty(String difficulty);
}
