package com.journalapp.DTO;

public class SentimentResult {
private String label;
private double score;
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
public double getScore() {
	return score;
}
public void setScore(double score) {
	this.score = score;
}
public SentimentResult(String label, double score) {
	super();
	this.label = label;
	this.score = score;
}
public SentimentResult() {
	super();
	// TODO Auto-generated constructor stub
}

}
