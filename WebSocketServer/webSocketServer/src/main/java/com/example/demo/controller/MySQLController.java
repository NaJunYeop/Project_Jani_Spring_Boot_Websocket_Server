package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.model.DBRepository;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.UserInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MySQLController {
	private static Logger logger = LoggerFactory.getLogger(MySQLController.class);
	
	@Autowired
	private DBRepository dbRepository;
	
	private ObjectMapper mapper = new ObjectMapper();
	private RegisterModel registerModel;
	
	private Optional<UserInformation> queryRegister;
	private List<UserInformation> queryList;
	
	@MessageMapping("/db-register")
	@SendTo("/topic/greetings") // @SendTo안의 Subscriber에게 전송하기 위해 MessageBroker로 전송한다.
	public String userRegistration(String json) {
		try {
			registerModel = mapper.readValue(json, RegisterModel.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		queryRegister = dbRepository.findByUserNameAndPhoneNumber(registerModel.getUserName(), registerModel.getPhoneNumber());
		if (!queryRegister.isPresent()) {
			UserInformation userInfo = new UserInformation.Builder(registerModel.getUserName(), registerModel.getPhoneNumber())
					.setEmail(registerModel.getEmail())
					.build();
			dbRepository.save(userInfo);
			logger.info(registerModel.toString());
			return "등록이 완료되었습니다." + registerModel.toString();
		}
		else {
			logger.info("Account Already Exists....\n");
			return "이미 존재하는 계정입니다.";
		}		
	}
}
