package com.juan.estevez.app.controllers;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.estevez.app.entities.Message;

@RestController
public class ChatController {
	
	@MessageMapping
	
	public Message receiveMessage(Message message) {
		message.setFecha(new Date().getTime());
		message.setText("Recibido por el broker : " + message.getText());
		return message;
	}
}
