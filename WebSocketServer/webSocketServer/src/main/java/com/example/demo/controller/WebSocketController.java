package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

	@MessageMapping("/end")
	@SendTo("/topic/greetings") // @SendTo안의 Subscriber에게 전송하기 위해 MessageBroker로 전송한다.
	public String sendMessage(String Message) {
		return Message;
	}
	
}
