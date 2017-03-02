package ozden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ozden.entities.WebMessage;
import ozden.repos.WebMessageRepository;

@Service
public class MessageService {
	@Autowired
	private WebMessageRepository webMessageRepository;
	
	public void saveWebMessage(WebMessage message){
		if (message != null){
			webMessageRepository.save(message);
		}
	}
	
	public WebMessage getLatestMessageBySender(String sender, String receiver){
		List<WebMessage> lst = webMessageRepository.findBySenderAndReceiverOrderByCreateDateDesc(sender, receiver);
		if (lst.isEmpty()){
			return new WebMessage();
		}
		else {
			return lst.get(0);
		}
	}
}
