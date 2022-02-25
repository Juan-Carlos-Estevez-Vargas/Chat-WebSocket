package com.juan.estevez.app.controllers;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.juan.estevez.app.entities.Message;

@RestController
public class ChatController {
	
	@MessageMapping("/message")
	@SendTo("/chat/messages") // Se le notifica a todos los usuarios registrados el nuevo mensaje
	public Message receiveMessage(Message message) {
		message.setFecha(new Date().getTime());
		if (message.getType().equals("NEW_USER")) {
			message.setText(" - New User connected");
		}
		return message;
	}
	
}
