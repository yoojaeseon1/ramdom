package com.yoo.dto;

import java.sql.Timestamp;

public class RandomDTO {
	
	private Timestamp currentTime; 
	private int randomNumber;
	
	public RandomDTO() {
		
	}
	
	public Timestamp getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Timestamp currentTime) {
		this.currentTime = currentTime;
	}



	public int getRandomNumber() {
		return randomNumber;
	}



	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}



	@Override
	public String toString() {
		return "RandomDTO [date=" + currentTime + ", randomNumber=" + randomNumber + "]";
	}
	
	

}
