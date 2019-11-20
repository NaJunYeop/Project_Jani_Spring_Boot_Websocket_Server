package com.example.demo.controller;

import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.model.DBRepository;
//import com.example.demo.model.DBRepository;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.UserInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WebSocketController {
	
	private List<UserInformation> queryList;
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
		queryList = dbRepository.findByNameAndPhoneNumber(registerModel.getName(), registerModel.getPhoneNumber()); 
		if (queryList.size() == 0) {
			UserInformation userInfo = new UserInformation.Builder("나준엽", "010-2604-7521")
					.setEmail("nnjy1992@naver.com")
					.build();
			dbRepository.save(userInfo);
			return "등록이 완료되었습니다." +
			"\nName : " + registerModel.getName() +
			"\nPhone Number : " + registerModel.getPhoneNumber() +
			"\nemail : " + registerModel.getEmail();
		}
		else {
			return "이미 계정이 존재합니다.";
		}
	}
}
