package com.quiz.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "questions")
@ToString
public class Question {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "response1")
	private String response1;
	
	@Column(name="response2")
	private String response2;
	
	@Column(name="response3")
	private String response3;
	
	@Column(name="correct")
	private String correct;
	
	@Column(name="difficulty")
	private String difficulty;
	
	@Column(name = "responded")
	private Boolean responded;

}
