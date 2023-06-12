package com.example.java.jxl.test.bean;

public class Student {
	String id;
	String name;
	Integer chinese;
	Integer math;
	Integer english;
	Integer politics;
	Integer history;
	Integer geography;
	
	
	public Student(String id, String name, Integer chinese, Integer math, Integer english, Integer politics,
			Integer history, Integer geography) {
		super();
		this.id = id;
		this.name = name;
		this.chinese = chinese;
		this.math = math;
		this.english = english;
		this.politics = politics;
		this.history = history;
		this.geography = geography;
	}
	
	public Student() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getChinese() {
		return chinese;
	}
	public void setChinese(Integer chinese) {
		this.chinese = chinese;
	}
	public Integer getMath() {
		return math;
	}
	public void setMath(Integer math) {
		this.math = math;
	}
	public Integer getEnglish() {
		return english;
	}
	public void setEnglish(Integer english) {
		this.english = english;
	}
	public Integer getPolitics() {
		return politics;
	}
	public void setPolitics(Integer politics) {
		this.politics = politics;
	}
	public Integer getHistory() {
		return history;
	}
	public void setHistory(Integer history) {
		this.history = history;
	}
	public Integer getGeography() {
		return geography;
	}
	public void setGeography(Integer geography) {
		this.geography = geography;
	}
}
