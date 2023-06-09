package com.juan.estevez.app.controllers;

import java.util.Date;
import java.util.Random;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import com.juan.estevez.app.entities.Message;

/**
 * Controlador de tipo rest para exponer los datos de la mensajería al frontend
 * 
 * @author Juan Carlos Estévez Vargas
 */
@RestController
public class ChatController {

	private String[] colors = { "red", "green", "blue", "cyan", "yellow", "purple", "pink", "orange" };

	/**
	 * Con el mensaje recibido setea la fecha actual del sistema, setea un color al
	 * usuario nuevo y el texto de usuario conectado.
	 * 
	 * @param message
	 * @return message transformado
	 */
	@MessageMapping("/message")
	@SendTo("/chat/messages") // Se le notifica a todos los usuarios registrados el nuevo mensaje
	public Message receiveMessage(Message message) {
		message.setFecha(new Date().getTime());
		if (message.getType().equals("NEW_USER")) {
			message.setColor(colors[new Random().nextInt(colors.length)]);
			message.setText(" - New User connected");
		}
		return message;
	}

	/**
	 * Setea el texto 'is typing ...' a cada usuario que se encuentre escribiendo un
	 * nuevo mensaje
	 * 
	 * @param username
	 * @return texto 'is typing ...'
	 */
	@MessageMapping("/typing")
	@SendTo("/chat/typing")
	public String cheackIfTheUserIsTyping(String username) {
		return username.concat(" is typing ...");
	}

}
