package com.project;

public class Score {
	private String palyer;
	private int score;
	
	public Score() {
		super();
	}
	public Score(String palyer, int score) {
		super();
		this.palyer = palyer;
		this.score = score;
	}
	public String getPalyer() {
		return palyer;
	}
	public void setPalyer(String palyer) {
		this.palyer = palyer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void addScore(){
		this.score=this.score+100;
	}
	
}
