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
import com.example.demo.model.MessageModel;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.UserInformationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WebSocketController {
	private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	private MessageModel messageModel;

	@MessageMapping("/end")
	@SendTo("/topic/greetings") // @SendTo안의 Subscriber에게 전송하기 위해 MessageBroker로 전송한다.
	public String sendMessage(String json) {
		try {
			messageModel = mapper.readValue(json, MessageModel.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
