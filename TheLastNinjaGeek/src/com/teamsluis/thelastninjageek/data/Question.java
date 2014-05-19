package com.teamsluis.thelastninjageek.data;

import java.util.ArrayList;
import java.util.List;

import com.teamsluis.thelastninjageek.Randomizer;

public class Question {
	public static final int TOTAL_ANSWERS = 4;
	private String category;
	private int correctAnswerId;
	private String value;
	private List<Answer> answers;
	private boolean isAsked;

	public Question(String category, int correctAnswerId, String value,
			List<Answer> answers) {
		this.setCategory(category);
		this.setCorrectAnswerId(correctAnswerId);
		this.setValue(value);
		this.setAnswers(answers);
		this.isAsked = false;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCorrectAnswerId() {
		return this.correctAnswerId;
	}

	public void setCorrectAnswerId(int correctAnswerId) {
		this.correctAnswerId = correctAnswerId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	/*To do fix*/
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public Answer getCorrectAnswer() {
		Answer correctAnswer = null;
		
		for (Answer answer : this.answers) {
			if (answer.getId() == this.correctAnswerId) {
				return answer;
			}
		}
		
		return correctAnswer;
	}
	
	public boolean isCorrect(Answer answer) {
		return answer.getId() == correctAnswerId;
	}

	public void markAsAsked() {
		this.isAsked = true;
	}
	
	public boolean isAsked() {
		return this.isAsked;
	}

	public void shuffleAnswers() {
		Randomizer.shuffleList(this.answers);	
	}
}
