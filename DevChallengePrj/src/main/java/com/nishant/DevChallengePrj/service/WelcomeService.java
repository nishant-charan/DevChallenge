package com.nishant.DevChallengePrj.service;

import org.springframework.stereotype.Service;

@Service("welcomeService")
public class WelcomeService {
	
	public String retrieveWelcomeMessage() {
		return "This is great! I can see something on screen!!";
	}

}

