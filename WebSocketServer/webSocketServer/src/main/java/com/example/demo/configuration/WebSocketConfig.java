package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	// Destination Prefix가 "/app"이면 @MessageMapping에서 처리 된 후 MessageBroker로 전송 
    	config.setApplicationDestinationPrefixes("/app");
    	// MessageBroker로 전송
        config.enableSimpleBroker("/topic", "/queue", "/req");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // HandShake할 EndPoint를 지정한다.
    	registry.addEndpoint("/janiwss").withSockJS();
    }
}