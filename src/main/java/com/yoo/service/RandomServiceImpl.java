package com.yoo.service;

import org.springframework.stereotype.Service;

import com.yoo.dto.RandomDTO;

@Service
public class RandomServiceImpl implements RandomService {

	@Override
	public RandomDTO getRandomNumber() {
		RandomDTO dto = new RandomDTO();
		
		dto.setCurrentTime();
		dto.setRandomNumber((int)(Math.random() * 100000) + 1);
		
		return dto;
	}

}
