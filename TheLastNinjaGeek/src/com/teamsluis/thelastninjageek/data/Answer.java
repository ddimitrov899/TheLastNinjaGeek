package com.teamsluis.thelastninjageek.data;

public class Answer {
	private int id;
	private String value;

	public Answer(int id, String value) {
		this.setId(id);
		this.setValue(value);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}