package com.yoo.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class RandomServiceImpl implements RandomService {
	
	public int getRandomNumber() {
		
		return (int)(Math.random() * 100000) + 1;
		
	}

	@Override
	public Timestamp getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = null;
		today = formatter.format(cal.getTime());
		Timestamp timestamp = Timestamp.valueOf(today);
		
		return timestamp;
	}

}
