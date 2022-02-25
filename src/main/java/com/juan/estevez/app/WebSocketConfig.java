package com.juan.estevez.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Clase de configuración para los broker de mensajería de tipo Socket
 * 
 * @author Juan Carlos Estévez Vargas
 */
@Configuration
@EnableWebSocketMessageBroker // Habilita el servidor websocket de spring
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat-websocket") // Ruta del servidor en spring
			.setAllowedOrigins("http://localhost:4200")
			.withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/chat/"); // Prefijo para los nombres del evento
		registry.setApplicationDestinationPrefixes("/app"); // Prefijo destinación
	}
}
