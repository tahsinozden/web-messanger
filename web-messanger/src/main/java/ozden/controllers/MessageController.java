package ozden.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import ozden.entities.WebMessage;
import ozden.services.MessageService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	
	@MessageMapping("/message")
	@SendTo("/topic/messages")
//	@SendToUser("/topic/messages")
	public WebMessage message(WebMessage message){
		if (message != null){
			message.setCreateDate(new Date());
			messageService.saveWebMessage(message);
		}
		message = messageService.getLatestMessageBySender(message.getSender(), message.getReceiver());
		return message;
	}
}
