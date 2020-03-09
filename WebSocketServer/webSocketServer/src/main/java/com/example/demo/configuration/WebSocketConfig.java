package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
    	// Destination Prefix�� "/app"�̸� @MessageMapping���� ó�� �� �� MessageBroker�� ���� 
    	config.setApplicationDestinationPrefixes("/app");
    	// MessageBroker�� ����
        config.enableSimpleBroker("/topic", "/queue", "/req");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // HandShake�� EndPoint�� �����Ѵ�.
    	registry.addEndpoint("/janiwss").withSockJS();
    }
}