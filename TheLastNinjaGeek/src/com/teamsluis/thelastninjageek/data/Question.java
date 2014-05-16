package com.teamsluis.thelastninjageek.data;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private String category;
	private int correctAnswerId;
	private String value;
	private List<Answer> answers;

	public Question(String category, int correctAnswerId, String value,
			List<Answer> answers) {
		this.setCategory(category);
		this.setCorrectAnswerId(correctAnswerId);
		this.setValue(value);
		this.setAnswers(answers);
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

	public void setAnswers(List<Answer> answers) {
		if (this.answers == null) {
			answers = new ArrayList<Answer>();
		}
		
		for (Answer answer : answers) {
			this.answers.add(answer);
		}
	}
}