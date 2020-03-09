package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import com.example.demo.models.ChatRoomModel;
import com.example.demo.models.MessageModel;
import com.example.demo.models.RegisterModel;
import com.example.demo.models.RequestModel;
import com.example.demo.models.UserInformationEntity;
import com.example.demo.repositories.UserInformationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WebSocketController {
	private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	
	@Autowired private SimpMessagingTemplate template;
	
	@MessageMapping("/queue/{receiverName}")// ws://IP:PORT/app/end public void
	public void sendQueueMessage(@DestinationVariable("receiverName") String receiverName, @RequestBody MessageModel messageModel) { 
		logger.info("To : " + receiverName + " : " + messageModel.getMsgContent());
		template.convertAndSend("/queue/"+ receiverName, messageModel); 
	}

	@MessageMapping("/req/{receiverName}") 
	public void sendRequestMessage(@DestinationVariable("receiverName") String receiverName, @RequestBody RequestModel requestModel) { 
		logger.info("request " + requestModel.getReqSenderName() + " to " + receiverName);
		template.convertAndSend("/req/" + receiverName, requestModel); 
	}
	
	@MessageMapping("/topic/{topicNumber}")// ws://IP:PORT/app/end public void
	public void sendTopicMessage(@DestinationVariable("topicNumber") String topicNumber, @RequestBody MessageModel messageModel) { 
		logger.info("To : " + topicNumber + " : " + messageModel.getMsgContent());
		template.convertAndSend("/topic/"+ topicNumber, messageModel); 
	}
}
