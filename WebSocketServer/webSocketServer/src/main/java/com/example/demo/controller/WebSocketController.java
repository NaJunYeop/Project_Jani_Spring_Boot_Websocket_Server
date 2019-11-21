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
public class WebSocketController {
	private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);
	private List<UserInformation> queryList;
	//private UserInformation queryRegister;
	private Optional<UserInformation> queryRegister;
	
	@Autowired
	private DBRepository dbRepository;
	private ObjectMapper mapper = new ObjectMapper();
	private RegisterModel registerModel;

	@MessageMapping("/end")
	@SendTo("/topic/greetings") // @SendTo안의 Subscriber에게 전송하기 위해 MessageBroker로 전송한다.
	public String sendMessage(String Message) {
		return Message;
	}
	
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
