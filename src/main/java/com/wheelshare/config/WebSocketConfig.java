package com.wheelshare.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.messaging.config.annotation.StompEndpointRegistry;
import org.springframework.messaging.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

//@Configuration
//@org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker

public class WebSocketConfig implements org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer {
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

}
