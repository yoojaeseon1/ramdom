package com.yoo.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class RandomDTO {
	
	private String currentTime; 
	private int randomNumber;
	
	public String getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime() {
		this.currentTime = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
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
