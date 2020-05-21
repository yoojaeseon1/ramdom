package com.yoo.contoller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoo.dto.RandomDTO;
import com.yoo.service.RandomService;

/**
 * Handles requests for the application home page.
 */
//@Component
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	
// 구현체가 여러 개 있는 경우
	
//	@Autowired
//	@Qualifier("random1")
//	private RandomService randomService; 
	
	
	
	private final RandomService randomService;
	
	@Autowired
	public HomeController(RandomService randomService) {
		
		this.randomService = randomService;
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/random")
	public RandomDTO showRandomNumber() {
		
		return randomService.getRandomNumber();
		
	}

	
	// @ReseponseBody를 제거했을 때 test (404에러 발생)
	
	@RequestMapping(value = "/random2")
	public RandomDTO showRandomNumber2() {
		
		return randomService.getRandomNumber();
		
	}
	
	
	// String을 response body에 올려 보낼 때
	
	@ResponseBody
	@RequestMapping(value = "/string")
	public String showString() {
		
		return "hahahoho";
		
	}
	
	
}
