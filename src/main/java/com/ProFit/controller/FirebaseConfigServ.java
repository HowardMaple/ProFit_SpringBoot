package com.ProFit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProFit.config.FirebaseAPI;
import com.ProFit.model.bean.FirebaseConfigBean.FirebaseConfig;

@RestController
public class FirebaseConfigServ {
	
	@Autowired
	private FirebaseConfig firebaseConfig;
	
	@Autowired
	private FirebaseAPI firebaseAPI;
		 
    @GetMapping("/FirebaseConfigServ")
	public FirebaseConfig getFirebaseConfig() {
		
    	firebaseConfig.setApiKey(firebaseAPI.getApiKey());
    	firebaseConfig.setAuthDomain(firebaseAPI.getAuthDomain());
    	firebaseConfig.setProjectId(firebaseAPI.getProjectId());
    	firebaseConfig.setStorageBucket(firebaseAPI.getStorageBucket());
    	firebaseConfig.setMessagingSenderId(firebaseAPI.getMessagingSenderId());
    	firebaseConfig.setAppId(firebaseAPI.getAppId());
    	firebaseConfig.setMeasurementId(firebaseAPI.getMeasurementId());

		return firebaseConfig;
	}
}