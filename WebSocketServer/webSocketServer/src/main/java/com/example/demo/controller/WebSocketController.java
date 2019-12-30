package com.example.demo.controller;

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

import com.example.demo.model.ChatRoomModel;
import com.example.demo.model.DBRepository;
import com.example.demo.model.MessageModel;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.RequestModel;
import com.example.demo.model.UserInformationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WebSocketController {
	private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/queue/{receiverName}")// ws://IP:PORT/app/end
	//@SendTo("{queueChatRoomName}") // @SendTo안의 Subscriber에게 전송하기 위해 MessageBroker로 전송한다.
	public void sendQueueMessage(@DestinationVariable("receiverName") String receiverName, @RequestBody MessageModel messageModel) {
		Map<String, String> ret = new HashMap<>();
		
		/*
		 * ret.put("senderSideDate", messageModel.getSenderSideDate());
		 * ret.put("senderName", messageModel.getSenderName()); ret.put("chatRoomName",
		 * messageModel.getChatRoomName()); ret.put("contents",
		 * messageModel.getContents());
		 */
		logger.info("To : " + receiverName + " : " + messageModel.getContents());
		
		template.convertAndSend("/queue/"+ receiverName, messageModel);
		//return ret;
	}
	/*
	@MessageMapping("{topicChatRoomName}")// ws://IP:PORT/app/end
	//@SendTo("{topicChatRoomName}") // @SendTo안의 Subscriber에게 전송하기 위해 MessageBroker로 전송한다.
	public void sendTopicMessage(@DestinationVariable String topicChatRoomName, @RequestBody MessageModel messageModel) {
		Map<String, String> ret = new HashMap<>();
		
		ret.put("senderSideDate", messageModel.getSenderSideDate());
		ret.put("senderName", messageModel.getSenderName());
		ret.put("chatRoomName", messageModel.getChatRoomName());
		ret.put("contents", messageModel.getContents());
		
		logger.info(messageModel.getContents());
		
		template.convertAndSend(topicChatRoomName, ret);
		//return ret;
	}
	*/
	
	@MessageMapping("/req/{receiverName}")
	public void sendRequestMessage(@DestinationVariable("receiverName") String receiverName , @RequestBody RequestModel requestModel) {
		Map<String, String> ret = new HashMap<>();
		
		logger.info("request " + requestModel.getSenderName() + " to " + receiverName);
	
		template.convertAndSend("/req/" + receiverName, requestModel);
	}
	
	
}
